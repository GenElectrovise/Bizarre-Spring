package genelectrovise.bizarre.spring.server.cmd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import genelectrovise.bizarre.spring.api.inter.ServiceRegister;
import genelectrovise.bizarre.spring.server.cmd.register.ServiceRegisterImpl;
import genelectrovise.bizarre.spring.server.cmd.ui.CmdWindow;

@Configuration
public class Beans {

	@Bean
	ServiceRegister newServiceRegister() {
		return new ServiceRegisterImpl();
	}

	@Bean
	CmdWindow newCmdWindow() {
		return new CmdWindow();
	}
	
}
