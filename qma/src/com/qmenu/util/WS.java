package com.qmenu.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.qmenu.R;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class WS extends AsyncTask<String, String, String> {
    private AsyncTaskCompleteListener<String> callback;
    private ProgressDialog progress;
//    private static String url = "http://10.0.1.3:8090/qmw/trocadados/";
//    private static String url = "http://192.168.0.5:8090/qmw/trocadados/";
    private static String url = "http://www.qmenu.com.br:8080/qmw/interface/";
    private static String chave = "823742jnkjdshfsa[sdf'sasd[]adf]084ASFF";
    private String action;
    private Context ctx;
    private int transacao;
    private static boolean executando = false;
    private List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
    private JSONArray json = null;


    public WS(String action, JSONArray json, Context ctx, AsyncTaskCompleteListener<String> cb, int transacao){
        this.ctx = ctx;
        this.callback = cb;
        this.action = action;
        this.transacao = transacao;
        this.json = json;
        addCampo("estab",Util.leSessao(ctx, "estab"));
    }

    public WS(String action, Context ctx, AsyncTaskCompleteListener<String> cb, int transacao){
        this.ctx = ctx;
        this.callback = cb;
        this.action = action;
        this.transacao = transacao;
        addCampo("estab",Util.leSessao(ctx, "estab"));
    }


    protected void onPreExecute() {
        if(executando == false){
            progress = ProgressDialog.show(ctx, null, ctx.getString(R.string.strMsgObtendoDados), true);
            if(transacao == 0)
                transacao = Numero.getInt(Util.leSessao(ctx, "transacao")) + 1;
        }else
            Log.e("qmenu", "onPreExecute pela segunda vez");
    }

    protected String doInBackground(String... paramss) {
        if(executando == false){
            executando = true;
            return processa(true, action);
        }else{
            Log.e("qmenu", "doInBackground pela segunda vez");
            return "exe";
        }
    }

    public void addCampo(String campo, String valor){
        nameValuePairs.add(new BasicNameValuePair(campo, valor));
    }

    public String processa(boolean primeiraChamada, String action){
        String result = "";
        try {
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
            HttpConnectionParams.setSoTimeout(httpParams, 10000);
            DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost httpPost = new HttpPost(url + action + "?chave=" + chave + "&transacao=" + transacao + "&dispositivo=" + Util.leSessao(ctx, "dispositivo"));
            if(json !=null){
                httpPost.setEntity(new ByteArrayEntity(json.toString().getBytes("UTF8")));
                httpPost.setHeader("json", json.toString());
            }else
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = null;
//            try{
            httpResponse = httpClient.execute(httpPost);
//            }catch (ConnectTimeoutException e){
//                Log.e("qmenu", "Network timeout reached!");
//            }
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null)
                result = Util.convertStreamToString(entity.getContent());
        }catch (Exception e) {
            if(primeiraChamada){
                Log.e("qmenu", "processa segunda chamada");
                processa(false, action);
            }else{
                result = "-2";
            }
        }
        return result;
    }

    protected void onPostExecute(String retorno) {
        if(executando && progress !=null && progress.isShowing()){
            progress.dismiss();
            if(!retorno.equals("-2"))
                Util.gravaSessao(ctx, "transacao", "" + transacao);
            if(retorno.indexOf("##ERRO##")>-1)
                Toast.makeText(ctx, R.string.strErroDados, Toast.LENGTH_LONG).show();
            else if(retorno.equals("exe"))
                Log.e("qmenu", "onPostExecute pela segunda vez");

            executando = false;
            callback.onTaskComplete(action, transacao, retorno);
        }
    }
}