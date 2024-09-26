package org.example;

public class Tarefa {
    private static int contador = 0;
    private int id;
    private String descricao;
    private String criador;

    public Tarefa(String descricao, String criador) {
        this.id = ++contador;
        this.descricao = descricao;
        this.criador = criador;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCriador() {
        return criador;
    }
}
