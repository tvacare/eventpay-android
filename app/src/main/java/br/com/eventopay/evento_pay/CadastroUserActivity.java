package br.com.eventopay.evento_pay;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

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
        BaseDAO dao = new BaseDAO();
        try {
            JSONStringer json = new JSONStringer();
            json.object();
            json.key("usuario").value(usuario.getText().toString());
            json.endObject();

            Context context;
            context = CadastroUserActivity.this;
            String url = "http://localhost:59721/api/usuario";

            dao.Cadastrar(json, context, url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listar (View view){
        BaseDAO dao = new BaseDAO();
        String url = "http://localhost:59721/api/usuario";
        try {
            JSONArray jsonArray = dao.Listar(url);

            Toast.makeText(CadastroUserActivity.this,jsonArray.toString(),Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
