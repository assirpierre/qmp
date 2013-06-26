package qmw

import java.util.Date;

class GrupoAdicionais {
	String descricao
	static belongsTo = [estab: Estabelecimento]
	static hasMany = [adicionais: Adicionais, menu:Menu]
	
	String toString () {
		"${descricao}"
	}
	
	static constraints = {
		descricao(blank:false, unique: 'estab', size: 5..30)
	}
	static mapping = {
		estab index: 'grupoAdicionais_estab_Idx'
	}
}