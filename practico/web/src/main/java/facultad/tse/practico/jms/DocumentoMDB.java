package facultad.tse.practico.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Message-Driven Bean que escucha mensajes de la cola DocumentosQueue.
 * Procesa los mensajes de forma asíncrona registrándolos en el log.
 */
@MessageDriven(
    name = "DocumentoMDB",
    activationConfig = {
        @ActivationConfigProperty(
            propertyName = "destinationLookup", 
            propertyValue = "java:/jms/queue/DocumentosQueue"
        ),
        @ActivationConfigProperty(
            propertyName = "destinationType", 
            propertyValue = "jakarta.jms.Queue"
        ),
        @ActivationConfigProperty(
            propertyName = "acknowledgeMode", 
            propertyValue = "Auto-acknowledge"
        )
    }
)
public class DocumentoMDB implements MessageListener {
    
    private static final Logger logger = Logger.getLogger(DocumentoMDB.class.getName());
    
    /**
     * Método invocado automáticamente cuando llega un mensaje a la cola.
     * Este método se ejecuta en un thread separado, de forma asíncrona.
     * 
     * @param message Mensaje JMS recibido
     */
    @Override
    public void onMessage(Message message) {
        try {
            logger.info("========================================");
            logger.info("MDB: Mensaje JMS recibido!");
            logger.info("========================================");
            
            // Verificar el tipo de mensaje
            if (message instanceof ObjectMessage) {
                procesarMensajeObjeto((ObjectMessage) message);
            } else {
                procesarMensajeGenerico(message);
            }
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al procesar mensaje JMS", e);
        }
    }
    
    /**
     * Procesa mensajes de tipo ObjectMessage que contienen DocumentoMensaje
     */
    private void procesarMensajeObjeto(ObjectMessage objectMessage) throws JMSException {
        Object obj = objectMessage.getObject();
        
        if (obj instanceof DocumentoMensaje) {
            DocumentoMensaje docMsg = (DocumentoMensaje) obj;
            
            logger.info("┌─────────────────────────────────────────┐");
            logger.info("│   NUEVO DOCUMENTO CREADO - AUDITORIA    │");
            logger.info("├─────────────────────────────────────────┤");
            logger.info("│ ID Documento: " + docMsg.getDocumentoId());
            logger.info("│ Paciente: " + docMsg.getPaciente());
            logger.info("│ Descripción: " + docMsg.getDescripcion());
            logger.info("│ Observaciones: " + docMsg.getObservaciones());
            logger.info("│ Acción: " + docMsg.getAccion());
            logger.info("│ Fecha: " + docMsg.getFechaCreacion());
            logger.info("└─────────────────────────────────────────┘");
            
            // Aquí podrías agregar lógica adicional:
            // - Guardar en una tabla de auditoría
            // - Enviar email de notificación
            // - Actualizar estadísticas
            // - Enviar a un sistema externo
            
            procesarLogicaNegocio(docMsg);
            
        } else {
            logger.warning("Mensaje recibido no es del tipo esperado: " + obj.getClass());
        }
    }
    
    /**
     * Procesa mensajes genéricos (TextMessage u otros)
     */
    private void procesarMensajeGenerico(Message message) {
        logger.info("Mensaje genérico recibido: " + message.toString());
    }
    
    /**
     * Lógica de negocio adicional al procesar el mensaje.
     * Aquí puedes agregar cualquier procesamiento que necesites.
     */
    private void procesarLogicaNegocio(DocumentoMensaje docMsg) {
        // Ejemplo: Contar documentos procesados
        logger.info("✓ Procesando lógica de negocio para documento ID: " + docMsg.getDocumentoId());
        
        // Simular algún procesamiento
        switch (docMsg.getAccion()) {
            case "CREADO":
                logger.info("  → Acción: Documento nuevo registrado en el sistema");
                // Aquí podrías: guardar en auditoría, enviar notificación, etc.
                break;
            case "ACTUALIZADO":
                logger.info("  → Acción: Documento modificado");
                break;
            case "ELIMINADO":
                logger.info("  → Acción: Documento eliminado del sistema");
                break;
            default:
                logger.info("  → Acción desconocida: " + docMsg.getAccion());
        }
        
        logger.info("✓ Procesamiento completado exitosamente");
    }
}