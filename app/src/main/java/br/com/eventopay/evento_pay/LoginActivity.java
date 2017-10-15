package br.com.eventopay.evento_pay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.eventopay.evento_pay.rest.BaseEndpoint;

public class LoginActivity extends AppCompatActivity {

    private EditText edUsuario;
    private EditText edSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edSenha = (EditText) findViewById(R.id.edSenha);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        boolean connectedd = preferences.contains("conectado");
        if (connectedd){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
    }

    public void logar(View view) {

        String endpoint = "api/Login?Nome="+edUsuario.getText().toString()+"&Senha="+edSenha.getText().toString();

        //TODO: get user data from API
        BaseEndpoint.logar(LoginActivity.this, endpoint);

//        SharedPreferences sp = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//
//        editor.putString("user", "1");
//        editor.putString("password", edSenha.getText().toString());
//        editor.putBoolean("conectado", true);
//
//        editor.commit();

    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, CadastroUserActivity.class));
    }
}
