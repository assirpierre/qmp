package qmw

import org.springframework.dao.DataIntegrityViolationException

class EstabelecimentoController {
    def estabelecimentoService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        if(session.estab)
            redirect(action: "show", id: session.estab.id)
        else
            redirect(uri:'/')
    }

    def create() {
        [estabelecimentoInstance: new Estabelecimento(params)]
    }

    def list(Integer max) {
        if(session.estab && session.estab.id == 1){
            params.max = Math.min(max ?: 10, 100)
            [estabelecimentoInstanceList: Estabelecimento.list(params), estabelecimentoInstanceTotal: Estabelecimento.count()]
        }else{
            redirect(action: "show", id: session.estab.id)
        }
    }

    def show(Long id) {
        def estabelecimentoInstance = Estabelecimento.get(id)
        if (!estabelecimentoInstance || (!estabelecimentoInstance.id.equals(session.estab.id) && session.estab.id != 1)) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estabelecimento.label', default: 'estabelecimento'), id])
            redirect(action: "list")
            return
        }
        [estabelecimentoInstance: estabelecimentoInstance]
    }

    def editParam(Long id) {
        edit (id)
    }

    def edit(Long id) {
        def estabelecimentoInstance = Estabelecimento.get(id)
        if (!estabelecimentoInstance || session.estab == null || (!estabelecimentoInstance.id.equals(session.estab.id) && session.estab.id != 1)) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estabelecimento.label', default: 'estabelecimento'), id])
            redirect(action: "list")
            return
        }else
            session.estab = estabelecimentoInstance
        [estabelecimentoInstance: estabelecimentoInstance]
    }

    def save() {
        def estabelecimentoInstance = new Estabelecimento(params)
        if(!session.estab)
            estabelecimentoService.criaEstab(params, session, estabelecimentoInstance)
        if (!estabelecimentoInstance.save(flush: true)) {
            render(view: "create", model: [estabelecimentoInstance: estabelecimentoInstance])
            return
        }else{
            session.estab = estabelecimentoInstance
            if (params['geraCadastro'])
                estabelecimentoService.criarModelo (estabelecimentoInstance)
            estabelecimentoService.enviaEmailNovoEstab(estabelecimentoInstance, message(code: 'contato.email.destino'))
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'estabelecimento.label', default: 'Estabelecimento'), estabelecimentoInstance.id])
        redirect(action: "show", id: estabelecimentoInstance.id)
    }

    def authenticate = {
        def estab = Estabelecimento.findByDocumentoAndSenha(params.userName, params.password)
        if(!estab)
            estab = Estabelecimento.findByEmailAndSenha(params.userName, params.password)
        if(estab){
            session.estab = estab
            session.back = (new java.util.Random().nextInt(4))
            session.setMaxInactiveInterval(1800);
            flash.message = "${message(code: 'estabelecimento.loginsucesso', default: 'Login sucesso')}"
            redirect(controller: 'pedido', action:'listplocal')
        }else{
            flash.message = "${message(code: 'estabelecimento.loginincorreto', default: 'Login incorreto')}"
            redirect(uri:'/')
        }
    }

    def logout = {
        flash.message = "${message(code: 'estabelecimento.logout', default: 'Logout efetuado')}"
        session.estab = null
        redirect(uri:'/')
    }

    def update(Long id, Long version) {
        def estabelecimentoInstance = Estabelecimento.get(id)
        if (!estabelecimentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estabelecimento.label', default: 'Estabelecimento'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (estabelecimentoInstance.version > version) {
                estabelecimentoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'estabelecimento.label', default: 'Estabelecimento')] as Object[],
                          "Another user has updated this Estabelecimento while you were editing")
                render(view: "edit", model: [estabelecimentoInstance: estabelecimentoInstance])
                return
            }
        }

        estabelecimentoInstance.properties = params

        if (!estabelecimentoInstance.save(flush: true)) {
            render(view: "edit", model: [estabelecimentoInstance: estabelecimentoInstance])
            return
        }
//        else{
//            if (params['geraCadastro'])
//                estabelecimentoService.criarModelo (estabelecimentoInstance)
//        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'estabelecimento.label', default: 'Estabelecimento'), estabelecimentoInstance.id])
        redirect(action: "show", id: estabelecimentoInstance.id)
    }

    def delete(Long id) {
        def estabelecimentoInstance = Estabelecimento.get(id)
        if (!estabelecimentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'estabelecimento.label', default: 'Estabelecimento'), id])
            redirect(action: "list")
            return
        }

        try {
            estabelecimentoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'estabelecimento.label', default: 'Estabelecimento'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'estabelecimento.label', default: 'Estabelecimento'), id])
            redirect(action: "show", id: id)
        }
    }
}
