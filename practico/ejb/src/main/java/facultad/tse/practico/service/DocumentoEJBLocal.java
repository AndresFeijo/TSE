package facultad.tse.practico.service;

import facultad.tse.practico.datatypes.*;
import jakarta.ejb.Local;

@Local
public interface DocumentoEJBLocal{

    // Agregar un documento
    public void agregar(String paciente, String descripcion, String observaciones);

    // Listar todos los documentos
    public DTListaDocumentos listar();

    // Buscar documentos por paciente
    public DTDocumento buscarPorPaciente(String paciente);

    // Buscar por ID
    public DTDocumento buscarPorId(Integer id);
    
}