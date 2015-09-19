package com.drem.games.ggs.player.action;

import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;

/**
 * @author drem
 */
public class ReloadAction implements IPlayerAction {

	private static final long serialVersionUID = -7203015274842062139L;

	@Override
	public PlayerOutcome doAction(Player player1, Player player2, IPlayerAction player2Action) {
		if (player2.hasWeapon() && player2Action.getActionName() == ActionType.SHOOT) {
			System.out.println("Oh no! You dead!");
			return PlayerOutcome.DEAD;
		}
		// Check if the other user has shot. If not, add bullet.
		player1.addBullet();
		System.out.println("*Click* Reloaded! You now have "
				+ player1.getBulletCount() + " bullets.");
		
		return PlayerOutcome.OK;
	}

	@Override
	public ActionType getActionName() {
		return ActionType.RELOAD;
	}

}
