package qmw

class LogDispositivo {

	int dispositivo
	Date data = new Date()
	String dados
	static belongsTo = [estab: Estabelecimento]
	
    static constraints = {
    }
	static mapping = {
		dados type: 'text'
	}

}
