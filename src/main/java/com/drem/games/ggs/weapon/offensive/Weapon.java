package com.drem.games.ggs.weapon.offensive;

import com.drem.games.ggs.api.IWeapon;

/**
 * @author drem
 */
public abstract class Weapon implements IWeapon, Comparable<IWeapon> {

	private static final long serialVersionUID = -4040581437691624454L;

	@Override
	public int compareTo(IWeapon otherWeapon) {
		if (this == otherWeapon) {
			return 0;
		} else if (this.getStrength() > otherWeapon.getStrength()) {
			return 1;
		} else if (this.getStrength() < otherWeapon.getStrength()) {
			return -1;
		} else {
			return 0;
		}
	}
}
