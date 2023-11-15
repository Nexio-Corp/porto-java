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

	public boolean adicionaBike(Bike bike) {
		try {
			dao.addBike(bike);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean existeBike(Bike bike) {
		try {
			var bikeEncontrada = dao.buscarPorChassi(bike.getNumChassi());
			var validacao = bikeEncontrada != null
					&& bike.getNumChassi().equals(bikeEncontrada.getNumChassi());
			return validacao;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
}
