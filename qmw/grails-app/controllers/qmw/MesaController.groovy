package qmw

import grails.converters.JSON

class MesaController {

    def autenticaService
    def beforeInterceptor = {autenticaService.autenticaSessao(this)}
    def numeroService
    def scaffold=true

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def mesaInstance = Mesa.where {
			estab == session.estab
		 }
		[mesaInstanceList: mesaInstance.list(params), mesaInstanceTotal: mesaInstance.count()]
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

    def exporta() {
        def estab = numeroService.getInt(params['estab']);
        render Mesa.where{estab.id == estab}.list() as JSON
    }

}
