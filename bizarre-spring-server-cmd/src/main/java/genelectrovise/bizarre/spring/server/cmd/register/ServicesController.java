package genelectrovise.bizarre.spring.server.cmd.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import genelectrovise.bizarre.spring.api.inter.GetServicesResponse;

@RestController
@Component
public class ServicesController {

	@Autowired private ServiceRegister serviceRegister;

	@ResponseStatus(code = HttpStatus.OK, reason = "Success. Returning map of all services.")
	@GetMapping("/services")
	GetServicesResponse getServices() { return (new GetServicesResponse(serviceRegister.getServices())); }

	@ResponseStatus(code = HttpStatus.OK, reason = "Success. Returning map of named service.")
	@GetMapping("/services/{name}")
	GetServicesResponse getService(@PathVariable String name) {
		return serviceRegister.getService(name);
	}
}
