package facultad.tse.practico.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import facultad.tse.practico.model.Documento;
import facultad.tse.practico.service.DocumentoEJBLocal;

/**
 * Servlet implementation class ListarDocumentosServlet
 */
@WebServlet("/ListarDocumentosServlet")
public class ListarDocumentosServlet extends HttpServlet {
    @EJB
    private DocumentoEJBLocal docEJB;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarDocumentosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Documento> lista = docEJB.listar();
        req.setAttribute("documentos", lista);
        req.getRequestDispatcher("/listar.jsp").forward(req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
