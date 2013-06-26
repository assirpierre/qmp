package qmw

class MesaController {

	def beforeInterceptor = [action:this.&auth]
    def scaffold=true
	def formService

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def mesaInstance = Mesa.where {
			estab == session.estab
		 }
		[mesaInstanceList: mesaInstance.list(params), mesaInstanceTotal: mesaInstance.count()]
	}
    
    def auth() {
        if(!session.estab) {
            redirect(controller:"Estabelecimento")
            return false
        }
    }
	
	def show(Long id) {
		def mesaInstance = Mesa.get(id)
		if (!mesaInstance || !mesaInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesa.label', default: 'Mesa'), id])
			redirect(action: "list")
			return
		}
		[mesaInstance: mesaInstance]
	}

	def edit(Long id) {
		def mesaInstance = Mesa.get(id)
		if (!mesaInstance || !mesaInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesa.label', default: 'Mesa'), id])
			redirect(action: "list")
			return
		}
		[mesaInstance: mesaInstance]
	}
	
	def save() {
		def mesaInstance = new Mesa(params)
		mesaInstance.setEstab(session.estab)
		if (!mesaInstance.save(flush: true)) {
			render(view: "create", model: [mesaInstance: mesaInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'mesa.label', default: 'Mesa'), mesaInstance.id])
		redirect(action: "show", id: mesaInstance.id)
	}
}
