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
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.MatchMaker;
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.data.CellCoordinatesData;
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.events.GameEvent;
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.events.MatchMakerEvent;
import dev.pschmalz.ultimate_tic_tac_toe.logic.match_management.events.MatchMakerEvent.MatchMakerEventType;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.Message;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.entities.PlayerImpl;

@Component
public class PlayerWebSocketHandler extends TextWebSocketHandler {
	private Map<WebSocketSession, Player> players = new Hashtable<>();
	private MatchMaker matchMaker;
	private Gson gson;
	
	public PlayerWebSocketHandler(MatchMaker matchMaker, Gson gson) {
		this.matchMaker = matchMaker;
		this.gson = gson;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		var player = new PlayerImpl(session);
		players.put(session, player);
		
		var event = new MatchMakerEvent();
		event.setPlayer(player);
		
		matchMaker.addEvent(event);
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage msgJson) {
		var msg = gson.fromJson(msgJson.getPayload(), Message.class);
		var event = new GameEvent();
		var coords = new CellCoordinatesData(
				msg.getMetaX(),
				msg.getMetaY(),
				msg.getX(),
				msg.getY());
		
		
		event.setCoords(coords);
		event.setSource(players.get(session));
		
		matchMaker
			.roomOf(players.get(session))
			.ifPresent(room -> 
				room.addEvent(event));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("onClose!");
		var event = new MatchMakerEvent();
		event.setPlayer(players.get(session));
		event.setType(MatchMakerEventType.LEAVE);
		
		matchMaker.addEvent(event);
	}
}
