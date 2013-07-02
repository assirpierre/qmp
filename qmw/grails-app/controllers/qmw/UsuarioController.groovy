package qmw


class UsuarioController {
    def autenticaService
    def beforeInterceptor = {autenticaService.autenticaSessao(this)}
    def scaffold=true
	
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)		
		def usuarioInstance = Usuario.where {
			estab == session.estab
		}
		[usuarioInstanceList: usuarioInstance.list(params), usuarioInstanceTotal: usuarioInstance.count()]
    }

	def show(Long id) {
		def usuarioInstance = Usuario.get(id)
		if (!usuarioInstance || !usuarioInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'usuario'), id])
			redirect(action: "list")
			return
		}
		[usuarioInstance: usuarioInstance]
	}

	def edit(Long id) {
		def usuarioInstance = Usuario.get(id)
		if (!usuarioInstance || !usuarioInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'usuario'), id])
			redirect(action: "list")
			return
		}
		[usuarioInstance: usuarioInstance]
	}
}
