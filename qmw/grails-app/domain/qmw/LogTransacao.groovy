package qmw

class LogTransacao {
	
	int dispositivo
	int transacao
	String metodo
	String chave
	String retorno
	int nchamada

    static constraints = {
    }
	static mapping = {
		retorno type: 'text'
	}
}
