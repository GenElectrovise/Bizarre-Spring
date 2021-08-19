package genelectrovise.bizarre.spring.server.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import genelectrovise.bizarre.spring.server.cmd.ui.CmdWindow;

/**
 *
 */
@SpringBootApplication
public class BizarreSpringServerCmd {

	@Autowired
	CmdWindow window;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(BizarreSpringServerCmd.class).headless(false).run(args);
		ctx.getBean(CmdWindow.class);
	}
}
