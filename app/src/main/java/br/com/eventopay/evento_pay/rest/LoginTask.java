/**
 * Created by kiddo on 10/8/17.
 */

package br.com.eventopay.evento_pay.rest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.eventopay.evento_pay.R;
import br.com.eventopay.evento_pay.activities.HomeActivity;

class LoginTask extends AsyncTask<String, Void, String> {

    private Activity activity;

    LoginTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onPostExecute(String s) {

        if (s != null && !s.equalsIgnoreCase("null")) {
            try {
                JSONObject json = new JSONObject(s);
                int id = json.getInt("Id");
                String nome = json.getString("Nome");
                String senha = json.getString("Senha");
                double saldo = json.getDouble("Saldo");

                float d= ((float) saldo);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putInt("user", id);
                editor.putString("nome",nome );
                editor.putString("password",senha );
                editor.putBoolean("conectado", true);
                editor.putFloat("saldo", d);

                editor.apply();

                Intent intent = new Intent(activity, HomeActivity.class);
                activity.startActivity(intent);
                activity.finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(activity, R.string.invalidUser, Toast.LENGTH_SHORT).show();
        }
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