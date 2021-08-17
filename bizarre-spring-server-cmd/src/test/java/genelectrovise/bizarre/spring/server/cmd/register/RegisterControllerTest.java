package genelectrovise.bizarre.spring.server.cmd.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import genelectrovise.bizarre.spring.api.inter.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.inter.RegisterServiceResponse;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

	private static final String ANY_TYPE = "any_type";

	private static final String ANY_HOST = "any_host";

	private static final int ANY_PORT = 2020;

	@InjectMocks
	private RegisterController controller;

	@Mock
	private RegisterServiceRequest.Concrete request;

	@Test
	public void whenAllOk_thenResponseReturned() {
		Mockito.when(request.getHost()).thenReturn(ANY_HOST);
		Mockito.when(request.getPort()).thenReturn(ANY_PORT);
		Mockito.when(request.getType()).thenReturn(ANY_TYPE);

		ResponseEntity<RegisterServiceResponse> response = controller.registerService(request);

		Assertions.assertEquals(response.getBody().getOk(), 42);
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
