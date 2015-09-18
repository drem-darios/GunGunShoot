package com.drem.games.ggs.player.action;

import com.drem.games.ggs.api.IPlayerAction;
import com.drem.games.ggs.api.IWeapon;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.weapon.WeaponFactory;

/**
 * @author drem
 */
public class ShootAction implements IPlayerAction {

	private static final long serialVersionUID = -7203015274842062139L;
	
	@Override
	public PlayerOutcome doAction(Player player1, Player player2, IPlayerAction player2Action) {
		
		if (player1.hasWeapon()) {
			if (player2.hasWeapon() && player2Action.getActionName() == ActionType.SHOOT) {
				IWeapon pWeapon = WeaponFactory.getWeapon(player1
						.getBulletCount());
				IWeapon cWeapon = WeaponFactory.getWeapon(player2
						.getBulletCount());
				int result = pWeapon.compareTo(cWeapon);
				if (result == 0) {
					System.out.println("You pulled out the same gun as your opponent!");
					return PlayerOutcome.DEAD;
				} else if (result == 1) {
					player1.useBullet();
					System.out.println("Pew! Pew! You have "
							+ player1.getBulletCount() + " bullets left!");
					return PlayerOutcome.OK;
				} else {
					System.out.println("Your opponent's weapon is stronger!");
					return PlayerOutcome.DEAD;
				}
			} else {
				player1.useBullet();
				System.out.println("Pew! Pew! You have "
						+ player1.getBulletCount() + " bullets left!");
				return PlayerOutcome.OK;
			}
		} else {
			if (player2.hasWeapon() && player2Action.getActionName() == ActionType.SHOOT) {
				System.out.println("Oh no! You dead!");
				return PlayerOutcome.DEAD;
			}

			System.out
					.println("*Ptooey* You spit at your opponent. Try getting some bullets.");
			return PlayerOutcome.OK;
		}
	}

	@Override
	public ActionType getActionName() {
		return ActionType.SHOOT;
	}

}
