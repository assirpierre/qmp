package qmw

class PedidoService {
	def numeroService

	void atender(params) {
		def pedido = Pedido.findById(params['id'])
        pedido.situacao = "A"
		pedido.setDataAtendimento(new Date())
		pedido.save(flush: true)
	}

	def excluir(params) {
		def pedido = Pedido.findById(params['id'])
		pedido.setSituacao("D")
		pedido.setTotal(0)
		pedido.save(flush: true)
        def tPedido = Pedido.where { pedidoCapa.id == pedido.pedidoCapaId}.get() {
            projections {
                sum('total')
            }
        }
        PedidoCapa uM = PedidoCapa.findById(pedido.pedidoCapaId)
        uM.subTotal = tPedido
        uM.total = tPedido + uM.servico
        uM.save(flush: true)
	}


	def getpedidoCapa(maior, params, session) {
		def vvmesa = numeroService.getInt(params['mesa'].toString())
		def u = PedidoCapa.createCriteria()
		def uMesa = u.get() {
			eq("estab", session.estab)
			mesa{ eq("numero", vvmesa)}
			projections {
				if(maior)
					max('id')
				else
					min('id')
			}
		}
		return uMesa
	}

	Pedido getFechamentoNaoImpresso(session) {
		def p = Pedido.createCriteria()
		def fechamentoNaoImpresso = p.get() {
			and{
				eq("estab", session.estab)
				pedidoCapa {
					and {
						eq("cupomImpresso", false)
						isNotNull("dataFim")
					}
					order( "dataFim", "asc")
				}
			}
			maxResults(1)
		}
		return fechamentoNaoImpresso
	}

	def getMesa(boolean maior, session) {
		def m = Mesa.createCriteria()
		def mMesa = m.get() {
			eq("estab", session.estab)
			projections {
				if(maior)
					max('numero')
				else
					min('numero')
			}
		}
		return mMesa
	}

	def getIdpedidoCapa_Pedido(params, session) {
		def acao = params['acao'].toString()
		def vvmesa = numeroService.getInt(params['mesa'].toString())
		def vvpedido = (long)numeroService.getInt(params['pedido'].toString())
		def uPedido = PedidoCapa.createCriteria().list() {
			and{
				eq("estab", session.estab)
				mesa{ eq("numero", vvmesa)}
				if(acao.equals("pedidoanterior")){
					if(vvpedido == getpedidoCapa (false, params, session))
						eq("id", vvpedido)
					else{
						lt("id", vvpedido)
						order("id", "desc")
					}
				}else if(acao.equals("pedidoproximo")){
					if(vvpedido == getpedidoCapa (true, params, session))
						eq("id", vvpedido)
					else{
						gt("id", vvpedido)
						order("id", "asc")
					}
				}else
					eq("id", vvpedido)
			}
			maxResults(1)
		}
		if(uPedido)
			return uPedido.first().id
		else
			return 0
	}

	def getIdpedidoCapa_Mesa(params, session) {
		def acao = params['acao'].toString()
		def uPedido = PedidoCapa.createCriteria().list() {
			eq("estab", session.estab)
			if (params['mesa'] == null){
				maxResults(1)
			}else{
				int vvmesa = numeroService.getInt(params['mesa'].toString())
				and{
					mesa{
						eq("estab", session.estab)
						if(acao.equals("mesaanterior")){
							if(vvmesa == getMesa (false, session))
								eq("numero", vvmesa)
							else{
								lt("numero", vvmesa)
								order("numero", "desc")
							}
						}else if(acao.equals("mesaproximo")){
							if(vvmesa == getMesa (true, session))
								eq("numero", vvmesa)
							else{
								gt("numero", vvmesa)
								order("numero", "asc")
							}
						}else
							eq("numero", vvmesa)
						maxResults(1)
					}
				}
			}
			order("id", "desc")
		}
		if(uPedido)
			return uPedido.first().id
		else
			return 0
	}


	def getUPedido(params, session){
		def uPedido
		if(params['pedido'] != null)
			uPedido = getIdpedidoCapa_Pedido(params, session)
		else
			uPedido = getIdpedidoCapa_Mesa(params, session)
		return uPedido
	}

	def getSeq(pedidoInstance, params){
		def seq = pedidoInstance.count()>0? pedidoInstance.list().last().sequencia:0
		if(params['sequencia'] != null && pedidoInstance.count() > 0){
			def seqP = numeroService.getInt(params['sequencia'].toString())
			if(params['acao'].equals("sequenciaanterior")){
				if(seqP > 1)
					seq = seqP-1;
				else if(seqP == 1)
					seq = seqP
			}else{
				if(seqP != seq)
					seq = seqP+1;

			}
		}
		return seq
	}
	
	def getJS(pedidoInstance, reimpressao){
		if(pedidoInstance.count() > 0){
			Pedido p = pedidoInstance.list().last();
			return "<script>jQuery('input')[1].value= '" + p.mesa  + "';" +
				   "jQuery('input')[4].value= '" + p.pedidoCapa.id  + "';" +
				   (reimpressao?
				   "jQuery('input')[7].value= '" + p.sequencia  + "';" +
				   "jQuery('input')[9].value= '" + p.pedidoCapa.dataFim  + "';":"") +
				   "</script>"
		}else
			return ""
	}
}
