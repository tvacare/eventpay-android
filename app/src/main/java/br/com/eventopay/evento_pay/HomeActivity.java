package br.com.eventopay.evento_pay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONStringer;

import br.com.eventopay.evento_pay.model.Evento;
import br.com.eventopay.evento_pay.rest.BaseEndpoint;
import br.com.eventopay.evento_pay.sqlite.EventoSQLiteDao;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView profileImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new ExtratoFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (item.getItemId()) {
            case (R.id.nav_extrato):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new ExtratoFragment()).commit();
                break;
            case (R.id.nav_credito):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new CreditoFragment()).commit();
                break;
            case (R.id.nav_transferencia):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new TransacaoFragment()).commit();
                break;
            case (R.id.nav_eventos):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new EventoFragment()).commit();
                break;
            case (R.id.nav_perfil):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new EventoFragment()).commit();
                break;
            case (R.id.nav_meus_eventos):
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, new MeusEventosFragment()).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void comprarCredito (View view){
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String usuario = sp.getString("user", "");

        String endpoint = "/api/credito/?IdUsuario=12&Valor="+ seekBar.getProgress();
        BaseEndpoint.cadastrar(null, endpoint, HomeActivity.this);
    }

    public void transferir (View view){
        TextView valorTransacao = (TextView) findViewById(R.id.txtxValorSuge);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinnerEventos);
        Evento dados2 = (Evento) mySpinner.getAdapter().getItem(mySpinner.getSelectedItemPosition());
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String usuario = sp.getString("user", "");
        try {
            JSONStringer json = new JSONStringer();
            json.object();
            //Alterar Id !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
            json.key("Id_Usuario").value(12);
            json.key("Valor").value(valorTransacao.getText().toString());
            json.key("Id_Evento").value(dados2.getId());
            json.endObject();

            String endpoint = "/api/transacao";
            BaseEndpoint.cadastrar(json, endpoint, HomeActivity.this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void adicionarEvento(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new CriarEventoFragment()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void cadastrarEvento (View view){
        BaseEndpoint dao = new BaseEndpoint();
        EditText nomeEvento = (EditText) findViewById(R.id.txtNomeEvento);
        EditText descricao = (EditText) findViewById(R.id.txtDescricao);
        EditText valorSugerido = (EditText) findViewById(R.id.txtValorUnitario);
        EditText local = (EditText) findViewById(R.id.txtLocal);
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String usuario = sp.getString("user", "");
        try {
            JSONStringer json = new JSONStringer();
            json.object();
            json.key("Nome").value(nomeEvento.getText().toString());
            json.key("ValorSugerido").value(valorSugerido.getText().toString());
            json.key("Descricao").value(descricao.getText().toString());
            json.key("Local").value(local.getText().toString());
            //Alterar Id !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
            json.key("Id_adm").value(13);
            json.endObject();


            String endpoint = "api/evento";
            dao.cadastrar(json, endpoint, HomeActivity.this );

            Evento eventoSqlLite = new Evento(0, 13, 0, 0,descricao.getText().toString(), nomeEvento.getText().toString(), local.getText().toString());
            EventoSQLiteDao sqlDao = new EventoSQLiteDao(this);
            sqlDao.insert(eventoSqlLite);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new EventoFragment()).commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}