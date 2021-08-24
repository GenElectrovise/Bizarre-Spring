package genelectrovise.bizarre.spring.server.cmd;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.google.common.collect.HashBiMap;

import genelectrovise.bizarre.spring.server.cmd.register.KeyRegister;
import genelectrovise.bizarre.spring.server.cmd.register.RegisterController;
import genelectrovise.bizarre.spring.server.cmd.register.ServiceRegister;
import genelectrovise.bizarre.spring.server.cmd.register.ServicesController;
import genelectrovise.bizarre.spring.server.cmd.ui.CmdWindow;

@Configuration
public class Beans {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	CmdMicroservice cmdMicroservice() {
		return new CmdMicroservice();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	RegisterController registerController() {
		return new RegisterController();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	ServiceRegister serviceRegister() {
		return new ServiceRegister();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	KeyRegister keyRegister() {
		return new KeyRegister(HashBiMap.create());
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	ServicesController servicesController() {
		return new ServicesController();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	CmdWindow newCmdWindow() {
		return new CmdWindow();
	}

}
