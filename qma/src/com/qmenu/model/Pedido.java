package com.qmenu.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;
import com.qmenu.util.Numero;
import com.qmenu.util.Util;
import org.json.JSONException;
import org.json.JSONObject;


public class Pedido {
    SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
    SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private String sequencia;
    private ArrayList<Menu> l_menu = new ArrayList<Menu>();
    private ArrayList<Adicionais> l_adicionais = new ArrayList<Adicionais>();
    private String item = "";
    private String itemadd = "";
    private String itemdescricao = "";
    private String itemadddescricao = "";
    private MenuPrincipal mprincipal;
	private Date datapedido;
	private String observacao;
	private String usuario;
	private String situacao;
	private int qtde;
	private double precounitario;
	private double precoadicionais = 0;
	private double total;
	private int posItemSelecionado;

	public String getDatapedido() {
		return sdfD.format(datapedido);
	}
	public void setDatapedido(Date datapedido) {
		this.datapedido = datapedido;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getSequencia() {
		return sequencia;
	}
	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
		calculaTotal();
	}
	
	private void calculaTotal() {
		this.total = precounitario * qtde + precoadicionais * qtde;
	}
	
	public double getTotal() {
		return total;
	}
	public String getTotalF() {
		return Numero.formata(total,2);
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public String getHora(){
        return sdfH.format(datapedido);
	}

	public void addItem(Menu menu){
        this.item += (l_menu.size() == 0?"":" - ") + menu.getNome();
        this.itemdescricao += (l_menu.size() == 0?"":" - ") + menu.getDescricao();
		l_menu.add(menu);
	}
	
	public void addItemadd(Adicionais adicionais, boolean recalcula){
        this.itemadd += (l_adicionais.size() == 0?"":" - ") + adicionais.getNome();
        this.itemadddescricao += (l_adicionais.size() == 0?"":" - ") + adicionais.getDescricao();
		l_adicionais.add(adicionais);
		if(recalcula){
			precoadicionais += adicionais.getPreco();
			calculaTotal();
		}
	}
	
	public int getQtdeItem(){
		return l_menu.size();
	}
	
	public int getQtdeItemadd(){
		return l_adicionais.size();
	}
	
	public Menu getMenuSelecionado(){
        return l_menu.get(posItemSelecionado);
	}
	
	public String getDescricaoItens(){
		String retorno = "";
		for(int i = 0; i < l_menu.size();i++)
			retorno += (i==0?"":" / ") + l_menu.get(i).getNome();
		return retorno;
	}

	public ArrayList<Menu> getL_menu() {
		return l_menu;
	}
	public void setL_menu(ArrayList<Menu> l_menu) {
		this.l_menu = l_menu;
	}
	public int getPosItemSelecionado() {
		return posItemSelecionado;
	}
	public void setPosItemSelecionado(int posItemSelecionado) {
		this.posItemSelecionado = posItemSelecionado;
	}
	public MenuPrincipal getMprincipal() {
		return mprincipal;
	}
	public void setMprincipal(MenuPrincipal mprincipal) {
		this.mprincipal = mprincipal;
	}
	public void setMprincipalIni(MenuPrincipal mprincipal) {
		this.mprincipal = mprincipal;
		qtde = 1;
		if(mprincipal.getTipoCobranca().equals("N")){
			for(Menu o: l_menu){
				if(qtde * o.getPreco()>total)
					total = qtde * o.getPreco();
			}
		}else{
			for(Menu o: l_menu)
				total += qtde * o.getPreco();			
			total = total / l_menu.size();
		}
		precounitario = total;		
		
	}
	public double getPrecounitario() {
		return precounitario;
	}
	public void setPrecounitario(double precounitario) {
		this.precounitario = precounitario;
	}
	public void limpaAdicionais(){
        itemadd = "";
        itemadddescricao = "";
		l_adicionais.clear();
		precoadicionais = 0;
		calculaTotal();
	}
	public double getPrecoadicionais() {
		return precoadicionais;
	}
	public void setPrecoadicionais(double precoadicionais) {
		this.precoadicionais = precoadicionais;
	}
	public ArrayList<Adicionais> getL_adicionais() {
		return l_adicionais;
	}
	public void setL_adicionais(ArrayList<Adicionais> l_adicionais) {
		this.l_adicionais = l_adicionais;
	}
	
	public boolean isItemaddChecked(Adicionais adicionais){
		for(Adicionais o: l_adicionais)
			if(o.getId() == adicionais.getId())
				return true;
		return false;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

    public String getItemadddescricao() {
        return itemadddescricao;
    }

    public void setItemadddescricao(String itemadddescricao) {
        this.itemadddescricao = itemadddescricao;
    }

    public String getItemdescricao() {
        return itemdescricao;
    }

    public void setItemdescricao(String itemdescricao) {
        this.itemdescricao = itemdescricao;
    }

    public String getItemadd() {
        return itemadd;
    }

    public void setItemadd(String itemadd) {
        this.itemadd = itemadd;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public JSONObject toJSON(Context ctx){
        JSONObject obj = new JSONObject();
        try {
            obj.put("estab.id", Util.leSessao(ctx, "estab"));
            obj.put("mesa.id", Util.leSessao(ctx, "mesa"));
            obj.put("usuario.id", Util.leSessao(ctx, "usuario_id"));
            obj.put("dispositivo", Util.leSessao(ctx, "dispositivo"));
            obj.put("item", item);
            obj.put("itemDescricao", itemdescricao);
            obj.put("itemAdicional", itemadd);
            obj.put("itemAdicionalDescricao", itemadddescricao);
            obj.put("menuPrincipal.id", mprincipal.getId());
            obj.put("dataPedido", sdfD.format(datapedido));
            obj.put("observacao", observacao);
            obj.put("usuarioCodigo", usuario);
            obj.put("situacao", "C");
            obj.put("qtde", qtde);
            obj.put("preco", precounitario);
            obj.put("precoAdicionais", precoadicionais);
            obj.put("total", total);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}