package com.drem.games.ggs.game.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.drem.games.ggs.api.IGame;
import com.drem.games.ggs.api.IMenu;
import com.drem.games.ggs.game.multiplayer.MultiplayerGame;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.RemotePlayer;

/**
 * @author drem
 */
public class MultiplayerTypeMenu extends AbstractMenu {

	private static final String REMOTE_IP = "54.149.136.143";
	private IGame game;
	private String ipAddress;

	@Override
	protected void readInput() {
		Scanner inputScanner = new Scanner(System.in);

		try {
			int choice = inputScanner.nextInt();
			if (choice == 1) {
				useLocalIpAddress();
			} else if (choice == 2) {
				useRemoteIpAddress();
			} else {
				// Choice was not recognized. Call start again.
				System.out
						.println("Sorry your selection was not valid. Please try again.");
				openMenu();
			}

		} catch (InputMismatchException e) {
			System.out.println("Numbers only please!");
			openMenu();
		}

		int count = 0;
		int prettyPrint = 10;
		RemotePlayer remotePlayer = connectRemotePlayer();
		while (remotePlayer == null && count < 30) {
			count++;
			System.out.print(".");
			if ((count % prettyPrint) == 0) {
				// Just to make output look a little nicer while waiting
				System.out.println();
				prettyPrint--;
			}
			sleep();
			remotePlayer = connectRemotePlayer();
		}
		System.out.println();
		if (remotePlayer == null) {
			System.out
					.println("No games found! Try single player or hosting a game instead.");
			IMenu menu = new MainGameMenu();
			menu.openMenu();
		} else {
			System.out.println("Game found. Prepare for battle!");
			game = new MultiplayerGame(new Player(), remotePlayer);
			game.play();
		}

		inputScanner.close();
	}

	private void useRemoteIpAddress() {
		this.ipAddress = REMOTE_IP;
		System.out.println("Creating public session!");
	}

	private void useLocalIpAddress() {
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			this.ipAddress = address.getHostAddress();
			System.out.println("Creating local session!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void printHeader() {
		System.out.println("Please select an option:");
	}

	@Override
	protected void printOptions() {
		System.out.println(" 1 - Join a local game");
		System.out.println(" 2 - Join a public game");
	}

	private RemotePlayer connectRemotePlayer() {
		try {
			System.out.println("Attempting to connect...");
			Socket socket = new Socket(ipAddress, 3736);
			RemotePlayer player = new RemotePlayer(socket);
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				System.out.println(userInput);
				break;
			}
			return player;
		} catch (IOException e) {
			return null;
		}
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
