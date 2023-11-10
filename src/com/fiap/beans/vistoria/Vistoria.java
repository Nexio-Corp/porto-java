package com.fiap.beans.vistoria;

import java.io.File;
import java.util.Date;

import com.fiap.beans.user.bike.Bike;

public class Vistoria {
    private int codigo;
    private File[] fotosBike;
    private Bike bike;
    private Date criacao;
    private String motivoRejeicao;
    private int confiancaResultado;
    private boolean aprovada;
    private boolean validada = false;

    public Vistoria(Bike bike, String[] linkFotos) {
        this.bike = bike;
        this.criacao = new Date();
        this.fotosBike = new File[linkFotos.length];
        for (int i = 0; i < linkFotos.length; i++) {
            // A fazer: baixar fotos do link e salvar em fotosBike[i]
            this.fotosBike[i] = new File(linkFotos[i]);
        }
    }

    @SuppressWarnings("unused")
    public boolean validarVistoria() {
        if (this.validada) {
            return this.aprovada;
        }
        for (File foto : fotosBike) {
            // A fazer: enviar foto para o IA
            // A fazer: receber resultado da IA
            // A fazer: definir se a vistoria foi aprovada ou nao
            // A fazer: atualizar confiancaResultado

            this.confiancaResultado = 0;
            this.aprovada = false;
            this.motivoRejeicao = "Não foi possível validar a vistoria, pois o sistema está em criação.";

        }
        if (this.confiancaResultado > 0.5 && this.aprovada) {
            this.validada = true;
            return true;
        }
        this.validada = true;
        return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public File[] getFotosBike() {
        return fotosBike;
    }

    public Bike getBike() {
        return bike;
    }

    public Date getCriacao() {
        return criacao;
    }

    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public int getConfiancaResultado() {
        return confiancaResultado;
    }

    public boolean isAprovada() {
        return aprovada;
    }
}