package facultad.tse.practico.ws;


import facultad.tse.practico.datatypes.DTDocumento;
import facultad.tse.practico.datatypes.DTListaDocumentos;
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
	 	public DTListaDocumentos listar() {
	 	    return controladorLocal.listar(); 
	 	}
	    

	 	@GET
	 	@Path("/documentos/{nombre}")
	 	// Buscar documentos por paciente
	 	public DTDocumento buscarPorPaciente(@PathParam("nombre") String paciente) {
	 	    return controladorLocal.buscarPorPaciente(paciente); 
	 	}
	 	

	    @GET
	    @Path("/documentos/{id}")
	 	// Buscar por ID
	 	public DTDocumento buscarPorId(@PathParam("id") Integer id) {
	 	    return controladorLocal.buscarPorId(id);
	 	}
}
