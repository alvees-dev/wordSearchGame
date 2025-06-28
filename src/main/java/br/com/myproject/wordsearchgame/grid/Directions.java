package br.com.myproject.wordsearchgame.grid;

public enum Directions {
	
	HORIZONTAL(0, 1),
	VERTICAL(1, 0);
	
	private int directionLine;
	private int directionColumn;
	
	Directions (int directionLine, int directionColumn)  {
		this.directionLine = directionLine;
		this.directionColumn = directionColumn;
	}

	public int getDeltaLine() {
		return directionLine;
	}

	public int getDeltaColumn() {
		return directionColumn;
	}
	
	public static Directions entry(Coordinates begin, Coordinates end) {
		int deltaLine = end.getLine() - begin.getLine();
		int deltaColumn = end.getColumn() - begin.getColumn();
		
		if (deltaLine == 0 && deltaColumn != 0) {
			return HORIZONTAL;
		} else if (deltaLine != 0 && deltaColumn == 0) {
			return VERTICAL;
		}
		return null;
	}

}
