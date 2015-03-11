package com.drem.games.ggs.weapon;

import java.io.Serializable;

/**
 * @author drem
 */
public enum WeaponAction implements Serializable {

	BLOCK(), SHOOT(), RELOAD();

	private int action;

	private WeaponAction() {
		this.action = ordinal(); 
	}

	public int getAction() {
		return this.action;
	}

	public static WeaponAction fromValue(int value)
			throws IllegalArgumentException {
		try {
			return WeaponAction.values()[value];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Unknown enum value: " + value);
		}
	}
}
