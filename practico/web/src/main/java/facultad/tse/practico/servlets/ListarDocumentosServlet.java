package facultad.tse.practico.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import facultad.tse.practico.jpa.entities.Documento;
import facultad.tse.practico.service.DocumentoEJBLocal;

/**
 * Servlet implementation class ListarDocumentosServlet
 */
@WebServlet("/ListarDocumentosServlet")
public class ListarDocumentosServlet extends HttpServlet {
    @EJB DocumentoEJBLocal docEJB;
       
    public ListarDocumentosServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	String idParam = req.getParameter("id");
    	List<Documento> lista = new ArrayList<>();
    	if (idParam != null && !idParam.isEmpty()) {
    		Integer id = Integer.parseInt(idParam);
    		Documento doc = docEJB.buscarPorId(id);
    		if (doc == null) {
    			req.setAttribute("busqueda", "no");
    		}
    			
    		else lista.add(doc);
    	}
    	else {
    		lista = docEJB.listar();
    	}
        req.setAttribute("documentos", lista);
        req.getRequestDispatcher("/listar.jsp").forward(req, resp);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
