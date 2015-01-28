package com.drem.games.ggs.weapon.defensive;

/**
 * @author drem
 */
public class BasicShield extends ShieldImpl {

	private static final long serialVersionUID = 1665938147809771820L;
	private int strength;
	
	public BasicShield() {
		this.strength = -10; // TODO: Inject this fool
	}
	
	public void takeHit() {
		strength++;
	}
	
	public boolean isBroken() {
		return strength >= 0;
	}

	public int getStrength() {
		return strength;
	}
}
