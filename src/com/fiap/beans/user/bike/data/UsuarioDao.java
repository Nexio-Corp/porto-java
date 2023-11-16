package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fiap.beans.user.Usuario;

public class UsuarioDao {

	private Connection conexao() throws ClassNotFoundException, SQLException {
		final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		final String USER = "rm99627";
		final String PASS = "051298";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		return conexao;
	}
	
	public boolean inserirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
	    var conexao = conexao();
	    var sql = "INSERT INTO T_NSB_usuario (usuario, token_auth, des_email, des_senha) VALUES (?,?,?,?)";
	    
	    PreparedStatement ps = conexao.prepareStatement(sql);
	    ps.setString(1, usuario.getUsuario());
	    ps.setString(2, usuario.getTokenAuth());
	    ps.setString(3, usuario.getEmail());
	    ps.setString(4, usuario.getSenha());
	    
	    
	    ps.executeUpdate();
	    
	    conexao.close();
	    return true;
	}

	public Usuario findById(Integer codigo) throws SQLException, ClassNotFoundException {
		Usuario usuario = null;
		var conexao = conexao();
		var sql = "SELECT * FROM T_NSB_MARCA WHERE cod_marca = ?";
        var ps = conexao.prepareStatement(sql);
        ps.setInt(1, codigo);
        var resultado = ps.executeQuery();
        
        while(resultado.next()){
			usuario = new Usuario(
					resultado.getString("usuario"),
					resultado.getString("token_auth"),
					resultado.getString("des_email"),
					resultado.getString("des_senha"));
		}
	    ps.executeUpdate();
	    conexao.close();
		return usuario;
	}
	
	public Usuario buscaUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
		Usuario usuarioEncontrado = null;
		var conexao = conexao();
	    var sql = "SELECT * FROM T_NSB_usuario where des_email = ? ";
	    var ps = conexao.prepareStatement(sql);
	    var resultado = ps.executeQuery(sql);
	    ps.setString(1, usuario.getEmail());
	    while(resultado.next()){
			usuarioEncontrado = new Usuario(
					resultado.getString("usuario"),
					resultado.getString("token_auth"),
					resultado.getString("des_email"),
					resultado.getString("des_senha"));
		}
	    ps.executeUpdate();
	    conexao.close();
		return usuarioEncontrado;
	}
	

	public Usuario buscaUsuarioPorEmail(String email) throws ClassNotFoundException, SQLException {
		Usuario usuario = null;
		var conexao = conexao();
	    var sql = "SELECT * FROM T_NSB_usuario where des_email = ? ";
	    var ps = conexao.prepareStatement(sql);
	    ps.setString(1, email);
	    var resultado = ps.executeQuery();
	    while(resultado.next()){
			usuario = new Usuario(
					resultado.getString("usuario"),
					resultado.getString("token_auth"),
					resultado.getString("des_email"),
					resultado.getString("des_senha"));
		}
	    conexao.close();
		return usuario;
	}
}
