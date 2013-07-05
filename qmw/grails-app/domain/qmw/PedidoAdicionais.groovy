package qmw

class PedidoAdicionais {
    String nome
    String descricao
    double preco

    static belongsTo = [estab: Estabelecimento, adicionais: Adicionais, pedido: Pedido]

    static constraints = {
        descricao(nullable: true)
    }
}
