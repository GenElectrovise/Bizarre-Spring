package genelectrovise.bizarre.spring.server.cmd.register;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import genelectrovise.bizarre.spring.api.inter.GetRegisteredServicesResponse;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.server.cmd.CmdMicroservice;

@RestController
public class RegisterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private CmdMicroservice service;

	@GetMapping("/register")
	GetRegisteredServicesResponse getRegisteredServices() {
		return new GetRegisteredServicesResponse() {
			@Override
			public List<String> getServices() {
				return new ArrayList<>(service.getServices().keySet());
			}
		};
	}

	@PostMapping("/register")
	ResponseEntity<RegisterServiceResponse> registerService(@RequestBody RegisterServiceRequest request) {

		ResponseEntity<RegisterServiceResponse> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		LOGGER.info("Recieved registration POST with RegisterServiceRequest " + request.toString());

		if (request.getServiceHost() == null)
			throw new InvalidRegistrationPacketException("Host is null. ", "host", request.getServiceHost());
		if (request.getServicePort() == 0)
			throw new InvalidRegistrationPacketException("Port is 0. ", "port", request.getServicePort());
		if (request.getServiceType() == null)
			throw new InvalidRegistrationPacketException("Service Type is null. ", "type", request.getServicePort());

		response = ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegisterServiceResponseImpl(42));

		LOGGER.info("Sending response: " + response);

		return response;
	}

	public static class RegisterServiceResponseImpl implements RegisterServiceResponse {
		
		int ok;

		public RegisterServiceResponseImpl(int ok) {
			this.ok = ok;
		}

		@Override
		public int ok() {
			return ok;
		}
	}
}
