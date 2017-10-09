/**
 * Created by Bruno on 27/09/2017.
 */

package br.com.eventopay.evento_pay.model;

public class Usuario {

    private int id;
    private String nome;
    private String senha;
    private String sexo;
    private String cpf;
    private String celular;
    private double saldo;

    public Usuario(int id, String nome, String senha, String sexo, String cpf, String celular, double saldo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.sexo = sexo;
        this.cpf = cpf;
        this.celular = celular;
        this.saldo = saldo;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public int getId() {
        return this.id;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", sexo='" + sexo + '\'' +
                ", cpf='" + cpf + '\'' +
                ", celular='" + celular + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}