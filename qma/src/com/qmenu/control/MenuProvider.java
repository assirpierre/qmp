package com.qmenu.control;

import android.annotation.SuppressLint;
import com.qmenu.model.Menu;
import com.qmenu.model.MenuPrincipal;
import com.qmenu.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuProvider {
	
	private static ArrayList<MenuPrincipal> menu = new ArrayList<MenuPrincipal>();
	@SuppressLint("UseSparseArrays")
	private static HashMap<Integer, MenuPrincipal> h_menu = new HashMap<Integer, MenuPrincipal>();
	
	public static void atualiza(String jsonStr){
        try{
            JSONArray jsonArray = new JSONArray(jsonStr);
            if(jsonArray.length() > 0){
                menu = new ArrayList<MenuPrincipal>();
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
                        if(!j.getString("grupoAdicionais").equals("null"))
                            o.setGrupoAdicionaisId(j.getJSONObject ("grupoAdicionais").getInt("id"));
                        menuPrincipal.getMenu().add(o);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	public static ArrayList<MenuPrincipal> getMenu() {
		return menu;
	}
	
	public static MenuPrincipal getMPrincipal(Integer codigo){
		return h_menu.get(codigo);
	}

	public static void limpaMenu(){
		menu = new ArrayList<MenuPrincipal>();
	}
}
