package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.beans.user.bike.Acessorio;
import com.fiap.beans.user.bike.Marca;
import com.fiap.beans.user.bike.ModeloBike;

public class AcessorioDao {

	private Connection conexao() throws ClassNotFoundException, SQLException {
		final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		final String USER = "rm99627";
		final String PASS = "051298";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		return conexao;
	}	
	
	public List<Acessorio> findAll() throws ClassNotFoundException, SQLException {
		List<Acessorio> lista = new ArrayList<>();
        var conexao = conexao();
        var resultado = conexao.createStatement().executeQuery("SELECT * FROM T_NSB_acessorio ORDER BY cod_acessorio");
        while (resultado.next()) {
            lista.add(new Acessorio(
                    resultado.getString("nom_acessorio"),
                    resultado.getString("des_acessorio"),
                    resultado.getDouble("val_acessorio"),
                    new Marca(resultado.getInt("cod_marca"))
                    ));
        }//String nome, String descricao, int valor, Marca marca
        System.out.println(lista);
        conexao.close();
        return lista;
	}

	public Acessorio findById(Long codigo) throws ClassNotFoundException, SQLException {
		Acessorio acessorio = null;
		var conexao = conexao();
		var sql = "SELECT * FROM T_NSB_acessorio WHERE cod_acessorio = ?";
        var ps = conexao.prepareStatement(sql);
        ps.setLong(1, codigo);
        var resultado = ps.executeQuery();
        
		while(resultado.next()){
			acessorio = new Acessorio(
					resultado.getString("nom_acessorio"),
                    resultado.getString("des_acessorio"),
                    resultado.getDouble("val_acessorio"),
                    new Marca(resultado.getInt("cod_marca")
					));
		}
		return acessorio;
	}

}
