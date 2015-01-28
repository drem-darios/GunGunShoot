package com.drem.games.ggs.api;

/**
 * @author drem
 */
public interface IShield extends IWeapon {

	public boolean isBroken();
	public void takeHit();
}
