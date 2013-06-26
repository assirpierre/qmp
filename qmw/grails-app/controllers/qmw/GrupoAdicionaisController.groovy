package qmw

class GrupoAdicionaisController {

	def beforeInterceptor = [action:this.&auth]
	def scaffold=true

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def grupoAdicionaisInstance = GrupoAdicionais.where {
			estab == session.estab
		}
		[grupoAdicionaisInstanceList: grupoAdicionaisInstance.list(params), grupoAdicionaisInstanceTotal: grupoAdicionaisInstance.count()]
	}

	def show(Long id) {
		def grupoAdicionaisInstance = GrupoAdicionais.get(id)
		if (!grupoAdicionaisInstance || !grupoAdicionaisInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoAdicionais.label', default: 'grupoAdicionais'), id])
			redirect(action: "list")
			return
		}
		[grupoAdicionaisInstance: grupoAdicionaisInstance]
	}

	def edit(Long id) {
		def grupoAdicionaisInstance = GrupoAdicionais.get(id)
		if (!grupoAdicionaisInstance || !grupoAdicionaisInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'grupoAdicionais.label', default: 'grupoAdicionais'), id])
			redirect(action: "list")
			return
		}
		[grupoAdicionaisInstance: grupoAdicionaisInstance]
	}
	
	def save() {
		def grupoadicionaisInstance = new GrupoAdicionais(params)
		grupoadicionaisInstance.setEstab(session.estab)
		if (!grupoadicionaisInstance.save(flush: true)) {
			render(view: "create", model: [grupoadicionaisInstance: grupoadicionaisInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'grupoAdicionais.label', default: 'Descricao'), grupoadicionaisInstance.id])
		redirect(action: "show", id: grupoadicionaisInstance.id)
	}

	def auth() {
		if(!session.estab) {
            redirect(controller:"Estabelecimento")
			return false
		}
	}
}
