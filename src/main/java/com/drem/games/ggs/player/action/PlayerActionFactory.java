package com.drem.games.ggs.player.action;

import java.util.HashMap;
import java.util.Map;

import com.drem.games.ggs.api.IPlayerAction;

/**
 * @author drem
 */
public class PlayerActionFactory {
	
	private static Map<Integer, IPlayerAction> actions; // TODO: Inject values here!!!

	static {
		actions = new HashMap<Integer, IPlayerAction>();
		actions.put(0, new BlockAction());
		actions.put(1, new ShootAction());
		actions.put(2, new ReloadAction());
	}

	public static IPlayerAction getPlayerAction(Integer action) {
		return actions.get(action);
	}
}
