package facultad.tse.practico.dao;

import jakarta.ejb.Local;
import java.util.List;
import java.util.Optional;

import facultad.tse.practico.jpa.entities.Documento;

/**
 * Interfaz local para operaciones CRUD de Documento
 */
@Local
public interface DocumentoDAO {
    
    /**
     * Crea un nuevo documento en la base de datos
     * @param documento Entidad a persistir
     * @return Documento persistido con ID generado
     */
    Documento create(Documento documento);
    
    /**
     * Actualiza un documento existente
     * @param documento Entidad con datos actualizados
     * @return Documento actualizado
     */
    Documento update(Documento documento);
    
    /**
     * Elimina un documento por ID
     * @param id ID del documento a eliminar
     * @return true si se eliminó, false si no existía
     */
    boolean delete(Integer id);
    
    /**
     * Busca un documento por ID
     * @param id ID del documento
     * @return Optional con el documento si existe
     */
    Optional<Documento> findById(Integer id);
    
    /**
     * Obtiene todos los documentos
     * @return Lista de todos los documentos
     */
    List<Documento> findAll();
    
    /**
     * Busca documentos por nombre de paciente (búsqueda parcial)
     * @param paciente Nombre o parte del nombre del paciente
     * @return Lista de documentos que coinciden
     */
    List<Documento> findByPaciente(String paciente);
    
    /**
     * Cuenta el total de documentos
     * @return Cantidad de documentos
     */
    long count();
}