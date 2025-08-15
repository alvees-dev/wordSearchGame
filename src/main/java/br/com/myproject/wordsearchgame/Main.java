package br.com.myproject.wordsearchgame;

import br.com.myproject.wordsearchgame.view.GameInterface;

public class Main {
	
    public static void main(String[] args) {
    	
        GameInterface gameInterface = new GameInterface();
        gameInterface.start();
    }
}
