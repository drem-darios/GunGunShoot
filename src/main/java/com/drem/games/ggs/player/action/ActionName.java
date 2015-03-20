package com.drem.games.ggs.player.action;

/**
 * @author drem
 */
public enum ActionName {

	BLOCK(), SHOOT(), RELOAD();

	private int action;

	private ActionName() {
		this.action = ordinal(); 
	}

	public int getAction() {
		return this.action;
	}

	public static ActionName fromValue(int value)
			throws IllegalArgumentException {
		try {
			return ActionName.values()[value];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Unknown enum value: " + value);
		}
	}
}
