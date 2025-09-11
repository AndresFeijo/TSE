package facultad.tse.practico.service;

import facultad.tse.practico.clases.Documento;
import facultad.tse.practico.controladores.DocLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless

public class DocumentoEJB implements DocumentoEJBLocal, DocumentoEJBRemoto{
	
@EJB DocLocal controladorLocal;

// Agregar un documento
public void agregar(Integer id, String paciente, String descripcion, String observaciones) {
	if (id == null || paciente.isEmpty()) {
		throw new IllegalArgumentException("El nombre no puede estar vacío");
	}
	controladorLocal.agregar(id, paciente, descripcion, observaciones);
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

    
    
