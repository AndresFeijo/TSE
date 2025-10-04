package facultad.tse.practico.beans;

import facultad.tse.practico.datatypes.*;
import facultad.tse.practico.service.DocumentoEJBLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named("documentoBean")
@ViewScoped
public class DocumentoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @EJB
    private DocumentoEJBLocal service;

    private DTListaDocumentos documentos;
    private DTDocumento resultado;
    private String id;
    private String idBuscar;
    private String descripcion;
    private String paciente;
    private String observaciones;

    @PostConstruct
    public void init() {
        documentos = service.listar();
    }

    // Getters y setters
    public DTListaDocumentos getDocumentos() {
        return documentos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public DTDocumento getResultado() {
        return resultado;
    }

    public String getIdBuscar() {
        return idBuscar;
    }

    public void setIdBuscar(String idBuscar) {
        this.idBuscar = idBuscar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Métodos
    public void buscar() {
        if (idBuscar != null) {
            resultado = service.buscarPorId(Integer.parseInt(idBuscar));
        }
    }

    public void guardar() {
        try {
            service.agregar(paciente, descripcion, observaciones);
            documentos = service.listar();
            limpiarFormulario();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Documento creado correctamente"));

        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage("formCrear:messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
    
    private boolean existeId(Integer idAVerificar) {
        if (documentos == null || idAVerificar == null) {
            return false;
        }
        DTDocumento doc = service.buscarPorId(idAVerificar);
        if (doc != null) 
        	return true;
        else return false;
    }
    
    private void limpiarFormulario() {
        id = null;
        paciente = null;
        descripcion = null;
        observaciones = null;
    }
    
    /**
     * Método para preparar un nuevo documento
     * Se llama cuando se hace clic en "Nuevo Documento"
     */
    public void nuevoDocumento() {
        limpiarFormulario();
        System.out.println("Preparando nuevo documento - campos limpiados");
    }
}