package com.drem.games.ggs.weapon;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.drem.games.ggs.weapon.offensive.MachineGun;
import com.drem.games.ggs.weapon.offensive.Pistol;
import com.drem.games.ggs.weapon.offensive.RocketLauncher;
import com.drem.games.ggs.weapon.offensive.Shotgun;

/**
 * @author drem
 */
public class WeaponFactoryTest {

	private static Collection<Integer> strengths = new ArrayList<Integer>();

	@BeforeClass
	public static void setup() {
		for (int i = 0; i < 11; i++) {
			strengths.add(i);
		}
	}

	@Test
	public void testAll() {
		for (Integer strength : strengths) {
			if (strength == 0) {
				Assert.assertNull(WeaponFactory.getWeapon(strength));
			} else {
				Assert.assertNotNull(WeaponFactory.getWeapon(strength));
			}
		}
	}

	@Test
	public void testPistol() {
		for (Integer strength : strengths) {
			if (strength == 1 || strength == 2) {
				Assert.assertEquals(new Pistol(),
						WeaponFactory.getWeapon(strength));
			}
		}
	}

	@Test
	public void testShotgun() {
		for (Integer strength : strengths) {
			if (strength == 3 || strength == 4) {
				Assert.assertEquals(new Shotgun(),
						WeaponFactory.getWeapon(strength));
			}
		}
	}

	@Test
	public void testMachineGun() {
		for (Integer strength : strengths) {
			if (strength == 5 || strength == 6 || strength == 7
					|| strength == 8 || strength == 9) {
				Assert.assertEquals(new MachineGun(),
						WeaponFactory.getWeapon(strength));
			}
		}
	}

	@Test
	public void testRocketLauncher() {
		for (Integer strength : strengths) {
			if (strength >= 10) {
				Assert.assertEquals(new RocketLauncher(),
						WeaponFactory.getWeapon(strength));
			}
		}
	}
}
