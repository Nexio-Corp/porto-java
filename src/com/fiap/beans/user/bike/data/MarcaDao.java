package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.beans.user.bike.Marca;

public class MarcaDao {
	
	public List<Marca> findAll() throws SQLException, ClassNotFoundException{
		List<Marca> listMarca = new ArrayList<>();
		var conexao = conexao();
		var resultado = conexao.createStatement().executeQuery("SELECT * FROM T_NSB_MARCA ORDER BY cod_marca");
		while(resultado.next()){
			listMarca.add(new Marca(
					resultado.getInt("cod_marca"),
					resultado.getString("nom_marca"))
					);
		}
		conexao.close();
		return listMarca;
	}
	
	public Marca findById(Integer codigo) throws SQLException, ClassNotFoundException {
		Marca marca = null;
		var conexao = conexao();
		var sql = "SELECT * FROM T_NSB_MARCA WHERE cod_marca = ?";
        var ps = conexao.prepareStatement(sql);
        ps.setInt(1, codigo);
        var resultado = ps.executeQuery();
        
		while(resultado.next()){
			marca = new Marca(
					resultado.getInt("cod_marca"),
					resultado.getString("nom_marca")
					);
		}
		System.out.println(marca);
		return marca;
	}

    public void inserir(Marca marca) throws SQLException, ClassNotFoundException {
		var conexao = conexao();
	    var sql = "INSERT INTO T_NSB_MARCA (nom_marca) VALUES (?)";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, marca.getNome());
		
		ps.executeUpdate();
		conexao.close();
	}
    
    public void delete(Marca marca) throws SQLException, ClassNotFoundException {
		var con = conexao();
        var ps = con.prepareStatement("DELETE FROM T_NSB_MARCA WHERE cod_marca = ?"); 
        ps.setLong(1, marca.getCodigo());
        ps.executeUpdate();
        con.close();
	}

	public void atualiza(Marca marca) throws SQLException, ClassNotFoundException {
		var con = conexao();
        var ps = con.prepareStatement("UPDATE T_NSB_MARCA SET nom_marca=? WHERE cod_marca=?"); 
        ps.setString(1, marca.getNome());
        ps.setInt(2, marca.getCodigo());
        ps.executeUpdate();
        con.close();
	}
	
	public Connection conexao() throws ClassNotFoundException, SQLException {

		final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		final String USER = "rm99627";
		final String PASS = "051298";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		return conexao;
	}	
}


