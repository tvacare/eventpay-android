/**
 * Created by Thiago on 10/8/17.
 */

package br.com.eventopay.evento_pay.rest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONStringer;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


class CadastroTask extends AsyncTask<JSONStringer,Void,Integer> {

    private ProgressDialog progressDialog;
    private Activity activity;
    private String endpoint;

    CadastroTask(Activity activity, String endpoint) {
        this.activity = activity;
        this.endpoint = endpoint;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(activity,"Aguarde", "Enviando para o servidor.");
    }

    @Override
    protected void onPostExecute(Integer integer) {

        progressDialog.dismiss();

        if (integer == 201){
            Toast.makeText(activity,"Sucesso!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(activity,"Erro",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected Integer doInBackground(JSONStringer... json) {
        try {
            URL uri = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");

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
