package com.qmenu.control;

import com.qmenu.model.Mesa;
import com.qmenu.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class MesaProvider {

	private static ArrayList<Mesa> mesa = new ArrayList<Mesa>();
	
	public static void atualiza(String jsonStr){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            JSONArray jsonArray = new JSONArray(jsonStr);
            if(jsonArray.length() > 0){
                mesa = new ArrayList<Mesa>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject j = jsonArray.getJSONObject(i);
                    if(j.getString("class").equals("qmw.Mesa")){
                        Mesa o = new Mesa();
                        o.setId(j.getInt("id"));
                        o.setNumero(Util.tostr(j.getString("numero")));
                        o.setNome(Util.tostr(j.getString("nome")));
                        o.setSituacao(Util.tostr(j.getString("situacao")));
                        o.setDataultsituacao(sdf.parse(j.getString("dataultsituacao")));
                        mesa.add(o);
                    }
                }
                Collections.sort(mesa);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

	}

	public static ArrayList<Mesa> getMesa() {
		return mesa;
	}

	public static void setMesa(ArrayList<Mesa> mesa) {
		MesaProvider.mesa = mesa;
	}

	public static Mesa getMesa(String codigo){		
		for(Mesa o: mesa)
			if(o.getNumero().equals(codigo))
				return o;
		return null;
	}

}
