package com.fiap.beans.user.bike;

public class Acessorio {
    private Long codigo;
    private String nome;
    private String descricao;
    private double valor;
    private Marca marca;

    public Acessorio(Long codigo, String nome, String descricao, double valor, Marca marca) {
    	this.codigo = codigo;
    	this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.marca = marca;
    }
    
    public Acessorio(String nome, String descricao, double valor, Marca marca) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.marca = marca;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
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