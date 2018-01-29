package Area.Tektik;

import Characters.Monster;
import Characters.Player;
import Gameplay.RoomNoHostile;
import Gameplay.Character.InventoryItem;
import Gameplay.Generation.ResponseGen;

// THIS IS A FUCKING TEMPLATE, DO WHAT YOU FUCKING WILL WITH IT, OKAY? YEAH, FUCKIN DO THAT

public class RoomSt implements RoomNoHostile {
	
	// Replace 1 with Room number

	public void check(TektikVar v, String c, Player p) {
		ResponseGen res = new ResponseGen();

		if (c.contains("examine") || c.contains("look") || c.startsWith("l")) {
			System.out.println(examine(p, c, v));
		} else if (c.contains("use")) {
			System.out.println(use(p, c, v));
		} else if (c.contains("take") || c.contains("get") || c.startsWith("g")) {
			System.out.println(take(p, c, v));
		} else if (c.contains("talk to")) {
			System.out.println(talkTo());
		} else if (c.equals("north") || c.equals("n")) {
			System.out.println(north(p));
		} else if (c.equals("east") || c.equals("e")) {
			System.out.println(east(p));
		} else if (c.equals("west") || c.equals("w")) {
			System.out.println(west(p));
		} else if (c.equals("south") || c.equals("s")) {
			System.out.println(south(p));
		} else {
			System.out.println(res.gameResponse(true));
		}
	}

	public String talkTo() {
		return "There\"s no-one to talk to.";
	}

	public String north(Player p) {
		int r = p.getRoom();
		if (r == 2) {
			return "Oh jeez, i\"m sorry but this way is closed off!";
		} else if (r == 5 || r == 8 || r == 11) {
			p.moveRoom(-3);
			return "You take a stroll up the road.";
		} else if (r == 10 || r == 12) {
			p.moveRoom(-3);
			return "You take a stroll up the grass.";

		} else {
			return "You cannot walk north for fear of striking something concrete.";
		}
	}

	public String east(Player p) {
		int r = p.getRoom();
		if (r == 2 || r == 5) {
			p.moveRoom(1);
			return "You deftly stroll into the building.";
		} else {
			return "Your path is blocked by a thick ass hedge.";
		}
	}

	public String south(Player p) {
		int r = p.getRoom();
		if (r == 11) {
			p.moveArea(-100);
			p.moveRoom(-9);
			return "You move back through the clearing into the Forest.";
		} else if (r == 2 || r == 5 || r == 8) {
			p.moveRoom(3);
			return "You take a stroll down the road.";
		} else if (r == 7 || r == 9) {
			p.moveRoom(3);
			return "You take a stroll down the grass.";
		} else {
			return "Your path is blocked by a thick ass hedge.";
		}
	}

	public String west(Player p) {
		int r = p.getRoom();
		if (r == 2 || r == 5) {
			p.moveRoom(-1);
			return "You deftly stroll into the building.";
		} else {
			return "Your path is blocked by a thick ass hedge.";
		}
	}

	/**
	 * Activates the "use" command. Checks for possible outputs with extra
	 * parameters.
	 * 
	 * @param command
	 *            Parameter interpretation. Required for appropriate output and
	 *            progress.
	 * @return Response Text response returned from the command to the
	 *         interpreter.
	 */
	public String use(Player p, String c, TektikVar v) {
		return "Use what? Make some sense please!";

	}

	public String take(Player p, String c, TektikVar v) {
		return "Take what? Make some sense please!";
	}

	public String examine(Player p, String c, TektikVar v) {
		int r = p.getRoom();
		if (c.contains("building")) {
			if (r == 2) {
				return "The building to the east has a sign which reads"
						+ "\n\"Huells Potions\" and the west has a sign which reads"
						+ "\n\"Fregars Tavern.\"";
			} else if (r == 5) {
				return "The building to the east has a sign which reads"
						+ "\n\"Newton\"s Craftables\" and the west has a sign which reads"
						+ "\n\"Hawk\"s General Store";
			} else {
				return "";
			}
		}
		if (c.equals("examine") || c.equals("look") || c.equals("l")) {
			if (r == 11) {
				return "You see a hedge both east and west, the east side with a sign"
						+ "\non it. There is a long road leading north to an exit "
						+ "\nwith buildings on both sides.";
			} else if (r == 8) {
				return "You see a wide gap east and west, with a long road leading "
						+ "\nnorth to an exit with buildings on both sides. The road"
						+ "\nruns behind you to the Glorious Forest.";
			} else if (r == 5 || r == 2) {
				return "You see buildings both east and west, a road exit north and the "
						+ "\nclearing south as well as the wide open space.";
			} else {
				return "You see stuff. I dunno, sue me.";
			}
		}
		if (c.contains("sign")) {
			if (r == 11) {
				return "The sign reads as follows:"
						+ "\nWELCOME TO THE HEDGED TOWN OF TEKTIK! ENJOY YOUR STAY!";
			} else if (r == 8) {
				return "From this angle, the sign appears to read:"
						+ "\nWRMCRM T TE HDG TW F TKTK ENJY YR STY";
			} else {
				return ("Its too far away to read.");
			}
		} else {
			return "Examine what? Make some sense please!";
		}
	}
}