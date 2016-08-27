package javaes.wordpress.com.rest;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Application;

/**
 * @author https://javaes.wordpress.com/
 *
 */
public class ContactApplication extends Application {

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		// Configura o pacote para fazer scan das classes com anota��es REST.
		properties.put("jersey.config.server.provider.packages", "javaes.wordpress.com.rest");
		return properties;
	}
}
