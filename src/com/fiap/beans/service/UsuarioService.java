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
		var usuario = Usuario.cadastroComEmailSenha(user.getEmail(), user.getTokenAuth());
		try {
			dao.inserirUsuario(usuario);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(usuario); 
		return false;
	}
	
	public boolean login(String email) {
		try {
			var usuario = dao.buscaUsuarioPorEmail(email);
//			System.out.println(usuario.getEmail()+"    "+email);
			var valida = usuario != null && usuario.getEmail().equals(email);
			return valida;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
