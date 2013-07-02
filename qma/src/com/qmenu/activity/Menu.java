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
import android.widget.ListView;
import android.widget.TextView;

import com.qmenu.R;
import com.qmenu.control.MenuPrincipalProvider;
import com.qmenu.control.PedidoProvider;
import com.qmenu.util.Util;

public class Menu extends ListActivity
{    
	private MenuItemAdapter m_adapter;
	private com.qmenu.model.MenuPrincipal mprincipal;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuitem);
		Util.carregaTitulo(this);
        Button btListaPedido = (Button) findViewById(R.id.btListaPedido);
        btListaPedido.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		startActivityForResult(new Intent(Menu.this, Pedido.class), 0);
        	}
        });
        Button btSelecionaItemC = (Button) findViewById(R.id.btSelecionaItemC);
        btSelecionaItemC.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
                com.qmenu.model.Pedido pedido = new com.qmenu.model.Pedido();
        		for(com.qmenu.model.Menu o:mprincipal.getMenu())
        			if(o.isSelecionado())
        				pedido.addItem(o);
        		if(pedido.getQtdeItem() != mprincipal.getQtdeItem())
        			Util.alert(Menu.this, getString(R.string.strQtdeItemSelIncorreto).replaceAll("##S", "" + pedido.getQtdeItem()).replaceAll("##E", "" + mprincipal.getQtdeItem()));
        		else
        			abrePedido(pedido);
        	}
        });
        TextView txSelItem = (TextView)findViewById(R.id.txSelItem);
        mprincipal = MenuPrincipalProvider.getMPrincipal(getIntent().getExtras().getInt("posMenuPrincipal"));
        if( mprincipal.getQtdeItem() == 1)
        	btSelecionaItemC.setVisibility(View.GONE);
        else
        	txSelItem.setText(R.string.strMsgSelItemMultiplo);
        	
		this.m_adapter = new MenuItemAdapter(this, R.layout.rowitem, mprincipal.getMenu(), mprincipal.getQtdeItem());
		setListAdapter(this.m_adapter);
	}
	
	public void onConfigurationChanged(Configuration newConfig) {  
		super.onConfigurationChanged(newConfig);  
	}

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        com.qmenu.model.Pedido pedido = new com.qmenu.model.Pedido();
        pedido.addItem((com.qmenu.model.Menu)this.getListAdapter().getItem(position));
		abrePedido(pedido);
    }

	private void abrePedido(com.qmenu.model.Pedido pedido) {
		Intent i = null;
		if(pedido.getQtdeItem()==1)
			i = new Intent(this, EfetuaPedido.class);
		else
			i = new Intent(this, EfetuaPedidoMItem.class);
		pedido.setMprincipalIni(mprincipal);
		pedido.setUsuario(Util.leSessao(this, "usuario"));
		pedido.setSituacao("P");
		PedidoProvider.setPedidoAtual(pedido, true);
		startActivityForResult(i, 0);
	}
	
	private class MenuItemAdapter extends ArrayAdapter<com.qmenu.model.Menu> {
		private ArrayList<com.qmenu.model.Menu> item;
		private int qtdeItem;
		public MenuItemAdapter(Context context, int textViewResourceId,  ArrayList<com.qmenu.model.Menu> item, int qtdeItem) {
			super(context, textViewResourceId, item);
			this.item = item;
			this.qtdeItem = qtdeItem;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.rowitem, null);
			}
			Util.formataRow(position, v);
			com.qmenu.model.Menu o = item.get(position);
			if (o != null) {
				o.setSelecionado(false);
				TextView txNome = (TextView) v.findViewById(R.id.nome);
				TextView txPreco = (TextView) v.findViewById(R.id.preco);
				TextView txDescricao = (TextView) v.findViewById(R.id.descricao);
				CheckBox chItem = (CheckBox)v.findViewById(R.id.chItem);
				chItem.setTag(o);
				if(qtdeItem == 1)
					chItem.setVisibility(View.GONE);
				chItem.setOnClickListener( new View.OnClickListener() {  
					public void onClick(View v) {  
						CheckBox cb = (CheckBox) v ;  
						com.qmenu.model.Menu o = (com.qmenu.model.Menu) cb.getTag();
						o.setSelecionado(cb.isChecked());
					}  
				});  
				txNome.setText(o.getNome());
				txDescricao.setText(o.getDescricao());
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
