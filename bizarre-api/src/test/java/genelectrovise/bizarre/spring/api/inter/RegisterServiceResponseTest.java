package genelectrovise.bizarre.spring.api.inter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;

public class RegisterServiceResponseTest {

	private Gson gson = new GsonBuilder().create();
	
	// {"parent_child_key": "pc","child_parent_key": "cp","host": "local","port": 2020,"type": "type"}

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
		RegisterServiceResponse request = new RegisterServiceResponse("pc", "cp", "local", 2020, "type");

		String json = gson.toJson(request);

		Assertions.assertEquals("{\"parent_child_key\": \"pc\",\"child_parent_key\": \"cp\",\"host\": \"local\",\"port\": 2020,\"type\": \"type\"}", json);
	}

	@Test 
	public void whenHasRegisterServiceResponseJson_deserializesCorrectly() {
		String json = "{\"parent_child_key\": \"pc\",\"child_parent_key\": \"cp\",\"host\": \"local\",\"port\": 2020,\"type\": \"type\"}";

		RegisterServiceResponse request = gson.fromJson(json, RegisterServiceResponse.class);

		Assertions.assertEquals("pc", request.getParentChildKey());
		Assertions.assertEquals("cp", request.getChildParentKey());
		Assertions.assertEquals("local", request.getHost());
		Assertions.assertEquals(2020, request.getPort());
		Assertions.assertEquals("type", request.getType());
	}

}
