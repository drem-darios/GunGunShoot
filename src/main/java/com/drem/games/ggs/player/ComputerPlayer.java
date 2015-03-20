package com.drem.games.ggs.player;

import java.util.Random;

import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.player.action.PlayerActionFactory;

/**
 * @author drem
 */
public class ComputerPlayer extends Player {

	private boolean firstMove = true;
	
	public IPlayerAction makeMove() {
		if (firstMove) {
			firstMove = false;
			return PlayerActionFactory.getPlayerAction(2); // FIXME: Put this number somewhere!!
		}
		
		int choice = getRandomChoice();
		if (getBulletCount() == 0 && choice == 1) { // FIXME: Put this number somewhere!!
			return makeMove(); // make move again bc you can't shoot.
		}
		return PlayerActionFactory.getPlayerAction(choice);
	}
	
	private int getRandomChoice() {
		Random r = new Random();
		return r.nextInt(3);
	}
}
