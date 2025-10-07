package facultad.tse.practico.ws;


import java.util.List;

import facultad.tse.practico.jpa.entities.Documento;
import facultad.tse.practico.service.DocumentoEJBLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@RequestScoped
@Path("/clinica")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

public class DocumentosREST  {
	 @EJB DocumentoEJBLocal controladorLocal;
	 

	    
	 // Agregar un documento
	 @POST
	 @Path("/documentos")
	 	public void agregar(String paciente, String descripcion, String observaciones) {
	 		if (paciente.isEmpty()) {
	 			throw new IllegalArgumentException("El nombre no puede estar vacío");
	 		}
	 		controladorLocal.agregar(paciente, descripcion, observaciones);
	 	}
	    

	 	@GET
	 	@Path("/documentos")
	 	// Listar todos los documentos
	 	public List<Documento> listar() {
	 	    return controladorLocal.listar(); 
	 	}
	    

	 	@GET
	 	@Path("/documentos/{nombre}")
	 	// Buscar documentos por paciente
	 	public Documento buscarPorPaciente(@PathParam("nombre") String paciente) {
	 	    return controladorLocal.buscarPorPaciente(paciente); 
	 	}
	 	

	    @GET
	    @Path("/documentos/{id}")
	 	// Buscar por ID
	 	public Documento buscarPorId(@PathParam("id") Integer id) {
	 	    return controladorLocal.buscarPorId(id);
	 	}
}
