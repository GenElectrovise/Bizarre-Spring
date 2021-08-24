package genelectrovise.bizarre.spring.api.inter;

import java.util.HashMap;
import java.util.Map;

public class GetServicesResponse {
	Map<String, ChildService> services;

	public GetServicesResponse(ChildService child) {
		this.services = new HashMap<>();
		services.put(child.getName(), child);
	}

	public GetServicesResponse(Map<String, ChildService> map) {
		this.services = map;
	}

	public Map<String, ChildService> getServices() { return services; }

	public void setServices(Map<String, ChildService> services) { this.services = services; }

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" + "services=" + services + "}";
	}
}