package facultad.tse.practico.controladores;

import facultad.tse.practico.clases.Documento;
import jakarta.ejb.Local;

import java.util.ArrayList;
import java.util.List;

@Local
public interface DocLocal {
	
	
//"Base de datos de documentos"
public List<Documento> documentos = new ArrayList<>();

// Agregar un documento
public void agregar(Integer id, String paciente, String descripcion, String observaciones) ;

// Listar todos los documentos
public List<Documento> listar();


// Buscar documentos por paciente
public Documento buscarPorPaciente(String paciente);

// Buscar por ID
public Documento buscarPorId(Integer id);
};

    
    
