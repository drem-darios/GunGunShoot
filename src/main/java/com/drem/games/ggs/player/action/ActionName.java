package com.drem.games.ggs.weapon;

/**
 * @author drem
 */
public enum WeaponAction {

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
