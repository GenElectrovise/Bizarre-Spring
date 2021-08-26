package genelectrovise.bizarre.spring.server.gate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.GsonBuilder;

import genelectrovise.bizarre.spring.api.BackendFor;
import genelectrovise.bizarre.spring.api.HandshakeRequest;
import genelectrovise.bizarre.spring.api.HandshakeResponse;
import genelectrovise.bizarre.spring.api.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.RegisterServiceResponse;

@RestController
public class HandshakeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HandshakeController.class);

	@Autowired GateMicroservice gate;

	@Autowired KeyStorage keyStorage;

	@BackendFor("GateMicroservice.findCmdService")
	void startServiceRegistration() {
		LOGGER.info("Preparing to access CMD at " + gate.cmdAddress);

		// Configure headers
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		// Configure content
		RegisterServiceRequest registerServiceRequest = new RegisterServiceRequest("gate", "localhost", 8082);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		String json = new GsonBuilder().create().toJson(registerServiceRequest);

		// Package into request
		HttpEntity<String> httpRequestEntity = new HttpEntity<>(json, headers);

		// Get destination
		String url = gate.cmdAddress + "/register";

		// Log request
		LOGGER.info("POSTing " + new GsonBuilder().create().toJson(httpRequestEntity) + " -to- " + url);

		// POST
		RegisterServiceResponse response = template.postForEntity(url, httpRequestEntity, RegisterServiceResponse.class).getBody();

		// Log the response
		LOGGER.info("Recieved response: " + response.toString());

		keyStorage.storePair(response.getChildParentKey(), response.getParentChildKey());
	}

	@BackendFor("GateRestApi.respondToHandshakeRequest")
	public HandshakeResponse respondToHandshakeRequest(HandshakeRequest request) { return new HandshakeResponse(request.getI1() * request.getI2(), keyStorage.getChildParentKey()); }
}
