package br.com.myproject.wordsearchgame.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.myproject.wordsearchgame.core.GameLogic;
import br.com.myproject.wordsearchgame.difficulty.GameDifficulty;
import br.com.myproject.wordsearchgame.grid.Board;

public class GameInterface {

	private static final Logger logger = LoggerFactory.getLogger(GameInterface.class);

	private final Scanner scanner;
	private final GameLogic gameLogic;
	private final Board board;

	public GameInterface() {
		this.scanner = new Scanner(System.in);
		this.gameLogic = new GameLogic();
		this.board = new Board(10);
	}

	public void startGame(GameDifficulty gameDifficulty) {
		gameLogic.preparingGame(gameDifficulty);
		gameLogic.mainLoop();
		replayGame();
	}

	private void showWelcome() {

		System.out.print("\n" + "=".repeat(15));
		System.out.print(" BEM-VINDO AO CAÇA-PALAVRAS " + "=".repeat(15));
	}

	public void start() {

		showWelcome();

		int userInput = 0;
		while (userInput != 3) {

			switch (menu()) {

			case 1 -> {
				startNewGame();

			}
			case 2 -> showTutorial();
			case 3 -> {
				logger.info("O jogo será encerrado!");
				return;
			}
			default -> logger.info("❌ Opção inválida! Digite uma das opções acima.");

			}
		}
	}

	private int menu() {

		System.out.println("\n\n📋 MENU PRINCIPAL");
		System.out.println("1. 🎮 Jogar");
		System.out.println("2. 📖 Tutorial");
		System.out.println("3. 🚪 Sair");
		System.out.print("\nCaso seja sua primeira vez jogando, veja o tutorial.");
		System.out.print("\n\nEscolha uma opção: ");

		return readUserInput();
	}

	public void startNewGame() {
		GameDifficulty gameDifficulty = selectingDifficulty();
		if (gameDifficulty != null) {
			startGame(gameDifficulty);
		}
	}

	private void showTutorial() {
		System.out.println("\n" + "=".repeat(60));
		System.out.println("📖 TUTORIAL - COMO JOGAR");
		System.out.println("=".repeat(60));
		System.out.println("🎯 OBJETIVO:");
		System.out.println("   Encontre todas as palavras escondidas no tabuleiro!\n");
		board.tutorialBoard();
		System.out.println("\n🎮 COMO JOGAR:");
		System.out.println("   1. O tabuleiro mostra letras organizadas em grade");
		System.out.println("   2. As palavras podem estar na horizontal ou vertical");
		System.out.println("   3. Digite as coordenadas de INÍCIO e FIM da palavra");
		System.out.println("   4. Exemplo: A1 A10 (da linha A, coluna 1 até A, coluna 10.");
		System.out.println("      Você poderá encontrar a banda THE BEATLES)");
		System.out.println("\n📍 COORDENADAS:");
		System.out.println("   • Linhas: A, B, C, D... (letras)");
		System.out.println("   • Colunas: 1, 2, 3, 4... (números)");
		System.out.println("\n✅ DICAS:");
		System.out.println("   • Palavras podem estar escritas de trás para frente");
		System.out.println("   • Digite 'SAIR' durante o jogo para desistir");
		System.out.println("   • Palavras encontradas ficam marcadas com '---'");

		System.out.println("\n" + "=".repeat(60));
		System.out.print("Pressione ENTER para continuar...");
		scanner.nextLine();
	}

	private GameDifficulty selectingDifficulty() {

		System.out.println("\n🎚️ SELECIONE A DIFICULDADE:");

		System.out.println("1. 🟢 " + GameDifficulty.EASY.getDescription() + " (Tabuleiro "
				+ GameDifficulty.EASY.getBoardSize() + "x" + GameDifficulty.EASY.getBoardSize() + ")");

		System.out.println("2. 🟡 " + GameDifficulty.NORMAL.getDescription() + " (Tabuleiro "
				+ GameDifficulty.NORMAL.getBoardSize() + "x" + GameDifficulty.NORMAL.getBoardSize() + ")");

		System.out.println("3. 🔴 " + GameDifficulty.HARD.getDescription() + " (Tabuleiro "
				+ GameDifficulty.HARD.getBoardSize() + "x" + GameDifficulty.HARD.getBoardSize() + ")");

		System.out.println("4. ⬅️ Voltar");
		System.out.print("\nEscolha: ");

		int userInput = readUserInput();

		return switch (userInput) {

		case 1 -> GameDifficulty.EASY;
		case 2 -> GameDifficulty.NORMAL;
		case 3 -> GameDifficulty.HARD;
		case 4 -> null;
		default -> {
			logger.error("❌ Opção inválida! Digite uma das opções acima.");
			yield selectingDifficulty();
		}
		};
	}

	private void replayGame() {

		int userInput = 0;
		while (userInput != 1) {
			System.out.println("-".repeat(50));
			System.out.println("Gostaria de jogar novamente?\n");
			System.out.println("1. ✅ Sim");
			System.out.println("2. ❌ Não");
			System.out.println("\nDigite sua escolha: ");

			userInput = readUserInput();

			switch (userInput) {

			case 1 -> startNewGame();
			case 2 -> {
				logger.info("\nO jogo será encerrado!");
				logger.info("Obrigado por jogar! Até mais. 👋 ");
				return;
			}

			default -> logger.error("❌ Opção inválida! Digite uma das opções acima.");
			}
		}
	}

	private int readUserInput() {

		while (true) {

			try {
				int userInput = scanner.nextInt();
				scanner.nextLine();
				return userInput;

			} catch (InputMismatchException e) {
				logger.error("❌ Digite apenas números. Tente novamente: ");
				scanner.nextLine();
			}
		}
	}
}
