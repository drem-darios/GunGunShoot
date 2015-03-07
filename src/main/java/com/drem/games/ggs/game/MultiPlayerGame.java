package com.drem.games.ggs.game;

import com.drem.games.ggs.player.Player;

/**
 * @author drem
 */
public class MultiPlayerGame extends AbstractGame {

	public MultiPlayerGame(Player player1, Player player2) {
		super(player1, player2);
	}

	@Override
	public void play() {
		System.out.println("Sorry this has not been implemented yet.");
	}

	@Override
	public void printRules() {
		System.out.println("Multiplayer rules are still under development.");
		
	}

	@Override
	public void exit() {
		System.out.println("Exiting now.");
		System.exit(0);

	}
	
	@Override
	protected void initGame() {
		System.out.println("Multiplayer init game called.");
	}

}
