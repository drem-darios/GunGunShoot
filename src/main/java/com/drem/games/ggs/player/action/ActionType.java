package com.drem.games.ggs.player.action;

/**
 * @author drem
 */
public enum ActionType {

	BLOCK(), SHOOT(), RELOAD();

	private int action;

	private ActionType() {
		this.action = ordinal(); 
	}

	public int getAction() {
		return this.action;
	}

	public static ActionType fromValue(int value)
			throws IllegalArgumentException {
		try {
			return ActionType.values()[value];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Unknown enum value: " + value);
		}
	}
}
