package qmw

import java.util.Date;

class PedidoCapa {

	String nome
	Date dataInicio
	Date dataFim
	double subTotal = 0
	double servico = 0
	double total = 0
	boolean cupomImpresso = false
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
