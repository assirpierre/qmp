package qmw

class Adicionais {

    String nome
	String descricao
	double preco
	static belongsTo = [estab: Estabelecimento, grupoAdicionais: GrupoAdicionais]
	
	String toString () {
		"${nome}"
	}
	
    static constraints = {
		grupoAdicionais(blank:false)
        nome(maxSize: 30, blank:false)
		descricao(maxSize: 300)
		preco(blank: false, matches: /\d+/)
		estab(display: false)
    }
	static mapping = {
		estab index: 'adicionais_estab_Idx'
	}
}
