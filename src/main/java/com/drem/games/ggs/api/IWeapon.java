package com.drem.games.ggs.api;

import java.io.Serializable;

/**
 * @author drem
 */
public interface IWeapon extends Serializable {

	public int getStrength();
	public int compareTo(IWeapon otherWeapon);
	
}
