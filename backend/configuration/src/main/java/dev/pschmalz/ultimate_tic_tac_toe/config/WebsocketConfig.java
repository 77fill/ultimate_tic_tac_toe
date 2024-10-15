package dev.pschmalz.ultimate_tic_tac_toe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import dev.pschmalz.ultimate_tic_tac_toe.websocket.PlayerWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

	@Autowired
	public PlayerWebSocketHandler playerWebSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(playerWebSocketHandler, "/websocket/player")
				.setAllowedOrigins("*");
		
	}

}
