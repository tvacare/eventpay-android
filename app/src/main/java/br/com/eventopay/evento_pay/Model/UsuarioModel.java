package br.com.eventopay.evento_pay.Model;

/**
 * Created by Bruno on 27/09/2017.
 */

public class UsuarioModel {
    private int id;
    private String nome;
    private String senha;
    private String sexo;
    private String cpf;
    private String celular;
    private double saldo;

    public UsuarioModel(int id, String nome, String senha, String sexo, String cpf, String celular, double saldo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.sexo = sexo;
        this.cpf = cpf;
        this.celular = celular;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
