package genelectrovise.bizarre.spring.api.inter;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public interface RegisterServiceResponse {

	public static final int OK = 42;
	public static final String OK_KEY = "ok";

	int getOk();

	public static class Adapter extends TypeAdapter<RegisterServiceResponse> {

		@Override
		public void write(JsonWriter writer, RegisterServiceResponse request) throws IOException {
			writer.beginObject();
			writer.name(OK_KEY).value(request.getOk());
			writer.endObject();
		}

		@Override
		public RegisterServiceResponse read(JsonReader reader) throws IOException {

			int port;

			JsonObject object = new Gson().fromJson(reader, JsonObject.class);

			// Get values
			port = object.get(OK_KEY).getAsInt();

			// Return
			return new RegisterServiceResponse.Concrete(port);
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "{" + "no fields" + "}";
		}

	}

	public static class Concrete implements RegisterServiceResponse {

		int ok;

		public Concrete(int ok) {
			this.ok = ok;
		}

		@Override
		public int getOk() {
			return ok;
		}

		@Override
		public String toString() {
			return getClass().getSimpleName() + "{" + "ok=" + ok + "}";
		}

	}
}
