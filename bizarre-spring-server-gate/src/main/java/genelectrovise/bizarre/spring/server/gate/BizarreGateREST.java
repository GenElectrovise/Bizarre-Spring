package genelectrovise.bizarre.spring.server.gate;

import java.util.List;

import javax.servlet.Servlet;

/**
 * https://www.baeldung.com/apache-camel-spring-boot
 */
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class BizarreGateREST extends RouteBuilder {

	@Bean
	public ServletRegistrationBean<Servlet> servletRegistrationBean() {
		ServletRegistrationBean<Servlet> registration = new ServletRegistrationBean<>(new CamelHttpTransportServlet(),
				"/camel/*");
		registration.setName("CamelServlet");
		return registration;
	}

	@Override
	public void configure() throws Exception {
		CamelContext context = new DefaultCamelContext();

		restConfiguration() // Configure all REST services
				.component("servlet") // We are using the default Camel servlet to collect messages
				.host("localhost") // The servlet will listen on localhost
				.port(8082) // The servlet will listen on this port
				.bindingMode(RestBindingMode.json) // We expect to receive JSON 
		;

		rest() // REST with no prefix
				.get("/hello-world") // On getting hello-world
				.produces(MediaType.APPLICATION_JSON_VALUE) // Produces a JSON
				.route() // Start a new route
				.setBody(constant("Welcome to Bizarre")) // Set the response body
		;

		from("timer:bizarre-timer?period=10000") // Make a timer
				.process(exchange -> {
					List<Route> routes = exchange.getContext().getRoutes();

					StringBuilder builder = new StringBuilder("Routes = ");
					routes.forEach((route) -> {
						builder.append("(" + route.getRouteId() + ":in=" + route.getEndpoint() + ")");
					});

					exchange.getIn().setBody(builder.toString());
				}) //
				.to("log:bizarre-logger") // Log
		;

	}

}
