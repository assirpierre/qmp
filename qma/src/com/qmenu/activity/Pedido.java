package com.qmenu.activity;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.qmenu.R;
import com.qmenu.control.EstabProvider;
import com.qmenu.control.MesaProvider;
import com.qmenu.control.PedidoProvider;
import com.qmenu.util.AsyncTaskCompleteListener;
import com.qmenu.util.Util;
import com.qmenu.util.WS;

import java.util.ArrayList;

public class Pedido extends ListActivity implements AsyncTaskCompleteListener<String>
{    
	private MenuItemAdapter m_adapter;
	private Button btConfPedido;
	private TextView txTotal;
	private boolean pendente;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listapedido);
		Util.carregaTitulo(this);
		btConfPedido = (Button) findViewById(R.id.btConfPedido);
    	btConfPedido.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
        		AlertDialog.Builder builder = new AlertDialog.Builder(Pedido.this);
        	    builder.setMessage(getString(R.string.strConfirmPedido));
        	    builder.setPositiveButton(R.string.strBtAlertOK, new DialogInterface.OnClickListener() {
        	    	public void onClick(DialogInterface dialog, int id) {
		        	getWSConfirmaPedido(0).execute();
        	    	}
        	    });
        	    builder.setNegativeButton(R.string.strBtAlertCancela, new DialogInterface.OnClickListener() {
        	    	public void onClick(DialogInterface dialog, int id) {
        	    	}
        	    });
        	    builder.show();    			
    		}
    	});
		txTotal = (TextView)findViewById(R.id.txTotal);
		TextView txMesa = (TextView)findViewById(R.id.txMesa);
		txMesa.setText(Util.getStringResourceByName(this, "str" + EstabProvider.getSistemaTrabalho()) + " " + Util.leSessao(this, "mesaNumero"));
        carrega(false);
	}

	public void onConfigurationChanged(Configuration newConfig) {  
		super.onConfigurationChanged(newConfig);  
	}

	private void carrega(boolean todos) {
		if(todos || PedidoProvider.getPedidoPendente().size() == 0){
        	getWSListaPedido(todos?"listatodospedidos":"listaultimopedido", 0).execute();
        	btConfPedido.setVisibility(View.INVISIBLE);			        	
        	pendente = false;
        }else{
        	pendente = true;
    		m_adapter = new MenuItemAdapter(this, R.layout.rowlistapedido, PedidoProvider.getPedidoPendente());
    		setListAdapter(m_adapter);
        	btConfPedido.setVisibility(View.VISIBLE);
        	atualizaTotal();
        }
	}
	
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        com.qmenu.model.Pedido pedido = (com.qmenu.model.Pedido)this.getListAdapter().getItem(position);
		Intent i = null;
		if(pedido.getQtdeItem()==1)
			i = new Intent(this, EfetuaPedido.class);
		else
			i = new Intent(this, EfetuaPedidoMItem.class);
		PedidoProvider.setPedidoAtual(pedido, false);
		startActivityForResult(i, 0);            
    }
	
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rbUltimo:
                if (checked)
                    carrega(false);                   
                break;
            case R.id.rbTodos:
                if (checked)
                    carrega(true);
                break;
        }
    }
    
	private WS getWSListaPedido(String action, int transacao){
    	WS ws = new WS(action, this, this, transacao);
    	ws.addCampo("mesa", Util.leSessao(this, "mesa"));
    	return ws;
	}

	private WS getWSConfirmaPedido(int transacao){
    	return new WS("gravapedido", PedidoProvider.getPedidosPendentes(this), this, this, transacao);
	}
	
	private class MenuItemAdapter extends ArrayAdapter<com.qmenu.model.Pedido> {
		private ArrayList<com.qmenu.model.Pedido> pedido;

		public MenuItemAdapter(Context context, int textViewResourceId,  ArrayList<com.qmenu.model.Pedido> o) {
			super(context, textViewResourceId, o);
			this.pedido = o;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.rowlistapedido, null);
			}
			Util.formataRow(position, v);
			com.qmenu.model.Pedido o = pedido.get(position);
			if (o != null) {
				TextView txItem = (TextView) v.findViewById(R.id.txItem);
				TextView txSituacao = (TextView) v.findViewById(R.id.txSituacao);
				TextView txQtde = (TextView) v.findViewById(R.id.txQtde);
				TextView txTotal = (TextView) v.findViewById(R.id.txTotal);
				txItem.setText(o.getHora() + " - " + o.getItem());
				Button btDelete = (Button) v.findViewById(R.id.btDel);
				if(o.getSituacao().equals("P"))
					txSituacao.setText(getString(R.string.strSituacaoPedidoP));
				else {
					btDelete.setVisibility(View.GONE);
					if(o.getSituacao().equals("C"))
						txSituacao.setText(getString(R.string.strSituacaoPedidoC));
					else if(o.getSituacao().equals("A"))
						txSituacao.setText(getString(R.string.strSituacaoPedidoA));
				}
				txSituacao.setText(txSituacao.getText() + " - " + o.getUsuario());
				txQtde.setText(getString(R.string.strQtde) + " " + o.getQtde());
				txTotal.setText(getString(R.string.strMoeda) + " " + o.getTotalF());
		        btDelete.setTag(o);
		        btDelete.setOnClickListener(new View.OnClickListener() {
		        	public void onClick(View v) {
		        		confirmaDelecao((com.qmenu.model.Pedido)v.getTag());
		        	}
		        });
			}
			return v;
		}
			    
		private void confirmaDelecao(final com.qmenu.model.Pedido pedido) {
    		if(pedido.getSituacao().equals("P")){
        		AlertDialog.Builder builder = new AlertDialog.Builder(Pedido.this);
        	    builder.setMessage(getString(R.string.strConfirmDelPedido) + " " + pedido.getDescricaoItens());
        	    builder.setPositiveButton(R.string.strBtAlertOK, new DialogInterface.OnClickListener() {
        	    	public void onClick(DialogInterface dialog, int id) {
        	    		PedidoProvider.getPedidoPendente().remove(pedido);
        	    		m_adapter.remove(pedido);
		        		m_adapter.notifyDataSetChanged();
        	    	}
        	    });
        	    builder.setNegativeButton(R.string.strBtAlertCancela, new DialogInterface.OnClickListener() {
        	    	public void onClick(DialogInterface dialog, int id) {
        	    	}
        	    });
        	    builder.show();
    		}else
    			Toast.makeText(Pedido.this, R.string.strPedidoAlteracaoNegada, Toast.LENGTH_LONG).show();
		}		
	}
	
    public void onTaskComplete(String action, int transacao, String retorno) {
    	if(retorno.equals("-2"))
    		if(action.equals("gravapedido"))
    			Util.semConexao(this, getWSConfirmaPedido(transacao));
    		else
    			Util.semConexao(this, getWSListaPedido(action, transacao));
    	else if(retorno.indexOf("##ERRO##")==-1){
    		if(action.equals("gravapedido")){
    			if(retorno.equals("")){
    				Util.alert(this, getString(R.string.strSemConexao));
    			}else{
	    			PedidoProvider.limpaPendente();
	    			MesaProvider.atualiza(retorno);
	    			Toast.makeText(Pedido.this, R.string.strPedidoConfirmado, Toast.LENGTH_LONG).show();
					Intent i = new Intent(Pedido.this, Mesa.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivityForResult(i, 0);
    			}
    		}else{
    			PedidoProvider.atualiza(retorno);
    			m_adapter = new MenuItemAdapter(this, R.layout.rowlistapedido, PedidoProvider.getPedido());
    			setListAdapter(m_adapter);		
    			atualizaTotal();
    		}
    	}
    }
    
    private void atualizaTotal(){
    	txTotal.setText(getString(R.string.strTotalPedido) + " " + (pendente?PedidoProvider.getTotalPendente():PedidoProvider.getTotal()));
    }
    
	protected void onRestart(){
		super.onRestart();
		m_adapter.notifyDataSetChanged();
		atualizaTotal();
	}

}
