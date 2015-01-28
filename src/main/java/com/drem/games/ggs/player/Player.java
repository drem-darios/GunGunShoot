package com.drem.games.ggs.player;

import com.drem.games.ggs.api.IShield;
import com.drem.games.ggs.weapon.defensive.BasicShield;


/**
 * @author drem
 */
public class Player {

	private int numBullets;
	private IShield shield;
	
	public Player() {
		shield = new BasicShield();
	}
	
	public int getBulletCount() {
		return numBullets;
	}
	
	public int getShieldStrength() {
		return shield.getStrength();
	}
	
	public void addBullet() {
		numBullets++;
	}
	
	public void useBullet() {
		if (numBullets > 0) {
			numBullets--;
		}
	}
	
	public void block() {
		if (!shield.isBroken()) {
			shield.takeHit();	
		}
	}
	
	public boolean hasWeapon() {
		return numBullets > 0;
	}
	
	public boolean canBlock() {
		return !shield.isBroken();
	}
	
}
