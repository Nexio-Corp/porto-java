package com.fiap.beans;

import java.util.List;

import com.fiap.beans.service.AcessorioService;
import com.fiap.beans.service.BikeService;
import com.fiap.beans.service.ClienteService;
import com.fiap.beans.service.MarcaService;
import com.fiap.beans.service.ModeloBikeService;
import com.fiap.beans.service.UsuarioService;
import com.fiap.beans.user.Cliente;
import com.fiap.beans.user.Usuario;
import com.fiap.beans.user.bike.Acessorio;
import com.fiap.beans.user.bike.Bike;
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
	
	BikeService bikeService = new BikeService();
	MarcaService marcaService = new MarcaService() ;
	UsuarioService userService = new UsuarioService() ;
	ClienteService clientService = new ClienteService();
	ModeloBikeService modeloService = new ModeloBikeService();
	AcessorioService acessorioService = new AcessorioService();

	@GET
	@Path("/marcas")
    @Produces(MediaType.APPLICATION_JSON)
	public Response buscarMarcas(){
		List<Marca> lista = marcaService.listarMarcas();
		if (lista==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(lista).build();
	}
	
	@GET
	@Path("/marca/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarMarcaPorId(@PathParam("codigo") Integer codigo){
		Marca marca = marcaService.listarPorCodigo(codigo);
		if(marca==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(marca).build();
	}
	
	@DELETE
	@Path("/marca/{codigo}")
	public Response deleteMarca(@PathParam("codigo") Integer codigo) {
		var marca = marcaService.listarPorCodigo(codigo);
		if(marca == null) return Response.status(Response.Status.NOT_FOUND).build();
		marcaService.delete(marca);
		return Response.noContent().build();
	}
	
	@POST
	@Path("/cadastro-marca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirMarca(Marca marca) {
		if (!marcaService.create(marca)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(marca).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/atualiza-marca/{id}")
	public Response atualizaMarca(@PathParam("id") Integer id, Marca marca) {
		var marcaEncontrada = marcaService.listarPorCodigo(id);
		if (marcaEncontrada == null) return Response.status(Response.Status.NOT_FOUND).build();
		if(!marcaService.update(marca)) return Response.status(Response.Status.BAD_REQUEST).build();
		return Response.ok(marca).build();
	}
	
	@GET
	@Path("/usuario/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarUsuarioPorEmail(@PathParam("email") String email){
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
			return Response.status(Response.Status.NOT_FOUND)
					  .header("Access-Control-Allow-Origin", "*")
				      .header("Access-Control-Allow-Credentials", "true")
				      .header("Access-Control-Allow-Headers",
				        "origin, content-type, accept, authorization")
				      .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				      .entity("Usuario Não Validado").build();
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
		if (userService.usuarioExiste(usuario)) {
	        return Response.status(Response.Status.CONFLICT)
	                .entity("E-mail já está em uso")
	                .header("Access-Control-Allow-Origin", "*")
	                .header("Access-Control-Allow-Credentials", "true")
	                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	                .build();
	    }
		if (!userService.cadastrarUsuario(usuario)) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Requisição Inválida")
					.header("Access-Control-Allow-Origin", "*")
				    .header("Access-Control-Allow-Credentials", "true")
				    .header("Access-Control-Allow-Headers",
				        "origin, content-type, accept, authorization")
				    .header("Access-Control-Allow-Methods", 
				        "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
		}
		return Response.ok(usuario)
				.entity("Usuario Cadastrado")
				.header("Access-Control-Allow-Origin", "*")
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
			Response.status(Response.Status.BAD_REQUEST)
			  .header("Access-Control-Allow-Origin", "*")
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
	public Response buscarModeloPorId(@PathParam("codigo") Long codigo){
		ModeloBike modelo = modeloService.listarPorCodigo(codigo);
		if(modelo==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(modelo).build();
	}
	
	@GET
	@Path("/acessorios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarAcessorios() {
		List<Acessorio> lista = acessorioService.buscarAcessorios();
		if (lista==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(lista).build();
	}
	
	@GET
	@Path("/acessorio/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarAcessorioPorId(@PathParam("codigo") Long codigo){
		Acessorio acessorio = acessorioService.listarPorCodigo(codigo);
		if(acessorio==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(acessorio).build();
	}
	
	@GET
	@Path("/bikes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarBikes() {
		List<Bike> lista = bikeService.buscarBikes();
		if (lista==null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(lista).build();
	}
	
	
}
