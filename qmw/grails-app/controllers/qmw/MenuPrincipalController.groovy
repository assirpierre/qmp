package qmw

class MenuPrincipalController {

    def autenticaService
    def beforeInterceptor = {autenticaService.autenticaSessao(this)}
    def scaffold=true
	
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		if(params.sort == null)
			params.sort = "sequencia"
		def menuPrincipalInstance = MenuPrincipal.where {
			estab == session.estab
		 }
		[menuPrincipalInstanceList: menuPrincipalInstance.list(params), menuPrincipalInstanceTotal: menuPrincipalInstance.count()]
	}
	
	def save() {
		def menuPrincipalInstance = new MenuPrincipal(params)
		menuPrincipalInstance.setEstab(session.estab)
		if (!menuPrincipalInstance.save(flush: true)) {
			render(view: "create", model: [menuPrincipalInstance: menuPrincipalInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'menuPrincipal.label', default: 'Descricao'), menuPrincipalInstance.id])
		redirect(action: "show", id: menuPrincipalInstance.id)
	}
	
	def show(Long id) {
		def menuPrincipalInstance = MenuPrincipal.get(id)
		if (!menuPrincipalInstance || !menuPrincipalInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'menuPrincipal.label', default: 'menuPrincipal'), id])
			redirect(action: "list")
			return
		}
		[menuPrincipalInstance: menuPrincipalInstance]
	}

	def edit(Long id) {
		def menuPrincipalInstance = MenuPrincipal.get(id)
		if (!menuPrincipalInstance || !menuPrincipalInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'menuPrincipal.label', default: 'menuPrincipal'), id])
			redirect(action: "list")
			return
		}
		[menuPrincipalInstance: menuPrincipalInstance]
	}
}
