package br.com.eventopay.evento_pay.model;

/**
 * Created by Bruno on 10/10/2017.
 */

public class Transacao {
    private int id;
    private int id_Usuario;
    private String nomeEvento;
    private int id_Evento;
    private double valor;

    public Transacao(int id_Usuario, String nomeEvento, int id_Evento, double valor) {
        this.id_Usuario = id_Usuario;
        this.nomeEvento = nomeEvento;
        this.id_Evento = id_Evento;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public int getId_Evento() {
        return id_Evento;
    }

    public void setId_Evento(int id_Evento) {
        this.id_Evento = id_Evento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", id_Usuario=" + id_Usuario +
                ", nomeEvento='" + nomeEvento + '\'' +
                ", id_Evento=" + id_Evento +
                ", valor=" + valor +
                '}';
    }
}
