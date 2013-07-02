package com.qmenu.model;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Mesa implements Comparable<Mesa>{
    SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
	private int id;
    private String numero;
	private String nome;
	private String situacao;
	private Date dataultsituacao;
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDataultsituacaoF() {
		return sdfD.format(dataultsituacao);
	}
	public Date getDataultsituacao() {
		return dataultsituacao;
	}

	public void setDataultsituacao(Date dataultsituacao) {
		this.dataultsituacao = dataultsituacao;
	}
	
	public String getHora(){
		return sdfH.format(dataultsituacao);
	}	
	
	public int compareTo(Mesa mesa) {
        if(mesa.getSituacao().equals("D") && getSituacao().equals("O"))
			return -1;
		else if(mesa.getSituacao().equals("O") && getSituacao().equals("D"))
			return 1;
		else
			return mesa.getDataultsituacao().compareTo(this.getDataultsituacao());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}