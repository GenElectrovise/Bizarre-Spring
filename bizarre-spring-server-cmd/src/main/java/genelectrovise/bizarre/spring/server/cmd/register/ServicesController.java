package genelectrovise.bizarre.spring.server.cmd.register;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import genelectrovise.bizarre.spring.api.ChildService;

@RestController
@Component
public class ServicesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

	@Autowired private ServiceRegister serviceRegister;

	@ResponseStatus(code = HttpStatus.OK, reason = "Success. Returning map of all services.")
	@GetMapping("/services")
	Map<String, ChildService> getServices() {
		LOGGER.info("Recieved request for all service data");
		return (serviceRegister.getServices());
	}

	@ResponseStatus(code = HttpStatus.OK, reason = "Success. Returning map of named service.")
	@GetMapping("/services/{name}")
	ChildService getService(@PathVariable String name) {
		LOGGER.info("Recieved service data request for " + name);

		return serviceRegister.getService(name);
	}
}
