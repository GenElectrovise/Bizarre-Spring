package genelectrovise.bizarre.spring.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;

@Configuration
public class GsonConfig {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(GsonConfig.class);
	
	@Bean
	public GsonBuilderCustomizer registerCustomTypeAdapters() {
		LOGGER.info("Registering custom Gson Type Adapters");
		
		return gsonBuilder -> gsonBuilder.registerTypeAdapter(RegisterServiceRequest.class, new RegisterServiceRequest.Adapter());
	}
}
