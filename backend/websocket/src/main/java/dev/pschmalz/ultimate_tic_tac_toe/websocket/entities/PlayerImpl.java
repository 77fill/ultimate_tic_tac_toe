package dev.pschmalz.ultimate_tic_tac_toe.websocket.entities;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

import dev.pschmalz.ultimate_tic_tac_toe.logic.MetaField;
import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.GameStateMessage;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.SetSymbolMessage;
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
	public void gameState(MetaField metaGame) {
		var gameStateMessage = new GameStateMessage();
		
		gameStateMessage.setSymbols(metaGame.getListOfStrings());
		
		try {
			session.sendMessage(new TextMessage(gson.toJson(gameStateMessage)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setSymbol(Symbol symbol) {
		var msg = new SetSymbolMessage();
		msg.setSymbol(symbol.toString());
		try {
			session.sendMessage(new TextMessage(gson.toJson(msg)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
