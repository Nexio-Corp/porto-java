package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fiap.beans.user.bike.Marca;

public class BikeDao {

    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private final String USER = "rm99627";
    private final String PASS = "051298";

    public void inserir(Marca marca) throws SQLException {

        
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO marca (codigo, nome) "
                    +
                    "VALUES (?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, marca.getCodigo());
                ps.setString(2, marca.getNome());

                ps.executeUpdate();
                con.close();
            }
        }
    }
}
