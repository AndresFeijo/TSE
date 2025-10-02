package facultad.tse.practico.mapper;
import facultad.tse.practico.datatypes.DTDocumento;
import jakarta.ejb.EJB;
import facultad.tse.practico.clases.Documento;
import facultad.tse.practico.controladores.DocLocal;

public class DocumentoMapper {
	
	@EJB DocLocal controladorLocal;
	
	public DTDocumento DocToDTDoc(Documento doc) {
		return new DTDocumento(doc.getId(), doc.getFecha().toString(), doc.getPaciente(), doc.getDescripcion(), doc.getObservaciones());
	}
	
	public Documento DTDocToDoc (DTDocumento dtdoc) {
		return controladorLocal.buscarPorId(dtdoc.getId());
		
	}
}
