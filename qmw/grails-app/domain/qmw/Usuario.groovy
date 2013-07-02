package qmw

class Usuario {
	String codigo
	String senha
	String nome
	String email
	static belongsTo = [estab: Estabelecimento]
	static hasMany = [pedido: Pedido]

	String toString () {
		"${codigo}"
	}

    static constraints = {
		codigo(blank:false, unique: 'estab')
		senha(password: true, size: 5..15)
		nome(blank:false)
		email(email: true, blank:false)
    }

	static mapping = {
		estab index: 'usuario_estab_Idx'
	}
}
