package qmw

class AdicionaisController {

	def beforeInterceptor = [action:this.&auth]
	def scaffold=true

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def adicionaisInstance = Adicionais.where {
			estab == session.estab
		 }
		[adicionaisInstanceList: adicionaisInstance.list(params), adicionaisInstanceTotal: adicionaisInstance.count()]
	}
	
	def show(Long id) {
		def adicionaisInstance = Adicionais.get(id)
		if (!adicionaisInstance || !adicionaisInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adicionais.label', default: 'adicionais'), id])
			redirect(action: "list")
			return
		}
		[adicionaisInstance: adicionaisInstance]
	}

	def edit(Long id) {
		def adicionaisInstance = Adicionais.get(id)
		if (!adicionaisInstance || !adicionaisInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'adicionais.label', default: 'adicionais'), id])
			redirect(action: "list")
			return
		}
		[adicionaisInstance: adicionaisInstance]
	}
	
	def save() {
		def adicionaisInstance = new Adicionais(params)
		adicionaisInstance.setEstab(session.estab)
		if (!adicionaisInstance.save(flush: true)) {
			render(view: "create", model: [adicionaisInstance: adicionaisInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'adicionais.label', default: 'Descricao'), adicionaisInstance.id])
		redirect(action: "show", id: adicionaisInstance.id)
	}

	def auth() {
		if(!session.estab) {
            redirect(controller:"Estabelecimento")
			return false
		}
	}
}
