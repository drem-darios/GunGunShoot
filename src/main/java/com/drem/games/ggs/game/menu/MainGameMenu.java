package com.drem.games.ggs.game.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IMenu;

/**
 * @author drem
 */
public class MainGameMenu implements IMenu {

	private Scanner inputScanner = new Scanner(System.in);
	private IMenu gameModeMenu;

	@Override
	public void openMenu() {
		printMenu();
		try {
			int choice = inputScanner.nextInt();
			if (choice == 1) {
				gameModeMenu = new SinglePlayerMenu();		
			} else if (choice == 2) {
				gameModeMenu = new MultiplayerMenu();
			} else {
				// Choice was not recognized. Call start again.
				System.out.println("Sorry your selection was not valid. Please try again.");
				openMenu();
			}

		} catch(InputMismatchException e) {
			System.out.println("Numbers only please!");
			printMenu();
		}
		
		gameModeMenu.openMenu();
	}

	@Override
	public void printMenu() {
		System.out.println("************************");
		System.out.println("Please select game mode:");
		System.out.println(" 1 - Single Player \n 2 - Multiplayer ");
		System.out.println("************************");
		System.out.println();
	}
}
