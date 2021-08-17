package genelectrovise.bizarre.spring.api.inter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface RegisterServiceRequest {

	public static final String TYPE_KEY = "type";

	String getType();

	public static final String HOST_KEY = "host";

	String getHost();

	public static final String PORT_KEY = "port";

	int getPort();

	@Override
	String toString();

	public static class Adapter extends TypeAdapter<RegisterServiceRequest> {

		@Override
		public void write(JsonWriter writer, RegisterServiceRequest request) throws IOException {
			writer.beginObject();
			writer.name(TYPE_KEY).value(request.getType());
			writer.name(HOST_KEY).value(request.getHost());
			writer.name(PORT_KEY).value(request.getPort());
			writer.endObject();
		}

		@Override
		public RegisterServiceRequest read(JsonReader reader) throws IOException {

			String type;
			String host;
			int port;

			JsonObject object = new Gson().fromJson(reader, JsonObject.class);

			// Get values
			type = object.get(TYPE_KEY).getAsString();
			host = object.get(HOST_KEY).getAsString();
			port = object.get(PORT_KEY).getAsInt();

			// Return
			return new RegisterServiceRequest.Concrete(type, host, port);
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "{" + "no fields" + "}";
		}

	}

	public static class Concrete implements RegisterServiceRequest {

		String type;
		String host;
		int port;

		public Concrete(String type, String host, int port) {
			this.type = type;
			this.host = host;
			this.port = port;
		}

		@Override
		public String getType() {
			return type;
		}

		@Override
		public String getHost() {
			return host;
		}

		@Override
		public int getPort() {
			return port;
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "{" + "type=" + type + " host=" + host + " port=" + port + "}";
		}

	}
}
