package genelectrovise.bizarre.spring.server.cmd.register;

import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

import genelectrovise.bizarre.spring.api.BackendFor;
import genelectrovise.bizarre.spring.api.ChildService;
import genelectrovise.bizarre.spring.api.HandshakeRequest;
import genelectrovise.bizarre.spring.api.HandshakeResponse;
import genelectrovise.bizarre.spring.api.KeyPair;
import genelectrovise.bizarre.spring.api.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.RegisterServiceResponse;

@Component
public class ServiceRegister {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegister.class);

	@Autowired protected KeyRegister keyRegister;

	protected Map<String, ChildService> services;

	public ServiceRegister() { this.services = Maps.newHashMap(); }

	public ServiceRegister(Map<String, ChildService> services) { this.services = services; }

	@BackendFor("RegisterController.getService()")
	public ChildService getService(String name) { return getServices().get(name); }

	@BackendFor("RegisterController.registerService()")
	public RegisterServiceResponse registerService(RegisterServiceRequest request) {

		RegisterServiceResponse response;

		if (request.getHost() == null) { throw new InvalidRegistrationPacketException("Host is null. ", "host", request.getHost()); }
		if (request.getPort() == 0) { throw new InvalidRegistrationPacketException("Port is 0. ", "port", request.getPort()); }
		if (request.getType() == null) { throw new InvalidRegistrationPacketException("Service Type is null. ", "type", request.getPort()); }

		ChildService child = new ChildService(request.getHost(), request.getPort(), request.getType());
		services.put(child.getName(), child);

		KeyPair keyPair = keyRegister.generateKeyPair();
		keyRegister.registerKeyPair(request.getType(), keyPair);
		response = new RegisterServiceResponse(keyPair.getParentChildKey(), keyPair.getChildParentKey(), request.getHost(), request.getPort(), request.getType());

		LOGGER.info("Sending response: " + response);

		return response;
	}

	/**
	 * POST {@link HandshakeRequest} to child, RECIEVE {@link HandshakeResponse}.
	 * Verify return key. Verify checksum.
	 * 
	 * @param serviceName
	 */
	@Async
	void doHandshake(String serviceName, long pause) {
		
		// Wait a moment to allow any previous requests to send
		try {
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		RestTemplate template = new RestTemplate();

		Random random = new Random();
		HandshakeRequest request = new HandshakeRequest(random.nextInt(10000), random.nextInt(10000), "mul", keyRegister.keys.get(serviceName).getParentChildKey());
		HandshakeResponse response = postHandshakeRequest(getServices().get(serviceName).getURL() + "/handshake", request, template);

		checkHandshakeKey(serviceName, response.getCpKey());
		checkChecksum(request, response);
	}

	private HandshakeResponse postHandshakeRequest(String url, HandshakeRequest request, RestTemplate template) { return template.postForEntity(url, request, HandshakeResponse.class).getBody(); }

	private boolean checkHandshakeKey(String serviceName, String cpKey) {
		if (keyRegister.verify(serviceName, cpKey)) { return true; }

		throw new FailedHandshakeException(new UnmatchedKeyException("Key " + cpKey + " does not match any keys on record for " + serviceName));
	}

	private boolean checkChecksum(HandshakeRequest request, HandshakeResponse response) {
		if (request.getI1() * request.getI2() == response.getResult()) { return true; }
		throw new FailedHandshakeException("Checksum of " + request.getI1() + " and " + request.getI2() + " returning " + response.getResult() + " was incorrect");
	}

	@BackendFor("RegisterController.getServices()")
	public Map<String, ChildService> getServices() { return services; }

	public void setServices(Map<String, ChildService> services) { this.services = services; }
}
