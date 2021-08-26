package genelectrovise.bizarre.spring.server.cmd;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import genelectrovise.bizarre.spring.api.RegisterServiceRequest;
import genelectrovise.bizarre.spring.server.cmd.register.KeyRegister;
import genelectrovise.bizarre.spring.server.cmd.register.RegisterController;
import genelectrovise.bizarre.spring.server.cmd.register.ServiceRegister;
import genelectrovise.bizarre.spring.server.cmd.register.ServicesController;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CmdMicroservice {

	public static final Logger LOGGER = LoggerFactory.getLogger(CmdMicroservice.class);

	@Value("server.host") String host;

	@Value("server.port") String port;

	@Autowired protected RegisterController registerController;

	@Autowired protected ServicesController servicesController;

	@Autowired protected KeyRegister keyRegister;

	@Autowired protected ServiceRegister serviceRegister;

	public CmdMicroservice() {}

	@PostConstruct
	public void postInit() {
		LOGGER.info("Post-initialising " + CmdMicroservice.class);
		LOGGER.info("Gson has Type Adapter - " + new Gson().getAdapter(RegisterServiceRequest.class).toString() + " - registered for use with class - " + RegisterServiceRequest.class);
	}

	public String getHost() { return host; }

	public String getPort() { return port; }

	public RegisterController getRegisterController() { return registerController; }

	public KeyRegister getKeyRegister() { return keyRegister; }

	public ServiceRegister getServiceRegister() { return serviceRegister; }

}
