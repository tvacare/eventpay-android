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

    public Usuario(String nome, double saldo) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.sexo = sexo;
        this.cpf = cpf;
        this.celular = celular;
        this.saldo = saldo;
    }

    public Usuario(int id, String s, String senha, String sexo, String cpf, String nome, double saldo) {
        this.nome = nome;
        this.senha = this.senha;
    }

    public Usuario(String nome, String sexo, double saldo) {
        this.nome = nome;
        this.sexo = sexo;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public double getSaldo() {
        return saldo;
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