package qmw

class PedidoItem {
    String nome
    String descricao
    double preco
    double qtde
    double total

    static belongsTo = [estab: Estabelecimento, menu: Menu, pedido: Pedido]

    static constraints = {
        nome(nullable: true)
    }
}
