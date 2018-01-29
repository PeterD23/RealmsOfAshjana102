package Area.DaringDungeon;

import org.fusesource.jansi.AnsiConsole;

import Characters.Monster;
import Characters.Player;
import Gameplay.SoundTrigger;
import Gameplay.Character.InventoryItem;
import Gameplay.Generation.ResponseGen;

// THIS IS A FUCKING TEMPLATE, DO WHAT YOU FUCKING WILL WITH IT, OKAY? YEAH, FUCKIN DO THAT

public class RoomSt {

	int areaLength = 3;
	int areaWidth = 3;
	public Monster m;
	InventoryItem loot;
	public boolean lootExists;

	// Replace 1 with Room number

	public void check(DungeonVar v, String c, Player p, Monster m) {
		ResponseGen res = new ResponseGen();

		if (!m.doesExist()) {
			if (c.contains("examine") || c.contains("look")
					|| c.startsWith("l")) {
				System.out.println(examine(p, c, v));
			} else if (c.contains("use")) {
				System.out.println(use(c, v));
			} else if (c.contains("take") || c.contains("get")
					|| c.startsWith("g")) {
				System.out.println(take(p, c, v));
			} else if (c.contains("talk to")) {
				System.out.println(talkTo());
			} else if (c.equals("north") || c.equals("n")) {
				System.out.println(north(p, v));
			} else if (c.equals("east") || c.equals("e")) {
				System.out.println(east(p, v));
			} else if (c.equals("west") || c.equals("w")) {
				System.out.println(west(p, v));
			} else if (c.equals("south") || c.equals("s")) {
				System.out.println(south(p, v));
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

	public String north(Player p, DungeonVar v) {
		int r = p.getRoom();
		if (r == 2) {
			p.moveRoom(100);
			if(!v.monsterDefeated){
				p.setCleared(true);
				return "Boss is coming! Type anything to continue!";
			}
			return "You walk into the fire cave.";
		} else if (v.canMoveNorth(r)) {
			p.setCleared(false);
			p.moveRoom(-4);
			lootExists = false;
			return "You creep north through the musty dungeon.";
		} else {
			return "The stone wall blocks your path.";
		}
	}

	public String east(Player p, DungeonVar v) {
		int r = p.getRoom();
		if (v.canMoveEast(r)) {
			p.setCleared(false);
			p.moveRoom(1);
			lootExists = false;
			return "You creep east through the musty dungeon.";
		} else {
			return "The stone wall blocks your path.";
		}
	}

	public String south(Player p, DungeonVar v) {
		int r = p.getRoom();
		if (r == 15) {
			p.moveArea(-1);
			p.moveRoom(-10);
			lootExists = false;
			return "You climb back up the staircase to the forest.";
		} else if (v.canMoveSouth(r)) {
			p.setCleared(false);
			p.moveRoom(4);
			lootExists = false;
			return "You creep south through the musty dungeon.";
		} else {
			return "The stone wall blocks your path.";
		}
	}

	public String west(Player p, DungeonVar v) {
		int r = p.getRoom();
		if (v.canMoveWest(r)) {
			p.setCleared(false);
			p.moveRoom(-1);
			lootExists = false;
			return "You creep west through the musty dungeon.";
		} else {
			return "The stone wall blocks your path.";
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
	public String use(String c, DungeonVar v) {
		return "Use what? Make some sense please!";
	}

	public String take(Player p, String c, DungeonVar v) {
		if (c.contains("loot") && lootExists) {
			p.getInv().addItem(loot);
			lootExists = false;
			return "You take the loot.";
		} else {
			return "Take what? Make some sense please!";
		}
	}

	public String examine(Player p, String c, DungeonVar v) {
		int r = p.getRoom();
		String extra = "";
		if (c.contains("loot") && lootExists) {
			return "That\"s OBVIOUSLY a " + loot.getName()
					+ "! What else could it be?";
		}
		if (c.contains("chest") && r == 3) {
			return "The chest is covered in dust, like it has never been opened.";
		}
		if ((c.equals("examine") || c.equals("look") || c.equals("l")) && r == 2) {
			return "You see a bright light to the north and dimness to the south.";
		}
		if ((c.equals("examine") || c.equals("look") || c.equals("l")) && r == 15) {
			return "You see the staircase to the south, and the light from outside \n dims in the east and west direction.";
		}
		if ((c.equals("examine") || c.equals("look") || c.equals("l")) && r == 3) {
			return "Adjacent to the west wall you see a chest.";
		} else if ((c.equals("examine") || c.equals("look") || c.equals("l"))) {
			if (v.canMoveNorth(r)) {
				extra = extra + "\nYou can go north.";
			}
			if (v.canMoveEast(r)) {
				extra = extra + "\nYou can go east.";
			}
			if (v.canMoveSouth(r)) {
				extra = extra + "\nYou can go south.";
			}
			if (v.canMoveWest(r)) {
				extra = extra + "\nYou can go west.";
			}
			return "The dungeon is dark and labyrinthine." + extra;
		} else {
			return "Examine what? Make some sense please!";
		}
	}

}