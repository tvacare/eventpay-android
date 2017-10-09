package br.com.eventopay.evento_pay;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.eventopay.evento_pay.model.Usuario;
import br.com.eventopay.evento_pay.services.BaseEndpoint;

public class LoginActivity extends AppCompatActivity {

    private EditText edUsuario;
    private EditText edSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

//        SharedPreferences sp = getPreferences(MODE_PRIVATE);

//      Intent intent = new Intent(this, HomeActivity.class);
//      startActivity(intent);

        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edSenha = (EditText) findViewById(R.id.edSenha);
    }

    public void logar(View view) {

        String endpoint = "api/Login?Nome="+edUsuario.getText().toString()+"&Senha="+edSenha.getText().toString();

        //TODO: get user data from API
//        BaseEndpoint.logar(LoginActivity.this, endpoint);


//        SharedPreferences sp = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();

//        editor.putString("user", usuario);
//        editor.putString("password", senha);
//
//        editor.commit();


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void cadastrar(View view) {
        startActivity(new Intent(this, CadastroUserActivity.class));
    }
}
