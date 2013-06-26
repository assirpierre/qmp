package qmw

class MenuPrincipal {
    String nome
	String tipoCobranca
	String localAtendimento
	int qtdeitem
	int sequencia
	boolean imprimeCupom
	
	static belongsTo = [estab: Estabelecimento]
	static hasMany = [menu:Menu]

	String toString () {
		"${nome}"
	}

	static constraints = {
        nome(maxSize: 30, blank:false)
		qtdeitem(blank:false, min:1)
		sequencia(blank:false)
		tipoCobranca(blank:false, inList: ["Normal","Media"])
		localAtendimento(blank:false, inList: ["1","2","3","4"])
	}
	static mapping = {
		sort "sequencia"
		estab index: 'menuPrincipal_estab_Idx'
	}

}
