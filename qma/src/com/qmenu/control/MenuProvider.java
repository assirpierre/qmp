package com.qmenu.control;

import android.annotation.SuppressLint;
import android.util.Log;
import com.qmenu.model.Menu;
import com.qmenu.model.MenuPrincipal;
import com.qmenu.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuProvider {
	
	private static ArrayList<Menu> menu = new ArrayList<Menu>();
	@SuppressLint("UseSparseArrays")
	private static HashMap<Integer, Menu> h_menu = new HashMap<Integer, Menu>();
	
	public static void atualiza(String jsonStr){
        try{
            JSONArray jsonArray = new JSONArray(jsonStr);
            if(jsonArray.length() > 0){
                menu = new ArrayList<Menu>();
                h_menu = new HashMap<Integer, Menu>();
                MenuPrincipal menuPrincipal = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject j = jsonArray.getJSONObject(i);
                    if(j.getString("class").equals("qmw.Menu")){
                        JSONObject jMP = j.getJSONObject ("menuPrincipal");
                        if(menuPrincipal == null || menuPrincipal.getId() != jMP.getInt("id"))
                            menuPrincipal = MenuPrincipalProvider.getMPrincipalById(jMP.getInt("id"));
                        Menu o = new Menu();
                        o.setId(j.getInt("id"));
                        o.setNome(Util.tostr(j.getString("nome")));
                        o.setDescricao(Util.tostr(j.getString("descricao")));
                        o.setPreco(j.getDouble("preco"));
                        if(!j.getString("grupoAdicionais").equals("null")){
                            o.setGrupoAdicionaisId(j.getJSONObject ("grupoAdicionais").getInt("id"));
                        }
                        menuPrincipal.getMenu().add(o);
                        menu.add(o);
                        h_menu.put(j.getInt("id"), o);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public static ArrayList<Menu> getMenu() {
		return menu;
	}
	
	public static Menu getMenuById(int id){
		return h_menu.get(id);
	}

	public static void limpaMenu(){
		menu = new ArrayList<Menu>();
	}
}
