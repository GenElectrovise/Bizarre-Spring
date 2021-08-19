package genelectrovise.bizarre.spring.api.inter;

import java.util.Map;

public interface GetRegisteredServicesResponse {

	Map<String, String> getServices();

	public static class Concrete implements GetRegisteredServicesResponse {
		Map<String, String> services;

		public Concrete(Map<String, String> map) {
			this.services = map;
		}

		public Map<String, String> getServices() {
			return services;
		}

		public void setServices(Map<String, String> services) {
			this.services = services;
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "{" + "services=" + services + "}";
		}
	}
}
