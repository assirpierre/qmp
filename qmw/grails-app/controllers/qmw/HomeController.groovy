package qmw

class HomeController {

    def index() {}
    def empresa() {}
    def diferenciais() {}
    def tutoriais() {}
    def funcionamento() {}
    def contato() {}
    def save() {
        sendMail {
            from "qmenu@qmenu.com.br"
            to message(code: 'contato.email.destino')
            subject "Contato site"
            body 'Tipo Contato: ' + message(code: 'contato.tipoContato.' + params['tipoContato']) + '\n'+
                 'Nome: ' + params['nome'] + '\n' +
                 'Email: ' + params['email'] + '\n' +
                 'Mensagem: ' + params['mensagem']
        }
        flash.message = message(code: 'contato.email.mensagem')
        redirect(action: "contato")
    }
}
