package com.drem.games.ggs.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IMenu;
import com.drem.games.ggs.api.IWeapon;
import com.drem.games.ggs.game.menu.GameEndMenu;
import com.drem.games.ggs.player.ComputerPlayer;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.weapon.WeaponAction;
import com.drem.games.ggs.weapon.WeaponFactory;

/**
 * @author drem
 */
public class SinglePlayerGame extends AbstractGame {

	private IMenu gameEndMenu = new GameEndMenu();
	private Scanner inputScanner;
	public SinglePlayerGame(Player player1, ComputerPlayer player2) {
		super(player1, player2);
	}

	@Override
	public void printRules() {
		System.out.println("************************");
		System.out.println("How to play:");
		System.out.println(" 1 - Block \n 2 - Shoot \n 3 - Reload "
				+ "\n 4 - Game State \n 0 - QUIT");
		System.out.println("************************");
		System.out.println();
	}

	@Override
	public void exit() {
		inputScanner.close();
		System.out.println("Goodbye!!!!");
		System.exit(0);
	}

	@Override
	public void play() {
		inputScanner = new Scanner(System.in);
		try {
			int choice = inputScanner.nextInt();
			while (choice != 0) {
				if (choice == 4) {
					System.out.print("Player:");
					printPlayer(player1);
					System.out.print("Computer:");
					printPlayer(player2);
					System.out.println();
					choice = inputScanner.nextInt();
					continue;
				} else if (choice > 4) {
					System.out.println("Please stick to the options given.");
					printRules();
					choice = inputScanner.nextInt();
					continue;
				}

				WeaponAction action = WeaponAction.fromValue(choice - 1);
				WeaponAction computerAction = ((ComputerPlayer) player2)
						.makeMove();
				makeMove(action, computerAction);

				choice = inputScanner.nextInt();
			}
		} catch (InputMismatchException e) {
			System.out
					.println("Numbers only please! Take a look at the rules again.");
			printRules();
			play();
		}

		gameEndMenu.openMenu();
	}

	@Override
	protected void initGame() {
		printRules();
	}


	private void makeMove(WeaponAction playerAction, WeaponAction computerAction) {
		PlayerOutcome playerOutcome = PlayerOutcome.OK;
		PlayerOutcome computerOutcome = PlayerOutcome.OK;

		System.out.println("Computer: " + computerAction.toString());
		System.out.println();

		switch (playerAction) {
		case RELOAD:
			if (computerAction == WeaponAction.SHOOT) {
				playerOutcome = PlayerOutcome.DEAD;
				break;
			}
			// Check if the other user has shot. If not, add bullet.
			player1.addBullet();
			System.out.println("*Click* Reloaded! You now have "
					+ player1.getBulletCount() + " bullets.");
			break;
		case SHOOT:
			if (player1.hasWeapon()) {
				if (computerAction == WeaponAction.SHOOT) {
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
				} else if (computerAction == WeaponAction.RELOAD) {
					computerOutcome = PlayerOutcome.DEAD;
				} else {
					player1.useBullet();
					computerOutcome = PlayerOutcome.SHIELD_DMG;
					System.out.println("Pew! Pew! You have "
							+ player1.getBulletCount() + " bullets left!");
				}
			} else {
				if (computerAction == WeaponAction.SHOOT) {
					playerOutcome = PlayerOutcome.DEAD;
					break;
				}

				System.out
						.println("*Ptooey* You spit at your opponent. Try getting some bullets.");
			}

			// Check if user is blocking. If not, check if they are reloading.
			// If they are, then you win! If not, check the gun sizes and the
			// bigger one wins!
			break;
		case BLOCK:
			// Check if a bullet was shot. If so, call block. If not, continue.
			if (computerAction == WeaponAction.SHOOT) {
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
			}
			System.out.println("Don't be scared. Go out there and fight!");
			break;
		}

		switch (computerAction) {
		case BLOCK:
			if (playerAction == WeaponAction.SHOOT) {
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
		System.out.println("Violence solves nothing! Everyone dies. It's a draw.");
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

	private void printPlayer(Player player) {
		System.out.println();
		System.out.println("Bullets: " + player.getBulletCount());
		System.out.println("Shield: " + Math.abs(player.getShieldStrength()));
		if (player.hasWeapon()) {
			System.out.println(WeaponFactory.getWeapon(player.getBulletCount())
					.getClass().getSimpleName());
		}
		System.out.println();
	}

}
