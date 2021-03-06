package genelectrovise.bizarre.spring.api.inter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import genelectrovise.bizarre.spring.api.RegisterServiceRequest;

public class RegisterServiceRequestTest {

	private static final String ANY_TYPE = "any_type";

	private static final String ANY_HOST = "any_host";

	private static final int ANY_PORT = 2020;

	private Gson gson = new GsonBuilder().create();

	private RegisterServiceRequest concrete = new RegisterServiceRequest(ANY_TYPE, ANY_HOST, ANY_PORT);

	@Test
	public void whenAllOk_toStringIsCorrect() {
		String str = RegisterServiceRequest.class.getSimpleName() + "{" + "type=" + ANY_TYPE + " host=" + ANY_HOST + " port=" + ANY_PORT + "}";
		Assertions.assertEquals(str, concrete.toString());
	}

	@Test
	public void whenTypeIsNotNull_returnsNotNull() { Assertions.assertEquals(ANY_TYPE, concrete.getType()); }

	@Test
	public void whenHostIsNotNull_returnsNotNull() { Assertions.assertEquals(ANY_HOST, concrete.getHost()); }

	@Test
	public void whenPortIsNotNull_returnsNotNull() { Assertions.assertEquals(ANY_PORT, concrete.getPort()); }

	@Test
	public void checkRegisterServiceRequestAdapter_isNotNull() { Assertions.assertNotNull(gson.getAdapter(RegisterServiceRequest.class)); }

	@Test
	public void checkRegisterServiceRequestAdapter_doesNotThrow() { Assertions.assertDoesNotThrow(() -> gson.getAdapter(RegisterServiceRequest.class)); }

	@Test
	public void whenHasRegisterServiceRequestPOJO_serialisesCorrectly() {
		RegisterServiceRequest request = new RegisterServiceRequest("gate", "localhost", 2020);

		String json = gson.toJson(request);

		Assertions.assertEquals("{\"type\":\"gate\",\"host\":\"localhost\",\"port\":2020}", json);
	}

	@Test
	public void whenHasRegisterServiceRequestJson_deserializesCorrectly() {
		String json = "{\"type\":\"gate\",\"host\":\"localhost\",\"port\":2020}";

		RegisterServiceRequest request = gson.fromJson(json, RegisterServiceRequest.class);

		Assertions.assertEquals("gate", request.getType());
		Assertions.assertEquals("localhost", request.getHost());
		Assertions.assertEquals(2020, request.getPort());
	}

}
