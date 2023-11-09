package com.fiap.beans;

import java.util.List;

import com.fiap.beans.service.BikeService;
import com.fiap.beans.service.UsuarioService;
import com.fiap.beans.user.Usuario;
import com.fiap.beans.user.bike.Marca;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("marcabike")
public class Resource {
	
	BikeService service = new BikeService() ;
	UsuarioService userService = new UsuarioService() ;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response index(){
		List<Marca> lista = service.listarMarcas();
		if (lista==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(lista).build();
	}
	
//	@GET
//	@Path("{codigo}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response buscarPorId(@PathParam("codigo") Integer codigo){
//		Marca marca = service.listarPorCodigo(codigo);
//		if(marca==null) return Response.status(Response.Status.NOT_FOUND).build();
//		return Response.ok(marca).build();
//	}
//	
//	@DELETE
//	@Path("{codigo}")
//	public Response delete(@PathParam("codigo") Integer codigo) {
//		var marca = service.listarPorCodigo(codigo);
//		if(marca == null) return Response.status(Response.Status.NOT_FOUND).build();
//		service.delete(marca);
//		return Response.noContent().build();
//	}
//	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response create(Marca marca) {
//		if (!service.create(marca)) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		}
//		return Response.ok(marca).build();
//	}
//	
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{id}")
//	public Response update(@PathParam("id") Integer id, Marca marca) {
//		var marcaEncontrada = service.listarPorCodigo(id);
//		if (marcaEncontrada == null) return Response.status(Response.Status.NOT_FOUND).build();
//		if(!service.update(marca)) return Response.status(Response.Status.BAD_REQUEST).build();
//		return Response.ok(marca).build();
//	}
	
	@GET
	@Path("{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("login") String email){
		if (!userService.login(email)) return Response.status(Response.Status.NOT_FOUND).entity("Usuario Não Encontrado!!").build();
		return Response.ok(email).build();
	}
	
	
	@POST
	@Path("/cadastro-usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Usuario usuario) {
//		Usuario novoUsuario =  userService.cadastrarUsuario(usuario);
		if (!userService.cadastrarUsuario(usuario)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(usuario).build();
	}
	
	
	
	
}
