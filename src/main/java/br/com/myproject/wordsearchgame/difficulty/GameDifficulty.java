package br.com.myproject.wordsearchgame.difficulty;

public enum GameDifficulty {
	
	EASY(10, "Fácil"),
	NORMAL(15, "Médio"),
	HARD(20, "Difícil");
	
	private final int boardSize;
	private final String description;
	
	GameDifficulty(int boardSize, String description) {
		this.boardSize = boardSize;
		this.description = description;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public String getDescription() {
		return description;
	}
	
	

}
