package facultad.tse.practico.jms;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EJB Stateless que actúa como productor de mensajes JMS.
 * Usa @Inject con @JMSConnectionFactory para especificar el ConnectionFactory correcto.
 */
@Stateless
public class DocumentoJMSProducer {
    
    private static final Logger logger = Logger.getLogger(DocumentoJMSProducer.class.getName());
    
    /**
     * JMSContext inyectado mediante CDI.
     * IMPORTANTE: Especificar qué ConnectionFactory usar con @JMSConnectionFactory
     */
    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext jmsContext;
    
    /**
     * Cola JMS inyectada usando @Resource.
     */
    @Resource(lookup = "java:/jms/queue/DocumentosQueue")
    private Queue documentosQueue;
    
    /**
     * Envía un mensaje JMS cuando se crea un documento.
     */
    public void enviarMensajeDocumentoCreado(DocumentoMensaje mensaje) {
        try {
            logger.info("📤 Enviando mensaje JMS para documento ID: " + mensaje.getDocumentoId());
            
            // Crear el productor JMS
            JMSProducer producer = jmsContext.createProducer();
            
            // Enviar el mensaje como objeto serializable
            producer.send(documentosQueue, mensaje);
            
            logger.info("✅ Mensaje JMS enviado exitosamente para documento: " + mensaje.getPaciente());
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al enviar mensaje JMS", e);
        }
    }
    
    /**
     * Envía un mensaje JMS con información específica del documento.
     */
    public void enviarMensaje(Integer documentoId, String paciente, String descripcion, 
                             String observaciones, String accion) {
        DocumentoMensaje mensaje = new DocumentoMensaje(
            documentoId, 
            paciente, 
            descripcion, 
            observaciones, 
            accion
        );
        enviarMensajeDocumentoCreado(mensaje);
    }
    
    /**
     * Envía un mensaje simple con solo texto.
     */
    public void enviarMensajeTexto(String mensajeTexto) {
        try {
            logger.info("📤 Enviando mensaje de texto JMS: " + mensajeTexto);
            
            JMSProducer producer = jmsContext.createProducer();
            producer.send(documentosQueue, mensajeTexto);
            
            logger.info("✅ Mensaje de texto JMS enviado exitosamente");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al enviar mensaje de texto JMS", e);
        }
    }
}