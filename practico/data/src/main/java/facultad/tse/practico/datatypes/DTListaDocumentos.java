package facultad.tse.practico.datatypes;

import java.util.List;

public class DTListaDocumentos {
	public List<DTDocumento> documentos;
	
	public DTListaDocumentos(List<DTDocumento> docs) {
		documentos = docs;
	}
	
	public List<DTDocumento> obtenerLista() {
		return documentos;
	}
}


