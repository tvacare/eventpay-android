/**
 * Created by kiddo on 10/8/17.
 */

package br.com.eventopay.evento_pay.rest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.eventopay.evento_pay.HomeActivity;

class LoginTask extends AsyncTask<String, Void, String> {

    private ProgressDialog progressDialog;
    private Activity activity;

    LoginTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(activity, null, null);
    }

    @Override
    protected void onPostExecute(String s) {
        //progressDialog.dismiss();


//        if (s != null) {
            //Ler o JSON Array
//            try {
//                JSON jsonArray = new JSON(s);
//
//                List<Usuario> lista = new ArrayList<Usuario>();

//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject item = (JSONObject) jsonArray.get(i);
//                    int id = item.getInt("Id");
//                    String nome = item.getString("Nome");
//                    String senha = item.getString("Senha");
//                    String sexo = item.getString("Sexo");
//                    String cpf = item.getString("Cpf");
//                    String celular = item.getString("Celular");
//                    double saldo = 0;//item.getDouble("Saldo");
//                    Usuario item1 = new Usuario(id, nome, senha, sexo, cpf, celular, saldo);
//                    lista.add(item1);
//                }

                //Exibir a lista de itens na tela
//                ListAdapter adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, lista);
//                //TextView txtView = (TextView) (activity).findViewById(R.id.txtUsuario);
//                ListView listView = (ListView) (activity).findViewById(R.id.extratoList);
//                listView.setAdapter(adapter);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Toast.makeText(activity, "Erro", Toast.LENGTH_SHORT).show();
//        }

        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    protected String doInBackground(String... params) {
        String jsonResult = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String linha;
                StringBuilder builder = new StringBuilder();
                while ((linha = reader.readLine()) != null) {
                    builder.append(linha);
                }
                connection.disconnect();
                jsonResult = builder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}