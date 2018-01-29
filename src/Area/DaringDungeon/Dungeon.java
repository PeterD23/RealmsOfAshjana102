package Area.DaringDungeon;

import Characters.Monster;
import Characters.Player;

public class Dungeon {
	static RoomSt room1;
	static RoomBoss roomBoss;
	//static Room3 room3; 
	//static Room4 room4;
	//static Room5 room5;
	//static Room6 room6;
	static DungeonVar v;
	
	public static void init(boolean flag, DungeonVar load) {
		if (flag) {
			v = load;
		} else {
			v = new DungeonVar();
		}
		room1 = new RoomSt();
		roomBoss = new RoomBoss();
		//room2 = new Room2();
		//room3 = new Room3();
		//room4 = new Room4();
		//room5 = new Room5();
		//room6 = new Room6();
	}

	public static void checkArea(String c, Player p, Monster m) {
		if(p.getRoom() < 100){
		room1.check(v, c, p, m);
		} else {
			roomBoss.check(v,c,p,m);
		}
	}

	public static DungeonVar getVar() {
		return v;
	}
}
