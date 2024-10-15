package dev.pschmalz.ultimate_tic_tac_toe.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

import dev.pschmalz.ultimate_tic_tac_toe.websocket.PlayerEndpoint;
import jakarta.websocket.Endpoint;
import jakarta.websocket.server.ServerEndpointConfig;

@Configuration
public class GeneralConfig {
	@Bean
	public Server jetty() {
		var jetty = new Server(8080);
		
		
		
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        jetty.setHandler(servletContextHandler);
        
        

        // Add javax.websocket support
        JakartaWebSocketServletContainerInitializer.configure(servletContextHandler, (context, container) ->
        {
            // Add echo endpoint to server container
            ServerEndpointConfig echoConfig = ServerEndpointConfig.Builder.create(PlayerEndpoint.class, "/websocket/player").build();
            container.addEndpoint(echoConfig);
            
            container.setDefaultMaxSessionIdleTimeout(Long.MAX_VALUE);
            
            
        });
		
		return jetty;
	}
	
	@Bean
	public PlayerEndpoint playerEndpoint() {
		return new PlayerEndpoint();
	}
	
	@Bean
	public Executor executor() {
		return Executors.newFixedThreadPool(10);
	}
}
