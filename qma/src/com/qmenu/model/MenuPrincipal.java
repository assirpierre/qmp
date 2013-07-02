package com.qmenu.model;

import java.util.ArrayList;

public class MenuPrincipal {
	private ArrayList<Menu> menu = new ArrayList<Menu>();
	private int id;
	private String nome;
	private String tipoCobranca;
	private int qtdeItem;

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtdeItem() {
		return qtdeItem;
	}
	public void setQtdeItem(int qtdeItem) {
		this.qtdeItem = qtdeItem;
	}
	public ArrayList<Menu> getMenu() {
		return menu;
	}
	public void setMenu(ArrayList<Menu> menu) {
		this.menu = menu;
	}
	public String getTipoCobranca() {
		return tipoCobranca;
	}
	public void setTipoCobranca(String tipoCobranca) {
		this.tipoCobranca = tipoCobranca;
	}
}
