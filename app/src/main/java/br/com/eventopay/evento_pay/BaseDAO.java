package br.com.eventopay.evento_pay;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.List;

import br.com.eventopay.evento_pay.Model.UsuarioModel;

public class BaseDAO {
    public void Cadastrar(JSONStringer json, String url, Activity activity){
        CadastroTask task = new CadastroTask();
        task.setActivity(activity);
        task.setUrlIn(url);
        task.execute(json);
    }

    public void Listar(String url, Activity activity){
        ListaTask task = new ListaTask();
        task.setUrlIn(url);
        task.setActivity(activity);
        task.execute();
    }

    private class CadastroTask extends AsyncTask<JSONStringer,Void,Integer> {

        private ProgressDialog progressDialog;
        private Activity activity;
        private String urlIn;

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        public String getUrlIn() {
            return urlIn;
        }

        public void setUrlIn(String urlIn) {
            this.urlIn = urlIn;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(activity,"Aguarde", "Enviando para o servidor.");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            progressDialog.dismiss();
            //201 - HTTP code CREATED
            if (integer == 201){
                Toast.makeText(activity,"Sucesso!",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(activity,"Erro",Toast.LENGTH_LONG).show();
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
                stream.write(json[0].toString());
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
        private Activity activity;

        public String getUrlIn() {
            return urlIn;
        }

        public void setUrlIn(String urlIn) {
            this.urlIn = urlIn;
        }

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(activity,"Aguarde", "Enviando para o servidor.");
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            if (s != null){
                //Ler o JSON Array
                try {
                    JSONArray jsonArray = new JSONArray(s);

                    List<UsuarioModel> lista = new ArrayList<UsuarioModel>();

                    for (int i=0; i <jsonArray.length(); i++){
                        JSONObject item = (JSONObject) jsonArray.get(i);
                        int id = item.getInt("Id");
                        String nome = item.getString("Nome");
                        String senha = item.getString("Senha");
                        String sexo = item.getString("Sexo");
                        String cpf = item.getString("Cpf");
                        String celular = item.getString("Celular");
                        double saldo = 0;//item.getDouble("Saldo");
                        UsuarioModel item1 = new UsuarioModel(id,nome,senha,sexo,cpf,celular,saldo);
                        lista.add(item1);
                    }

                    //Exibir a lista de itens na tela
                    ListAdapter adapter = new ArrayAdapter(activity,android.R.layout.simple_list_item_1,lista);
                    //TextView txtView = (TextView) (activity).findViewById(R.id.txtUsuario);
                    
                    //a.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(activity,
                        "Erro",Toast.LENGTH_SHORT).show();
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
