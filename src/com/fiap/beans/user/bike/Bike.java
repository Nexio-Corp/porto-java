package com.fiap.beans.user.bike;

import java.util.Date;
import java.util.List;

import com.fiap.beans.user.Cliente;

public class Bike {
    private int codigo;
    private ModeloBike modelo;
    private List<Modificacao> modificacoes;
    private List<Acessorio> acessorios;
    private int mediaUso;
    private Date aquisicao;
    private String notas;
    private String utilizacao;
    private String numNota;
    private boolean paraLocacao;
    private Cliente cliente;
    private String numChassi;

    public Bike(ModeloBike modelo, List<Modificacao> listModificacao, 
    		List<Acessorio> acessorios, int mediaUso,
            Date aquisicao, String notas, String utilizacao, String numNota, 
            boolean paraLocacao, Cliente cliente, String numChassi) {
        // A fazer: enviar para o banco de dados
        this.modelo = modelo;
        this.modificacoes = listModificacao;
        this.acessorios = acessorios;
        this.mediaUso = mediaUso;
        this.aquisicao = aquisicao;
        this.notas = notas;
        this.utilizacao = utilizacao;
        this.numNota = numNota;
        this.paraLocacao = paraLocacao;
        this.cliente = cliente;
        this.numChassi = numChassi;;
    }

    public Bike addModificacao(Modificacao modificacao) {
        // A fazer: enviar para o banco de dados
        this.modificacoes.add(modificacao);
        return this;
    }

    public Bike addAcessorio(Acessorio acessorio) {
        // A fazer: enviar para o banco de dados
        this.acessorios.add(acessorio);
        return this;
    }

    public int getCodigo() {
        return codigo;
    }

    public ModeloBike getModelo() {
        return modelo;
    }

    public Modificacao[] getModificacoes() {
        return modificacoes.toArray(new Modificacao[modificacoes.size()]);
    }

    public Acessorio[] getAcessorios() {
        return acessorios.toArray( new Acessorio[acessorios.size()]);
    }

    public int getMediaUso() {
        return mediaUso;
    }

    public Date getAquisicao() {
        return aquisicao;
    }

    public String getNotas() {
        return notas;
    }

    public String getUtilizacao() {
        return utilizacao;
    }

    public String getNumNota() {
        return numNota;
    }

    public boolean isParaLocacao() {
        return paraLocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNumChassi() {
        return numChassi;
    }
    public void setCodigo(int codigo) {
    	this.codigo = codigo;
    }

    @Override
    public String toString() {
        String bikeString = "";
        bikeString += "Modelo: " + this.modelo + "\n";
        bikeString += "Modificações: " + this.modificacoes + "\n";
        bikeString += "Acessórios: " + this.acessorios + "\n";
        bikeString += "Numero de Chassi: " + this.numChassi + "\n";
        bikeString += "Nota Fiscal: " + this.numNota + "\n";
        return bikeString;
    }

}
