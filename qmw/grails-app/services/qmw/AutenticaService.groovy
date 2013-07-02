package qmw

class AutenticaService {

    def autenticaSessao(controller) {
        if(!controller.session.estab) {
            controller.redirect(controller:"Estabelecimento")
            return false
        }
    }

    def autenticaChave(controller) {
        if(controller.params['chave'] != "823742jnkjdshfsa[sdf'sasd[]adf]084ASFF"){
            controller.redirect(controller:"Estabelecimento")
            return false
        }
    }
}
