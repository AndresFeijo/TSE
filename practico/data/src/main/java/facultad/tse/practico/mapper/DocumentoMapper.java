package facultad.tse.practico.mapper;

import facultad.tse.practico.clases.Documento;
import facultad.tse.practico.datatypes.DTDocumento;
import facultad.tse.practico.jpa.entities.DocumentoEntity;
import jakarta.ejb.Stateless;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase utilitaria para convertir entre Entity, Domain y DTO.
 * Patrón Mapper para mantener las capas desacopladas.
 */
@Stateless
public class DocumentoMapper {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    /**
     * Convierte de Entity JPA a objeto de dominio
     */
    public Documento entityToDomain(DocumentoEntity entity) {
        if (entity == null) return null;
        
        return new Documento(
            entity.getId(),
            entity.getFecha(),
            entity.getPaciente(),
            entity.getDescripcion(),
            entity.getObservaciones()
        );
    }
    
    /**
     * Convierte de objeto de dominio a Entity JPA
     */
    public DocumentoEntity domainToEntity(Documento documento) {
        if (documento == null) return null;
        
        DocumentoEntity entity = new DocumentoEntity();
        entity.setId(documento.getId());
        entity.setFecha(documento.getFecha());
        entity.setPaciente(documento.getPaciente());
        entity.setDescripcion(documento.getDescripcion());
        entity.setObservaciones(documento.getObservaciones());
        
        return entity;
    }
    
    /**
     * Convierte de Entity JPA a DTO
     */
    public DTDocumento entityToDTO(DocumentoEntity entity) {
        if (entity == null) return null;
        
        return new DTDocumento(
            entity.getId(),
            entity.getFecha() != null ? entity.getFecha().format(FORMATTER) : "",
            entity.getPaciente(),
            entity.getDescripcion(),
            entity.getObservaciones()
        );
    }
    
    /**
     * Convierte de DTO a Entity JPA
     */
    public DocumentoEntity dtoToEntity(DTDocumento dto) {
        if (dto == null) return null;
        
        DocumentoEntity entity = new DocumentoEntity();
        entity.setId(dto.getId());
        
        // Parsear la fecha del String
        if (dto.getFecha() != null && !dto.getFecha().isEmpty()) {
            entity.setFecha(LocalDateTime.parse(dto.getFecha(), FORMATTER));
        }
        
        entity.setPaciente(dto.getPaciente());
        entity.setDescripcion(dto.getDescripcion());
        entity.setObservaciones(dto.getObservaciones());
        
        return entity;
    }
    
    /**
     * Convierte de objeto de dominio a DTO
     */
    public DTDocumento domainToDTO(Documento documento) {
        if (documento == null) return null;
        
        return new DTDocumento(
            documento.getId(),
            documento.getFecha() != null ? documento.getFecha().format(FORMATTER) : "",
            documento.getPaciente(),
            documento.getDescripcion(),
            documento.getObservaciones()
        );
    }
    
    /**
     * Convierte de DTO a objeto de dominio
     */
    public Documento dtoToDomain(DTDocumento dto) {
        if (dto == null) return null;
        
        LocalDateTime fecha = null;
        if (dto.getFecha() != null && !dto.getFecha().isEmpty()) {
            fecha = LocalDateTime.parse(dto.getFecha(), FORMATTER);
        }
        
        return new Documento(
            dto.getId(),
            fecha,
            dto.getPaciente(),
            dto.getDescripcion(),
            dto.getObservaciones()
        );
    }
}