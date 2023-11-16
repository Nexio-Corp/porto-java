package com.fiap.beans.service;

import java.sql.SQLException;
import java.util.List;

import com.fiap.beans.user.bike.Acessorio;
import com.fiap.beans.user.bike.data.AcessorioDao;

public class AcessorioService {
	
	private AcessorioDao dao = new AcessorioDao();

	public List<Acessorio> buscarAcessorios(){
		try {
			List<Acessorio> lista = dao.findAll();
			return lista;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Acessorio listarPorCodigo(Long codigo) {
		try {
			Acessorio acessorio = dao.findById(codigo);
			return acessorio;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
