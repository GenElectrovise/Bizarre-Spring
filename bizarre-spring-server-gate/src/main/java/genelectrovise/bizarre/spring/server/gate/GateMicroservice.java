package genelectrovise.bizarre.spring.server.gate;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import genelectrovise.bizarre.spring.api.impl.RegisterServiceRequestImpl;
import genelectrovise.bizarre.spring.api.inter.BizarreService;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;

@Service
public class GateMicroservice {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GateMicroservice.class);

	private String cmdAddress = "http://localhost:8081";
	private BizarreService cmdService;

	@PostConstruct
	public void findCmdService() {
		try {

			// Configure headers
			RestTemplate template = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();

			// Configure content
			RegisterServiceRequest registerServiceRequest = new RegisterServiceRequestImpl(() -> "gate", "localhost", 8082);			
			HttpEntity<RegisterServiceRequest> httpRequestEntity = new HttpEntity<>(registerServiceRequest, null);
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			String url = cmdAddress + "/register";
			String json = new ObjectMapper().writeValueAsString(httpRequestEntity);

			// Log request
			LOGGER.info("POSTing " + json + " -to- " + url);

			// POST
			ResponseEntity<RegisterServiceResponse> httpResponseEntity = template.postForEntity(url, json, RegisterServiceResponse.class);

			// Log the response
			LOGGER.info("Recieved response: " + httpResponseEntity.toString());

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
