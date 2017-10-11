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
import br.com.eventopay.evento_pay.layout.CustomAdapter;
import br.com.eventopay.evento_pay.model.Evento;
import br.com.eventopay.evento_pay.model.Transacao;
import br.com.eventopay.evento_pay.model.Usuario;

/**
 * Created by kiddo on 10/8/17.
 */

class ListaTask extends AsyncTask<Void, Void, String> {

    private ProgressDialog progressDialog;
    private String endpoint;
    private Context context;
    private View view;
    private String layout;

    ListaTask(Context context, String endpoint, View view, String layout) {
        this.context = context;
        this.endpoint = endpoint;
        this.view = view;
        this.layout = layout;
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

/**
 * TODO: Thiago -> implementar - Nao remover
 * ->>>>>>>>>>>>>>>>>>>
 */
//                List<Usuario> lista = new ArrayList<Usuario>();
//
//                ListView listView = (ListView) view.findViewById(R.id.extratoList);
//
//                for (int i=0; i <jsonArray.length(); i++){
//                    JSONObject item = (JSONObject) jsonArray.get(i);
//
////                    int id = item.getInt("Id");
//                    String nome = item.getString("Nome");
////                    String senha = item.getString("Senha");
//                    String sexo = item.getString("Sexo");
////                    String cpf = item.getString("Cpf");
////                    String celular = item.getString("Celular");
////                    double saldo = 0;//item.getDouble("Saldo");
//                    Usuario usuario = new Usuario(nome);
//                    lista.add(usuario);
//
//                }
//
//                CustomAdapter customAdapter = new CustomAdapter(context, R.layout.custom_adapter, lista);
//
//                listView.setAdapter(customAdapter);
/**
 * <<<<<<<<<<<<<<<<<-
 */


                switch (layout) {
                    case "extratoLayout":
                        List<Transacao> listaExtrato = new ArrayList<Transacao>();

                        for (int i=0; i <jsonArray.length(); i++){
                            JSONObject item = (JSONObject) jsonArray.get(i);
                            int id = item.getInt("Id");
                            int id_usuario = item.getInt("id_Usuario");
                            String nomeEvento = item.getString("nomeEvento");
                            int id_evento = item.getInt("id_Evento");
                            double valor = item.getDouble("valor");
                            Transacao item1 = new Transacao(id_usuario,nomeEvento,id_evento,valor);
                            listaExtrato.add(item1);
                        }

                        //Exibir a lista de itens na tela
                        ListAdapter adapterExtrato = new ArrayAdapter(context,android.R.layout.simple_list_item_1,listaExtrato);
                        ListView listViewExtrato = (ListView) view.findViewById(R.id.extratoList);
                        listViewExtrato.setAdapter(adapterExtrato);
                        break;
                    case "usuarioLayout":

                        //Caso precise de lista de usuario:

                        List<Usuario> listaUser = new ArrayList<Usuario>();

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
                            listaUser.add(item1);
                        }

                        //Exibir a lista de itens na tela
                        ListAdapter adapterUser = new ArrayAdapter(context,android.R.layout.simple_list_item_1,listaUser);
                        //ListView listViewUser = (ListView) view.findViewById(R.id.userList);
                        //listViewUser.setAdapter(adapterUser);
                        break;
                    case "eventoLayout":
                        List<Evento> listaEvento = new ArrayList<Evento>();

                        for (int i=0; i <jsonArray.length(); i++){
                            JSONObject item = (JSONObject) jsonArray.get(i);
                            int id = item.getInt("Id");
                            int id_adm = item.getInt("Id_adm");
                            double valorTotal = item.getDouble("ValorTotal");
                            double valorSugerido = item.getDouble("ValorSugerido");
                            String descricao = item.getString("Descricao");
                            String nome = item.getString("Nome");
                            String local = item.getString("Local");
                            Evento item1 = new Evento(id, id_adm, valorTotal,valorSugerido, descricao, nome, local);
                            listaEvento.add(item1);
                        }

                        //Exibir a lista de itens na tela
                        ListAdapter adapterEvento = new ArrayAdapter(context,android.R.layout.simple_list_item_1,listaEvento);
                        ListView listViewEvento = (ListView) view.findViewById(R.id.eventList);
                        listViewEvento.setAdapter(adapterEvento);
                        break;
                    default:
                        System.out.println("Este não é um dia válido!");
                }

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
