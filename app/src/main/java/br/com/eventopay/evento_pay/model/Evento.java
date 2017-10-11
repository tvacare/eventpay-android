package br.com.eventopay.evento_pay.model;

/**
 * Created by Bruno on 10/10/2017.
 */

public class Evento {
    private int Id;
    private int Id_adm;
    private double ValorTotal;
    private double ValorSugerido;
    private String Descricao;
    private String nome;
    private String Local;

    public Evento(int id, int id_adm, double valorTotal, double valorSugerido, String descricao, String nome, String local) {
        Id = id;
        Id_adm = id_adm;
        ValorTotal = valorTotal;
        ValorSugerido = valorSugerido;
        Descricao = descricao;
        this.nome = nome;
        Local = local;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_adm() {
        return Id_adm;
    }

    public void setId_adm(int id_adm) {
        Id_adm = id_adm;
    }

    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double valorTotal) {
        ValorTotal = valorTotal;
    }

    public double getValorSugerido() {
        return ValorSugerido;
    }

    public void setValorSugerido(double valorSugerido) {
        ValorSugerido = valorSugerido;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "Id=" + Id +
                ", Id_adm=" + Id_adm +
                ", ValorTotal=" + ValorTotal +
                ", ValorSugerido=" + ValorSugerido +
                ", Descricao='" + Descricao + '\'' +
                ", nome='" + nome + '\'' +
                ", Local='" + Local + '\'' +
                '}';
    }
}
