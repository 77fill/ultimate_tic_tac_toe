package dev.pschmalz.ultimate_tic_tac_toe;

import java.util.concurrent.Executor;

import org.eclipse.jetty.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.pschmalz.ultimate_tic_tac_toe.config.GeneralConfig;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.Lobby;

public class App {
	public static void main(String[] args) throws Exception {
		var ctx = new AnnotationConfigApplicationContext();
		ctx.register(GeneralConfig.class);
		ctx.refresh();
		
		var jetty = ctx.getBean(Server.class);
		
		Lobby.instance = new Lobby(ctx.getBean(Executor.class));
		
		jetty.start();
		jetty.join();
	}
}
