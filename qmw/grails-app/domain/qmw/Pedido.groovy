package qmw

class Pedido {
	int sequencia
	Date dataPedido
	Date dataAtendimento
	int dispositivo
	double preco
	double qtde
	double precoAdicionais
	double total
	String observacao
	String situacao
    String item
    String itemDescricao
    String itemAdicional
    String itemAdicionalDescricao
    String usuarioCodigo
	boolean cupomImpresso
	static belongsTo = [estab: Estabelecimento, usuario: Usuario, mesa: Mesa, menuPrincipal: MenuPrincipal, pedidoCapa: PedidoCapa]

	static constraints = {
		observacao(nullable: true)
		dataAtendimento(nullable: true)
        itemDescricao(nullable: true)
        itemAdicional(nullable: true)
        itemAdicionalDescricao(nullable: true)
	}

	static mapping = {
		mesa index: 'pedido_mesa_Idx'
		estab index: 'pedido_estab_Idx'
		pedidoCapa index: 'pedido_pedidoCapa_Idx'
		usuario index: 'pedido_usuario_Idx'
		sequencia index: 'pedido_codigo_Idx'
		cupomImpresso index: 'pedido_cupomImpresso_Idx'
	}
}
