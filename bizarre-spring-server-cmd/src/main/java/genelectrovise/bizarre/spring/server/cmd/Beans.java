package genelectrovise.bizarre.spring.server.cmd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import genelectrovise.bizarre.spring.server.cmd.register.ServiceRegister;
import genelectrovise.bizarre.spring.server.cmd.ui.CmdWindow;

@Configuration
public class Beans {

	@Bean
	ServiceRegister newServiceRegister() {
		return new ServiceRegister();
	}

	@Bean
	CmdWindow newCmdWindow() {
		return new CmdWindow();
	}
}
