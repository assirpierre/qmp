package qmw

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONArray
import org.hibernate.SessionFactory

class InterfaceController {

    def numeroService
    def autenticaService
    def beforeInterceptor = {autenticaService.autenticaChave(this)}
    def SessionFactory sessionFactory

    def cadastros() {
        def estabId = numeroService.getInt(params['estab']);
        Dispositivo d = Dispositivo.get(params['dispositivo'])
        if(!d){
            d = new Dispositivo();
            d.save(flush:true);
        }
        if(!params['log'].equals("")){
            new LogDispositivo(dispositivo: d, dados: params['log']).save(flush: true)
            println "log: " + params['log']
        }
        render Dispositivo.where {id == d.id}.list() +
                Estabelecimento.where{id == estabId}.list() +
                Adicionais.where{estab.id == estabId}.list(sort:'grupoAdicionais') +
                Mesa.where{estab.id == estabId}.list() +
                MenuPrincipal.where{estab.id == estabId}.list() +
                Menu.where{estab.id == estabId}.list(sort:'menuPrincipal') as JSON
    }

    def login() {
        def u = Usuario.findByEmailOrCodigo(params['usuario'], params['usuario'])
        if(u.senha.equals(params['senha']))
            render u as JSON
        else
            render "##ACESSONEGADO##"
    }

    def abremesa() {
        def estabId = numeroService.getInt(params['estab']);
        def mesaId = numeroService.getInt(params['mesa']);
        def nome = params['nome']
        Mesa m = Mesa.get(mesaId)
        if(m.situacao == 'O')
            render Mesa.where{estab.id == estabId}.list() as JSON
        else{
            PedidoCapa p = new PedidoCapa(estab:Estabelecimento.get(estabId),mesa:m,nome:nome, dataInicio: new Date())
            p.save(flush: true)
            m.pedidoCapa = p
            m.nome = nome
            m.dataultsituacao = new Date()
            m.situacao = 'O'
            if(m.save(flush: true))
                render "##true##"
            else
                render "##ERRO##"
        }
    }

    def fechamesa() {
        def estabId = numeroService.getInt(params['estab']);
        def mesaId = numeroService.getInt(params['mesa']);
        Mesa m = Mesa.get(mesaId)
        if(m.situacao == 'D')
            render "##situacaodisponivel##"
        else{
            def p = Pedido.where{pedidoCapa.id == m.pedidoCapa.id}
            if(p.where{situacao == 'C'}.count()>0)
                render "##pedidopendente##"
            else{
                PedidoCapa pc = PedidoCapa.get(m.pedidoCapa.id)
                def subTotal = p.get{
                    projections {
                        sum('total')
                    }
                }
                if(subTotal==null)
                    subTotal = 0
                def servico = subTotal*pc.estab.taxaServico/100
                pc.subTotal = subTotal
                pc.setServico(servico)
                pc.setTotal(servico + subTotal)
                pc.dataFim = new Date()
                pc.save(flush: true)
                m.pedidoCapa = null
                m.nome = null
                m.dataultsituacao = new Date()
                m.situacao = 'D'
                if(m.save(flush: true))
                    render Mesa.where{estab.id == estabId}.list() as JSON
                else
                    render "##ERRO##"
            }
        }
    }

    def listaultimopedido(){
        def mesaId = numeroService.getInt(params['mesa']);
        Mesa m = Mesa.get(mesaId)
        def ultSequencia = getUltSequencia(m);
        render Pedido.where{sequencia == ultSequencia
            and
            pedidoCapa.id == m.pedidoCapa.id}.list() +
                PedidoAdicionais.where{pedido.sequencia == ultSequencia
                    and
                    pedido.pedidoCapa.id == m.pedidoCapa.id}.list() +
                PedidoItem.where{pedido.sequencia == ultSequencia
                    and
                    pedido.pedidoCapa.id == m.pedidoCapa.id}.list() as JSON
    }

    def listatodospedidos(){
        def mesaId = numeroService.getInt(params['mesa']);
        Mesa m = Mesa.get(mesaId)
        render Pedido.where{pedidoCapa.id == m.pedidoCapa.id}.list() +
                PedidoAdicionais.where{pedido.pedidoCapa.id == m.pedidoCapa.id}.list() +
                PedidoItem.where{pedido.pedidoCapa.id == m.pedidoCapa.id}.list() as JSON
    }


    def gravapedido(){
        assert sessionFactory != null
        def json = (request.JSON as JSON).toString()
        def list = new JsonSlurper().parseText(json)
        def m, sequencia = null, retorno = null, estabId = null, dispositivo = null, transacao = null, transacaoOK = false
        if(!LogTransacao.findByDispositivoAndTransacao(params['dispositivo'], params['transacao'])){
            list.each {
                if(sequencia == null){
                    m = Mesa.get(it.getAt("mesa.id"))
                    sequencia = getUltSequencia(m) + 1
                    estabId = it.getAt("estab.id")
                }
                it.sequencia = sequencia
                it.dataPedido = Date.parse("yyyy-MM-dd'T'HH:mm:ss'Z'", it.getAt("dataPedido"))
                it.pedidoCapa = m.pedidoCapa
                Pedido p = new Pedido(it);
                if(!p.save(flush:true))
                    retorno = "##ERRO##"
                else{
                    JSONArray add = new JSONArray(it.getAt("add"));
                    add.each {
//                        p.addToPedidoAdicionais(new JSONArray(it.getAt("add"))
                        it.pedido = p;
                        if(!new PedidoAdicionais(it).save(flush: true))
                            retorno = "##ERRO##"
                    }
                    JSONArray itens = new JSONArray(it.getAt("itens"));
                    itens.each {
                        it.pedido = p;
                        if(!new PedidoItem(it).save(flush: true))
                            retorno = "##ERRO##"
                    }

                }
            }
            if(m)
                calculaTotalPedido(m.pedidoCapa)

        }
        if(retorno == null){
            new LogTransacao(dispositivo: params['dispositivo'], transacao: params['transacao']).save(flush: true)
            render Mesa.where{estab.id == estabId}.list() as JSON
        }else{
            render retorno
        }

    }

    def calculaTotalPedido (pc){
        def p = Pedido.where{pedidoCapa.id == pc.id}
        if(p){
            def subTotal = p.get{
                projections {
                    sum('total')
                }
            }
            if(subTotal==null)
                subTotal = 0
            def servico = subTotal*pc.estab.taxaServico/100
            pc.subTotal = subTotal
            pc.setServico(servico)
            pc.setTotal(servico + subTotal)
            pc.save(flush: true)
        }
    }

    def getUltSequencia(m){
        def p = Pedido.where{pedidoCapa.id == m.pedidoCapa.id}
        def ultSequencia = p.get{
            projections {
                max('sequencia')
            }
        }
        return ultSequencia?ultSequencia:0
    }
}
