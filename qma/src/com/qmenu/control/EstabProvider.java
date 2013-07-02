package com.qmenu.control;

import android.content.Context;
import android.util.Log;
import com.qmenu.model.Estabelecimento;
import com.qmenu.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class EstabProvider {
	
	private static ArrayList<Estabelecimento> estab = new ArrayList<Estabelecimento>();
	private static String codigo = "";
	private static String nomefantasia = "";
	private static String sistemaTrabalho = "";
	
	public static void atualiza(Context ctx, String jsonStr){
        try{
            JSONArray jsonArray = new JSONArray(jsonStr);
            if(jsonArray.length() > 0){
                estab = new ArrayList<Estabelecimento>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject j = jsonArray.getJSONObject(i);
                    if(j.getString("class").equals("qmw.Estabelecimento")){
                        Estabelecimento o = new Estabelecimento();
                        o.setId(j.getInt("id"));
                        o.setNomeFantasia(j.getString("nomefantasia"));
                        o.setSistemaTrabalho(j.getString("sistemaTrabalho"));
                        estab.add(o);
                        codigo = j.getString("id");
                        nomefantasia = j.getString("nomefantasia");
                        sistemaTrabalho = j.getString("sistemaTrabalho");
                    }
                    if(j.getString("class").equals("qmw.Dispositivo") && Util.leSessao(ctx, "dispositivo").equals("")){
                        Util.gravaSessao(ctx, "dispositivo", j.getString("id"));
                        Util.gravaSessao(ctx, "transacao", "0");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public static ArrayList<Estabelecimento> getEstab() {
		return estab;
	}

	public static void setEstab(ArrayList<Estabelecimento> estab) {
		EstabProvider.estab = estab;
	}

	public static String getCodigo() {
		return codigo;
	}

	public static void setCodigo(String codigo) {
		EstabProvider.codigo = codigo;
	}

	public static String getNomefantasia() {
		return nomefantasia;
	}

	public static void setNomefantasia(String nomefantasia) {
		EstabProvider.nomefantasia = nomefantasia;
	}

	public static String getSistemaTrabalho() {
		return sistemaTrabalho;
	}

}
