package com.drem.games.ggs.game.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IMenu;


/**
 * @author drem
 */
public class MultiplayerMenu extends AbstractMenu {

	private IMenu multiplayerModeMenu;
	
	@Override
	protected void readInput() {
		Scanner inputScanner = new Scanner(System.in);
		try {
			int choice = inputScanner.nextInt();
			if (choice == 1) {
				multiplayerModeMenu = new HostMultiplayerMenu();
			} else if (choice == 2) {
				multiplayerModeMenu = new MultiplayerTypeMenu();
			} else {
				// Choice was not recognized. Call start again.
				System.out.println("Sorry your selection was not valid. Please try again.");
				openMenu();
			}

		} catch(InputMismatchException e) {
			System.out.println("Numbers only please!");
			openMenu();
		}
		
		multiplayerModeMenu.openMenu();
		inputScanner.close();
	}

	@Override
	protected void printHeader() {
		System.out.println("Please select an option:");
	}

	@Override
	protected void printOptions() {
		System.out.println(" 1 - Host a game");
		System.out.println(" 2 - Join a game");
	}

}
