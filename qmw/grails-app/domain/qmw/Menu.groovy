package qmw

class Menu {

    String nome
	String descricao
	double preco
	int sequencia
	static belongsTo = [estab: Estabelecimento, grupoAdicionais: GrupoAdicionais, menuPrincipal: MenuPrincipal]

	String toString () {
		"${nome}"
	}

	static constraints = {
		menuPrincipal(blank:false)
        nome(maxSize: 30, blank:false)
		descricao(maxSize: 300)
		preco(blank: false, matches: /\d+/)
		grupoAdicionais(nullable: true)
	}
	static mapping = {
		estab index: 'menu_estab_Idx'
		menuPrincipal index: 'menu_menuPrincipal_Idx'
	}
}
