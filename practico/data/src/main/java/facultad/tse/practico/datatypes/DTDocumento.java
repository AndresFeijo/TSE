package facultad.tse.practico.datatypes;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class DTDocumento {
	Integer id;
	String fecha;
	String paciente, descripcion, observaciones;
	
	public DTDocumento() {
		this.id = 0;
		this.fecha = LocalDateTime.now().toString();
		this.paciente = "";
		this.descripcion = "";
		this.observaciones = "";
	}
	
	public DTDocumento(Integer id, String fecha, String paciente, String descripcion, String observaciones) {
		this.id = id;
		this.fecha = fecha;
		this.paciente = paciente;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
	}
	
	 // Getters y setters
	
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}


