package facultad.tse.practico.servlets;

import java.io.IOException;
import java.util.Random;

import facultad.tse.practico.service.DocumentoEJBLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**	
 * Servlet implementation class DocumentoServlet
 */
@WebServlet("/DocumentoServlet")
public class DocumentoServlet extends HttpServlet {
    @EJB DocumentoEJBLocal service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String descripcion = req.getParameter("descripcion");
            String paciente = req.getParameter("paciente");
            String observaciones = req.getParameter("observaciones");

            service.agregar(new Random().nextInt(10000), paciente, descripcion, observaciones);

            // si todo sale bien → redirige al listado
            resp.sendRedirect("ListarDocumentosServlet");
        } catch (Exception e) {
            // log para ver el detalle en servidor
            e.printStackTrace();

            // redirigir a una página de error o mostrar mensaje
            req.setAttribute("error", "No se pudo guardar el documento: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
