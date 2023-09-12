package com.fiap.beans.user;

import java.sql.Date;
import java.util.Random;

public class Usuario {
    private String usuario;
    private String tokenAuth;
    private Cliente cliente;
    private String email;

    Usuario(String usuario, String tokenAuth, String email) {
        this.usuario = usuario;
        this.tokenAuth = tokenAuth;
        this.email = email;
    }

    public static Usuario cadastroComEmailSenha(String email, String senha) {
        // A fazer: usar um serviço de auth

        return new Usuario(email.split("@")[0], Double.toString(new Random().nextDouble()), email);
    }

    public static Usuario loginComEmailSenha(String email, String senha) {
        // implementação
        return null;
    }

    public void cadastroDadosCliente(String nome, String cpf, String cep, String endereco, Date dataNascimento,
            String Telefone) {
        this.cliente = new Cliente(nome, cpf, this.email, cep, endereco, dataNascimento, Telefone);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTokenAuth() {
        return tokenAuth;
    }

    public String getEmail() {
        return email;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String userString = "";
        userString += "Usuario: " + this.usuario + "\n";
        userString += "Email: " + this.email + "\n";

        if (this.cliente != null)
            userString += "Cliente: " + this.cliente.toString() + "\n";

        return userString;
    }
}
