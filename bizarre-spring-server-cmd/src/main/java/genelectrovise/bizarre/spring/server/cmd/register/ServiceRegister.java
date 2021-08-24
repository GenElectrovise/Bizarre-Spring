package genelectrovise.bizarre.spring.server.cmd.register;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import genelectrovise.bizarre.spring.api.inter.BackendFor;
import genelectrovise.bizarre.spring.api.inter.ChildService;
import genelectrovise.bizarre.spring.api.inter.GetServicesResponse;
import genelectrovise.bizarre.spring.api.inter.KeyPair;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;
import genelectrovise.bizarre.spring.server.cmd.CmdMicroservice;

public class ServiceRegister {

	Map<String, ChildService> services;

	@Autowired
	CmdMicroservice cmd;

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegister.class);

	public ServiceRegister(Map<String, ChildService> services) {
		this.services = services;
	}

	public void setServices(Map<String, ChildService> services) {
		this.services = services;
	}

	@BackendFor("RegisterController.getServices()")
	public Map<String, ChildService> getServices() {
		return services;
	}

	@BackendFor("RegisterController.getService()")
	public GetServicesResponse getService(String name) {
		return new GetServicesResponse(services.get(name));
	}

	@BackendFor("RegisterController.registerService()")
	public RegisterServiceResponse registerService(RegisterController register, RegisterServiceRequest request) {

		RegisterServiceResponse response;

		if (request.getHost() == null)
			throw new InvalidRegistrationPacketException("Host is null. ", "host", request.getHost());
		if (request.getPort() == 0)
			throw new InvalidRegistrationPacketException("Port is 0. ", "port", request.getPort());
		if (request.getType() == null)
			throw new InvalidRegistrationPacketException("Service Type is null. ", "type", request.getPort());

		ChildService child = new ChildService(request.getHost(), request.getPort(), request.getType());
		services.put(child.getName(), child);

		KeyPair keyPair = register.getKeyPairGenerator().generateKeyPair();
		register.getKeyPairGenerator().registerKeyPair(request.getType(), keyPair);
		response = new RegisterServiceResponse(keyPair.getParentChildKey(), keyPair.getChildParentKey(), request.getHost(), request.getPort(), request.getType());

		LOGGER.info("Sending response: " + response);
		return response;
	}

	/**
	 * 
	 * @param serviceName The name of the service to follow up with
	 */
	@Async
	void doHandshake(String serviceName) {
		ChildService child = getServices().get(serviceName);

		/*
		 * RestTemplate template = new RestTemplate(); HttpHeaders headers = new
		 * HttpHeaders();
		 * 
		 * // Configure content RegisterServiceRequest registerServiceRequest = new
		 * RegisterServiceRequest.Concrete("gate", "localhost", 8082);
		 * headers.setContentType(MediaType.APPLICATION_JSON);
		 * headers.setAccept(List.of(MediaType.APPLICATION_JSON)); String json = new
		 * GsonBuilder().create().toJson(registerServiceRequest);
		 * 
		 * // Package into request HttpEntity<String> httpRequestEntity = new
		 * HttpEntity<>(json, headers);
		 * 
		 * // Get destination String url = cmdAddress + "/register";
		 * 
		 * // Log request LOGGER.info("POSTing " + new
		 * GsonBuilder().create().toJson(httpRequestEntity) + " -to- " + url);
		 * 
		 * // POST ResponseEntity<String> httpResponseEntity =
		 * template.postForEntity(url, httpRequestEntity, String.class);
		 * 
		 * // Log the response LOGGER.info("Recieved response: " +
		 * httpResponseEntity.toString());
		 */
	}
}
