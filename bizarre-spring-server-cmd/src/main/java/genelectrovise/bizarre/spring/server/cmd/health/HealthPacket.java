package genelectrovise.bizarre.spring.server.cmd.health;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import genelectrovise.bizarre.spring.api.inter.BizarreService;


public class HealthPacket {

	private List<String> connectedServices;

	public HealthPacket(Map<String, BizarreService> connectedServices) {
		List<String> names = new ArrayList<>();
		connectedServices.forEach((name, service) -> names.add(name));
		this.connectedServices = List.copyOf(names);
	}

	public HealthPacket(List<String> connectedServices) {
		this.connectedServices = connectedServices;
	}

	public List<String> getConnectedServices() {
		return connectedServices;
	}

}
