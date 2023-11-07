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
	
	public List<Marca> findAll() throws SQLException, ClassNotFoundException{
		List<Marca> listMarca = new ArrayList<>();
		classForName();
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		var resultado = conexao.createStatement().executeQuery("SELECT * FROM marca ORDER BY codigo");
		while(resultado.next()){
			listMarca.add(new Marca(
					resultado.getInt("codigo"),
					resultado.getString("nome"))
					);
		}
		conexao.close();
		return listMarca;
	}
	
	public Marca findById(Integer codigo) throws SQLException, ClassNotFoundException {
		Marca marca = null;
		classForName();
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		var sql = "SELECT * FROM marca WHERE codigo = ?";
        var stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, codigo);
        var resultado = stmt.executeQuery();
        
		while(resultado.next()){
			marca = new Marca(
					resultado.getInt("codigo"),
					resultado.getString("nome")
					);
		}
		System.out.println(marca);
		return marca;
	}

    public void inserir(Marca marca) throws SQLException, ClassNotFoundException {
    	classForName();
		var conexao = DriverManager.getConnection(URL, USER, PASS);
	    var sql = "INSERT INTO marca (codigo, nome) VALUES (marca_seq.nextval, ?)";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, marca.getNome());
		
		ps.executeUpdate();
		conexao.close();
	}
    
    public void delete(Marca marca) throws SQLException {
		var con = DriverManager.getConnection(URL, USER, PASS);
        var ps = con.prepareStatement("DELETE FROM estados WHERE codigo = ?"); 
        ps.setLong(1, marca.getCodigo());
        ps.executeUpdate();
        con.close();
	}

	public void atualiza(Marca marca) throws SQLException {
		assert marca != null : "O objeto Estado não pode ser nulo.";
		var con = DriverManager.getConnection(URL, USER, PASS);
        var ps = con.prepareStatement("UPDATE estados SET nome=? WHERE codigo=?"); 
        ps.setString(1, marca.getNome());
        ps.setInt(2, marca.getCodigo());
        ps.executeUpdate();
        con.close();
	}
	
	public void classForName() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}	
}


