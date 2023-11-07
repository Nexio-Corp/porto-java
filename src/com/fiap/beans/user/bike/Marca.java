package com.fiap.beans.user.bike;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Marca {
    private int codigo;
    private String nome;

    public Marca(String nome) {
        this.nome = nome;
    }

	public Marca(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}