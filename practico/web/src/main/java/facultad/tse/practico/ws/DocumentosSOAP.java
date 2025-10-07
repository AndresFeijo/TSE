package facultad.tse.practico.ws;

import java.util.List;

import facultad.tse.practico.jpa.entities.Documento;
import facultad.tse.practico.service.DocumentoEJBLocal;
import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class DocumentosSOAP  {
    @EJB DocumentoEJBLocal controladorLocal;
 
    @WebMethod
    
 // Agregar un documento
 	public void agregar(String paciente, String descripcion, String observaciones) {
 		if (paciente.isEmpty()) {
 			throw new IllegalArgumentException("El nombre no puede estar vacío");
 		}
 		controladorLocal.agregar(paciente, descripcion, observaciones);
 	}
    
    @WebMethod
 	
 	// Listar todos los documentos
 	public List<Documento> listar() {
 	    return controladorLocal.listar(); 
 	}
    
    @WebMethod
 	
 	// Buscar documentos por paciente
 	public Documento buscarPorPaciente(String paciente) {
 	    return controladorLocal.buscarPorPaciente(paciente); 
 	}
 	
    @WebMethod
    
 	// Buscar por ID
 	public Documento buscarPorId(Integer id) {
 	    return controladorLocal.buscarPorId(id);
 	}

}
