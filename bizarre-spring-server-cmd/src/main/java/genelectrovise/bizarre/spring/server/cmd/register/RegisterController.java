package genelectrovise.bizarre.spring.server.cmd.register;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import genelectrovise.bizarre.spring.api.inter.GetRegisteredServicesResponse;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;
import genelectrovise.bizarre.spring.server.cmd.CmdMicroservice;

@RestController
public class RegisterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private CmdMicroservice service;

	@GetMapping("/services")
	ResponseEntity<GetRegisteredServicesResponse> getRegisteredServices(@RequestParam(required = false) String name) {

		// If name null, empty, or "all_services"
		if (name == null || name == "" || name.equals("all_services")) {
			GetRegisteredServicesResponse responseBody = new GetRegisteredServicesResponse.Concrete(service.getServiceRegister().getServices());
			ResponseEntity<GetRegisteredServicesResponse> responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseBody);
			
			LOGGER.info(responseEntity.toString());
			
			return responseEntity;
		}

		// If name doesn't exist
		if (!service.getServiceRegister().getServices().containsKey(name)) {
			GetRegisteredServicesResponse responseBody = new GetRegisteredServicesResponse.Concrete(Maps.newHashMap());
			ResponseEntity<GetRegisteredServicesResponse> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
			return responseEntity;
		}

		// If name exists
		if (service.getServiceRegister().getServices().containsKey(name)) {

			// Prepare content
			String content = service.getServiceRegister().getServices().get(name);
			HashMap<String, String> contentMap = Maps.newHashMap();
			contentMap.put(name, name);

			// Prepare response
			GetRegisteredServicesResponse responseBody = new GetRegisteredServicesResponse.Concrete(Maps.newHashMap());
			BodyBuilder builder;

			// null -> internal server error :: not null -> ok
			if (content == null || content == "") {
				builder = ResponseEntity.internalServerError();
			} else {
				builder = ResponseEntity.ok();
			}
			ResponseEntity<GetRegisteredServicesResponse> responseEntity = builder.body(responseBody);

			return responseEntity;
		}

		// How did you get here??
		return ResponseEntity.badRequest().body(new GetRegisteredServicesResponse.Concrete(Maps.newHashMap()));
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

		service.getServiceRegister();

		response = ResponseEntity.status(HttpStatus.ACCEPTED).body(new RegisterServiceResponse.Concrete(42));

		LOGGER.info("Sending response: " + response);

		// Will be sent asynchronously
		afterRespondingToRegisterService_followUpWithKeyExchange();

		return response;
	}

	@Async
	void afterRespondingToRegisterService_followUpWithKeyExchange() {

	}

}
