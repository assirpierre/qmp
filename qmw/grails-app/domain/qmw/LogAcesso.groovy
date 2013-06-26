package qmw

class LogAcesso {

	int dispositivo
	String metodo
	int qtde

    static constraints = {
    }

	static mapping = {
		metodo index: 'logAcesso_metodo_Idx'
	}		
}
