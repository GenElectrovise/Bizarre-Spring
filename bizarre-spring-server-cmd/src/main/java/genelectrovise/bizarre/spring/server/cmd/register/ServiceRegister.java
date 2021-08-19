package genelectrovise.bizarre.spring.server.cmd.register;

import java.util.Map;

import com.google.common.collect.Maps;

public class ServiceRegister {

	private Map<String, String> services;

	public ServiceRegister() {
		this(Maps.newHashMap());
	}

	public ServiceRegister(Map<String, String> services) {
		this.services = services;
	}

	public Map<String, String> getServices() {
		return services;
	}
	
}
