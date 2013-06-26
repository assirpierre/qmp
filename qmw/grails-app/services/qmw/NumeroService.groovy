package qmw

class NumeroService {

	def getInt(String numero){ 
		int n = 0;
		try{
			if(numero!=null && !numero.equals(""))
				n = Integer.parseInt(geraNumero(numero));
		}catch(Exception e){}
		return n;
	}
	
	def geraNumero(String numero) {
		String retorno = "0";
		if (numero!=null && !numero.equals(""))
			retorno =  numero.replaceAll(",",".");
		return retorno;
	}
}
