package br.com.eventopay.evento_pay.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.eventopay.evento_pay.model.Evento;

/**
 * Created by Bruno on 15/10/2017.
 */

public class EventoSQLiteDao extends SQLiteOpenHelper {
    private static final String BANCO = "loja";
    private static final int VERSAO  = 1;

    public EventoSQLiteDao(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TB_EVENTO) (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, VALOR REAL, DESCRICAO TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS TB_EVENTO";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Evento evento){
        ContentValues valores = getContentValues(evento);
        getWritableDatabase().insert("TB_EVENTO",null,valores);
    }

    @NonNull
    private ContentValues getContentValues(Evento evento) {
        ContentValues valores = new ContentValues();
        valores.put("NOME",evento.getNome());
        valores.put("VALOR",evento.getValorSugerido());
        valores.put("DESCRICAO",evento.getDescricao());
        return valores;
    }

    public void update(Evento evento){
        ContentValues valores = getContentValues(evento);
        getWritableDatabase().update("TB_EVENTO", valores,
                "CODIGO = ?",new String[]{
                        String.valueOf(evento.getId())});
    }

    public void delete(int codigo){
        getWritableDatabase().delete("TB_EVENTO",
                "CODIGO = ?", new String[]{String.valueOf(codigo)});
    }

    public List<Evento> list(){
        Cursor cursor = getReadableDatabase()
                .query("TB_EVENTO",null,null,null,null,null,null);
        List<Evento> eventos = new ArrayList<>();
        while (cursor.moveToNext()){
            int codigo = cursor.getInt(0);
            String nome = cursor.getString(1);
            double valor = cursor.getDouble(2);
            String desc = cursor.getString(3);

            Evento evento = new Evento(0, 13, 0, 0, desc, nome, "");
            eventos.add(evento);
        }
        return eventos;
    }
}
