package Gameplay.Character;

import java.io.Serializable;

public class Map implements Serializable {

	private boolean anyPieces;
	
	public Map(boolean anyPieces) {
		this.setAnyPieces(anyPieces);
	}

	public static String getLocation(int area) {
		if (area == 100) {
			return "Southwest Kytos, Castle Glorious.";
		} else if (area == 1) {
			return "West Kytos, Glorious Forest.";
		} else if (area == 101) {
			return "West Kytos, Tektik.";
		} else if (area == 2) {
			return "West Kytos, Daring Dungeon.";
		} else {
			return "null";
		} 
	}

	public boolean hasAnyPieces() {
		return anyPieces;
	}

	public void setAnyPieces(boolean anyPieces) {
		this.anyPieces = anyPieces;
	}
}
