package genelectrovise.bizarre.spring.server.gate;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.GsonBuilder;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;

@Service
public class GateMicroservice {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GateMicroservice.class);

	private String cmdAddress = "http://localhost:8081";

	@PostConstruct
	public void findCmdService() {

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
		String url = cmdAddress + "/register";

		// Log request
		LOGGER.info("POSTing " + new GsonBuilder().create().toJson(httpRequestEntity) + " -to- " + url);

		// POST
		ResponseEntity<String> httpResponseEntity = template.postForEntity(url, httpRequestEntity, String.class);

		// Log the response
		LOGGER.info("Recieved response: " + httpResponseEntity.toString());

	}
}
