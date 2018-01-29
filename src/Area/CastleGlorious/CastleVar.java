package Area.CastleGlorious;

import java.io.Serializable;

public class CastleVar implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean rv1[]; //Room 1 Variables. 0-foundKey 1-takenKey 2-potionFound 3-potionTaken 4-ranIntoNorthWall 5-ranIntoDoor
				   //6-ranIntoSouthWall 7-doorUnlocked 8-doorOpen 9-leftArea 10-knockedOver 11-monsterPresent 12-monsterDefeated
	public boolean rv5[]; //0-talkedKing //1-gotSword //2-doorOpen
	
	public boolean rv6[]; //0-talkedToTrader 1-talkedToWoman
	
	public CastleVar() {
		rv1 = new boolean[13];
		for(int i = 0; i < 13; i++){
			rv1[i] = false;
		}
		rv5 = new boolean[3];
		for(int i = 0; i < 3; i++){
			rv5[i] = false;
		}
		rv6 = new boolean[2]; 
		for(int i = 0; i < 2; i++){
			rv6[i] = false;
		}
	}
	//-------------------------------------- END OF ROOM 1
	
	//ROOM 5
	
	
	
	
}
