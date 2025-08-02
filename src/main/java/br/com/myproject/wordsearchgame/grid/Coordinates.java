package br.com.myproject.wordsearchgame.grid;

import java.util.Objects;

public class Coordinates {

	private final char line;
	private final int column;

	public Coordinates(char line, int column) {
		this.line = line;
		this.column = column;
	}

	public Coordinates(String coordinates) {
		if (!isValidFormat(coordinates)) {
			throw new IllegalArgumentException("Formato de coordenada inválido");
		}
		this.line = Character.toUpperCase(coordinates.charAt(0));
		this.column = Integer.parseInt(coordinates.substring(1));
	}

	private boolean isValidFormat(String coordinates) {
		return coordinates != null && coordinates.matches("[A-Za-z]\\d+");
	}	

	public int getLine() {
		return line - 'A' + 1;
	}
	
	public int getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		return line + String.valueOf(column);
	}
	
	@Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Coordinates that = (Coordinates) obj;
	        return line == that.line && column == that.column;
	    }
	
	@Override
	public int hashCode() {
		return Objects.hash(line, column);
	}

}
