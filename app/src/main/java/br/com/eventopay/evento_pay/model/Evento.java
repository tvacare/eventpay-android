package br.com.eventopay.evento_pay.model;

public class Evento {

    private int id;
    private double valorTotal;
    private double valorSugerido;
    private String descricao;
    private String nome;

    public Evento(int id, double valorTotal, double valorSugerido, String descricao, String nome) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.valorSugerido = valorSugerido;
        this.descricao = descricao;
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorSugerido() {
        return this.valorSugerido;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}