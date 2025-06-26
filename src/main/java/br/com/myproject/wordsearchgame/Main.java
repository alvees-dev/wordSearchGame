package br.com.myproject.wordsearchgame;

import br.com.myproject.wordsearchgame.grid.Board;

public class Main {
	
    public static void main(String[] args) {
    	
        Board board = new Board(10);
        board.printMatrix();
        
    }
}
