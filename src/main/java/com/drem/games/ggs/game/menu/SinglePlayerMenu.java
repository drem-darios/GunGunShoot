package com.drem.games.ggs.game.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IGame;
import com.drem.games.ggs.game.SinglePlayerGame;
import com.drem.games.ggs.player.ComputerPlayer;
import com.drem.games.ggs.player.Player;

/**
 * @author drem
 */
public class SinglePlayerMenu extends AbstractMenu {

	private IGame game;
	
	@Override
	protected void readInput() {
		Scanner inputScanner = new Scanner(System.in);
		try {
			int choice = inputScanner.nextInt();
			if (choice == 1) {
				game = new SinglePlayerGame(new Player(), new ComputerPlayer());
				game.play();
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
		System.out.println("Please select a difficulty:");
	}

	@Override
	protected void printOptions() {
		/* TODO: Eventually I want to have many game modes. Ones that will
		 * track health, computer difficulties, different shield levels, etc.
		 */
		System.out.println(" 1 - Simple Computer Player");
	}

}
