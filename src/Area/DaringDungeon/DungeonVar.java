package Area.DaringDungeon;

import java.io.Serializable;

public class DungeonVar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean chestOpened;
	public boolean monsterDefeated;
	public boolean goneIn;

	public DungeonVar() {
		monsterDefeated = false;
		goneIn = false;
		chestOpened = false;
	}

	public boolean canMoveNorth(int r) {
		int[] walls = { 1, 3, 4, 7, 10, 15 };
		boolean canMove = true;
		for (int i = 0; i < walls.length; i++) {
			if (r == walls[i]) {
				canMove = false;
			}
		}
		return canMove;
	}

	public boolean canMoveEast(int r) {
		int[] walls = { 1, 2, 4, 6, 8, 10, 11, 12, 13, 16 };
		boolean canMove = true;
		for (int i = 0; i < walls.length; i++) {
			if (r == walls[i]) {
				canMove = false;
			}
		}
		return canMove;
	}

	public boolean canMoveSouth(int r) {
		int[] walls = { 3, 6, 11, 13, 14, 16 };
		boolean canMove = true;
		for (int i = 0; i < walls.length; i++) {
			if (r == walls[i]) {
				canMove = false;
			}
		}
		return canMove;
	}

	public boolean canMoveWest(int r) {
		int[] walls = { 1, 2, 3, 5, 7, 9, 11, 12, 13, 14 };
		boolean canMove = true;
		for (int i = 0; i < walls.length; i++) {
			if (r == walls[i]) {
				canMove = false;
			}
		}
		return canMove;
	}

	// -------------------------------------- END OF ROOM 1

	// ROOM 5

}
