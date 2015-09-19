package com.drem.games.ggs.game.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.drem.games.ggs.api.IGame;
import com.drem.games.ggs.api.IMenu;
import com.drem.games.ggs.game.multiplayer.MultiplayerGame;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.RemotePlayer;

/**
 * @author drem
 */
public class JoinMultiplayerMenu extends AbstractMenu {

	private IGame game;
	private String ipAddress;

	@Override
	protected void readInput() {
		Scanner inputScanner = new Scanner(System.in);
		// TODO: Validate input
		this.ipAddress = inputScanner.nextLine();
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
			System.out.println("No games found! Try single player or hosting a game instead.");
			IMenu menu = new MainGameMenu();
			menu.openMenu();
		} else {
			System.out.println("Game found. Prepare for battle!");
			game = new MultiplayerGame(new Player(), remotePlayer);
			game.play();
		}
		
		inputScanner.close();
	}

	@Override
	protected void printHeader() {
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			ipAddress = address.getHostAddress();
			System.out.println("Please enter IP address of game: " );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void printOptions() {/* NO-OP */}

	private RemotePlayer connectRemotePlayer() {
		try {
			System.out.println("Attempting to connect to: " + ipAddress);
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
