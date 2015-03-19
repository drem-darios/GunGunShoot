package com.drem.games.ggs.player.action;

import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;

/**
 * @author drem
 */
public class BlockAction implements IPlayerAction {

	private static final long serialVersionUID = -7203015274842062139L;

	@Override
	public PlayerOutcome doAction(Player player1, Player player2,
			IPlayerAction player2Action) {
		// Check if a bullet was shot. If so, call block. If not, continue.
		if (player2.hasWeapon() && player2Action.getActionName() == ActionName.SHOOT) {
			if (player1.canBlock()) {
				player1.block();
				System.out.println("*Ching* Shield up! Your shield has "
						+ Math.abs(player1.getShieldStrength())
						+ " strength left!");
				return PlayerOutcome.SHIELD_DMG;
			} else {
				System.out.println("*Crack* Your opponent has broken through your shield!");
				return PlayerOutcome.DEAD;
			}
		}
		
		System.out.println("Don't be scared. Go out there and fight!");
		return PlayerOutcome.OK;
	}

	@Override
	public ActionName getActionName() {
		return ActionName.BLOCK;
	}

}
