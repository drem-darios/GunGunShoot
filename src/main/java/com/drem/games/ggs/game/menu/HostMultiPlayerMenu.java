package com.drem.games.ggs.game.menu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
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
public class HostMultiPlayerMenu extends AbstractMenu {

	private IGame game;
	private String ipAddress;
	private boolean remotePlayerConnected;
	private RemotePlayer remotePlayer;
	private ServerSocket ss = null;

	@Override
	protected void readInput() {

		Thread socketConnectThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ss = new ServerSocket(3736);
					Socket socket = ss.accept(); // This will block until
													// complete
					remotePlayer = new RemotePlayer(socket);
					remotePlayerConnected = true;
				} catch (IOException e) {}
			}
		});
		socketConnectThread.start();

		System.out.print("Waiting for opponent...");
		int count = 0;
		int prettyPrint = 10;

		while (!isRemotePlayerConnected() && count < 30) {
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
		if (remotePlayer == null) {
			try {
				if (ss != null) {
					ss.close();
				}
			} catch (IOException e) {}
			System.out.println("No opponents found! Try single player or joining a game instead.");
			IMenu menu = new MainGameMenu();
			menu.openMenu();
		} else {
			System.out.println("Opponent found. Prepare for battle!");
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
			System.out
					.println("Game session created! Your IP is: " + ipAddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void printOptions() {
		// System.out.println("Waiting for opponent...");
	}

	private boolean isRemotePlayerConnected() {
		return remotePlayerConnected;
	}
	
	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
