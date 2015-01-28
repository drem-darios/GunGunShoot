package com.drem.games.ggs;

import java.util.Scanner;

import com.drem.games.ggs.api.IWeapon;
import com.drem.games.ggs.player.ComputerPlayer;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.PlayerOutcome;
import com.drem.games.ggs.weapon.WeaponAction;
import com.drem.games.ggs.weapon.WeaponFactory;

/**
 * @author drem
 */
public class GunGunShoot {

	private Player player1 = new Player();
	private ComputerPlayer player2 = new ComputerPlayer();
	private Scanner inputScanner = new Scanner(System.in);

	public void start() {
		printHelp();
		play();
	}

	private void play() {
		try {
			String userInput = inputScanner.nextLine();
			int choice = Integer.valueOf(userInput);
			while (choice != 0) {
				if (choice == 4) {
					System.out.print("Player:");
					printPlayer(player1);
					System.out.print("Computer:");
					printPlayer(player2);
					System.out.println();
					userInput = inputScanner.nextLine();
					choice = Integer.valueOf(userInput);
					continue;
				} else if (choice > 4) {
					System.out.println("Please stick to the options given.");
					printHelp();
					userInput = inputScanner.nextLine();
					choice = Integer.valueOf(userInput);
					continue;
				}

				WeaponAction action = WeaponAction.fromValue(choice - 1);
				WeaponAction computerAction = player2.makeMove();
				makeMove(action, computerAction);

				userInput = inputScanner.nextLine();
				choice = Integer.valueOf(userInput);
			}
		} catch(NumberFormatException e) {
			System.out.println("Numbers only please! Take a look at the rules again.");
			printHelp();
			play();
		}
		

		exit();
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
			if (playerAction == WeaponAction.SHOOT){
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
		exit();
	}

	// private void declareLoser(Player loser) {
	// System.out.println("Player " + loser.getClass().getSimpleName()
	// + " has lost the game!");
	// exit();
	// }

	private void declareWinner(Player winner) {
		System.out.println(winner.getClass().getSimpleName()
				+ " has won the game!");
		exit();
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

	private void exit() {
		inputScanner.close();
		System.out.println("Goodbye!!!!");
		System.exit(0);
	}

	private void printHelp() {
		System.out.println("************************");
		System.out.println("How to play:");
		System.out.println(" 1 - Block \n 2 - Shoot \n 3 - Reload "
				+ "\n 4 - Game State \n 0 - QUIT");
		System.out.println("************************");
		System.out.println();
	}

}
