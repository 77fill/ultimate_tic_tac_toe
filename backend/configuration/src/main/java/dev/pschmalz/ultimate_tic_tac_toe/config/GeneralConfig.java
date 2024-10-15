package dev.pschmalz.ultimate_tic_tac_toe.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.pschmalz.ultimate_tic_tac_toe.logic.management.Lobby;

@Configuration
public class GeneralConfig {
	
	@Bean
	public Lobby lobby() {
		var lobby = new Lobby(executor());
		
		executor().execute(lobby);
		
		return lobby;
	}
	
	@Bean
	public Executor executor() {
		return Executors.newFixedThreadPool(10);
	}
}
