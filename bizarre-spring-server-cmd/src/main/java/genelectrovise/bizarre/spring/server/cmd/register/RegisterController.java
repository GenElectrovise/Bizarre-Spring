package genelectrovise.bizarre.spring.server.cmd.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import genelectrovise.bizarre.spring.api.inter.GetServicesResponse;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;

@RestController
public class RegisterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	protected KeyRegister keyRegister;

	@Autowired
	protected ServiceRegister serviceRegister;

	public RegisterController(ServiceRegister serviceRegister, KeyRegister keyPairGenerator) {
		this.keyRegister = keyPairGenerator;
	}

	@ResponseStatus(code = HttpStatus.OK, reason = "Success. Returning map of all services.")
	@GetMapping("/services")
	GetServicesResponse getServices() {
		return (new GetServicesResponse(serviceRegister.getServices()));
	}

	@ResponseStatus(code = HttpStatus.OK, reason = "Success. Returning map of named service.")
	@GetMapping("/services/{name}")
	GetServicesResponse getService(@PathVariable String name) {
		return serviceRegister.getService(name);
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Success. Service registered. Handshake following.")
	@PostMapping("/register")
	RegisterServiceResponse registerService(@RequestBody RegisterServiceRequest request) {
		RegisterServiceResponse response = serviceRegister.registerService(this, request);
		serviceRegister.doHandshake(request.getType()); // Asynchronous
		return response;
	}

	public void setKeyPairGenerator(KeyRegister keyPairGenerator) {
		this.keyRegister = keyPairGenerator;
	}

	public KeyRegister getKeyPairGenerator() {
		return keyRegister;
	}

}
