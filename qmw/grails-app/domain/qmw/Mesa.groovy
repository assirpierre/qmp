package qmw

class Mesa {
	
	int numero
	String situacao = "D"
	Date dataultsituacao = new Date();
	String nome
	static belongsTo = [estab: Estabelecimento]
	
	String toString () {
		"${numero}"
	}
	
    static constraints = {
		numero(blank: false, unique: 'estab')
		dataultsituacao(display: false)
		situacao(display: false)
		nome(nullable:true, display: false)
    }
	static mapping = {
		estab index: 'mesa_estab_Idx'
	}
}
