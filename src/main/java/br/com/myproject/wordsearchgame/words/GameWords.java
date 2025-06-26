package br.com.myproject.wordsearchgame.words;

import java.util.Arrays;
import java.util.List;

public class GameWords {
	
	private static final List<String> WORDS = Arrays.asList(
			"THEBEATLES", "PINKFLOYD", "IRONMAIDEN", "GREENDAY", "PEARLJAM");
	
	public String[] getWords() {
		return WORDS.toArray(new String[0]);
	}

}
