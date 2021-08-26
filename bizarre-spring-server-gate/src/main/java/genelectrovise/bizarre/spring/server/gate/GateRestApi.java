package genelectrovise.bizarre.spring.server.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import genelectrovise.bizarre.spring.api.HandshakeRequest;
import genelectrovise.bizarre.spring.api.HandshakeResponse;

@RestController
public class GateRestApi {

	@Autowired HandshakeController handshakeController;

	@PostMapping("/handshake")
	@ResponseStatus(code = HttpStatus.OK, reason = "Handshake OK.")
	HandshakeResponse respondToHandshakeRequest(HandshakeRequest request) { return handshakeController.respondToHandshakeRequest(request); }

}
