package com.drem.games.ggs.game.menu;

import com.drem.games.ggs.api.IMenu;

/**
 * @author drem
 */
public abstract class AbstractMenu implements IMenu {

	@Override
	public void openMenu() {
		printMenu();
		readInput();
	}

	@Override
	public void printMenu() {
		System.out.println("************************");
		this.printHeader();
		this.printOptions();
		System.out.println("************************");
		System.out.println();
	}
	
	protected abstract void readInput();
	protected abstract void printHeader();
	protected abstract void printOptions();
	
}
