package com.fiap.beans.service;

import java.sql.SQLException;
import java.util.List;

import com.fiap.beans.user.bike.ModeloBike;
import com.fiap.beans.user.bike.data.ModeloBikeDao;

public class ModeloBikeService {
	
	private ModeloBikeDao modeloDao = new ModeloBikeDao(); 

	public List<ModeloBike> buscarModelos(){
		try {
			List<ModeloBike> lista = modeloDao.findAll();
			return lista;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ModeloBike listarPorCodigo(Long codigo) {
		try {
			ModeloBike modelo = modeloDao.findById(codigo);
			return modelo;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
