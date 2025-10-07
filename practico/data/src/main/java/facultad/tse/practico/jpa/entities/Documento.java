package facultad.tse.practico.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un Documento en la base de datos.
 * Esta clase está mapeada a la tabla "documentos" en PostgreSQL.
 */
@Entity
@Table(name = "documentos")
@NamedQueries({
    @NamedQuery(
        name = "DocumentoEntity.findAll",
        query = "SELECT d FROM DocumentoEntity d ORDER BY d.fecha DESC"
    ),
    @NamedQuery(
        name = "DocumentoEntity.findByPaciente",
        query = "SELECT d FROM DocumentoEntity d WHERE d.paciente LIKE :paciente ORDER BY d.fecha DESC"
    ),
    @NamedQuery(
        name = "DocumentoEntity.findById",
        query = "SELECT d FROM DocumentoEntity d WHERE d.id = :id"
    )
})

public class Documento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * ID autogenerado usando secuencia de PostgreSQL
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    /**
     * Fecha y hora de creación del documento
     */
    @NotNull(message = "La fecha no puede ser nula")
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    
    /**
     * Nombre del paciente
     */
    @NotNull(message = "El paciente no puede ser nulo")
    @Size(min = 1, max = 50, message = "El nombre del paciente debe tener entre 1 y 50 caracteres")
    @Column(name = "paciente", nullable = false, length = 255)
    private String paciente;
    
    /**
     * Descripción del documento
     */
    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 1, max = 1000, message = "La descripción debe tener entre 1 y 1000 caracteres")
    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;
    
    /**
     * Observaciones adicionales
     */
    @Column(name = "observaciones", length = 2000)
    private String observaciones;
    
    // Constructores
    
    public Documento() {
        this.fecha = LocalDateTime.now();
        this.paciente = "";
        this.descripcion = "";
        this.observaciones = "";
    }
    
    public Documento(LocalDateTime fecha, String paciente, String descripcion, String observaciones) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
    }
    
    // Getters y Setters
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
    
    // Métodos lifecycle de JPA
    
    /**
     * Se ejecuta antes de persistir la entidad por primera vez
     */
    @PrePersist
    protected void onCreate() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
    }
    
    /**
     * Se ejecuta antes de actualizar la entidad
     */
    @PreUpdate
    protected void onUpdate() {
        // Aquí podrías agregar lógica de auditoría si lo necesitas
    }
    
    // equals y hashCode basados en ID
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento that = (Documento) o;
        return id != null && id.equals(that.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
    @Override
    public String toString() {
        return "DocumentoEntity{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", paciente='" + paciente + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}