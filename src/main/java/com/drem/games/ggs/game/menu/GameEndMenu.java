package com.drem.games.ggs.game.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IMenu;


/**
 * @author drem
 */
public class GameEndMenu extends AbstractMenu {

	@Override
	protected void readInput() {
		Scanner inputScanner = new Scanner(System.in);
		try {
			int choice = inputScanner.nextInt();
			if (choice == 1) {
				IMenu menu = new MainGameMenu();
				menu.openMenu();
			} else if (choice  == 2) {
				System.out.println("Goodbye!!!!");
				System.exit(0);
			} else {
				// Choice was not recognized. Call start again.
				System.out.println("Sorry your selection was not valid. Please try again.");
				openMenu();
			}
			
		} catch(InputMismatchException e) {
			System.out.println("Numbers only please!");
			openMenu();
		}
		
		inputScanner.close();
	}

	@Override
	protected void printHeader() {
		System.out.println("Would you like to play again?:");
	}

	@Override
	protected void printOptions() {
		System.out.println(" 1 - Yes please \n 2 - No thanks");
		
	}

}
