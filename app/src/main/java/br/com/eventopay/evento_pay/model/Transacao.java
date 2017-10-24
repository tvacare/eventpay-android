package br.com.eventopay.evento_pay.model;

public class Transacao {

    private int id;
    private int id_Usuario;
    private String nomeEvento;
    private int id_Evento;
    private double valor;

    public Transacao(String nomeEvento, double valor) {
        this.nomeEvento = nomeEvento;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Evento: " + nomeEvento + "\nValor: R$ " + valor + "0";
    }
}
