package com.drem.games.ggs.game.menu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.drem.games.ggs.api.IGame;
import com.drem.games.ggs.api.IMenu;
import com.drem.games.ggs.game.multiplayer.MultiplayerGame;
import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.player.RemotePlayer;

/**
 * @author drem
 */
public class HostMultiplayerMenu extends AbstractMenu {

	private IGame game;
	private String ipAddress;

	@Override
	protected void readInput() {
		RemotePlayer remotePlayer = null;
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(3736);
			ss.setSoTimeout(30000);
			Socket socket = ss.accept(); // This will block until complete
			remotePlayer = new RemotePlayer(socket);
			ss.close();
		} catch(IOException e) {
			if (ss != null) {
				try {ss.close();} catch (IOException e1) {}
				ss = null;
			}
		}

		if (remotePlayer == null) {
			System.out.println("No opponents found! Try single player or joining a game instead.");
			IMenu menu = new MainGameMenu();
			menu.openMenu();
		} else {
			System.out.println("Opponent found. Prepare for battle!");
			try {
				remotePlayer.write("Opponent found. Prepare for battle!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game = new MultiplayerGame(new Player(), remotePlayer);
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
		 System.out.println("Waiting for opponent...");
	}
}
