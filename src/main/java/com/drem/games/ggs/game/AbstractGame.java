package com.drem.games.ggs.game;

import com.drem.games.ggs.api.IGame;
import com.drem.games.ggs.player.Player;

/**
 * @author drem
 */
public abstract class AbstractGame implements IGame {

	protected Player player1;
	protected Player player2;

	/**
	 * Initializes a game with the players passed in
	 * 
	 * @param player1
	 *            - The first player of the game
	 * @param player2
	 *            - The second player of the game
	 */
	public AbstractGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		initGame();
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	protected abstract void initGame();

}
