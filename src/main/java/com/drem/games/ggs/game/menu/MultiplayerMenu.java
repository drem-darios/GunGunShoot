package com.drem.games.ggs.game.menu;


/**
 * @author drem
 */
public class MultiplayerMenu extends AbstractMenu {

	@Override
	public void openMenu() {
		System.out.println("Sorry but multiplayer is not available at this time.");
		MainGameMenu menu = new MainGameMenu();
		menu.openMenu();
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void printHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void printOptions() {
		// TODO Auto-generated method stub
		
	}

}
