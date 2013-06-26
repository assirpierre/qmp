package qmw

import grails.converters.JSON
import grails.converters.XML

class MenuController {

	def beforeInterceptor = [action:this.&auth]
	def scaffold=true

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		if(params.sort == null)
			params.sort = "sequencia"
		def vvmenuPrincipal = params['menuPrincipal']
		if (vvmenuPrincipal == null)
			vvmenuPrincipal =  MenuPrincipal.findByEstab(session.estab)?.id
		def menuInstance = Menu.where {
			estab == session.estab
			and
			menuPrincipal.id == vvmenuPrincipal
		 }
		def model = [menuInstanceList: menuInstance.list(params), menuInstanceTotal: menuInstance.count()]
		
		if (request.xhr)
			render(template: "list", model: model)
		else
			model
	}
	
	def show(Long id) {
		def menuInstance = Menu.get(id)
		if (!menuInstance || !menuInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'menu.label', default: 'menu'), id])
			redirect(action: "list")
			return
		}
		[menuInstance: menuInstance]
	}

	def edit(Long id) {
		def menuInstance = Menu.get(id)
		if (!menuInstance || !menuInstance.estab.id.equals(session.estab.id)) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'menu.label', default: 'menu'), id])
			redirect(action: "list")
			return
		}
		[menuInstance: menuInstance]
	}
		
	def save() {
		def menuInstance = new Menu(params)
		menuInstance.setEstab(session.estab)
		if (!menuInstance.save(flush: true)) {
			render(view: "create", model: [menuInstance: menuInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'menu.label', default: 'Descricao'), menuInstance.id])
		redirect(action: "show", id: menuInstance.id)
	}

	def auth() {
		if(!session.estab) {
            println params['chave']
            if(params['chave'] != "823742jnkjdshfsa[sdf'sasd[]adf]084ASFF"){
                redirect(controller:"Estabelecimento")
                return false
            }
		}
	}

    def exporta() {
        render MenuPrincipal.where{id == 240}.list() as JSON
//        render MenuPrincipal.where{estab.id == 218}.list() + Menu.where{estab.id == 218}.list() as XML;
    }
}