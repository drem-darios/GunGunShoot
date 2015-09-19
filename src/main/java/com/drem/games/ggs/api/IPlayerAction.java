package com.drem.games.ggs.api;

import java.io.Serializable;

import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.player.action.ActionType;

/**
 * @author drem
 */
public interface IPlayerAction extends Serializable {

	public PlayerOutcome doAction(Player player1, Player player2, IPlayerAction otherPlayerAction);
	public ActionType getActionName();
}
