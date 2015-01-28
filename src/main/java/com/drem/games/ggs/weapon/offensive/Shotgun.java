package com.drem.games.ggs.weapon.offensive;


/**
 * @author drem
 */
public class Shotgun extends Weapon {

	private static final long serialVersionUID = 1098378679208306490L;

	private int strength;
	
	public Shotgun() {
		setStrength(3);
	}
	
	public int getStrength() {return strength;}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + strength;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shotgun other = (Shotgun) obj;
		if (strength != other.strength)
			return false;
		return true;
	}
	
}
