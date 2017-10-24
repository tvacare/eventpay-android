/**
 * Created by Thiago on 10/8/17.
 */

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

import br.com.eventopay.evento_pay.adapters.ExtratoAdapter;
import br.com.eventopay.evento_pay.R;
import br.com.eventopay.evento_pay.model.Evento;
import br.com.eventopay.evento_pay.model.Transacao;
import br.com.eventopay.evento_pay.model.Usuario;


class ListarTask extends AsyncTask<Void, Void, String> {

    private ProgressDialog progressDialog;
    private String endpoint;
    private Context context;
    private View view;
    private String layout;
    private ExtratoAdapter adapter;

    ListarTask(Context context, String endpoint, View view, String layout, ExtratoAdapter adapter) {
        this.context = context;
        this.endpoint = endpoint;
        this.view = view;
        this.layout = layout;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, context.getString(R.string.wait), context.getString(R.string.loadingData));
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();

        if (s != null) {
            try {
                JSONArray jsonArray = new JSONArray(s);

                switch (layout) {
                    case "extractLayout":
                        addExtractList(jsonArray);
                        break;
                    case "usuarioLayout":
                        adduserList(jsonArray);
                        break;
                    case "eventLayout":
                        addEventList(jsonArray);
                        break;
                    case "spinnerLayout":
                        addSpinner(jsonArray);
                        break;
                    default:
                        Toast.makeText(context, R.string.invalidDay, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, R.string.err, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
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
                return builder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addSpinner(JSONArray jsonArray) throws JSONException {
        List<Evento> listaSpinnerEvent = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = (JSONObject) jsonArray.get(i);

            int id = item.getInt("Id");
            double valorTotal = item.getDouble("ValorTotal");
            double valorSugerido = item.getDouble("ValorSugerido");
            String descricao = item.getString("Descricao");
            String nome = item.getString("Nome");

            Evento evento = new Evento(id, valorTotal, valorSugerido, descricao, nome);

            listaSpinnerEvent.add(evento);
        }

        Spinner mySpinner = (Spinner) view.findViewById(R.id.spinnerEventos);
        mySpinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listaSpinnerEvent));
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                TextView nomeEvetno = (TextView) view.findViewById(R.id.txtNomeEvent);
                TextView descEvento = (TextView) view.findViewById(R.id.txtDesc);
                TextView valorEvento = (TextView) view.findViewById(R.id.txtxValorSuge);
                Spinner mySpinner = (Spinner) view.findViewById(R.id.spinnerEventos);

                Evento evento = (Evento) mySpinner.getAdapter().getItem(position);

                double valDados = evento.getValorSugerido();
                nomeEvetno.setText(evento.getNome());
                descEvento.setText(evento.getDescricao());
                valorEvento.setText(String.valueOf(valDados));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void addEventList(JSONArray jsonArray) throws JSONException {
        List<Evento> listaEvento = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = (JSONObject) jsonArray.get(i);

            int id = item.getInt("Id");
            double valorTotal = item.getDouble("ValorTotal");
            double valorSugerido = item.getDouble("ValorSugerido");
            String descricao = item.getString("Descricao");
            String nome = item.getString("Nome");

            Evento evento = new Evento(id, valorTotal, valorSugerido, descricao, nome);

            listaEvento.add(evento);
        }

        ListAdapter adapterEvento = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listaEvento);
        ListView listViewEvento = (ListView) view.findViewById(R.id.eventList);
        listViewEvento.setAdapter(adapterEvento);
    }

    private void adduserList(JSONArray jsonArray) throws JSONException {
        List<Usuario> listaUser = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = (JSONObject) jsonArray.get(i);

            String nome = item.getString("Nome");
            String senha = item.getString("Senha");
            String sexo = item.getString("Sexo");
            String cpf = item.getString("Cpf");
            String celular = item.getString("Celular");
            double saldo = 0;

            Usuario usuario = new Usuario(nome, senha, sexo, cpf, celular, saldo);

            listaUser.add(usuario);
        }
    }

    private void addExtractList(JSONArray jsonArray) throws JSONException {

        List<Transacao> listaExtract = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = (JSONObject) jsonArray.get(i);

            String nomeEvento = item.getString("NomeEvento");
            double valor = item.getDouble("Valor");

            Transacao transacao = new Transacao(nomeEvento, valor);
            listaExtract.add(transacao);
        }

        adapter.addList(listaExtract);
    }
}