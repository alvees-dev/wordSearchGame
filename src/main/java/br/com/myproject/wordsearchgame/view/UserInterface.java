package br.com.myproject.wordsearchgame.view;

import java.util.Scanner;

import br.com.myproject.wordsearchgame.core.GameLogic;

public class UserInterface {
	
	private final Scanner scanner;
	private final GameLogic gameLogic;
	
	UserInterface() {
		this.scanner = new Scanner(System.in);
		this.gameLogic = new GameLogic();
		
		
	}

}
