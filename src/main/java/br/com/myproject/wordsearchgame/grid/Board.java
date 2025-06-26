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
		fillingMatrix();
	}

	private void createMatrix() {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = ' ';
			}
		}
	}

	private void fillingMatrix() {
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
