package com.qmenu.model;

import com.qmenu.util.Numero;

public class Adicionais{
    private int id;
	private String nome;
	private Integer grupoAdicionaisId;
	private String descricao;
	private double preco;
    private boolean selecionado;

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

	public double getPreco() {
		return preco;
	}

    public String getPrecoF() {
        return Numero.formata(preco, 2);
    }

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}