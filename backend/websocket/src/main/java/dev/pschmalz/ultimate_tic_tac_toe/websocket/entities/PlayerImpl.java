package dev.pschmalz.ultimate_tic_tac_toe.websocket.entities;

import java.io.IOException;

import dev.pschmalz.ultimate_tic_tac_toe.logic.Player;
import jakarta.websocket.Session;

public class PlayerImpl implements Player {
	private Session session;
	
	public PlayerImpl(Session session) {
		this.session = session;
	}
	
	@Override
	public void itsYourTurn() {
		try {
			session.getBasicRemote().sendText("It's your turn!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
