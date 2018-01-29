package Area.GloriousForest;

import Characters.Monster;
import Characters.Player;

public class Forest {
	static RoomSt room1;
	static RoomBoss roomBoss;
	static ForestVar v;

	public static void init(boolean flag, ForestVar load) {
		if (flag) {
			v = load;
		} else {
			v = new ForestVar();
		}
		room1 = new RoomSt();
		roomBoss = new RoomBoss();
	}

	public static void checkArea(String c, Player p, Monster m) {
		if (p.getRoom() < 100) {
			room1.check(v, c, p, m);
		} else {
			roomBoss.check(v, c, p, m);
		}
	}

	public static ForestVar getVar() {
		return v;
	}
}
