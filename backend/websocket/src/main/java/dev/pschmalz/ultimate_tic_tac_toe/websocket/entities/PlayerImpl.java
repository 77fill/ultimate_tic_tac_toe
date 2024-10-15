package dev.pschmalz.ultimate_tic_tac_toe.websocket.entities;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

import dev.pschmalz.ultimate_tic_tac_toe.logic.MetaGame;
import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.GameStateMessage;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.YourTurnMessage;

public class PlayerImpl implements Player {
	private WebSocketSession session;
	private Gson gson = new Gson();
	
	public PlayerImpl(WebSocketSession session) {
		this.session = session;
	}
	
	@Override
	public void itsYourTurn() {
		try {
			session.sendMessage(new TextMessage(gson.toJson(new YourTurnMessage())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void gameState(MetaGame metaGame) {
		var gameStateMessage = new GameStateMessage();
		
		gameStateMessage.setSymbols(metaGame.getListOfStrings());
		
		try {
			session.sendMessage(new TextMessage(gson.toJson(gameStateMessage)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
