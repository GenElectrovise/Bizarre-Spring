package genelectrovise.bizarre.spring.server.cmd;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import genelectrovise.bizarre.spring.server.cmd.ui.CmdWindow;

@Configuration
public class Beans {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	CmdWindow newCmdWindow() { return new CmdWindow(); }

}
