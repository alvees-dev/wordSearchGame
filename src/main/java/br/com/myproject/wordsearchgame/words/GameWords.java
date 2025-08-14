package br.com.myproject.wordsearchgame.words;

import java.util.Arrays;
import java.util.List;

import br.com.myproject.wordsearchgame.difficulty.GameDifficulty;

public class GameWords {

	private static final List<String> EASY_WORDS = Arrays.asList("THEBEATLES", "PINKFLOYD", "IRONMAIDEN", "GREENDAY",
			"PEARLJAM");

	private static final List<String> NORMAL_WORDS = Arrays.asList("METALLICA", "FOOFIGHTERS", "GUNSNROSES",
			"LINKINPARK", "SLIPKNOT");

	private static final List<String> HARD_WORDS = Arrays.asList("THEBEATLES", "PINKFLOYD", "IRONMAIDEN", "GREENDAY",
			"PEARLJAM");

	public String[] getWords(GameDifficulty gameDifficulty) {
		return switch (gameDifficulty) {

		case EASY -> EASY_WORDS.toArray(new String[0]);
		case NORMAL -> NORMAL_WORDS.toArray(new String[0]);
		case HARD -> HARD_WORDS.toArray(new String[0]);
		
		};
	}

}
