package com.drem.games.ggs.game.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IMenu;

/**
 * @author drem
 */
public class MainGameMenu extends AbstractMenu {

	private IMenu gameModeMenu;

	@Override
	protected void readInput() {
		Scanner inputScanner = new Scanner(System.in);
		try {
			int choice = inputScanner.nextInt();
			if (choice == 1) {
				gameModeMenu = new SinglePlayerMenu();		
			} else if (choice == 2) {
				gameModeMenu = new MultiplayerMenu();
			} else if(choice == 0) {
				inputScanner.close();
				exit();
			} else {
				// Choice was not recognized. Call start again.
				System.out.println("Sorry your selection was not valid. Please try again.");
				openMenu();
			}

		} catch(InputMismatchException e) {
			System.out.println("Numbers only please!");
			openMenu();
		}
		
		gameModeMenu.openMenu();
		inputScanner.close();
	}

	@Override
	protected void printHeader() {
		System.out.println("Please select game mode:");
	}

	@Override
	protected void printOptions() {
		System.out.println(" 1 - Single Player \n 2 - Multiplayer \n 0 - Exit ");
	}
	
	private void exit() {
		System.out.println("Goodbye!!!!");
		System.exit(0);
	}
}
