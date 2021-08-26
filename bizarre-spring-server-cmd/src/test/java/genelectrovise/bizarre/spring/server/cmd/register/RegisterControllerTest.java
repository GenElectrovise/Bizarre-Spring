package genelectrovise.bizarre.spring.server.cmd.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import genelectrovise.bizarre.spring.api.RegisterServiceRequest;
import genelectrovise.bizarre.spring.api.RegisterServiceResponse;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

	private static final String ANY_PC_KEY = "pc";
	private static final String ANY_CP_KEY = "cp";
	private static final String ANY_TYPE = "any_type";
	private static final String ANY_HOST = "any_host";
	private static final int ANY_PORT = 2020;

	@InjectMocks
	@Autowired private RegisterController controller;

	@Mock private ServiceRegister serviceRegister;
	@Mock private KeyRegister keyRegister;
	@Mock private RegisterServiceRequest request;

	@Test
	public void whenAllOk_thenResponseReturned() {
		Mockito.doNothing().when(controller.getServiceRegister()).doHandshake(Mockito.anyString());

		RegisterServiceResponse response = controller.registerService(request);

		Assertions.assertEquals("pc", response.getParentChildKey());
		Assertions.assertEquals("cp", response.getChildParentKey());
		Assertions.assertEquals(ANY_HOST, response.getHost());
		Assertions.assertEquals(ANY_PORT, response.getPort());
		Assertions.assertEquals(ANY_TYPE, response.getType());
	}

	@Test
	public void whenHostNull_thenThrowsException() {
		Mockito.when(request.getHost()).thenReturn(null);

		Assertions.assertThrows(InvalidRegistrationPacketException.class, () -> { controller.registerService(request); });
	}

	@Test
	public void whenPortNull_thenThrowsException() {
		Mockito.when(request.getHost()).thenReturn(ANY_HOST);
		Mockito.when(request.getPort()).thenReturn(0);

		Assertions.assertThrows(InvalidRegistrationPacketException.class, () -> { controller.registerService(request); });
	}

	@Test
	public void whenTypeNull_thenThrowsException() {
		Mockito.when(request.getHost()).thenReturn(ANY_HOST);
		Mockito.when(request.getPort()).thenReturn(ANY_PORT);
		Mockito.when(request.getType()).thenReturn(null);

		Assertions.assertThrows(InvalidRegistrationPacketException.class, () -> { controller.registerService(request); });

	}
}
