package com.fiap.beans.service;

import java.sql.SQLException;

import com.fiap.beans.user.Cliente;
import com.fiap.beans.user.bike.data.ClienteDao;

public class ClienteService {

	ClienteDao dao = new ClienteDao();

	public boolean inserirCliente(Cliente cliente) {
		try {
			dao.inserirCliente(cliente);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
