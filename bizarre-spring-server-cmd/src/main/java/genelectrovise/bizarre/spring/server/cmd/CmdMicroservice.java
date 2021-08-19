package genelectrovise.bizarre.spring.server.cmd;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import genelectrovise.bizarre.spring.api.inter.BizarreService;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.server.cmd.register.ServiceRegister;

@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CmdMicroservice implements BizarreService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CmdMicroservice.class);

	private ServiceRegister serviceRegister;

	public CmdMicroservice(ServiceRegister serviceRegister) {
		this.serviceRegister = serviceRegister;
	}

	public ServiceRegister getServiceRegister() {
		return serviceRegister;
	}

	@PostConstruct
	public void postInit() {
		LOGGER.info("Post-initialising " + CmdMicroservice.class);
		LOGGER.info("Gson has Type Adapter - " + new Gson().getAdapter(RegisterServiceRequest.class).toString() + " - registered for use with class - " + RegisterServiceRequest.class);
	}

	@Override
	public String getHost() {
		return "";
	}

	@Override
	public int getPort() {
		return 0;
	}

}
