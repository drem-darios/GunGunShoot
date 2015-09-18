package com.drem.games.ggs.api;

import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.action.ActionType;
import com.drem.games.ggs.util.Pair;

/**
 * @author drem
 */
public interface IBattleStrategy {

	public void battle(Pair<Player, ActionType> player1Move,
			Pair<Player, ActionType> player2Move);

}
