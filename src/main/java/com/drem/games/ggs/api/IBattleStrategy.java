package com.drem.games.ggs.api;

import com.drem.games.ggs.player.Player;
import com.drem.games.ggs.util.Pair;
import com.drem.games.ggs.weapon.WeaponAction;

/**
 * @author drem
 */
public interface IBattleStrategy {

	public void battle(Pair<Player, WeaponAction> player1Move,
			Pair<Player, WeaponAction> player2Move);

}
