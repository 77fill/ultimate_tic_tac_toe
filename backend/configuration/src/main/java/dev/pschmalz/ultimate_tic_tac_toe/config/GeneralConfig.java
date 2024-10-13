package dev.pschmalz.ultimate_tic_tac_toe.config;

import java.net.URL;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.ee10.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.pschmalz.ultimate_tic_tac_toe.websocket.PlayerEndpoint;
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
        });
		
		return jetty;
	}
	
	@Bean
	public Executor executor() {
		return Executors.newFixedThreadPool(10);
	}
}
