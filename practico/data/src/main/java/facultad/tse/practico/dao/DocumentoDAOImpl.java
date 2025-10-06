package facultad.tse.practico.dao;

import facultad.tse.practico.jpa.entities.DocumentoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Implementación del DAO para operaciones de persistencia de Documento.
 * Este EJB Stateless maneja todas las operaciones de base de datos.
 */
@Stateless
public class DocumentoDAOImpl implements DocumentoDAO {
    
    private static final Logger logger = Logger.getLogger(DocumentoDAOImpl.class.getName());
    
    /**
     * EntityManager inyectado automáticamente por el contenedor.
     * Referencia al persistence unit "practicoPU" definido en persistence.xml
     */
    @PersistenceContext(unitName = "practicoPU")
    private EntityManager entityManager;
    
    @Override
    public DocumentoEntity create(DocumentoEntity documento) {
        logger.info("Creando nuevo documento para paciente: " + documento.getPaciente());
        entityManager.persist(documento);
        entityManager.flush(); // Forzar la generación del ID
        logger.info("Documento creado con ID: " + documento.getId());
        return documento;
    }
    
    @Override
    public DocumentoEntity update(DocumentoEntity documento) {
        logger.info("Actualizando documento ID: " + documento.getId());
        DocumentoEntity updated = entityManager.merge(documento);
        entityManager.flush();
        return updated;
    }
    
    @Override
    public boolean delete(Integer id) {
        logger.info("Intentando eliminar documento ID: " + id);
        DocumentoEntity documento = entityManager.find(DocumentoEntity.class, id);
        if (documento != null) {
            entityManager.remove(documento);
            logger.info("Documento eliminado exitosamente");
            return true;
        }
        logger.warning("Documento no encontrado para eliminar");
        return false;
    }
    
    @Override
    public Optional<DocumentoEntity> findById(Integer id) {
        logger.info("Buscando documento por ID: " + id);
        DocumentoEntity documento = entityManager.find(DocumentoEntity.class, id);
        return Optional.ofNullable(documento);
    }
    
    @Override
    public List<DocumentoEntity> findAll() {
        logger.info("Obteniendo todos los documentos");
        TypedQuery<DocumentoEntity> query = entityManager.createNamedQuery(
            "DocumentoEntity.findAll", 
            DocumentoEntity.class
        );
        return query.getResultList();
    }
    
    @Override
    public List<DocumentoEntity> findByPaciente(String paciente) {
        logger.info("Buscando documentos por paciente: " + paciente);
        TypedQuery<DocumentoEntity> query = entityManager.createNamedQuery(
            "DocumentoEntity.findByPaciente", 
            DocumentoEntity.class
        );
        query.setParameter("paciente", "%" + paciente + "%");
        return query.getResultList();
    }
    
    @Override
    public long count() {
        logger.info("Contando total de documentos");
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(d) FROM DocumentoEntity d", 
            Long.class
        );
        return query.getSingleResult();
    }
}