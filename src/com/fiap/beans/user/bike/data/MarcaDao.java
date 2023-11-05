package com.fiap.beans.user.bike.data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.beans.user.bike.Marca;

public class MarcaDao {
	
	private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private final String USER = "rm99627";
	private final String PASS = "051298";
	List<Marca> listMarca = new ArrayList<>();
	
	public List<Marca> findAll() throws SQLException, ClassNotFoundException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		var resultado = conexao.createStatement().executeQuery("SELECT * FROM marca");
		while(resultado.next()){
			listMarca.add(new Marca(
					resultado.getString("nome"))
					);
		}
		conexao.close();
		return listMarca;
	}
	
	public Marca findById(Integer codigo) throws SQLException {
		Marca marca = null;
		
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		var sql = "SELECT * FROM estados WHERE id = ?";
        var stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, codigo);
        var resultado = stmt.executeQuery();
        
		while(resultado.next()){
			marca = new Marca(
					resultado.getString("nome")
					);
		}
		return marca;
	}

    public void inserir(Marca marca) throws SQLException {
		var conexao = DriverManager.getConnection(URL, USER, PASS);
	    var sql = "INSERT INTO marca (nome) VALUES (?)";
		PreparedStatement ps = conexao.prepareStatement(sql);
//		ps.setInt(1, marca.getCodigo());
		ps.setString(2, marca.getNome());
		
		ps.executeUpdate();
		conexao.close();
	}
}


