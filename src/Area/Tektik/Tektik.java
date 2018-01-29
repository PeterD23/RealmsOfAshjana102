package Area.Tektik;

import Characters.Player;

public class Tektik {

	static RoomSt townExterior;
	static Shop1 bar;
	static Shop2 alc;
	static TektikVar v;

	public static void init(boolean flag, TektikVar vL) {
		townExterior = new RoomSt();
		bar = new Shop1();
		alc = new Shop2();
		v = new TektikVar();
	}

	public static void checkArea(String c, Player p) {
		if (p.getRoom() == 1) {
			bar.check(v, c, p);
		}else if (p.getRoom() == 3) {
			alc.check(v, c, p);
		} else if(p.getRoom() == 4){
			p.moveRoom(1);
			System.out.println("The tinker boots you out shouting \"We're closed!\"");
		} else if(p.getRoom() == 6){
			p.moveRoom(-1);
			System.out.println("The trader boots you out shouting \"We're closed!\"");
		}
		else {
			townExterior.check(v, c, p);
		}

	}

	public static TektikVar getVar() {
		return v;
	}
}
