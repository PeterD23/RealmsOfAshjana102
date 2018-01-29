package Area.CastleGlorious;

import Characters.Player;

public class Castle {

	static Room1 room1;
	static Room2 room2;
	static Room3 room3;
	static Room4 room4;
	static Room5 room5;
	static Room6 room6;
	static CastleVar v;

	public static void init(boolean flag, CastleVar load) {
		if (flag) {
			v = load;
		} else {
			v = new CastleVar();
		}
		room1 = new Room1();
		room2 = new Room2();
		room3 = new Room3();
		room4 = new Room4();
		room5 = new Room5();
		room6 = new Room6();
	}

	public static void checkArea(String c, Player p) {
		if (p.getRoom() == 1) {
			room1.check(v, c, p);
		} else if (p.getRoom() == 2) {
			room2.check(v, c, p);
		} else if (p.getRoom() == 3) {
			room3.check(v, c, p);
		} else if (p.getRoom() == 4) {
			room4.check(v, c, p);
		} else if (p.getRoom() == 5) {
			room5.check(v, c, p);
		} else if (p.getRoom() == 6) {
			room6.check(v, c, p);
		}

	}

	public static CastleVar getVar() {
		return v;
	}
}
