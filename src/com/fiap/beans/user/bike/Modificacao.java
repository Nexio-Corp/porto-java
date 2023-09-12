package com.fiap.beans.user.bike;

public class Modificacao {
    private String codigo;
    private String nome;
    private String descricao;
    private int valor;
    private Marca marca;

    public Modificacao(String nome, String descricao, int valor, Marca marca) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor() {
        return valor;
    }

    public Marca getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return this.nome + " da " + this.marca.toString();
    }
}