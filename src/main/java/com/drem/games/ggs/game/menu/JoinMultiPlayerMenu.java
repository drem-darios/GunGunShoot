package com.drem.games.ggs.game.menu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.drem.games.ggs.api.IGame;
import com.drem.games.ggs.api.IMenu;
import com.drem.games.ggs.game.multiplayer.MultiPlayerGame;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.RemotePlayer;

/**
 * @author drem
 */
public class JoinMultiPlayerMenu extends AbstractMenu {

	private IGame game;
	private String ipAddress;

	@Override
	protected void readInput() {
		int count = 0;
		int prettyPrint = 10;
		RemotePlayer remotePlayer = connectRemotePlayer();
		System.out.print("No games found...");
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
			game = new MultiPlayerGame(new Player(), remotePlayer);
			game.play();
		}

	}

	@Override
	protected void printHeader() {
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			ipAddress = address.getHostAddress();
			System.out.println("Looking for games at address: " + ipAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void printOptions() {/* NO-OP */}

	private RemotePlayer connectRemotePlayer() {
		try {
			Socket socket = new Socket(ipAddress, 3736);
			RemotePlayer player = new RemotePlayer(socket);
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
