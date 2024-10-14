package dev.pschmalz.ultimate_tic_tac_toe.websocket;

import com.google.gson.Gson;

import dev.pschmalz.ultimate_tic_tac_toe.logic.management.Lobby;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.data.GameEvent;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.data.LobbyEvent;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.data.RegularCellCoordinatesData;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.Message;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.entities.PlayerImpl;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/player")
public class PlayerEndpoint {
	private PlayerImpl player;
	private Gson gson = new Gson();

	@OnOpen
	public void onOpen(Session session) {
		player = new PlayerImpl(session);
		var event = new LobbyEvent();
		event.setPlayer(player);
		
		Lobby.instance.addEvent(event);
	}
	
	@OnMessage
	public void onMessage(String msgJson) {
		var msg = gson.fromJson(msgJson, Message.class);
		var event = new GameEvent();
		var coords = new RegularCellCoordinatesData();
		coords.setMetaX(msg.getMetaX());
		coords.setMetaY(msg.getMetaY());
		coords.setX(msg.getX());
		coords.setY(msg.getY());
		
		event.setCoords(coords);
		event.setSource(player);
		
		Lobby.instance
			.whereIs(player)
			.ifPresent(room -> room.addEvent(
					event));
	}
	
	@OnClose
	public void onClose() {
		System.out.println("onClose!");
		Lobby.instance.searchAndDestroy(player);
	}
}
