package br.com.eventopay.evento_pay;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseDAO {
    public void Cadastrar(JSONStringer json, Context c, String url){
        CadastroTask task = new CadastroTask();
        task.setContext(c);
        task.setUrlIn(url);
        task.execute(json);
    }

    public JSONArray Listar(String url){
        ListaTask task = new ListaTask();
        task.setUrlIn(url);
        task.execute();
        return task.getJsonOut();
    }

    private class CadastroTask extends AsyncTask<JSONStringer,Void,Integer> {

        private Context context;
        private String urlIn;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public String getUrlIn() {
            return urlIn;
        }

        public void setUrlIn(String urlIn) {
            this.urlIn = urlIn;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            //201 - HTTP code CREATED
            if (integer == 201){
               // Toast.makeText(context,"Sucesso!",Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(context,"Erro",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Integer doInBackground(JSONStringer... json) {
            try {
                URL url = new URL(urlIn);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json");

                //Envia para o servidor
                OutputStreamWriter stream = new OutputStreamWriter(connection.getOutputStream());
                stream.write(json.toString());
                stream.close();

                return connection.getResponseCode();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class ListaTask extends AsyncTask<Void, Void, String> {

        private ProgressDialog progressDialog;
        private String urlIn;
        private JSONArray jsonOut;

        public String getUrlIn() {
            return urlIn;
        }

        public void setUrlIn(String urlIn) {
            this.urlIn = urlIn;
        }

        public JSONArray getJsonOut() {
            return jsonOut;
        }

        public void setJsonOut(JSONArray jsonOut) {
            this.jsonOut = jsonOut;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null){
                //Ler o JSON Array
                try {
                    JSONArray jsonOut = new JSONArray(s);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{

            }
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(urlIn);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept","application/json");

                if (connection.getResponseCode() == 200){
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String linha;
                    StringBuilder builder = new StringBuilder();
                    while ((linha = reader.readLine()) != null){
                        builder.append(linha);
                    }
                    connection.disconnect();
                    return builder.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
