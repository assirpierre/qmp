package com.qmenu.control;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import android.content.Context;
import android.util.Log;
import com.qmenu.model.Adicionais;
import com.qmenu.model.Menu;
import com.qmenu.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.app.Activity;

import com.qmenu.model.Pedido;
import com.qmenu.util.Numero;

public class PedidoProvider {
	
	private static Pedido pedidoAtual;
	private static boolean novo;
	private static ArrayList<Pedido> pedido;
    private static HashMap<Integer, Pedido> h_pedido = new HashMap<Integer, Pedido>();
	private static ArrayList<Pedido> pedidoPendente = new ArrayList<Pedido>();
	private static double total;
	
	public static void atualiza(String jsonStr){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            JSONArray jsonArray = new JSONArray(jsonStr);
            if(jsonArray.length() > 0){
                total = 0;
                pedido = new ArrayList<Pedido>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject j = jsonArray.getJSONObject(i);
                    if(j.getString("class").equals("qmw.Pedido")){
                        Pedido o = new Pedido();
                        o.setSequencia(j.getString("sequencia"));
                        o.setDatapedido(sdf.parse(j.getString("dataPedido")));
                        o.setObservacao(Util.tostr(j.getString("observacao")));
                        o.setSituacao(j.getString("situacao"));
                        o.setUsuario(j.getString("usuarioCodigo"));
                        o.setMprincipal(MenuPrincipalProvider.getMPrincipalById(j.getJSONObject ("menuPrincipal").getInt("id")));
                        o.setQtde(j.getInt("qtde"));
                        o.setPrecoadicionais(j.getDouble("precoAdicionais"));
                        o.setPrecounitario(j.getDouble("preco"));
                        o.setTotal(j.getDouble("total"));
                        total += j.getDouble("total");
                        o.setItemCompleto(j.getString("itemCompleto"));
                        o.setItem(j.getString("item"));
                        o.setItemdescricao(j.getString("itemDescricao"));
                        o.setItemadd(j.getString("itemAdicional"));
                        o.setItemadddescricao(j.getString("itemAdicionalDescricao"));
                        pedido.add(o);
                        h_pedido.put(j.getInt("id"),o);
                    }
                    if(j.getString("class").equals("qmw.PedidoAdicionais")){
                        Pedido p = getPedidoById(j.getJSONObject ("pedido").getInt("id"));
                        Adicionais a = AdicionaisProvider.getAdicionaisById(j.getJSONObject ("adicionais").getInt("id"));
                        p.getL_adicionais().add(a);
                    }
                    if(j.getString("class").equals("qmw.PedidoItem")){
                        Pedido p = getPedidoById(j.getJSONObject ("pedido").getInt("id"));
                        Menu m = MenuProvider.getMenuById(j.getJSONObject("menu").getInt("id"));
                        p.getL_menu().add(m);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public static ArrayList<Pedido> getPedido() {
		return pedido;
	}

	public static void setPedido(ArrayList<Pedido> pedido) {
		PedidoProvider.pedido = pedido;
	}

	public static ArrayList<Pedido> getPedidoPendente() {
		return pedidoPendente;
	}
	
	public static void setPedidoPendente(ArrayList<Pedido> pedidoPendente) {
		PedidoProvider.pedidoPendente = pedidoPendente;
	}

	public static void gravaPedidoPendente() {
		if(novo)
			pedidoPendente.add(pedidoAtual);
	}	

	public static JSONArray getPedidosPendentes(Context ctx) {
        JSONArray json = null;
		try{
			if(pedidoPendente.size() > 0){
                json = new JSONArray();
                for(Pedido p: pedidoPendente)
                    json.put(p.toJSON(ctx));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return json;
	}
	
	private static Element getE(Document document, String nome, String valor, String tipo){
		Element e = document.createElement(nome);
		e.setAttribute("tipo", tipo);
		e.setTextContent(valor);
		return e;
	}
	
	public static void limpaPendente(){
		pedidoPendente = new ArrayList<Pedido>();
	}

	public static Pedido getPedidoAtual() {
		return pedidoAtual;
	}

	public static void setPedidoAtual(Pedido pedidoAtual, boolean novo) {
		PedidoProvider.pedidoAtual = pedidoAtual;
		PedidoProvider.novo = novo;
	}

	public static boolean isNovo() {
		return novo;
	}
	
	public static String getTotal(){
		return Numero.formata(total, 2);
	}

	public static String getTotalPendente(){
		double total = 0;
		for(Pedido p: pedidoPendente)
			total += p.getTotal();
		return Numero.formata(total, 2);
	}

    public static Pedido getPedidoById(int id){
        return h_pedido.get(id);
    }
}
