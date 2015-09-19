package com.drem.games.ggs.game.mode;

import com.drem.games.ggs.api.IBattleStrategy;
import com.drem.games.ggs.api.IMenu;
import com.drem.games.ggs.api.IWeapon;
import com.drem.games.ggs.game.menu.GameEndMenu;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.player.action.ActionType;
import com.drem.games.ggs.util.Pair;
import com.drem.games.ggs.weapon.WeaponFactory;

/**
 * @author drem
 */
public class RegularMode implements IBattleStrategy {

	private IMenu gameEndMenu = new GameEndMenu();

	@Override
	public void battle(Pair<Player, ActionType> player1Move,
			Pair<Player, ActionType> player2Move) {
		Player player1 = player1Move.getFirst();
		Player player2 = player2Move.getFirst();

		ActionType player1Action = player1Move.getSecond();
		ActionType player2Action = player2Move.getSecond();

		PlayerOutcome playerOutcome = PlayerOutcome.OK;
		PlayerOutcome computerOutcome = PlayerOutcome.OK;

		System.out.println("Player2: " + player2Action.toString());
		System.out.println();

		switch (player1Action) {
		case RELOAD:
			if (player2Action == ActionType.SHOOT) {
				if (player2.hasWeapon()) {
					playerOutcome = PlayerOutcome.DEAD;
					break;
				} else {
					System.out
							.println("Pfftt! Your opponent has shot an empty gun!");
				}

			}
			// Check if the other user has shot. If not, add bullet.
			player1.addBullet();
			System.out.println("*Click* Reloaded! You now have "
					+ player1.getBulletCount() + " bullets.");
			break;
		case SHOOT:
			if (player1.hasWeapon()) {
				if (player2Action == ActionType.SHOOT) {
					if (player2.hasWeapon()) {
						IWeapon pWeapon = WeaponFactory.getWeapon(player1
								.getBulletCount());
						IWeapon cWeapon = WeaponFactory.getWeapon(player2
								.getBulletCount());
						int result = pWeapon.compareTo(cWeapon);
						if (result == 0) {
							declareDraw();
						} else if (result == 1) {
							computerOutcome = PlayerOutcome.DEAD;
							break;
						} else {
							playerOutcome = PlayerOutcome.DEAD;
							break;
						}
						// Compare weapons and declare a winner or draw
						playerOutcome = PlayerOutcome.DEAD;
					} else {
						System.out
								.println("Pfftt! Your opponent has shot an empty gun!");
					}

				} else if (player2Action == ActionType.RELOAD) {
					computerOutcome = PlayerOutcome.DEAD;
				} else {
					player1.useBullet();
					computerOutcome = PlayerOutcome.SHIELD_DMG;
					System.out.println("Pew! Pew! You have "
							+ player1.getBulletCount() + " bullets left!");
				}
			} else {
				if (player2Action == ActionType.SHOOT) {
					if (player2.hasWeapon()) {
						playerOutcome = PlayerOutcome.DEAD;
						break;
					} else {
						System.out
								.println("Pfftt! Your opponent has shot an empty gun!");
					}

				}

				System.out
						.println("*Ptooey* You spit at your opponent. Try getting some bullets.");
			}

			// Check if user is blocking. If not, check if they are reloading.
			// If they are, then you win! If not, check the gun sizes and the
			// bigger one wins!
			break;
		case BLOCK:
			// Check if a bullet was shot and if player could shoot. If so, call
			// block. If not, continue.
			if (player2Action == ActionType.SHOOT) {
				if (player2.hasWeapon()) {
					if (player1.canBlock()) {
						playerOutcome = PlayerOutcome.SHIELD_DMG;
						player1.block();
						System.out.println("*Ching* Shield up! Your shield has "
								+ Math.abs(player1.getShieldStrength())
								+ " strength left!");
					} else {
						playerOutcome = PlayerOutcome.DEAD;
						System.out.println("*Crack* Your shield is broken!");
					}
					break;
				} else {
					System.out
							.println("Pfftt! Your opponent has shot an empty gun!");
				}
			}
			System.out.println("Don't be scared. Go out there and fight!");
			break;
		}

		switch (player2Action) {
		case BLOCK:
			if (player1Action == ActionType.SHOOT && player1.hasWeapon()) {
				if (player2.canBlock()) {
					player2.block();
				} else {
					computerOutcome = PlayerOutcome.DEAD;
				}

			}
			break;
		case SHOOT:
			player2.useBullet();
			break;
		case RELOAD:
			player2.addBullet();
			break;
		}

		if (playerOutcome == PlayerOutcome.DEAD) {
			declareWinner(player2);
		} else if (computerOutcome == PlayerOutcome.DEAD) {
			declareWinner(player1);
		}
	}

	private void declareDraw() {
		System.out
				.println("Violence solves nothing! Everyone dies. It's a draw.");
		gameEndMenu.openMenu();
	}

	// private void declareLoser(Player loser) {
	// System.out.println("Player " + loser.getClass().getSimpleName()
	// + " has lost the game!");
	// exit();
	// }

	private void declareWinner(Player winner) {
		System.out.println(winner.getClass().getSimpleName()
				+ " has won the game!");
		gameEndMenu.openMenu();
	}

}
