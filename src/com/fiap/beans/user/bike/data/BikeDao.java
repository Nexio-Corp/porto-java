package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fiap.beans.user.bike.Acessorio;
import com.fiap.beans.user.bike.Bike;
import com.fiap.beans.user.bike.Modificacao;

public class BikeDao {

    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private final String USER = "rm99627";
    private final String PASS = "051298";

    public void inserir(Bike bike) throws SQLException {

        Acessorio[] acessorios = bike.getAcessorios();
        String acessoriosString = String.join(",", (CharSequence[]) acessorios);

        Modificacao[] modificacoes = bike.getModificacoes();
        String modificacoesString = String.join(",", (CharSequence[]) modificacoes);

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO bike (codigo, modelo, modificacoes, acessorios, aquisicao, notas, utilizacao, numNota, numChassi) "
                    +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, bike.getCodigo());
                ps.setString(2, bike.getModelo().getNome());
                ps.setString(3, modificacoesString);
                ps.setString(4, acessoriosString);
                ps.setDate(5, new java.sql.Date(bike.getAquisicao().getTime()));
                ps.setString(6, bike.getNotas());
                ps.setString(7, bike.getUtilizacao());
                ps.setString(8, bike.getNumNota());
                ps.setString(9, bike.getNumChassi());

                ps.executeUpdate();
                con.close();
            }
        }
    }
}
