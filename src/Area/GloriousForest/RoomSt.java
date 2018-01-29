package Area.GloriousForest;

import org.fusesource.jansi.AnsiConsole;

import Characters.Monster;
import Characters.Player;
import Gameplay.Character.InventoryItem;
import Gameplay.Generation.ResponseGen;

// THIS IS A FUCKING TEMPLATE, DO WHAT YOU FUCKING WILL WITH IT, OKAY? YEAH, FUCKIN DO THAT

public class RoomSt {

	int areaLength = 3;
	int areaWidth = 3;
	InventoryItem loot;
	public boolean lootExists;

	// Replace 1 with Room number

	public void check(ForestVar v, String c, Player p, Monster m) {
		ResponseGen res = new ResponseGen();

		if (!m.doesExist()) {
			if (c.contains("examine") || c.contains("look")
					|| c.startsWith("l")) {
				System.out.println(examine(p, c, v));
			} else if (c.contains("use")) {
				System.out.println(use(p, c, v));
			} else if (c.contains("take") || c.contains("get")
					|| c.startsWith("g")) {
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
		} else if (c.contains("attack") && m.doesExist()) {
			AnsiConsole.systemInstall();
			System.out.println(p.attack(m));
			if (m.doesExist()) {
				System.out.println(m.attack(p, null));
			}
			AnsiConsole.systemUninstall();
			if (p.getCleared()) {
				loot = m.getLoot();
				if (loot.getType() != 8) {
					lootExists = true;
				}
			}
		} else {
			System.out
					.println("You cannot do anything else right now! Regardless of invalid syntax!");
		}
	}

	public String talkTo() {
		return "There's no-one to talk to.";
	}

	public String north(Player p) {
		if (p.getRoom() == 2) {
			p.moveArea(100);
			p.moveRoom(9);
			lootExists = false;
			return "You walk north through the clearing."
					+ "\nThis must be Tektik!";
		} else if (p.getRoom() > 3) {
			p.setCleared(false);
			p.moveRoom(-3);
			lootExists = false;
			return "You take a walk north through the murky bushes.";
		} else {
			return "A thick layer of shrubs blocks you from moving.";
		}
	}

	public String east(Player p) {
		if (p.getRoom() != 3 && p.getRoom() != 6 && p.getRoom() != 9) {
			p.setCleared(false);
			p.moveRoom(1);
			lootExists = false;
			return "You take a walk east through the murky bushes.";
		} else {
			return "A thick layer of shrubs blocks you from moving.";
		}
	}

	public String south(Player p) {
		if (p.getRoom() == 8) {
			p.moveArea(99);
			p.moveRoom(-2);
			lootExists = false;
			return "You move through the clearing back to Glorious.";
		} else if (p.getRoom() < 7) {
			p.setCleared(false);
			p.moveRoom(3);
			lootExists = false;
			return "You take a walk south through the murky bushes.";
		} else {
			return "A thick layer of shrubs blocks you from moving.";
		}
	}

	public String west(Player p) {
		if (p.getRoom() != 1 && p.getRoom() != 7) {
			if (p.getRoom() == 4) {
				p.moveRoom(96);
				return "You move through the clearing.";
			} else {
				p.setCleared(false);
				p.moveRoom(-1);
				lootExists = false;
				return "You take a walk west through the murky bushes.";
			}
		} else {
			return "A thick layer of shrubs blocks you from moving.";
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
	public String use(Player p, String c, ForestVar v) {
		if (c.contains("sign") && !v.pushedSign) {
			if (p.getStrength() > 20) {
				v.pushedSign = true;
				return "You push the sign, and it moves like a lever,"
						+ "\nrevealing a staircase right next to it!";
			} else {
				return "You attempt to push the sign, but it won't budge.";
			}
		} else if (c.contains("sign") && v.pushedSign) {
			return "You already pushed the sign and revealed the staircase!";
		} else if (c.contains("stair") && v.pushedSign) {
			p.moveArea(1);
			p.moveRoom(10);
			return "You cautiously descend the steps into the dungeon.";
		} else {
			return "Use what? Make some sense please!";
		}

	}

	public String take(Player p, String c, ForestVar v) {
		if (c.contains("loot") && lootExists) {
			p.getInv().addItem(loot);
			lootExists = false;
			return "You take the loot.";
		} else {
			return "Take what? Make some sense please!";
		}
	}

	public String examine(Player p, String c, ForestVar v) {
		if (c.contains("loot") && lootExists) {
			return "That's OBVIOUSLY a " + loot.getName()
					+ "! What else could it be?";
		} else if (c.contains("sign") && p.getRoom() == 5) {
			return "The signpost reads as follows: \nTEKTIK - 2N\nGLORIOUS - 2S";
		} else if ((c.equals("examine") || c.equals("look") || c.equals("l"))
				&& p.getRoom() == 2) {
			return "You see a clearing to the north.";
		} else if ((c.equals("examine") || c.equals("look") || c.equals("l"))
				&& p.getRoom() == 5) {
			return "Amongst all the trees, you see a signpost.";
		} else if ((c.equals("examine") || c.equals("look") || c.equals("l"))) {
			return "You see nothing but trees. Its all the same, so"
					+ "\nyou should probably take note of where you entered.";
		} else {
			return "Examine what? Make some sense please!";
		}
	}

}