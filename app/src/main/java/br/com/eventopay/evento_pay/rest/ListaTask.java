package br.com.eventopay.evento_pay.rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

import br.com.eventopay.evento_pay.ExtratoAdapter;
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
    private  ExtratoAdapter adapter;

    ListaTask(Context context, String endpoint, View view, String layout, ExtratoAdapter adapter) {
        this.context = context;
        this.endpoint = endpoint;
        this.view = view;
        this.layout = layout;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Aguarde", "Carregando dados.");
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
                            int id_usuario = item.getInt("Id_Usuario");
                            String nomeEvento = item.getString("NomeEvento");
                            int id_evento = item.getInt("Id_Evento");
                            double valor = item.getDouble("Valor");
                            Transacao item1 = new Transacao(id_usuario,nomeEvento,id_evento,valor);
                            listaExtrato.add(item1);
                        }

                        //Exibir a lista de itens na tela
//                        ListAdapter adapterExtrato = new ArrayAdapter(context,android.R.layout.simple_list_item_1,listaExtrato);
//                        ListView listViewExtrato = (ListView) view.findViewById(R.id.extratoList);
//                        listViewExtrato.setAdapter(adapterExtrato);

                        adapter.addList(listaExtrato);
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
                    case "spinnerLayout":
                        List<Evento> listaSpinnerEvento = new ArrayList<Evento>();

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
                            listaSpinnerEvento.add(item1);
                        }

                        //Exibir a lista de itens no spinner
                        Spinner mySpinner = (Spinner) view.findViewById(R.id.spinnerEventos);
                        mySpinner.setAdapter(new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listaSpinnerEvento));
                        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> arg0,
                                                       View arg1, int position, long arg3) {
                                // TODO Auto-generated method stub
                                // Locate the textviews in activity_main.xml
                                TextView nomeEvetno = (TextView) view.findViewById(R.id.txtNomeEvent);
                                TextView descEvento = (TextView) view.findViewById(R.id.txtDesc);
                                TextView valorEvento = (TextView) view.findViewById(R.id.txtxValorSuge);
                                Spinner mySpinner = (Spinner) view.findViewById(R.id.spinnerEventos);
                                Evento dados2 = (Evento) mySpinner.getAdapter().getItem(position);


                                // Set the text followed by the position
                                double valDados = dados2.getValorSugerido();
                                nomeEvetno.setText(dados2.getNome().toString());
                                descEvento.setText(dados2.getDescricao().toString());
                                valorEvento.setText(String.valueOf(valDados));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                                // TODO Auto-generated method stub
                            }
                        });

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
