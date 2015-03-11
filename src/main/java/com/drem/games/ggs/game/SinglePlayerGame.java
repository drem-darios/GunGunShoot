package com.drem.games.ggs.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IMenu;
import com.drem.games.ggs.api.IBattleStrategy;
import com.drem.games.ggs.game.menu.GameEndMenu;
import com.drem.games.ggs.game.mode.RegularMode;
import com.drem.games.ggs.player.ComputerPlayer;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.util.Pair;
import com.drem.games.ggs.weapon.WeaponAction;
import com.drem.games.ggs.weapon.WeaponFactory;

/**
 * @author drem
 */
public class SinglePlayerGame extends AbstractGame {

	private IMenu gameEndMenu = new GameEndMenu();
	private IBattleStrategy moveStrategy = new RegularMode();
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
				moveStrategy.battle(new Pair<Player, WeaponAction>(player1,
						action), new Pair<Player, WeaponAction>(player2,
						computerAction));

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
