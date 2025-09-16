package facultad.tse.practico.beans;

import facultad.tse.practico.clases.Documento;
import facultad.tse.practico.service.DocumentoEJBLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@Named("documentoBean")
@RequestScoped
public class DocumentoBean implements Serializable {

    @EJB
    private DocumentoEJBLocal service;

    private List<Documento> documentos;
    private Documento resultado;
    private Integer idBuscar;
    private String descripcion;
    private String paciente;
    private String observaciones;
    
    public String guardar() {
        service.agregar(1, paciente, descripcion, observaciones);
        documentos = service.listar(); // refresca la lista
        return "listar?faces-redirect=true"; // navega a listar.xhtml
    }

    // getters y setters
    public List<Documento> getDocumentos() { return documentos; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @PostConstruct
    public void init() {
        documentos = service.listar();
    }



    public void buscar() {
        if (idBuscar != null) {
            resultado = service.buscarPorId(idBuscar);
        }
    }

    public Documento getResultado() { return resultado; }
    public Integer getIdBuscar() { return idBuscar; }
    public void setIdBuscar(Integer idBuscar) { this.idBuscar = idBuscar; }
    
    
}
