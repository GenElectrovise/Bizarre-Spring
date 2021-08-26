package genelectrovise.bizarre.spring.server.cmd.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import genelectrovise.bizarre.spring.api.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.RegisterServiceResponse;

/**
 * The REST Controller for the registration API
 * 
 * @author adam_
 *
 */
@RestController
public class RegisterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@Autowired private ServiceRegister serviceRegister;

	public RegisterController() {}

	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Success. Service registered. Handshake following.")
	RegisterServiceResponse registerService(@RequestBody RegisterServiceRequest request) {
		LOGGER.info("Recieved " + RegisterServiceRequest.class.getSimpleName() + " - " + request.toString());

		RegisterServiceResponse response = getServiceRegister().registerService(request);
		getServiceRegister().doHandshake(request.getType(), 1000L); // Asynchronous
		return response;
	}

	public ServiceRegister getServiceRegister() { return serviceRegister; }

}
