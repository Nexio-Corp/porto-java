package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fiap.beans.user.bike.Bike;
import com.fiap.beans.user.bike.ModeloBike;
import com.fiap.beans.user.bike.Modificacao;

public class BikeDao {

	private ModeloBikeDao modeloDao = new ModeloBikeDao();
	private ModificacaoDao modificacaoDao = new ModificacaoDao();
	private AcessorioDao acessorioDao = new AcessorioDao();
	private ClienteDao clienteDao = new ClienteDao();
	
    private Connection conexao() throws ClassNotFoundException, SQLException {
    	final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    	final String USER = "rm99627";
    	final String PASS = "051298";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		return conexao;
	}
	
    public void addBike(Bike bike) throws SQLException, ClassNotFoundException {
    	Date dataAtual = new Date();
    	var conexao = conexao();
        var query = "INSERT INTO T_NSB_BIKE (cod_cliente, cod_modelo_bike, qtd_media_uso, dat_aquisicao_bike, " +
                       "obs_notas_bike, sta_utilizacao_bike, num_nota_bike, sta_locacao_bike, des_chassi_bike, val_bike) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        var ps = conexao.prepareStatement(query);
        ps.setInt(1, bike.getCliente().getCodigo());
        ps.setInt(2, bike.getModelo().getCodigo());
        ps.setInt(3, bike.getMediaUso());
        ps.setDate(4, new java.sql.Date(dataAtual.getTime()));
        ps.setString(5, bike.getNotas());
        ps.setString(6, bike.getUtilizacao());
        ps.setString(7, bike.getNumNota());
        ps.setBoolean(8, bike.isParaLocacao() ? true : false);
        ps.setString(9, bike.getNumChassi());
        ps.setLong(10, bike.getValor());

        ps.executeUpdate();
        conexao.close();
    }

    public List<Bike> findAll() throws SQLException, ClassNotFoundException {
    	var conexao = conexao();
        var resultado = conexao.createStatement().executeQuery("SELECT * FROM T_NSB_bike ORDER BY cod_bike");

        List<Bike> bikes = new ArrayList<>();

        while (resultado.next()) {
        	ModeloBike modelo = modeloDao.findById(resultado.getLong("cod_modelo_bike"));
        	List<Modificacao> listModificacao = modificacaoDao.findAll();
        	var listAcessorios = acessorioDao.findAll();
        	var cliente = clienteDao.findById(resultado.getInt("cod_cliente"));
        	
        	
            bikes.add(new Bike(
            		modelo,
            		listModificacao, 
            		listAcessorios,
            		resultado.getInt("qtd_media_uso"),
            		resultado.getDate("dat_aquisicao_bike"), 
            		resultado.getString("obs_notas_bike"), 
            		resultado.getString("sta_utilizacao_bike"), 
            		resultado.getString("num_nota_bike"), 
            		resultado.getBoolean("sta_locacao_bike"),
            		cliente,
            		resultado.getString("des_chassi_bike"),
            		resultado.getLong("val_bike")));
        }
        conexao.close();
        return bikes;
    }

	public Bike buscarPorChassi(String numChassi) throws ClassNotFoundException, SQLException {
		Bike bike = null;
		
		var conexao = conexao();
		var sql = "SELECT * FROM T_NSB_bike where des_chassi_bike = ? ";
	    var ps = conexao.prepareStatement(sql);
	    ps.setString(1, numChassi);
		var resultado = ps.executeQuery();
        
        while (resultado.next()) {
        	ModeloBike modelo = modeloDao.findById(resultado.getLong("cod_modelo_bike"));
        	List<Modificacao> listModificacao = modificacaoDao.findAll();
        	var listAcessorios = acessorioDao.findAll();
        	var cliente = clienteDao.findById(resultado.getInt("cod_cliente"));
        	
            bike = new Bike(
            		modelo,
            		listModificacao, 
            		listAcessorios,
            		resultado.getInt("qtd_media_uso"),
            		resultado.getDate("dat_aquisicao_bike"), 
            		resultado.getString("obs_notas_bike"), 
            		resultado.getString("sta_utilizacao_bike"), 
            		resultado.getString("num_nota_bike"), 
            		resultado.getBoolean("sta_locacao_bike"),
            		cliente,
            		resultado.getString("des_chassi_bike"),
            		resultado.getLong("val_bike"));
        }
        conexao.close();
        return bike;
	} 
}
