package qmw

import java.util.Date;

class PedidoCapa {

	String nome
	Date dataInicio
	Date dataFim
	double subTotal
	double servico
	double total
	boolean cupomImpresso
	static belongsTo = [estab: Estabelecimento, mesa: Mesa]
	
	static constraints = {
		nome(nullable: true)
		dataFim(nullable: true)
		subTotal(nullable: true)
		servico(nullable: true)
		total(nullable: true)
	}
	static mapping = {
		estab index: 'pedidoCapa_estab_Idx'
	}
}
