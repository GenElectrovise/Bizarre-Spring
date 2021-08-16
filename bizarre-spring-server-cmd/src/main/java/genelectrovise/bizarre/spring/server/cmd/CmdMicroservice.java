package genelectrovise.bizarre.spring.server.cmd;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import genelectrovise.bizarre.spring.api.inter.BizarreService;


@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CmdMicroservice implements BizarreService {

	private Map<String, BizarreService> services;

	public CmdMicroservice(Map<String, BizarreService> services) {
		this.services = services;
	}
	
	public Map<String, BizarreService> getServices() {
		return services;
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
