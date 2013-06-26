package qmw

class Estabelecimento {
	String documento
	String nomefantasia
	String razaosocial
	String responsavel
	String telefone
	String cep
	String estado
	String cidade
	String endereco
	String numero
	String complemento
	String email
	String senha
	String localFechamento
    String sistemaTrabalho
	boolean nolocal
	boolean viagem
	boolean delivery
	double taxaServico
	
	static hasMany = [menuPrincipal: MenuPrincipal, usuario:Usuario, mesa:Mesa, grupoadicionais: GrupoAdicionais, 
					  adicionais: Adicionais, menu:Menu, logDispositivo: LogDispositivo]
	
	String toString () {
		"${nomefantasia}"
	}
	
	static constraints = {
		documento(blank:false, unique: true, size: 5..14, matches: "[0-9]+")
		nomefantasia(blank:false, size: 3..30)
		razaosocial(blank:false)
		responsavel(blank:false)
		telefone(blank:false)
		cep(blank:false)
		estado(inList: ["AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PR","PB","PA","PE","PI","RJ","RN","RS","RO","RR","SC","SE","SP","TO"])
		cidade(blank:false)
		endereco(blank:false)
		numero(blank:false)
		complemento()	
		email(email: true, blank:false, unique: true)
		senha(password: true, blank:false, size: 5..15)
		localFechamento(nullable: true, inList: ["1","2","3","4"])
        sistemaTrabalho(nullable:true,inList: ["mesa","comanda"])
	}
	static mapping = {
		documento index: 'estabalecimento_documento_Idx'
	}

}
