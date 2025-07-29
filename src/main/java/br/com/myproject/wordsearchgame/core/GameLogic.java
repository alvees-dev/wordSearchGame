package br.com.myproject.wordsearchgame.core;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.myproject.wordsearchgame.grid.Board;
import br.com.myproject.wordsearchgame.grid.Coordinates;
import br.com.myproject.wordsearchgame.words.GameWords;

public class GameLogic {
	
	private static final Logger logger = LoggerFactory.getLogger(GameLogic.class);
	
	private Board board;
	private List<String> remainingWords;
	private Scanner scanner;
	private GameWords gameWords;
	
	public GameLogic() {
		this.scanner = new Scanner(System.in);
	}
	
	private void preparingGame(GameWords gameWords) {
		
		board = new Board(10);
		
		board.printWordsInTheMatrix(gameWords.getWords());
		board.fillingBlanksSpacesInTheMatrix();
	}
	
	public void startGame(GameWords gameWords) {
		
		preparingGame(gameWords.getWords());
	}
	
	private void mainLoop() {
		while (!remainingWords.isEmpty()) {
			logger.info("Digite as Coordenadas (Início e Fim) ou  " + "sair" + " para terminar");
			
			String input = scanner.nextLine().trim().toUpperCase();
			if("SAIR".equals(input)) {
				logger.info("O jogo será encerrado!");
				System.exit(0);
			}
			
			
		}
	}
	
	private void userInput(String input) {
		String[] coordinates = input.split("\\s+");
		
		if(coordinates.length != 2) {
			logger.info(" ❌ Formato inválido. Use, por exemplo: A1 A5");
			return;
		}
		
		try {
			Coordinates start = new Coordinates(coordinates[0]);
			Coordinates end = new Coordinates(coordinates[1]);
			
			
		} catch(IllegalArgumentException exception) {
			
		}
	}

}
