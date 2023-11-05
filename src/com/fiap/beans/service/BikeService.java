package com.fiap.beans.service;

import java.sql.SQLException;
import java.util.List;

import com.fiap.beans.user.bike.Marca;
import com.fiap.beans.user.bike.data.MarcaDao;

public class BikeService {
	
	MarcaDao marcaDao = new MarcaDao();

	public List<Marca> listarMarcas(){
		try {
			List<Marca> listMarca = marcaDao.findAll();
			return listMarca;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Marca listarPorCodigo(Integer codigo) {
		Marca marca;
		try {
			marca = marcaDao.findById(codigo);
			return marca;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
