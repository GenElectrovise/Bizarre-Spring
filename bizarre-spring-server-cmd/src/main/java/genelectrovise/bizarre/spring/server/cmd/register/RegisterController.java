package genelectrovise.bizarre.spring.server.cmd.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;

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

	@Autowired private KeyRegister keyRegister;

	public RegisterController() {
	}

	@ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Success. Service registered. Handshake following.")
	@PostMapping("/register")
	RegisterServiceResponse registerService(@RequestBody RegisterServiceRequest request) {
		RegisterServiceResponse response = serviceRegister.registerService(this, request);
		serviceRegister.doHandshake(request.getType()); // Asynchronous
		return response;
	}

	public void setKeyPairGenerator(KeyRegister keyPairGenerator) { this.keyRegister = keyPairGenerator; }

	public KeyRegister getKeyPairGenerator() { return keyRegister; }

}
