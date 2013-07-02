package com.qmenu.activity;


import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.qmenu.R;
import com.qmenu.control.AdicionaisProvider;
import com.qmenu.control.PedidoProvider;
import com.qmenu.model.Adicionais;
import com.qmenu.model.Menu;
import com.qmenu.model.Pedido;
import com.qmenu.util.Util;

public class ListaAdicionais extends ListActivity 
{    
	private MenuItemAdapter m_adapter;
	private ArrayList<Adicionais> l_adicionais;
	private Pedido pedido;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaadicionais);
		Util.carregaTitulo(this);
		pedido = PedidoProvider.getPedidoAtual();
        l_adicionais = AdicionaisProvider.getAdicionais(pedido.getItemSelecionado().getGrupoAdicionaisId());
        Button btOK = (Button) findViewById(R.id.btOK);
        btOK.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		pedido.limpaAdicionais();
        		for(Adicionais o:l_adicionais)
        			if(o.isSelecionado()){
        				pedido.addItemadd(o, true);
        				o.setSelecionado(false);
        			}
	    		setResult(RESULT_OK, new Intent());
	    		finish();	            		        		
        	}
        });
        if(!pedido.getSituacao().equals("P")){
        	TextView txSelItem = (TextView)findViewById(R.id.txSelItem);
        	txSelItem.setVisibility(View.GONE);
        	btOK.setEnabled(false);
        	this.m_adapter = new MenuItemAdapter(this, R.layout.rowitem, pedido.getL_adicionais());
        }else
        	this.m_adapter = new MenuItemAdapter(this, R.layout.rowitem, l_adicionais);
		setListAdapter(this.m_adapter);
	}
	
	public void onConfigurationChanged(Configuration newConfig) {  
		super.onConfigurationChanged(newConfig);  
	}

	private class MenuItemAdapter extends ArrayAdapter<Adicionais> {
		private ArrayList<Adicionais> adicionais;
		public MenuItemAdapter(Context context, int textViewResourceId,  ArrayList<Adicionais> adicionais) {
			super(context, textViewResourceId, adicionais);
			this.adicionais = adicionais;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.rowitem, null);
			}
			Util.formataRow(position, v);
			Adicionais o = adicionais.get(position);
			if (o != null) {
				TextView txNome = (TextView) v.findViewById(R.id.nome);
				TextView txPreco = (TextView) v.findViewById(R.id.preco);
				TextView txDescricao = (TextView) v.findViewById(R.id.descricao);
				CheckBox chItem = (CheckBox)v.findViewById(R.id.chItem);
				if(!pedido.getSituacao().equals("P"))
					chItem.setVisibility(View.GONE);
				chItem.setChecked(pedido.isItemaddChecked(o));
				o.setSelecionado(chItem.isChecked());
				chItem.setTag(o);
				chItem.setOnClickListener( new View.OnClickListener() {  
					public void onClick(View v) {  
						CheckBox cb = (CheckBox) v ;  
						Menu o = (Menu) cb.getTag();
						o.setSelecionado(cb.isChecked());
					}  
				});  
				txNome.setText(o.getNome());
				txPreco.setText(getString(R.string.strMoeda) + " " + o.getPrecoF());
				if(o.getDescricao().equals(""))
					txDescricao.setVisibility(View.GONE);
				else
					txDescricao.setText(o.getDescricao());
			}
			return v;
		}
	}
}
