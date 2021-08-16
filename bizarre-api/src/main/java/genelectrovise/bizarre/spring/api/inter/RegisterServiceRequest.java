package genelectrovise.bizarre.spring.api.inter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface RegisterServiceRequest {

	ServiceType getServiceType();

	String getServiceHost();

	int getServicePort();

	@Override
	String toString();
	
	@JsonAdapter(value = RegisterServiceRequest.class)
	public static class Adapter extends TypeAdapter<RegisterServiceRequest> {

		@Override
		public void write(JsonWriter writer, RegisterServiceRequest request) throws IOException {
			writer.beginObject();
			writer.name("serviceType").value(request.getServiceType().getName());
			writer.name("serviceHost").value(request.getServiceHost());
			writer.name("servicePort").value(request.getServicePort());
			writer.endObject();
		}

		@Override
		public RegisterServiceRequest read(JsonReader reader) throws IOException {

			ServiceType getServiceType;
			String getServiceHost;
			int getServicePort;

			JsonObject object = new Gson().fromJson(reader, JsonObject.class);

			// Service type
			String name = object.get("serviceType").getAsString();
			getServiceType = new ServiceType() {
				@Override
				public String getName() {
					return name;
				}
			};

			// Service host
			getServiceHost = object.get("serviceHost").getAsString();

			// Service port
			getServicePort = object.get("servicePort").getAsInt();

			// Return
			return new RegisterServiceRequest() {

				@Override
				public ServiceType getServiceType() {
					return getServiceType;
				}

				@Override
				public int getServicePort() {
					return getServicePort;
				}

				@Override
				public String getServiceHost() {
					return getServiceHost;
				}
			};
		}

	}
}
