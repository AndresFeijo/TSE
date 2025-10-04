package facultad.tse.practico.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import facultad.tse.practico.datatypes.DTDocumento;
import facultad.tse.practico.datatypes.DTListaDocumentos;
import facultad.tse.practico.service.DocumentoEJBLocal;
import jakarta.ejb.EJB;

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
 	public DTListaDocumentos listar() {
 	    return controladorLocal.listar(); 
 	}
    
    @WebMethod
 	
 	// Buscar documentos por paciente
 	public DTDocumento buscarPorPaciente(String paciente) {
 	    return controladorLocal.buscarPorPaciente(paciente); 
 	}
 	
    @WebMethod
    
 	// Buscar por ID
 	public DTDocumento buscarPorId(Integer id) {
 	    return controladorLocal.buscarPorId(id);
 	}

}
