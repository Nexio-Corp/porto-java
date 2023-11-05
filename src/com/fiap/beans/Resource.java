package com.fiap.beans;

import java.util.List;

import com.fiap.beans.service.BikeService;
import com.fiap.beans.user.bike.Marca;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("bikeapi")
public class Resource {
	
	BikeService service = new BikeService() ;
	

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response index(){
		List<Marca> lista = service.listarMarcas();
		if (lista==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(lista).build();
	}
	
	
	@GET
	@Path("{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("codigo") Integer codigo){
		Marca marca = service.listarPorCodigo(codigo);
		if(marca==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(marca).build();
	}
}
