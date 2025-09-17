package facultad.tse.practico.controladores;

import facultad.tse.practico.clases.Documento;

import jakarta.ejb.Singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton

public class Doc implements DocLocal, DocRemoto {
	
//"Base de datos de documentos"
public List<Documento> documentos = new ArrayList<>();

// Agregar un documento
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
public List<Documento> listar() {
    return new ArrayList<>(documentos); 
}

// Buscar documentos por paciente
public Documento buscarPorPaciente(String paciente) {
    for (Documento doc : documentos) {
        if (doc.getPaciente().equalsIgnoreCase(paciente)) {
            return doc;
        }
    }
    return null; 
}

// Buscar por ID
public Documento buscarPorId(Integer id) {
    for (Documento doc : documentos) {
        if (doc.getId().equals(id)) {
            return doc;
        }
    }
    return null;
}

};

    
    
