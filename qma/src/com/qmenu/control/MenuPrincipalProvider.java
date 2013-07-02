package com.qmenu.control;

import android.annotation.SuppressLint;
import com.qmenu.model.MenuPrincipal;
import com.qmenu.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuPrincipalProvider {
	
	private static ArrayList<MenuPrincipal> menuPrincipal = new ArrayList<MenuPrincipal>();
	@SuppressLint("UseSparseArrays")
	private static HashMap<Integer, MenuPrincipal> h_menuPrincipal = new HashMap<Integer, MenuPrincipal>();
	
	public static void atualiza(String jsonStr){
        try{
            JSONArray jsonArray = new JSONArray(jsonStr);
            if(jsonArray.length() > 0){
                menuPrincipal = new ArrayList<MenuPrincipal>();
                h_menuPrincipal = new HashMap<Integer, MenuPrincipal>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject j = jsonArray.getJSONObject(i);
                    if(j.getString("class").equals("qmw.MenuPrincipal")){
                        MenuPrincipal o = new MenuPrincipal();
                        o.setId(j.getInt("id"));
                        o.setNome(Util.tostr(j.getString("nome")));
                        o.setTipoCobranca(Util.tostr(j.getString("tipoCobranca")));
                        o.setQtdeItem(j.getInt("qtdeitem"));
                        menuPrincipal.add(o);
                        h_menuPrincipal.put(j.getInt("id"), o);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public static ArrayList<MenuPrincipal> getMenuPrincipal() {
		return menuPrincipal;
	}
	
	public static MenuPrincipal getMPrincipal(int pos){
		return menuPrincipal.get(pos);
	}

	public static MenuPrincipal getMPrincipalById(int id){
		return h_menuPrincipal.get(id);
	}

	public static void limpaMenu(){
		menuPrincipal = new ArrayList<MenuPrincipal>();
	}
}
