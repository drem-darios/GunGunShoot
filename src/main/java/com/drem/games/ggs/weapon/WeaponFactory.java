package com.drem.games.ggs.weapon;

import java.util.ArrayList;
import java.util.Collection;

import com.drem.games.ggs.api.IWeapon;
import com.drem.games.ggs.weapon.offensive.MachineGun;
import com.drem.games.ggs.weapon.offensive.Pistol;
import com.drem.games.ggs.weapon.offensive.RocketLauncher;
import com.drem.games.ggs.weapon.offensive.Shotgun;

/**
 * @author drem
 */
public class WeaponFactory {

	private static Collection<IWeapon> weapons; // TODO: Inject values here!!!

	static {
		weapons = new ArrayList<IWeapon>();
		weapons.add(new Pistol());
		weapons.add(new Shotgun());
		weapons.add(new MachineGun());
		weapons.add(new RocketLauncher());
	}

	public static IWeapon getWeapon(int strength) {
		IWeapon result = null;
		int currLowest = 0;
		for (IWeapon weapon : weapons) {
			if (weapon.getStrength() <= strength
					&& weapon.getStrength() > currLowest) {
				result = weapon;
				currLowest = weapon.getStrength();
			}
		}

		return result;
	}
}
