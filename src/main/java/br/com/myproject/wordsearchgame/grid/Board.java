package br.com.myproject.wordsearchgame.grid;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Board {

	private static final Logger logger = LoggerFactory.getLogger(Board.class);

	private int size;
	private char[][] matrix;
	private Random random;

	public Board(int size) {
		this.size = size;
		this.matrix = new char[size][size];
		this.random = new Random();
		createMatrix();
	}

	private void createMatrix() {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = ' ';
			}
		}
	}
	
	private Coordinates generateRandomPosition() {
		char line = (char) ('A' + random.nextInt(size));
		int column = random.nextInt(size) + 1;
		return new Coordinates(line, column);
	}
	
	private Directions generateRandomDirection() {
		Directions[] directions = {Directions.HORIZONTAL, Directions.VERTICAL};
		return directions[random.nextInt(directions.length)];
	}
	
	private void insertWord(String word, Coordinates begin, Directions directions) {
		
		for (int i = 0; i < word.length(); i ++) {
			int line = begin.getLine() - 1 + (i * directions.getDeltaLine());
			int column = begin.getColumn() - 1 + (i * directions.getDeltaColumn());
			matrix[line][column] = word.charAt(i);
		}
	}
	
	private boolean canPutWord(String word, Coordinates begin, Directions direction) {
		
		int initialLine = begin.getLine() - 1;
		int initialColumn = begin.getColumn() - 1;
		
		for ( int i = 0; i < word.length(); i++) {
			int line = initialLine + (i * direction.getDeltaLine());
			int column = initialColumn + (i * direction.getDeltaColumn());
			
			if (line < 0 || line >= size || column < 0 || column >= size) {
				return false;
			}
			
			char actualLyric = matrix[line][column];
			char lyricWord = word.charAt(i);
			
			if (actualLyric != ' ' && actualLyric != lyricWord) {
				return false;
			}
		}
		return true;
	}
	
	private void putTheWordsInTheMatrix(String palavras) {
		
		int attempts = 0;
		
		while (attempts < 100) {
			Coordinates position = generateRandomPosition();
			Directions direction = generateRandomDirection();
			
			if(canPutWord(palavras, position, direction)) {
				insertWord(palavras, position, direction);
				return;
			}
			attempts++;
		}
	}
	
	public void printWordsInTheMatrix(String[] words) {
		
		for(String word : words) {
			putTheWordsInTheMatrix(word);
		}
	}
	
	

	public void fillingBlanksSpacesInTheMatrix() {
		
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if (matrix[i][j] == ' ') {
					matrix[i][j] = (char) ('A' + random.nextInt(26));

				}
			}
		}
	}

	public void printMatrix() {

		System.out.print("  ");

		// Coloca os números das colunas
		for (int i = 1; i <= size; i++) {
			System.out.printf("%2d ", i);
		}
		System.out.println();

		// Coloca as letras das linhas
		for (int i = 0; i < size; i++) {
			System.out.print((char) ('A' + i) + "  ");

			for(int j = 0; j < size; j++) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println();

		}
	}

}
