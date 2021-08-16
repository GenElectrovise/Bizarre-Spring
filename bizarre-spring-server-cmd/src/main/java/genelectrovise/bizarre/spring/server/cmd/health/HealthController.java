package genelectrovise.bizarre.spring.server.cmd.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import genelectrovise.bizarre.spring.server.cmd.CmdMicroservice;

@RestController
public class HealthController {
	
	@Autowired
	private CmdMicroservice service;
	
	@GetMapping("/health")
	HealthPacket getHealth() {
		return new HealthPacket(service.getServices());
	}
}
