package com.fiap.beans;

import java.util.List;

import com.fiap.beans.service.AcessorioService;
import com.fiap.beans.service.ClienteService;
import com.fiap.beans.service.MarcaService;
import com.fiap.beans.service.ModeloBikeService;
import com.fiap.beans.service.UsuarioService;
import com.fiap.beans.user.Cliente;
import com.fiap.beans.user.Usuario;
import com.fiap.beans.user.bike.Acessorio;
import com.fiap.beans.user.bike.Marca;
import com.fiap.beans.user.bike.ModeloBike;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api")
public class Resource {
	
	MarcaService bikeService = new MarcaService() ;
	UsuarioService userService = new UsuarioService() ;
	ClienteService clientService = new ClienteService();
	ModeloBikeService modeloService = new ModeloBikeService();
	AcessorioService acessorioService = new AcessorioService();

	@GET
	@Path("/marcas")
    @Produces(MediaType.APPLICATION_JSON)
	public Response index(){
		List<Marca> lista = bikeService.listarMarcas();
		if (lista==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(lista).build();
	}
	
	@GET
	@Path("/marca/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("codigo") Integer codigo){
		Marca marca = bikeService.listarPorCodigo(codigo);
		if(marca==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(marca).build();
	}
	
	@DELETE
	@Path("/marca/{codigo}")
	public Response delete(@PathParam("codigo") Integer codigo) {
		var marca = bikeService.listarPorCodigo(codigo);
		if(marca == null) return Response.status(Response.Status.NOT_FOUND).build();
		bikeService.delete(marca);
		return Response.noContent().build();
	}
	
	@POST
	@Path("/cadastro-marca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Marca marca) {
		if (!bikeService.create(marca)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(marca).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/atualiza-marca/{id}")
	public Response update(@PathParam("id") Integer id, Marca marca) {
		var marcaEncontrada = bikeService.listarPorCodigo(id);
		if (marcaEncontrada == null) return Response.status(Response.Status.NOT_FOUND).build();
		if(!bikeService.update(marca)) return Response.status(Response.Status.BAD_REQUEST).build();
		return Response.ok(marca).build();
	}
	
	@GET
	@Path("/usuario/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorEmail(@PathParam("email") String email){
		if (!userService.buscaUsuario(email)) return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("Usuario Não Encontrado!!").build();
		return Response.ok(email).build();
	}
	
	@POST
	@Path("/login/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("user") String user, Usuario usuario){
		if (!userService.validarUsuario(usuario)) {
			return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
				      .header("Access-Control-Allow-Credentials", "true")
				      .header("Access-Control-Allow-Headers",
				        "origin, content-type, accept, authorization")
				      .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("Usuario Não Encontrado!!").build();
		}
		return Response.ok("Validado").header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	}
	
	
	@POST
	@Path("/cadastro-usuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirUsuario(Usuario usuario) {
		System.out.println("dentro do create");
//		Usuario novoUsuario =  userService.cadastrarUsuario(usuario);
		if (!userService.cadastrarUsuario(usuario)) {
			return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*")
				      .header("Access-Control-Allow-Credentials", "true")
				      .header("Access-Control-Allow-Headers",
				        "origin, content-type, accept, authorization")
				      .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
		}
		return Response.ok(usuario).header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	}
	
	@POST
	@Path("/cadastro-cliente")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirCliente(Cliente cliente) {
		if (!clientService.inserirCliente(cliente)) {
			Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*")
		      .header("Access-Control-Allow-Credentials", "true")
		      .header("Access-Control-Allow-Headers",
		        "origin, content-type, accept, authorization")
		      .header("Access-Control-Allow-Methods", 
		        "GET, POST, PUT, DELETE, OPTIONS, HEAD").entity("Dados Invalidos").build();
		}
		return Response.ok(cliente).header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	}
	
	@GET
	@Path("/modelos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirModeloBike() {
		List<ModeloBike> lista = modeloService.buscarModelos();
		if (lista==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(lista).build();
	}
	
	@GET
	@Path("/modelo/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("codigo") Long codigo){
		ModeloBike modelo = modeloService.listarPorCodigo(codigo);
		if(modelo==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(modelo).build();
	}
	
	@GET
	@Path("/modelos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirAcessorio() {
		List<Acessorio> lista = acessorioService.buscarAcessorios();
		if (lista==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(lista).build();
	}
	
	
	
	
}
