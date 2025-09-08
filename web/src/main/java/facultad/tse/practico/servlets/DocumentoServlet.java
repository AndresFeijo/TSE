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
    @EJB
    private DocumentoEJBLocal service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        String descripcion = req.getParameter("descripcion");
        String paciente = req.getParameter("paciente");
        String observaciones = req.getParameter("observaciones");

        service.agregar(new Random().nextInt(10000), paciente, descripcion, observaciones);
        resp.sendRedirect("listar.jsp");
    }
}

