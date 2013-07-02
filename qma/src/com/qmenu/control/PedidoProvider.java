package com.qmenu.control;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import android.content.Context;
import android.util.Log;
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
	private static ArrayList<Pedido> pedidoPendente = new ArrayList<Pedido>();
	private static double total;
	
	public static void atualiza(String jsonStr, Activity a){
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
                        o.setMprincipal(MenuProvider.getMPrincipal(j.getJSONObject ("menuPrincipal").getInt("id")));
                        o.setQtde(j.getInt("qtde"));
                        o.setPrecoadicionais(j.getDouble("precoAdicionais"));
                        o.setTotal(j.getDouble("total"));
                        total += j.getDouble("total");
                        o.setItem(j.getString("item"));
                        o.setItemdescricao(j.getString("itemDescricao"));
                        o.setItemadd(j.getString("itemAdicional"));
                        o.setItemadddescricao(j.getString("itemAdicionalDescricao"));
                        pedido.add(o);
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
                for(Pedido p: pedidoPendente){
                    json.put(p.toJSON(ctx));
                }

//				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//				Document document = null;
//				document = builder.newDocument();
//				document.getXmlEncoding();
//				Element e = document.createElement("cadastro");
//				e.setAttribute("treg", "" + pedidoPendente.size());
//				e.setAttribute("tcol", "32");
//				for(Pedido p: pedidoPendente){
//					Element el = document.createElement("registro");
//					el.appendChild(getE(document, "datapedido", p.getDatapedido(), "d"));
//					el.appendChild(getE(document, "observacao", p.getObservacao(), "t"));
//					el.appendChild(getE(document, "grupoitem", "" + p.getMprincipal().getId(), "t"));
//					el.appendChild(getE(document, "preco", "" + p.getPrecounitario(), "t"));
//					el.appendChild(getE(document, "precoadicionais", "" + p.getPrecoadicionais(), "t"));
//					el.appendChild(getE(document, "qtde", "" + p.getQtde(), "t"));
//					el.appendChild(getE(document, "total", p.getTotalF(), "t"));
//                    String item = "", itemdescricao = "", itemadd = "", itemadddescricao = "";
//					for(int i = 0; i <p.getL_menu().size();i++){
//                        item += p.getL_menu().get(i).getId() + (i==0?"":" - ");
//                        itemdescricao += p.getL_menu().get(i).getDescricao() + (i==0?"":" - ");
//                    }
//					for(int i = 0; i <p.getL_adicionais().size();i++){
//                        itemadd += p.getL_adicionais().get(i).getId() + (i==0?"":" - ");
//                        itemadddescricao += p.getL_adicionais().get(i).getDescricao() + (i==0?"":" - ");
//                    }
//                    el.appendChild(getE(document, "item", item, "t"));
//                    el.appendChild(getE(document, "itemdescricao", itemdescricao, "t"));
//                    el.appendChild(getE(document, "itemadd", itemadd, "t"));
//                    el.appendChild(getE(document, "itemadddescricao", itemadddescricao, "t"));
//    				e.appendChild(el);
//    			}
//    			document.appendChild(e);
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				TransformerFactory tf = TransformerFactory.newInstance();
//				Transformer trans = tf.newTransformer();
//				trans.setOutputProperty("encoding", "ISO-8859-1");
//				trans.transform(new DOMSource(document), new StreamResult(os));
//				xml = os.toString();
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
}
