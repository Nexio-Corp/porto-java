package com.fiap.beans.user.bike;

public class ModeloBike {
    private int codigo;
    private String nome;
    private Marca marca;
    private double valor;
    private String tipo;

    public ModeloBike(){};
    
    public ModeloBike(String nome, Marca marca, double valor, String tipo) {
        this.nome = nome;
        this.marca = marca;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}