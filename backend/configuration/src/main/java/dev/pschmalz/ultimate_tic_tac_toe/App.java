package dev.pschmalz.ultimate_tic_tac_toe;

import org.eclipse.jetty.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.pschmalz.ultimate_tic_tac_toe.config.GeneralConfig;

public class App {
	public static void main(String[] args) throws Exception {
		var ctx = new AnnotationConfigApplicationContext();
		ctx.register(GeneralConfig.class);
		ctx.refresh();
		
		var jetty = ctx.getBean(Server.class);
		
		jetty.start();
		jetty.join();
	}
}
