package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.beans.user.bike.Marca;
import com.fiap.beans.user.bike.ModeloBike;

public class ModeloBikeDao {
	
	MarcaDao marcaDao = new MarcaDao();

	private Connection conexao() throws ClassNotFoundException, SQLException {
		
		final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		final String USER = "rm99627";
		final String PASS = "051298";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		return conexao;
	}
	
	public List<ModeloBike> findAll() throws SQLException, ClassNotFoundException {
        List<ModeloBike> lista = new ArrayList<>();
        var conexao = conexao();
        var resultado = conexao.createStatement().executeQuery("SELECT * FROM T_NSB_modelo_bike ORDER BY cod_modelo_bike");
        while (resultado.next()) {
            Marca marca = marcaDao.findById(resultado.getInt("cod_marca"));
            
            lista.add(new ModeloBike(
                    resultado.getString("nom_modelo_bike"),
                    marca,
                    resultado.getDouble("val_medio_modelo"),
                    resultado.getString("des_tipo_modelo")));
        }
        conexao.close();
        return lista;
    }


	public ModeloBike findById(Long codigo) throws ClassNotFoundException, SQLException {
		ModeloBike modelo = null;
		var conexao = conexao();
        var sql = "SELECT * FROM T_NSB_modelo_bike where cod_modelo_bike = ?";
        var ps = conexao.prepareStatement(sql);
		ps.setLong(1, codigo);
		var resultado = ps.executeQuery();
		while(resultado.next()){
			Marca marca = marcaDao.findById(resultado.getInt("cod_marca"));
			
			modelo = new ModeloBike(
//					resultado.getInt("cod_modelo_bike"),
					resultado.getString("nom_modelo_bike"),
                    marca,
                    resultado.getDouble("val_medio_modelo"),
                    resultado.getString("des_tipo_modelo"));
		}
		return modelo;
	}
}
