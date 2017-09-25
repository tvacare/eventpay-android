package br.com.eventopay.evento_pay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String usuarioIn = sp.getString("user", "");
        String senhaIn = sp.getString("password", "");
        if (usuarioIn.isEmpty() || senhaIn.isEmpty()){

        }else {
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
    }

    public void logar(View view){
        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("user", usuario);
        editor.putString("password",senha);

        editor.commit();

        //Navegar para a tela de arquivo
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void novoCadastro(View view){
        Intent intent = new Intent(this,CadastroUserActivity.class);
        startActivity(intent);
    }
}
