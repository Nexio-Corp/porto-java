package com.fiap.beans.service;

import java.sql.SQLException;
import java.util.List;

import com.fiap.beans.user.bike.Bike;
import com.fiap.beans.user.bike.data.BikeDao;

public class BikeService {

	BikeDao dao = new BikeDao();
	
	public List<Bike> buscarBikes(){
		try {
			List<Bike> lista = dao.findAll();
			return lista;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
