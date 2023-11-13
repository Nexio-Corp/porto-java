package com.fiap.beans.service;

import java.sql.SQLException;
import java.util.List;

import com.fiap.beans.user.bike.Marca;
import com.fiap.beans.user.bike.data.MarcaDao;

public class MarcaService {
	
	MarcaDao marcaDao = new MarcaDao();

	public List<Marca> listarMarcas(){
		try {
			List<Marca> listMarca = marcaDao.findAll();
			return listMarca;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Marca listarPorCodigo(Integer codigo) {
		Marca marca;
		try {
			marca = marcaDao.findById(codigo);
			return marca;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void delete(Marca marca) {
		try {
			marcaDao.delete(marca);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}

	public boolean create(Marca marca) {
		if (marca.getNome().isEmpty()) return false;
		try {
			marcaDao.inserir(marca);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean update(Marca marca) {
		if (marca.getNome().isEmpty()) return false;
		try {
			marcaDao.atualiza(marca);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}

}
