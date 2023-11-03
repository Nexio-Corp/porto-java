package com.fiap.beans;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("bikeapi")
public class Resource {
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it! - Bike";
    }
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("id") Long id){
		
		return Response.ok().build();
	}
}
