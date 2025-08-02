package br.com.myproject.wordsearchgame.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.myproject.wordsearchgame.grid.Board;
import br.com.myproject.wordsearchgame.grid.Coordinates;
import br.com.myproject.wordsearchgame.grid.Directions;

public class WordFinder {

	private static final Logger logger = LoggerFactory.getLogger(WordFinder.class);

	private Board board;
	private GameLogic gameLogic;
	private List<String> foundWords;
	private List<String> remainingWords;

	public WordFinder(Board board, List<String> remainingWords) {
		this.board = board;
		this.remainingWords = remainingWords;
		this.foundWords = new ArrayList<>();
	}

	public void searchWord(Coordinates start, Coordinates end) {
		Directions direction = Directions.entry(start, end);
		System.out.println();

		if (direction == null) {
			logger.info("❌ Direção inválida. Use apenas horizontal ou vertical.");
			return;
		}

		String foundWord = extractWord(start, end, direction);
		if (foundWord == null) {
			logger.info("❌ Coordenadas fora dos limites da matriz.");
			return;
		}

		if (isValidWord(foundWord)) {
			
			addFoundWord(foundWord);
			board.markFoundWords(start, end, direction);
			logger.info("✅ Palavra encontrada: " + foundWord);
		} else {
			logger.info("❌ A sequência '" + foundWord + "' não é uma palavra válida do jogo.");
		}
	}

	private String extractWord(Coordinates start, Coordinates end, Directions direction) {
		StringBuilder word = new StringBuilder();

		int startLine = start.getLine() - 1;
		int startColumn = start.getColumn() - 1;
		int endLine = end.getLine() - 1;
		int endColumn = end.getColumn() - 1;

		int length;
		if (direction == Directions.HORIZONTAL) {
			length = Math.abs(endColumn - startColumn) + 1;
		} else {
			length = Math.abs(endLine - startLine) + 1;
		}

		int stepLine = (endLine > startLine) ? 1 : (endLine < startLine) ? -1 : 0;
		int stepColumn = (endColumn > startColumn) ? 1 : (endColumn < startColumn) ? -1 : 0;

		for (int i = 0; i < length; i++) {
			int currentLine = startLine + (i * stepLine);
			int currentColumn = startColumn + (i * stepColumn);

			if (currentLine < 0 || currentLine >= board.getSize() || currentColumn < 0
					|| currentColumn >= board.getSize()) {
				return null;
			}

			char character = board.getCharacterAt(currentLine, currentColumn);
			word.append(character);
		}

		return word.toString();
	}

	private boolean isValidWord(String word) {
		return remainingWords.contains(word);
	}

	private void addFoundWord(String word) {
		if (!foundWords.contains(word)) {
			foundWords.add(word);
			remainingWords.remove(word);
			logger.info("Palavras encontradas até agora: " + foundWords.size());
		} else {
			logger.info(" ⚠️ Você já encontrou esta palavra: " + word);
		}
	}
	
	public List<String> getRemainingWords() {
		return remainingWords;
	}

}