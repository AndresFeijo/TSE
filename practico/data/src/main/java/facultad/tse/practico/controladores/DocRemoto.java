package facultad.tse.practico.controladores;

import facultad.tse.practico.datatypes.*;
import jakarta.ejb.Remote;

@Remote
public interface DocRemoto {
	
	
//"Base de datos de documentos"
	public DTListaDocumentos documentos = new DTListaDocumentos();

// Agregar un documento
	public DTDocumento agregar(String paciente, String descripcion, String observaciones) ;

// Listar todos los documentos
	public DTListaDocumentos listar();


// Buscar documentos por paciente
	public DTDocumento buscarPorPaciente(String paciente);

// Buscar por ID
	public DTDocumento buscarPorId(Integer id);
	
};