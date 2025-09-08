package facultad.tse.practico.model;

import java.time.LocalDateTime;

public class Documento {
	Integer id;
	LocalDateTime fecha;
	String paciente, descripcion, observaciones;
	
	public Documento() {
		this.id = 0;
		this.fecha = LocalDateTime.now();
		this.paciente = "";
		this.descripcion = "";
		this.observaciones = "";
	}
	
	public Documento(Integer id, LocalDateTime fecha, String paciente, String descripcion, String observaciones) {
		this.id = id;
		this.fecha = fecha;
		this.paciente = paciente;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
	}
	
	 // Getters y setters
	
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}


