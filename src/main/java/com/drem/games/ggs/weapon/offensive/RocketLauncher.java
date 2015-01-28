package com.drem.games.ggs.weapon.offensive;


/**
 * @author drem
 */
public class RocketLauncher extends Weapon {

	private static final long serialVersionUID = -6673204091697125051L;

	private int strength;
	
	public RocketLauncher() {
		setStrength(10);
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
		RocketLauncher other = (RocketLauncher) obj;
		if (strength != other.strength)
			return false;
		return true;
	}
	
	
}
