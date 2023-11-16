package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.beans.user.bike.Marca;
import com.fiap.beans.user.bike.Modificacao;

public class ModificacaoDao {

	private MarcaDao marcaDao = new MarcaDao();

	private Connection conexao() throws ClassNotFoundException, SQLException {
		
		final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		final String USER = "rm99627";
		final String PASS = "051298";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		return conexao;
	}

	public List<Modificacao> findAll() throws SQLException, ClassNotFoundException {
        List<Modificacao> lista = new ArrayList<>();
        var conexao = conexao();
        var resultado = conexao.createStatement().executeQuery("SELECT * FROM T_NSB_modificacao ORDER BY cod_modificacao");
        while (resultado.next()) {
            Marca marca = marcaDao.findById(resultado.getInt("cod_marca"));
            
            lista.add(new Modificacao(
            		resultado.getString("nom_modificacao"),
					resultado.getString("des_modificacao"),
                    resultado.getInt("val_modificacao"),
                    marca));
        }
        conexao.close();
        return lista;
    }
	
	public Modificacao findById(Long codigo) throws ClassNotFoundException, SQLException {
		Modificacao modificacao = null;
		var conexao = conexao();
        var sql = "SELECT * FROM T_NSB_modificacao where cod_modoficacao = ?";
        var ps = conexao.prepareStatement(sql);
		ps.setLong(1, codigo);
		var resultado = ps.executeQuery();
		while(resultado.next()){
			Marca marca = marcaDao.findById(resultado.getInt("cod_marca"));
			
			modificacao = new Modificacao(
					resultado.getString("nom_modificacao"),
					resultado.getString("des_modificacao"),
                    resultado.getInt("val_modificacao"),
                    marca);
		}
		conexao.close();
		return modificacao;
	}
}
