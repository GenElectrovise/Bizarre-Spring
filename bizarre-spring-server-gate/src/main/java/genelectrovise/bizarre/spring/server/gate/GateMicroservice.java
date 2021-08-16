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

import genelectrovise.bizarre.spring.api.inter.BizarreService;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;
import genelectrovise.bizarre.spring.api.inter.ServiceType;

@Service
public class GateMicroservice {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GateMicroservice.class);

	private String cmdAddress = "http://localhost:8081";
	private BizarreService cmdService;

	@PostConstruct
	public void findCmdService() {

		// Configure headers
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		// Configure content
		RegisterServiceRequest registerServiceRequest = new RegisterServiceRequestImpl(() -> "gate", "localhost", 8082);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<RegisterServiceRequest> httpRequestEntity = new HttpEntity<>(registerServiceRequest, headers);

		String url = cmdAddress + "/register";
		String json = new GsonBuilder().create().toJson(httpRequestEntity);

		// Log request
		LOGGER.info("POSTing " + httpRequestEntity + " -to- " + url);

		// POST
		ResponseEntity<RegisterServiceResponse> httpResponseEntity = template.postForEntity(url, httpRequestEntity, RegisterServiceResponse.class);

		// Log the response
		LOGGER.info("Recieved response: " + httpResponseEntity.toString());

	}

	public static class RegisterServiceRequestImpl implements RegisterServiceRequest {

		ServiceType serviceType;
		String serviceHost;
		int servicePort;

		public RegisterServiceRequestImpl(ServiceType serviceType, String serviceHost, int servicePort) {
			this.serviceType = serviceType;
			this.serviceHost = serviceHost;
			this.servicePort = servicePort;
		}

		@Override
		public ServiceType getServiceType() {
			return serviceType;
		}

		@Override
		public String getServiceHost() {
			return serviceHost;
		}

		@Override
		public int getServicePort() {
			return servicePort;
		}
	}
}
