package facultad.tse.practico.servlets;

import java.io.IOException;

import facultad.tse.practico.jms.DocumentoJMSProducer;
import facultad.tse.practico.jpa.entities.Documento;
import facultad.tse.practico.service.DocumentoEJBLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet que maneja la creación de documentos.
 * Ahora integrado con JMS para enviar notificaciones asíncronas.
 */
@WebServlet("/DocumentoServlet")
public class DocumentoServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @EJB 
    private DocumentoEJBLocal service;
    
    @EJB
    private DocumentoJMSProducer jmsProducer;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try {
            // Obtener parámetros del formulario
            String descripcion = req.getParameter("descripcion");
            String paciente = req.getParameter("paciente");
            String observaciones = req.getParameter("observaciones");

            // Guardar el documento en la base de datos
            service.agregar(paciente, descripcion, observaciones);
            Documento documentoCreado = service.buscarPorPaciente(paciente);
            
            // *** NUEVO: Enviar mensaje JMS ***
            if (documentoCreado != null && documentoCreado.getId() != null) {
                jmsProducer.enviarMensaje(
                    documentoCreado.getId(),
                    paciente,
                    descripcion,
                    observaciones,
                    "CREADO"
                );
                System.out.println("✓ Mensaje JMS enviado para documento ID: " + documentoCreado.getId());
            }

            // Redirigir al listado
            resp.sendRedirect("ListarDocumentosServlet");
            
        } catch (Exception e) {
            // Manejar error
            e.printStackTrace();
            req.setAttribute("error", "No se pudo guardar el documento: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}