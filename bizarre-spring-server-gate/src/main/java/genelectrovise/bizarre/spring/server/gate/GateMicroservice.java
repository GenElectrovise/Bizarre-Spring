package genelectrovise.bizarre.spring.server.gate;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GateMicroservice {

	private static final Logger LOGGER = LoggerFactory.getLogger(GateMicroservice.class);

	@Value("${bizarre.cmdAddress}") String cmdAddress;

	@Autowired KeyStorage keyStorage;
	
	@Autowired HandshakeController handshakeController;

	@PostConstruct
	public void findCmdService() {
		handshakeController.startServiceRegistration();
	}
	
}
