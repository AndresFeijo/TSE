package facultad.tse.practico.jms;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.glassfish.hk2.utilities.reflection.Logger;

public class DocumentoJMSClient {
	private static final Logger log = Logger.getLogger(DocumentoJMSClient.class.getName());
	
	private static final String DEFAULT_MESSAGE = "Hello World";
	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_DESTINATION = "jms/queue/test";
	private static final String DEFAULT_MESSAGE_COUNT = "5";
	
	private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
	private static final String PROVIDER_URL = "https://localhost:8080";
	
	public static void main(String[] args) {
		Context namingContext = null;
		
		try {
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
			env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
			
			namingContext = new InitialContext(env);
			
			String connectionFactoryString = System.getProperty("connection_factory", DEFAULT_CONNECTION_FACTORY);
			
		}
		catch {
			
		}
		
	}
}
