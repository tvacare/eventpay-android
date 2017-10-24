/**
 * Created by Thiago on 10/8/17.
 */

package br.com.eventopay.evento_pay.rest;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import org.json.JSONStringer;

import br.com.eventopay.evento_pay.adapters.ExtratoAdapter;

public class BaseEndpoint {

    private static final String URL = "http://api-eventpayment.azurewebsites.net/";

    public static void logar(Activity activity, String endpoint) {
        LoginTask login = new LoginTask(activity);
        login.execute(URL+endpoint);
    }

    public static void cadastrar(JSONStringer json, String endpoint, Activity activity) {
        CadastroTask cadastro = new CadastroTask(activity, URL+endpoint);
        cadastro.execute(json);
    }

    public static void listar(String endpoint, Context context, View view, String layout, ExtratoAdapter adapter) {
        ListarTask listar = new ListarTask(context, URL+endpoint, view, layout, adapter);
        listar.execute();
    }
}