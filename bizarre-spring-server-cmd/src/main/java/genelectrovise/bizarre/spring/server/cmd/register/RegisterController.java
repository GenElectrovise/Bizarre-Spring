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
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;
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
	ResponseEntity<RegisterServiceResponse> registerService(@RequestBody RegisterServiceRequest.Concrete request) {

		ResponseEntity<RegisterServiceResponse> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		if (request.getHost() == null)
			throw new InvalidRegistrationPacketException("Host is null. ", "host", request.getHost());
		if (request.getPort() == 0)
			throw new InvalidRegistrationPacketException("Port is 0. ", "port", request.getPort());
		if (request.getType() == null)
			throw new InvalidRegistrationPacketException("Service Type is null. ", "type", request.getPort());

		response = ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegisterServiceResponse.Concrete(42));

		LOGGER.info("Sending response: " + response);

		return response;
	}

}
