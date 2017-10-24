/**
 * Created by Bruno on 27/09/2017.
 */

package br.com.eventopay.evento_pay.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "usuario")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    private String nome;
    private double saldo;
    private String senha;
    private String sexo;
    private String cpf;
    private String celular;

    public Usuario(String nome, String senha, String sexo, String cpf, String celular, Double saldo) {
        this.nome = nome;
        this.senha = senha;
        this.sexo = sexo;
        this.cpf = cpf;
        this.celular = celular;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return " nome: " + nome + " saldo:" + saldo;
    }
}