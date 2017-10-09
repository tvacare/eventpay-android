package br.com.eventopay.evento_pay.services;

import android.app.Activity;

import org.json.JSONStringer;

import br.com.eventopay.evento_pay.ExtratoFragment;

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

    public static void listar(String endpoint, ExtratoFragment activity) {
        ListaTask listar = new ListaTask(activity, URL+endpoint);
        listar.execute();
    }
}