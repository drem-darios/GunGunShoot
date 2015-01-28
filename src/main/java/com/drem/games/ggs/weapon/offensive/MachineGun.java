package com.drem.games.ggs.weapon.offensive;


/**
 * @author drem
 */
public class MachineGun extends Weapon {

	private static final long serialVersionUID = 8416001988547605738L;

	private int strength;
	
	public MachineGun() {
		setStrength(5);
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
		MachineGun other = (MachineGun) obj;
		if (strength != other.strength)
			return false;
		return true;
	}
	
	
}
