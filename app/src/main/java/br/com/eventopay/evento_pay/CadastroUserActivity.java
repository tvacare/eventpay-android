package br.com.eventopay.evento_pay;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONStringer;

import br.com.eventopay.evento_pay.rest.BaseEndpoint;

public class CadastroUserActivity extends AppCompatActivity {

    private EditText usuario, senha, senhaConf, conta, cpf, fone;
    private RadioButton masculino, feminino;
    private RadioGroup sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        usuario = (EditText) findViewById(R.id.txtUsuario);
        senha = (EditText) findViewById(R.id.txtSenha);
        senhaConf = (EditText) findViewById(R.id.txtSenhaConf);
        conta =(EditText) findViewById(R.id.txtConta);
        cpf = (EditText) findViewById(R.id.txtCpf);
        fone = (EditText) findViewById(R.id.txtFOne);
        masculino = (RadioButton) findViewById(R.id.rbMasc);
        feminino = (RadioButton) findViewById(R.id.rbFem);
        sexo = (RadioGroup) findViewById(R.id.rdGroup);
    }

    public void cadastrar (View view){
        BaseEndpoint dao = new BaseEndpoint();
        try {
            JSONStringer json = new JSONStringer();
            json.object();
            json.key("Nome").value(usuario.getText().toString());
            json.endObject();

            Context context;
            context = CadastroUserActivity.this;
            String endpoint = "api/usuario";

            dao.cadastrar(json, endpoint, CadastroUserActivity.this );
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
