package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fiap.beans.user.Cliente;

public class ClienteDao {

	 public Connection conexao() throws ClassNotFoundException, SQLException {
		 final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		 final String USER = "rm99627";
		 final String PASS = "051298";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			var conexao = DriverManager.getConnection(URL, USER, PASS);
			return conexao;
		}
    // Método para inserir um cliente no banco de dados
    public Cliente inserirCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
    	var connection = conexao();
        var sql = "INSERT INTO T_NSB_CLIENTE (nom_cliente, des_cpf_cliente, dat_nasc_cliente, des_endereco_cliente, des_cep_cliente, des_telefone_cliente, des_email_cliente, cod_usuario) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        var ps = connection.prepareStatement(sql);
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getCpf());
        ps.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
        ps.setString(4, cliente.getEndereco());
        ps.setString(5, cliente.getCep());
        ps.setString(6, cliente.getTelefone());
        ps.setString(7, cliente.getEmail());
        ps.setInt(8, cliente.getCodigoUsuario());
    
        ps.executeUpdate();
	    
	    connection.close();
		return cliente;
    }
    
    public Cliente findById(Integer codigo) throws SQLException, ClassNotFoundException {
		Cliente cliente = null;
		var conexao = conexao();
		var sql = "SELECT * FROM T_NSB_cliente WHERE cod_cliente = ?";
        var ps = conexao.prepareStatement(sql);
        ps.setInt(1, codigo);
        var resultado = ps.executeQuery();
        
		while(resultado.next()){
			cliente = new Cliente(
					resultado.getString("nom_cliente"),
					resultado.getString("des_cpf_cliente"),
					resultado.getString("des_email_cliente"),
					resultado.getString("des_cep_cliente"),
					resultado.getString("des_endereco_cliente"),
					resultado.getDate("dat_nasc_cliente"),
					resultado.getString("des_telefone_cliente")
					);
		}
		conexao.close();
		return cliente;
	}

}
