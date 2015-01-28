package com.drem.games.ggs.player;

import java.util.Random;

import com.drem.games.ggs.weapon.WeaponAction;

/**
 * @author drem
 */
public class ComputerPlayer extends Player {

	private boolean firstMove = true;
	
	public WeaponAction makeMove() {
		if (firstMove) {
			firstMove = false;
			return WeaponAction.RELOAD;
		}
		
		int choice = getRandomChoice();
		if (getBulletCount() == 0 && WeaponAction.fromValue(choice) == WeaponAction.SHOOT) {
			return makeMove(); //make move again bc you can't shoot.
		}
		return WeaponAction.fromValue(choice);
	}
	
	private int getRandomChoice() {
		Random r = new Random();
		return r.nextInt(3);
	}
}
