package Area.Tektik;

import java.io.Serializable;

public class TektikVar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean rv1[]; //Room 1 Variables. 0-foundKey 1-takenKey 2-potionFound 3-potionTaken 4-ranIntoNorthWall 5-ranIntoDoor
				   //6-ranIntoSouthWall 7-doorUnlocked 8-doorOpen 9-leftArea 10-knockedOver 11-monsterPresent 12-monsterDefeated
	boolean rv5[];
	public boolean talkedAboutJob;
	public boolean talkedAboutRats;
	public boolean talkedToJeromah;
	public boolean talkedToJim;
	
	public TektikVar() {
		talkedToJim = false;
		talkedToJeromah = false;
		talkedAboutJob = false;
		talkedAboutRats = false;
	}

	//-------------------------------------- END OF ROOM 1
	
	//ROOM 5
	
	
	
	
}
