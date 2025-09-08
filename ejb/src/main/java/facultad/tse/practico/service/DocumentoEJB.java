package facultad.tse.practico.service;

import facultad.tse.practico.dao.DocumentoDAO;
import facultad.tse.practico.model.Documento;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless

public class DocumentoEJB implements DocumentoEJBLocal, DocumentoEJBRemoto{
	
@EJB
	private DocumentoDAO dao;

// Agregar un documento
public void agregar(Integer id, String paciente, String descripcion, String observaciones) {
	if (id == null || paciente.isEmpty()) {
		throw new IllegalArgumentException("El nombre no puede estar vacío");
	}
	dao.agregar(id, paciente, descripcion, observaciones);
}

// Listar todos los documentos
public List<Documento> listar() {
    return dao.listar(); 
}

// Buscar documentos por paciente
public Documento buscarPorPaciente(String paciente) {
    return dao.buscarPorPaciente(paciente); 
}

// Buscar por ID
public Documento buscarPorId(Integer id) {
    return dao.buscarPorId(id);
}

};

    
    
