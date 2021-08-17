package genelectrovise.bizarre.spring.api.inter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;

public class RegisterServiceResponseTest {

	private Gson gson = new GsonBuilder().create();

	@Test
	public void checkRegisterServiceResponseAdapter_isNotNull() {
		Assertions.assertNotNull(gson.getAdapter(RegisterServiceResponse.class));
	}

	@Test
	public void checkRegisterServiceResponseAdapter_doesNotThrow() {
		Assertions.assertDoesNotThrow(() -> gson.getAdapter(RegisterServiceResponse.class));
	}

	@Test
	public void whenHasRegisterServiceResponsePOJO_serialisesCorrectly() {
		RegisterServiceResponse request = new RegisterServiceResponse.Concrete(42);

		String json = gson.toJson(request);

		Assertions.assertEquals("{\"ok\":42}", json);
	}

	@Test 
	public void whenHasRegisterServiceResponseJson_deserializesCorrectly() {
		String json = "{\"ok\":42}";

		RegisterServiceResponse request = gson.fromJson(json, RegisterServiceResponse.Concrete.class);

		Assertions.assertEquals(42, request.getOk());
	}

}
