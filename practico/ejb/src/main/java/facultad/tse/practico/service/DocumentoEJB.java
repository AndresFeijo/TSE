package facultad.tse.practico.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

import facultad.tse.practico.controladores.DocLocal;
import facultad.tse.practico.jpa.entities.Documento;

@Stateless

public class DocumentoEJB implements DocumentoEJBLocal, DocumentoEJBRemoto{
	
	@EJB DocLocal controladorLocal;


	public DocumentoEJB () {};
	
	// Agregar un documento
	public void agregar(String paciente, String descripcion, String observaciones) {
		if (paciente.isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede estar vacío");
		}
		controladorLocal.agregar(paciente, descripcion, observaciones);
	}
	
	// Listar todos los documentos
	public List<Documento> listar() {
	    return controladorLocal.listar(); 
	}
	
	// Buscar documentos por paciente
	public Documento buscarPorPaciente(String paciente) {
	    return controladorLocal.buscarPorPaciente(paciente); 
	}
	
	// Buscar por ID
	public Documento buscarPorId(Integer id) {
	    return controladorLocal.buscarPorId(id);
	}

};

    
    
