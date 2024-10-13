package dev.pschmalz.ultimate_tic_tac_toe.websocket;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/player")
public class PlayerEndpoint {
	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("HERE");
	}
	
	
}
