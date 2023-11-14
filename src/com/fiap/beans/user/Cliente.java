package com.fiap.beans.user;

import java.util.ArrayList;
import java.util.Date;

import com.fiap.beans.user.bike.Acessorio;
import com.fiap.beans.user.bike.Bike;
import com.fiap.beans.user.bike.ModeloBike;
import com.fiap.beans.user.bike.Modificacao;

public class Cliente {
    private int codigo;
    private String nome;
    private String cpf;
    private String email;
    private String cep;
    private String endereco;
    private Date dataNascimento;
    private String telefone;
    private ArrayList<Bike> bikes = new ArrayList<Bike>();
    private int codigoUsuario;

    public Cliente() {}
    
    public Cliente(String nome, String cpf, String email, String cep, String endereco, Date dataNascimento,
            String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cep = cep;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    // Método usado para criar uma bike e garantir que ela seja associada ao cliente
    public void cadastroBike(
            ModeloBike modelo, ArrayList<Modificacao> modificacoes, ArrayList<Acessorio> acessorios,
            int mediaUso, Date aquisicao, String notas, String utilizacao,
            String numNota, boolean paraLocacao, String numChassi, int codUsuario) {

        this.bikes.add(new Bike(modelo, modificacoes, acessorios, mediaUso, aquisicao, notas,
                utilizacao, numNota, paraLocacao, this, numChassi));
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }
    
    public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
    
    public void setCodigo(int codigo) {
    	this.codigo = codigo;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        String clienteString = "";

        clienteString += "Nome: " + this.nome + "\n";

        if (this.bikes.size() > 0) {
            clienteString += "Bikes: \n";
        }
        for (Bike bike : this.bikes) {
            clienteString += bike.toString();
        }

        return clienteString;
    }

	public void setBikes(ArrayList<Bike> carregarBikesDoCliente) {
		// TODO Auto-generated method stub
		
	}

}