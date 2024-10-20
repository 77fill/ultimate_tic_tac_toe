package dev.pschmalz.ultimate_tic_tac_toe.websocket.entities;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

import dev.pschmalz.ultimate_tic_tac_toe.logic.MetaField;
import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.CellCoordinates;
import dev.pschmalz.ultimate_tic_tac_toe.logic.data.Symbol;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.GameStateMessage;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.NetworkMessage;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.SetSymbolMessage;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.VictoryMessage;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.ViolationMessage;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.YourTurnMessage;

public class PlayerImpl implements Player {
	private WebSocketSession session;
	private Gson gson = new Gson();
	
	public PlayerImpl(WebSocketSession session) {
		this.session = session;
	}
	
	@Override
	public void itsYourTurnAnyField() {
		var msg = new YourTurnMessage();
		sendMessage(msg);
	}
	
	@Override 
	public void itsYourTurn(int metaX, int metaY) {
		var msg = new YourTurnMessage();
		msg.addCurrentFieldCoord(metaX);
		msg.addCurrentFieldCoord(metaY);
		
		sendMessage(msg);
	}

	@Override
	public void gameState(MetaField metaField) {
		var gameStateMessage = new GameStateMessage();
		
		gameStateMessage.setSymbols(metaField.toListOfStrings());
		
		sendMessage(gameStateMessage);
	}

	@Override
	public void setSymbol(Symbol symbol) {
		var msg = new SetSymbolMessage();
		msg.setSymbol(symbol.toString());
		
		sendMessage(msg);
	}

	@Override
	public void violation(MetaField metaField) {
		var msg = new ViolationMessage();
		msg.setSymbols(metaField.toListOfStrings());
		
		sendMessage(msg);
	}

	private void sendMessage(NetworkMessage msg) {
		try {
			session.sendMessage(new TextMessage(gson.toJson(msg)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void victory(Symbol symbol, CellCoordinates metaCoords) {
		var msg = new VictoryMessage();
		msg.setSymbol(symbol.toString());
		msg.addCoords(metaCoords.getX(), metaCoords.getY());
		
		sendMessage(msg);
	}

	@Override
	public void victory(Symbol symbol) {
		var msg = new VictoryMessage();
		msg.setSymbol(symbol.toString());
		
		sendMessage(msg);
	}
}
