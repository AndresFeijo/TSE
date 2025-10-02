package facultad.tse.practico.controladores;

import facultad.tse.practico.clases.Documento;
import facultad.tse.practico.datatypes.*;
import facultad.tse.practico.mapper.*;

import jakarta.ejb.Singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton

public class Doc implements DocLocal, DocRemoto {
	
//"Base de datos de documentos"
public List<Documento> documentos = new ArrayList<>();

// Agregar un documento

public DTDocumento DocToDTDoc(Documento doc) {
	return new DTDocumento(doc.getId(), doc.getFecha().toString(), doc.getPaciente(), doc.getDescripcion(), doc.getObservaciones());
}

public void agregar(Integer id, String paciente, String descripcion, String observaciones) {
    if (id == null) {
        throw new IllegalArgumentException("El ID no puede ser nulo.");
    }
    
    if (paciente == null || paciente.isEmpty()) {
        throw new IllegalArgumentException("El nombre del paciente no puede estar vacío.");
    }

    // Verificar si el ID ya existe
    
    if (buscarPorId(id) != null) {
        throw new IllegalArgumentException("El ID " + id + " ya está en uso.");
    }

    // Si todo está bien, crear y agregar el documento
    Documento doc = new Documento(id, LocalDateTime.now(), paciente, descripcion, observaciones);
    documentos.add(doc);
}

// Listar todos los documentos
public DTListaDocumentos listar() {
	List<DTDocumento> lista = new ArrayList<>();
	for (Documento doc : documentos) {
		lista.add(DocToDTDoc(doc));
	}
    return new DTListaDocumentos(lista); 
}

// Buscar documentos por paciente
public DTDocumento buscarPorPaciente(String paciente) {
    for (Documento doc : documentos) {
        if (doc.getPaciente().equalsIgnoreCase(paciente)) {
            return DocToDTDoc(doc);
        }
    }
    return null; 
}

// Buscar por ID
public DTDocumento buscarPorId(Integer id) {
    for (Documento doc : documentos) {
        if (doc.getId().equals(id)) {
            return DocToDTDoc(doc);
        }
    }
    return null;
}

};

    
    
