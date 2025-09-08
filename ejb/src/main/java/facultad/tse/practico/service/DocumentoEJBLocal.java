package facultad.tse.practico.service;

import java.util.List;

import facultad.tse.practico.model.Documento;

import jakarta.ejb.Local;

@Local
public interface DocumentoEJBLocal{

    // Agregar un documento
    public void agregar(Integer id, String paciente, String descripcion, String observaciones);

    // Listar todos los documentos
    public List<Documento> listar();

    // Buscar documentos por paciente
    public Documento buscarPorPaciente(String paciente);

    // Buscar por ID
    public Documento buscarPorId(Integer id);
}