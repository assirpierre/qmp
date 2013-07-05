package com.qmenu.control;

import android.annotation.SuppressLint;
import com.qmenu.model.Adicionais;
import com.qmenu.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressLint("UseSparseArrays")
public class AdicionaisProvider {
    private static HashMap<Integer, ArrayList> h_grupoAdicionais = new HashMap<Integer, ArrayList>();
    private static HashMap<Integer, Adicionais> h_adicionais = new HashMap<Integer, Adicionais>();
    private static ArrayList<Adicionais> adicionais = new ArrayList<Adicionais>();

	public static void atualiza(String jsonStr){
        try{
            JSONArray jsonArray = new JSONArray(jsonStr);
            if(jsonArray.length() > 0){
                int grupo = 0;
                h_grupoAdicionais = new HashMap<Integer, ArrayList>();
                ArrayList<Adicionais> adicionais_g = new ArrayList<Adicionais>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject j = jsonArray.getJSONObject(i);
                    if(j.getString("class").equals("qmw.Adicionais")){
                        if(grupo != j.getJSONObject ("grupoAdicionais").getInt("id")){
                            if(grupo != 0)
                                h_grupoAdicionais.put(grupo, adicionais_g);
                            adicionais_g = new ArrayList<Adicionais>();
                            grupo = j.getJSONObject ("grupoAdicionais").getInt("id");
                        }
                        Adicionais o = new Adicionais();
                        o.setId(j.getInt("id"));
                        o.setNome(Util.tostr(j.getString("nome")));
                        o.setDescricao(Util.tostr(j.getString("descricao")));
                        o.setGrupoAdicionaisId(j.getJSONObject ("grupoAdicionais").getInt("id"));
                        o.setPreco(j.getDouble("preco"));
                        adicionais.add(o);
                        h_adicionais.put(j.getInt("id"), o);
                        adicionais_g.add(o);
                    }
                }
                h_grupoAdicionais.put(grupo, adicionais_g);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}

    public static ArrayList<Adicionais> getAdicionais(Integer grupoAdicionalId) {
        return h_grupoAdicionais.get(grupoAdicionalId);
    }

    public static Adicionais getAdicionaisById(int id){
        return h_adicionais.get(id);
    }
}
