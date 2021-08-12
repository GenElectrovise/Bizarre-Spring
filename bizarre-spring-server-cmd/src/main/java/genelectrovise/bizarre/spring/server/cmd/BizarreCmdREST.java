package genelectrovise.bizarre.spring.server.cmd;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;

/**
 * https://www.baeldung.com/apache-camel-spring-boot
 */
public class BizarreCmdREST extends RouteBuilder {

	/**
	 * The root path for the API, i.e. localhost://path
	 */
	@Value("${bizarre.api.path}")
	String camelRootContextPath;

	/**
	 * The port for the Camel servlet
	 */
	@Value("${bizarre.api.serverPort}")
	String serverPort;

	@Override
	public void configure() throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.getClass();

		//
		restConfiguration() //
				.contextPath(camelRootContextPath) //
				.port(serverPort) //
				.enableCORS(true) //
				.apiContextPath("/api-doc") //
				.apiProperty("api.title", "Bizarre REST API") //
				.apiProperty("api.version", "v1") //
				.apiContextRouteId("doc-api") //
				.component("servlet") //
				.bindingMode(RestBindingMode.json) // Allows interfacing with the API through JSON
		;

		// Create REST end-points ON THIS NODE
		rest("/api/") //
				.id("") //
				.post();
		;

		from("");
	}

}
