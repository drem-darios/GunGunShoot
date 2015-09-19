package com.drem.games.ggs.game.multiplayer;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IBattleStrategy;
import com.drem.games.ggs.api.IMenu;
import com.drem.games.ggs.game.AbstractGame;
import com.drem.games.ggs.game.menu.GameEndMenu;
import com.drem.games.ggs.game.mode.RegularMode;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.RemotePlayer;
import com.drem.games.ggs.player.action.ActionType;
import com.drem.games.ggs.util.Pair;
import com.drem.games.ggs.weapon.WeaponFactory;

/**
 * @author drem
 */
public class MultiplayerGame extends AbstractGame {

	private Scanner inputScanner;
	private IBattleStrategy moveStrategy = new RegularMode();
	private IMenu gameEndMenu = new GameEndMenu();
	private boolean remotePlayerMoved;
	private ActionType remoteAction;
	
	public MultiplayerGame(Player player1, RemotePlayer player2) {
		super(player1, player2);
	}

	@Override
	public void printRules() {
		// TODO: ADD Disconnect option...
		System.out.println("************************");
		System.out.println("How to play:");
		System.out.println(" 1 - Block \n 2 - Shoot \n 3 - Reload "
				+ "\n 4 - Game State \n 0 - QUIT");
		System.out.println("************************");
		System.out.println();

	}

	@Override
	public void exit() {
		try {
			((RemotePlayer) player2).closeStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameEndMenu.openMenu();
	}

	@Override
	public void play() {
		inputScanner = new Scanner(System.in);
		try {
			int choice = -1;
			while (choice != 0) {
				inputScanner.reset();
				System.out.print("Make a move: ");
				choice = inputScanner.nextInt();
				if (choice == 4) {
					System.out.print("Player:");
					printPlayer(player1);
					System.out.print("Remote:");
					printPlayer(player2);
					System.out.println();
					continue;
				} else if (choice > 4) {
					System.out.println("Please stick to the options given.");
					printRules();
					continue;
				} else if (choice <= 0) {
					exit();
				}
				ActionType action = ActionType.fromValue(choice - 1);
				// TODO: Move this to a thread and print some waiting statement
				// here
				Thread readMove = new Thread(new Runnable() {

					@Override
					public void run() {
						remoteAction = ((RemotePlayer) player2).readMove();
						remotePlayerMoved = true;
					}
				});

				((RemotePlayer) player2).writeMove(action);
				System.out.println("Move: " + action.toString()
						+ " written to remote player.");
				
				readMove.start();
				waitForOpponent();

				if (remoteAction == null) {
					System.out.println("Could not read opponent's move. Please try your move again.");
					printRules();
					play();
				} else {
					moveStrategy.battle(new Pair<Player, ActionType>(player1,
							action), new Pair<Player, ActionType>(player2,
							remoteAction));
					remoteAction = null; // clear last action
					remotePlayerMoved = false;
				}

			}
		} catch (InputMismatchException e) {
			System.out
					.println("Numbers only please! Take a look at the rules again.");
			printRules();
			play();
		}

		exit();
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
	
	private void waitForOpponent() {
		System.out.print("Waiting for opponent...");
		int count = 0;
		int prettyPrint = 10;

		while (!hasRemotePlayerMoved() && count < 30) {
			count++;
			System.out.print('.');
			if ((count % prettyPrint) == 0) {
				// Just to make output look a little nicer while waiting
				System.out.println();
				prettyPrint--;
			}
			sleep();
		}
		System.out.println();
	}

	private boolean hasRemotePlayerMoved() {
		return remotePlayerMoved;
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
