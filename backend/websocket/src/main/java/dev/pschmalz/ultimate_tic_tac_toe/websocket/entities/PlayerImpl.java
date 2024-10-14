package dev.pschmalz.ultimate_tic_tac_toe.websocket.entities;

import java.io.IOException;

import com.google.gson.Gson;

import dev.pschmalz.ultimate_tic_tac_toe.logic.MetaGame;
import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.GameStateMessage;
import dev.pschmalz.ultimate_tic_tac_toe.websocket.data.YourTurnMessage;
import jakarta.websocket.Session;

public class PlayerImpl implements Player {
	private Session session;
	private Gson gson = new Gson();
	
	public PlayerImpl(Session session) {
		this.session = session;
	}
	
	@Override
	public void itsYourTurn() {
		try {
			session.getBasicRemote().sendText(gson.toJson(new YourTurnMessage()));
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
			session.getBasicRemote().sendText(gson.toJson(gameStateMessage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
