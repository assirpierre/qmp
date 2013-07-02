package com.qmenu.model;

import com.qmenu.util.Numero;

public class Menu {
    private int id;
	private String nome;
	private String descricao;
	private Integer grupoAdicionaisId;
	private double preco;
	private boolean selecionado;

    public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
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
	public double getPreco() {
		return preco;
	}
	public String getPrecoF() {
		return Numero.formata(preco,2);
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public boolean isSelecionado() {
		return selecionado;
	}
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	public Integer getGrupoAdicionaisId() {
		return grupoAdicionaisId;
	}
	public void setGrupoAdicionaisId(Integer grupoAdicionaisId) {
		this.grupoAdicionaisId = grupoAdicionaisId;
	}
}