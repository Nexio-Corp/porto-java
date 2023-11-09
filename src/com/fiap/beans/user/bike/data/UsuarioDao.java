package com.fiap.beans.user.bike.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.fiap.beans.user.Usuario;

public class UsuarioDao {
	private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private final String USER = "rm99627";
	private final String PASS = "051298";
	
	public boolean inserirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
	    var conexao = conexao();
	    var sql = "INSERT INTO T_NSB_usuario (usuario, token_auth, des_email) VALUES (?,?,?)";
	    
	    PreparedStatement ps = conexao.prepareStatement(sql);
	    ps.setString(1, usuario.getUsuario());
	    ps.setString(2, usuario.getTokenAuth());
	    ps.setString(3, usuario.getEmail());
	    
	    ps.executeUpdate();
	    conexao.close();
	    return true;
	}
	
	public Usuario buscaUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
		Usuario usuarioEncontrado = null;
		var conexao = conexao();
	    var sql = "SELECT FROM T_NSB_usuario where usuario = ? ";
	    
	    var ps = conexao.prepareStatement(sql);
	    ps.setString(1, usuario.getUsuario());
	    
	    ps.executeUpdate();
	    conexao.close();
		return usuarioEncontrado;
	}
	
	public Connection conexao() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		var conexao = DriverManager.getConnection(URL, USER, PASS);
		return conexao;
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
					resultado.getString("des_email"));
		}
//	    System.out.println("usuario no Dao: \n"+usuario);
		return usuario;
	}
}
