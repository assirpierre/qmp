package qmw

class LogTransacao {
	
	int dispositivo
	int transacao

    static mapping = {
        dispositivo index: 'LogTransacao_dispositivo_Idx'
        transacao index: 'LogTransacao_transacao_Idx'
    }
}
