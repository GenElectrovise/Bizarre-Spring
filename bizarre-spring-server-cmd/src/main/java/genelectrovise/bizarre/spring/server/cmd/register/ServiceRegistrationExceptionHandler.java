package genelectrovise.bizarre.spring.server.cmd.register;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceRegistrationExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistrationExceptionHandler.class);

	@ExceptionHandler(InvalidRegistrationPacketException.class)
	ResponseEntity<Object> handleInvalidServiceType(InvalidRegistrationPacketException e) {
		
		LOGGER.error("Handling " + InvalidRegistrationPacketException.class.getSimpleName() + " with " + e.getMessage());
		
		Map<Object, Object> body = new LinkedHashMap<>();

		body.put(e.getKey(), e.getValue());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
