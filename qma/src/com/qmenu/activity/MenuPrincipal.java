package com.qmenu.activity;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.qmenu.R;
import com.qmenu.control.MenuPrincipalProvider;
import com.qmenu.control.MesaProvider;
import com.qmenu.util.AsyncTaskCompleteListener;
import com.qmenu.util.Util;
import com.qmenu.util.WS;

import java.util.ArrayList;

public class MenuPrincipal extends ListActivity implements AsyncTaskCompleteListener<String>
{    
	private MenuItemAdapter m_adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuprincipal);
		Util.carregaTitulo(this);
        Button btListaPedido = (Button) findViewById(R.id.btListaPedido);
        btListaPedido.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		startActivityForResult(new Intent(MenuPrincipal.this, Pedido.class), 0);
        	}
        });

        Button btFecharMesa = (Button) findViewById(R.id.btFecharMesa);
        btFecharMesa.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
        	    builder.setMessage(getString(R.string.strConfirmFechaMesa));
        	    builder.setPositiveButton(R.string.strBtAlertOK, new DialogInterface.OnClickListener() {
        	    	public void onClick(DialogInterface dialog, int id) {
                    	getWSFechaMesa(0).execute();	    		        
        	    	}
        	    });
        	    builder.setNegativeButton(R.string.strBtAlertCancela, new DialogInterface.OnClickListener() {
        	    	public void onClick(DialogInterface dialog, int id) {
        	    	}
        	    });
        	    builder.show();           		
        	}
        });
		this.m_adapter = new MenuItemAdapter(this, R.layout.rowmenuprincipal, MenuPrincipalProvider.getMenuPrincipal());
		setListAdapter(this.m_adapter);
	}

	public void onConfigurationChanged(Configuration newConfig) {  
		super.onConfigurationChanged(newConfig);  
	}

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, Menu.class);
		Bundle bundle = new Bundle();
		bundle.putInt("posMenuPrincipal", position);
		i.putExtras(bundle);
		startActivityForResult(i, 0);            
    }
	
    private WS getWSFechaMesa(int transacao){
    	WS ws = new WS("fechamesa", this, this, transacao);
    	ws.addCampo("mesa", Util.leSessao(this, "mesa"));
    	return ws;
    }
    
	private class MenuItemAdapter extends ArrayAdapter<com.qmenu.model.MenuPrincipal> {
		private  ArrayList<com.qmenu.model.MenuPrincipal> o;
		public MenuItemAdapter(Context context, int textViewResourceId,  ArrayList<com.qmenu.model.MenuPrincipal> o) {
			super(context, textViewResourceId, o);
			this.o = o;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.rowmenuprincipal, null);
			}
			Util.formataRow(position, v);
			com.qmenu.model.MenuPrincipal ol = o.get(position);
			TextView txNome = (TextView) v.findViewById(R.id.nome);
			txNome.setText(ol.getNome());
			return v;
		}
	}
	
    public void onTaskComplete(String action, int transacao, String retorno) {
    	if(retorno.equals("-2"))
   			Util.semConexao(this, getWSFechaMesa(transacao));
    	else if(retorno.indexOf("##pedidopendente##")>-1){
    		Util.alert(this, getString(R.string.strFechaMesaInvPedido));
    	}else if(retorno.indexOf("##situacaodisponivel##")>-1){
    		Util.alert(this, getString(R.string.strFechaMesaInvSituacao));
    	}else if(retorno.indexOf("##ERRO##")==-1){
            MesaProvider.atualiza(retorno);
    		setResult(RESULT_OK, new Intent());
    		finish();
	    }
    }
}
