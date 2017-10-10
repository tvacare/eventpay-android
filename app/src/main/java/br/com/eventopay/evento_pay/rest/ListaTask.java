package br.com.eventopay.evento_pay.rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.eventopay.evento_pay.R;
import br.com.eventopay.evento_pay.model.Usuario;

/**
 * Created by kiddo on 10/8/17.
 */

class ListaTask extends AsyncTask<Void, Void, String> {

    private ProgressDialog progressDialog;
    private String endpoint;
    private Context context;
    private View view;

    ListaTask(Context context, String endpoint, View view) {
        this.context = context;
        this.endpoint = endpoint;
        this.view = view;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Aguarde", "Enviando para o servidor.");
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        if (s != null){
            //Ler o JSON Array
            try {
                JSONArray jsonArray = new JSONArray(s);

                List<Usuario> lista = new ArrayList<Usuario>();

                for (int i=0; i <jsonArray.length(); i++){
                    JSONObject item = (JSONObject) jsonArray.get(i);
                    int id = item.getInt("Id");
                    String nome = item.getString("Nome");
                    String senha = item.getString("Senha");
                    String sexo = item.getString("Sexo");
                    String cpf = item.getString("Cpf");
                    String celular = item.getString("Celular");
                    double saldo = 0;//item.getDouble("Saldo");
                    Usuario item1 = new Usuario(id,nome,senha,sexo,cpf,celular,saldo);
                    lista.add(item1);
                }

                //Exibir a lista de itens na tela
                ListAdapter adapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,lista);
                //TextView txtView = (TextView) (activity).findViewById(R.id.txtUsuario);
                ListView listView = (ListView) view.findViewById(R.id.extratoList);
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(context, "Erro",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(endpoint);
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
