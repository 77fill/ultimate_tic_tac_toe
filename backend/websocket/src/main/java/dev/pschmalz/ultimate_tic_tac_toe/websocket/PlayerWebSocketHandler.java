package dev.pschmalz.ultimate_tic_tac_toe.websocket;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.Lobby;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.data.GameEvent;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.data.LobbyEvent;
import dev.pschmalz.ultimate_tic_tac_toe.logic.management.data.RegularCellCoordinatesData;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.Message;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.entities.PlayerImpl;

@Component
public class PlayerWebSocketHandler extends TextWebSocketHandler {
	private Map<WebSocketSession, Player> players = new Hashtable<>();
	private Lobby lobby;
	private Gson gson;
	
	public PlayerWebSocketHandler(Lobby lobby, Gson gson) {
		this.lobby = lobby;
		this.gson = gson;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		var player = new PlayerImpl(session);
		players.put(session, player);
		
		var event = new LobbyEvent();
		event.setPlayer(player);
		
		lobby.addEvent(event);
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage msgJson) {
		var msg = gson.fromJson(msgJson.getPayload(), Message.class);
		var event = new GameEvent();
		var coords = new RegularCellCoordinatesData();
		coords.setMetaX(msg.getMetaX());
		coords.setMetaY(msg.getMetaY());
		coords.setX(msg.getX());
		coords.setY(msg.getY());
		
		event.setCoords(coords);
		event.setSource(players.get(session));
		
		lobby
			.whereIs(players.get(session))
			.ifPresent(room -> room.addEvent(
					event));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("onClose!");
		lobby.searchAndDestroy(players.get(session));
	}
}
