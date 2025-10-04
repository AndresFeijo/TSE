package facultad.tse.practico.controladores;

import facultad.tse.practico.clases.Documento;
import facultad.tse.practico.datatypes.*;

import jakarta.ejb.Singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton

public class Doc implements DocLocal, DocRemoto {
	
//"Base de datos de documentos"
public List<Documento> documentos = new ArrayList<>();
private Integer proximoID = 1;

// Agregar un documento

public Doc() {
	// Carga de datos estaticos
	this.agregar("Juan Pérez", "Consulta general", "Paciente refiere dolor de cabeza persistente");
	this.agregar("María Gómez", "Control anual", "Sin observaciones relevantes");
	this.agregar("Carlos López", "Análisis de sangre", "Esperando resultados del laboratorio");
	this.agregar("Ana Torres", "Consulta ginecológica", "Paciente menciona ciclos irregulares");
	this.agregar("Pedro Martínez", "Chequeo post-operatorio", "Cicatriz en buen estado, sin complicaciones");
	this.agregar("Lucía Fernández", "Consulta pediátrica", "Niño con tos leve desde hace 3 días");
	this.agregar("Roberto Díaz", "Radiografía de tórax", "Posible neumonía, enviar a especialista");
	this.agregar("Laura Rodríguez", "Vacunación", "Aplicada dosis de refuerzo contra influenza");
	this.agregar("Sofía Silva", "Consulta traumatológica", "Esguince leve en tobillo derecho");
	this.agregar("Martín Castro", "Control odontológico", "Carie tratada en molar superior izquierdo");
}

public DTDocumento DocToDTDoc(Documento doc) {
	return new DTDocumento(doc.getId(), doc.getFecha().toString(), doc.getPaciente(), doc.getDescripcion(), doc.getObservaciones());
}

public void agregar(String paciente, String descripcion, String observaciones) {
    
    if (paciente == null || paciente.isEmpty()) {
        throw new IllegalArgumentException("El nombre del paciente no puede estar vacío.");
    }

    // Si todo está bien, crear y agregar el documento
    Documento doc = new Documento(proximoID, LocalDateTime.now(), paciente, descripcion, observaciones);
    documentos.add(doc);
    proximoID = proximoID + 1;
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