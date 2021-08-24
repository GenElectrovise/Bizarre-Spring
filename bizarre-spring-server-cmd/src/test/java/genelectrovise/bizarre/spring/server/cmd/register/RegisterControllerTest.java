package genelectrovise.bizarre.spring.server.cmd.register;

import java.util.HashMap;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import genelectrovise.bizarre.spring.api.inter.BizarreService;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.KeyPair;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;
import genelectrovise.bizarre.spring.api.inter.ServiceRegister;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

	private static final String ANY_TYPE = "any_type";

	private static final String ANY_HOST = "any_host";

	private static final int ANY_PORT = 2020;

	@InjectMocks private RegisterController controller;

	@Mock private ServiceRegister serviceRegister;

	@Mock private KeyRegister keyPairGenerator;

	@Mock private RegisterServiceRequest request;

	@Test
	public void whenAllOk_thenResponseReturned() {
		Mockito.when(request.getHost()).thenReturn(ANY_HOST);
		Mockito.when(request.getPort()).thenReturn(ANY_PORT);
		Mockito.when(request.getType()).thenReturn(ANY_TYPE);
		Mockito.when(keyPairGenerator.generateKeyPair()).thenReturn(new KeyPair("pc", "cp"));
		Mockito.when(serviceRegister.getKeyPairs()).thenReturn(new HashMap<>());

		ResponseEntity<RegisterServiceResponse> response = controller.registerService(request);

		Assertions.assertEquals("pc", response.getBody().getParentChildKey());
		Assertions.assertEquals("cp", response.getBody().getChildParentKey());
		Assertions.assertEquals(ANY_HOST, response.getBody().getHost());
		Assertions.assertEquals(ANY_PORT, response.getBody().getPort());
		Assertions.assertEquals(ANY_TYPE, response.getBody().getType());
	}

	@Test
	public void whenHostNull_thenThrowsException() {
		Mockito.when(request.getHost()).thenReturn(null);

		Assertions.assertThrows(InvalidRegistrationPacketException.class, () -> {
			ResponseEntity<RegisterServiceResponse> response = controller.registerService(request);
		});
	}

	@Test
	public void whenPortNull_thenThrowsException() {
		Mockito.when(request.getHost()).thenReturn(ANY_HOST);
		Mockito.when(request.getPort()).thenReturn(0);

		Assertions.assertThrows(InvalidRegistrationPacketException.class, () -> {
			ResponseEntity<RegisterServiceResponse> response = controller.registerService(request);
		});
	}

	@Test
	public void whenTypeNull_thenThrowsException() {
		Mockito.when(request.getHost()).thenReturn(ANY_HOST);
		Mockito.when(request.getPort()).thenReturn(ANY_PORT);
		Mockito.when(request.getType()).thenReturn(null);

		Assertions.assertThrows(InvalidRegistrationPacketException.class, () -> {
			ResponseEntity<RegisterServiceResponse> response = controller.registerService(request);
		});

	}
}
