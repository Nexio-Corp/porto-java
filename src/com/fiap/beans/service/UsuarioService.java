package com.fiap.beans.service;

import java.sql.SQLException;

import com.fiap.beans.user.Usuario;
import com.fiap.beans.user.bike.data.UsuarioDao;

public class UsuarioService {

	UsuarioDao dao = new UsuarioDao() ;
	
	public boolean inserirUsuario(Usuario user) {
//		Usuario usuario = cadastrarUsuario(user.getUsuario(), user.getTokenAuth());
		try {
			dao.inserirUsuario(user);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean cadastrarUsuario(Usuario user){
		var usuario = Usuario.cadastroComEmailSenha(user.getEmail(), user.getSenha());
		try {
			if (user.getSenha()==null || user.getEmail()== null) return false;
			dao.inserirUsuario(usuario);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean buscaUsuario(String email) {
		try {
			var usuario = dao.buscaUsuarioPorEmail(email);
			var valida = usuario != null && usuario.getEmail().equals(email);
			return valida;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validarUsuario(Usuario usuarioRecebido) {
		try {
			var user = dao.buscaUsuarioPorEmail(usuarioRecebido.getEmail());
			
			var validacao = user != null && usuarioRecebido != null 
					&& usuarioRecebido.getEmail().equals(user.getEmail())
					&& usuarioRecebido.getSenha().equals(user.getSenha());
			return validacao;		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean usuarioExiste(Usuario usuario) {
		try {
			var user = dao.buscaUsuarioPorEmail(usuario.getEmail());
			var validacao = user != null 
					&& usuario.getEmail().equals(user.getEmail());
			return validacao;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
