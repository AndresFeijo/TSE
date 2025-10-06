package facultad.tse.practico.controladores;

import facultad.tse.practico.dao.DocumentoDAO;
import facultad.tse.practico.datatypes.DTDocumento;
import facultad.tse.practico.datatypes.DTListaDocumentos;
import facultad.tse.practico.jpa.entities.DocumentoEntity;
import facultad.tse.practico.mapper.DocumentoMapper;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Controlador Singleton que gestiona los documentos.
 * Ahora usa JPA para persistencia en PostgreSQL.
 */
@Singleton
@Startup  // Se inicializa al arrancar la aplicación
public class Doc implements DocLocal, DocRemoto {
    
    private static final Logger logger = Logger.getLogger(Doc.class.getName());
    
    // Inyección del DAO para acceso a datos
    @EJB
    private DocumentoDAO documentoDAO;
    
    // Inyección del Mapper para conversiones
    @EJB
    private DocumentoMapper mapper;
    
    /**
     * Constructor por defecto
     */
    public Doc() {
        // Constructor vacío requerido por EJB
    }
    
    /**
     * Método que se ejecuta después de la construcción del bean.
     * Carga datos iniciales si la base de datos está vacía.
     */
    @PostConstruct
    public void inicializar() {
        logger.info("Inicializando controlador de documentos...");
        
        // Verificar si la base de datos ya tiene datos
        long count = documentoDAO.count();
        
        if (count == 0) {
            logger.info("Base de datos vacía. Cargando datos de prueba...");
            cargarDatosPrueba();
        } else {
            logger.info("Base de datos ya contiene " + count + " documentos.");
        }
    }
    
    /**
     * Carga datos de prueba iniciales en la base de datos
     */
    private void cargarDatosPrueba() {
        agregar("Juan Pérez", "Consulta general", "Paciente refiere dolor de cabeza persistente");
        agregar("María Gómez", "Control anual", "Sin observaciones relevantes");
        agregar("Carlos López", "Análisis de sangre", "Esperando resultados del laboratorio");
        agregar("Ana Torres", "Consulta ginecológica", "Paciente menciona ciclos irregulares");
        agregar("Pedro Martínez", "Chequeo post-operatorio", "Cicatriz en buen estado, sin complicaciones");
        agregar("Lucía Fernández", "Consulta pediátrica", "Niño con tos leve desde hace 3 días");
        agregar("Roberto Díaz", "Radiografía de tórax", "Posible neumonía, enviar a especialista");
        agregar("Laura Rodríguez", "Vacunación", "Aplicada dosis de refuerzo contra influenza");
        agregar("Sofía Silva", "Consulta traumatológica", "Esguince leve en tobillo derecho");
        agregar("Martín Castro", "Control odontológico", "Carie tratada en molar superior izquierdo");
        
        logger.info("Datos de prueba cargados exitosamente.");
    }
    
    /**
     * Agrega un nuevo documento a la base de datos
     * 
     * @param paciente Nombre del paciente
     * @param descripcion Descripción del documento
     * @param observaciones Observaciones adicionales
     * @return DTO del documento creado
     * @throws IllegalArgumentException si el paciente es nulo o vacío
     */
    public DTDocumento agregar(String paciente, String descripcion, String observaciones) {
        logger.info("Agregando nuevo documento para paciente: " + paciente);
        
        // Validación
        if (paciente == null || paciente.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacío.");
        }
        
        // Crear la entidad
        DocumentoEntity entity = new DocumentoEntity();
        entity.setFecha(LocalDateTime.now());
        entity.setPaciente(paciente);
        entity.setDescripcion(descripcion != null ? descripcion : "");
        entity.setObservaciones(observaciones != null ? observaciones : "");
        
        // Persistir en la base de datos
        DocumentoEntity creado = documentoDAO.create(entity);
        
        // Convertir a DTO y retornar
        return mapper.entityToDTO(creado);
    }
    
    /**
     * Lista todos los documentos de la base de datos
     * 
     * @return Lista de DTOs de documentos
     */
    @Override
    public DTListaDocumentos listar() {
        logger.info("Listando todos los documentos");
        
        // Obtener todas las entidades
        List<DocumentoEntity> entities = documentoDAO.findAll();
        
        // Convertir a DTOs
        List<DTDocumento> dtos = entities.stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
        
        return new DTListaDocumentos(dtos);
    }
    
    /**
     * Busca documentos por nombre de paciente (búsqueda parcial, case-insensitive)
     * 
     * @param paciente Nombre o parte del nombre del paciente
     * @return DTO del primer documento encontrado, o null si no hay coincidencias
     */
    @Override
    public DTDocumento buscarPorPaciente(String paciente) {
        logger.info("Buscando documento por paciente: " + paciente);
        
        if (paciente == null || paciente.trim().isEmpty()) {
            return null;
        }
        
        // Buscar en la base de datos
        List<DocumentoEntity> entities = documentoDAO.findByPaciente(paciente);
        
        // Retornar el primero encontrado (o null si la lista está vacía)
        if (!entities.isEmpty()) {
            return mapper.entityToDTO(entities.get(0));
        }
        
        return null;
    }
    
    /**
     * Busca todos los documentos por nombre de paciente
     * 
     * @param paciente Nombre o parte del nombre del paciente
     * @return Lista de DTOs de documentos que coinciden
     */
    public DTListaDocumentos buscarTodosPorPaciente(String paciente) {
        logger.info("Buscando todos los documentos por paciente: " + paciente);
        
        List<DocumentoEntity> entities = documentoDAO.findByPaciente(paciente);
        
        List<DTDocumento> dtos = entities.stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
        
        return new DTListaDocumentos(dtos);
    }
    
    /**
     * Busca un documento por su ID
     * 
     * @param id ID del documento
     * @return DTO del documento encontrado, o null si no existe
     */
    @Override
    public DTDocumento buscarPorId(Integer id) {
        logger.info("Buscando documento por ID: " + id);
        
        if (id == null) {
            return null;
        }
        
        Optional<DocumentoEntity> entity = documentoDAO.findById(id);
        
        return entity.map(mapper::entityToDTO).orElse(null);
    }
    
    /**
     * Actualiza un documento existente
     * 
     * @param dto DTO con los datos actualizados (debe incluir el ID)
     * @return DTO del documento actualizado
     * @throws IllegalArgumentException si el ID es nulo o el documento no existe
     */
    public DTDocumento actualizar(DTDocumento dto) {
        logger.info("Actualizando documento ID: " + dto.getId());
        
        if (dto.getId() == null) {
            throw new IllegalArgumentException("El ID del documento no puede ser nulo.");
        }
        
        // Verificar que el documento existe
        Optional<DocumentoEntity> existente = documentoDAO.findById(dto.getId());
        if (!existente.isPresent()) {
            throw new IllegalArgumentException("Documento con ID " + dto.getId() + " no encontrado.");
        }
        
        // Convertir DTO a Entity
        DocumentoEntity entity = mapper.dtoToEntity(dto);
        
        // Actualizar en la base de datos
        DocumentoEntity actualizado = documentoDAO.update(entity);
        
        // Convertir de vuelta a DTO
        return mapper.entityToDTO(actualizado);
    }
    
    /**
     * Elimina un documento por su ID
     * 
     * @param id ID del documento a eliminar
     * @return true si se eliminó exitosamente, false si no existía
     */
    public boolean eliminar(Integer id) {
        logger.info("Eliminando documento ID: " + id);
        
        if (id == null) {
            return false;
        }
        
        return documentoDAO.delete(id);
    }
    
    /**
     * Cuenta el total de documentos en la base de datos
     * 
     * @return Cantidad de documentos
     */
    public long contarDocumentos() {
        return documentoDAO.count();
    }
    
    /**
     * Método helper para convertir de Documento a DTDocumento
     * (Mantenido por compatibilidad, aunque ya no se usa internamente)
     * 
     * @deprecated Usar DocumentoMapper en su lugar
     */
    @Deprecated
    public DTDocumento DocToDTDoc(facultad.tse.practico.clases.Documento doc) {
        if (doc == null) return null;
        return new DTDocumento(
            doc.getId(), 
            doc.getFecha() != null ? doc.getFecha().toString() : "", 
            doc.getPaciente(), 
            doc.getDescripcion(), 
            doc.getObservaciones()
        );
    }
}