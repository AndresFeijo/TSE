package facultad.tse.practico.datatypes;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class DTListaDocumentos {
	public List<DTDocumento> documentos;
	
	public DTListaDocumentos() {
		documentos = new ArrayList<>();
	}
	
	public DTListaDocumentos(List<DTDocumento> docs) {
		documentos = docs;
	}
	
	public List<DTDocumento> obtenerLista() {
		return documentos;
	}
	
	public void agregarDocumento(DTDocumento doc) {
		documentos.add(doc);
	}
}


