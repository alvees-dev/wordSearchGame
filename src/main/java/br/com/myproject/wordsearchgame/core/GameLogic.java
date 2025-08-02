package br.com.myproject.wordsearchgame.core;

import java.util.ArrayList;
import java.util.Arrays;
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
	private WordFinder wordFinder;

	public GameLogic() {
		this.scanner = new Scanner(System.in);
		this.gameWords = new GameWords();
	}

	public void startGame() {
		preparingGame(gameWords.getWords());
		mainLoop();
	}

	private void preparingGame(String[] words) {
		board = new Board(10);

		remainingWords = new ArrayList<>(Arrays.asList(words));

		wordFinder = new WordFinder(board, remainingWords);

		board.printWordsInTheMatrix(gameWords.getWords());
		board.fillingBlanksSpacesInTheMatrix();
		showGameStatus();
	}

	private void mainLoop() {
		while (!remainingWords.isEmpty()) {
			logger.info("Digite as Coordenadas (Início e Fim) ou 'sair' para terminar");

			String input = scanner.nextLine().trim().toUpperCase();
			if ("SAIR".equals(input)) {
				logger.info("O jogo será encerrado!");
				break;
			}
			
			userInput(input);
			showGameStatus();
		}

		if (remainingWords.isEmpty()) {
			logger.info("\n🎉 Parabéns! Você encontrou todas as palavras!");
			logger.info("O jogo será encerrado.");
			logger.info("Obrigado por jogar :)");
		}
	}

	private void userInput(String input) {
		String[] coordinates = input.split("\\s+");

		if (coordinates.length != 2) {
			logger.info(" ❌ Formato inválido. Use, por exemplo: A1 A5");
			return;
		}

		try {
			Coordinates start = new Coordinates(coordinates[0]);
			Coordinates end = new Coordinates(coordinates[1]);

			wordFinder.searchWord(start, end);

		} catch (IllegalArgumentException exception) {
			logger.info(" ❌ Coordenadas inválidas. Use o formato correto (ex: A1 B5)");
		}
	}

	public void showGameStatus() {
		clearConsole();
		System.out.println("\n" + "=".repeat(50));
		board.printMatrix();
		System.out.println("\nPalavras restantes (" + wordFinder.getRemainingWords().size() + "):");
		wordFinder.getRemainingWords().forEach(words -> System.out.println("• " + words));
		System.out.println("=".repeat(50));

	}
	
	private void clearConsole() {
		for(int line = 0; line <= 50; line ++) {
			System.out.println();
		}
	}

}