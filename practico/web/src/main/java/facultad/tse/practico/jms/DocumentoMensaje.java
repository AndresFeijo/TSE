package facultad.tse.practico.jms;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO que se enviará por JMS cuando se cree un documento.
 * Debe implementar Serializable para poder ser enviado por JMS.
 */
public class DocumentoMensaje implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer documentoId;
    private String paciente;
    private String descripcion;
    private String observaciones;
    private LocalDateTime fechaCreacion;
    private String accion; // "CREADO", "ACTUALIZADO", "ELIMINADO"
    
    public DocumentoMensaje() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public DocumentoMensaje(Integer documentoId, String paciente, String descripcion, 
                           String observaciones, String accion) {
        this.documentoId = documentoId;
        this.paciente = paciente;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.accion = accion;
        this.fechaCreacion = LocalDateTime.now();
    }
    
    // Getters y Setters
    
    public Integer getDocumentoId() {
        return documentoId;
    }
    
    public void setDocumentoId(Integer documentoId) {
        this.documentoId = documentoId;
    }
    
    public String getPaciente() {
        return paciente;
    }
    
    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public String getAccion() {
        return accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    @Override
    public String toString() {
        return "DocumentoMensaje{" +
                "documentoId=" + documentoId +
                ", paciente='" + paciente + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", accion='" + accion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}