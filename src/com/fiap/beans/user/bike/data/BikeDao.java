package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.beans.user.bike.Bike;

public class BikeDao {

    // Método para adicionar uma bike no banco de dados
    public void addBike(Bike bike) throws SQLException, ClassNotFoundException {
    	var connection = conexao();
        String query = "INSERT INTO T_NSB_BIKE (cod_cliente, cod_modelo_bike, qtd_media_uso, dat_aquisicao_bike, " +
                       "obs_notas_bike, sta_utilizacao_bike, num_nota_bike, sta_locacao_bike, des_chassi_bike) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, bike.getCliente().getCodigo());
            ps.setInt(2, bike.getModelo().getCodigo());
            ps.setInt(3, bike.getMediaUso());
            ps.setDate(4, new java.sql.Date(bike.getAquisicao().getTime()));
            ps.setString(5, bike.getNotas());
            ps.setString(6, bike.getUtilizacao());
            ps.setString(7, bike.getNumNota());
            ps.setString(8, bike.isParaLocacao() ? "Disponível" : "Indisponível");
            ps.setString(9, bike.getNumChassi());

            ps.executeUpdate();
        }
    }

    // Método para obter todas as bikes do banco de dados
    public List<Bike> getAllBikes() throws SQLException, ClassNotFoundException {
    	var connection = conexao();
        List<Bike> bikes = new ArrayList<>();
        String query = "SELECT * FROM T_NSB_BIKE";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Bike bike = mapResultSetToBike(resultSet);
                bikes.add(bike);
            }
        }

        return bikes;
    }

    // Método auxiliar para mapear um ResultSet para um objeto Bike
    private Bike mapResultSetToBike(ResultSet resultSet) throws SQLException {
        // Implemente o mapeamento aqui, utilizando os métodos apropriados da classe ResultSet
        // Exemplo fictício:
        // int codigo = resultSet.getInt("cod_bike");
        // String modelo = resultSet.getString("modelo");
        // ... mapear outras colunas ...

        // Retorne uma nova instância de Bike com os valores mapeados
        // return new Bike(codigo, modelo, ...);
        return null;  // Substitua pelo código adequado
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
