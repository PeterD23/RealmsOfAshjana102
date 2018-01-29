package Area.GloriousForest;

import org.fusesource.jansi.AnsiConsole;

import Characters.Monster;
import Characters.Player;
import Gameplay.SoundTrigger;
import Gameplay.Character.InventoryItem;
import Gameplay.Generation.LootGen;
import Gameplay.Generation.ResponseGen;

// THIS IS A FUCKING TEMPLATE, DO WHAT YOU FUCKING WILL WITH IT, OKAY? YEAH, FUCKIN DO THAT

public class RoomBoss {

	InventoryItem loot;
	public Monster m;
	SoundTrigger bossLoop;
	boolean lootExists;

	public void check(ForestVar v, String c, Player p, Monster m) {
		ResponseGen res = new ResponseGen();

		// Use if needed
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
				System.out.println(north());
			} else if (c.equals("east") || c.equals("e")) {
				System.out.println(east(p));
			} else if (c.equals("west") || c.equals("w")) {
				System.out.println(west(p, v));
			} else if (c.equals("south") || c.equals("s")) {
				System.out.println(south(p));
			} else {
				System.out.println(res.gameResponse(true));
			}
		} else if (c.contains("attack") && m.doesExist()) {
			AnsiConsole.systemInstall();
			System.out.println(p.attack(m));
			if (m.doesExist()) {
				System.out.println(m.attack(p, bossLoop));
			}
			AnsiConsole.systemUninstall();
			if (p.getCleared()) {
				v.monsterDefeated = true;
				bossLoop.stop();
				p.addStory("Who casually whooped a giant rat's ass");
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
		return "There\"s no-one to talk to.";
	}

	public String north() {
		return "The hedge extends around the garden, you can\"t go north.";
	}

	public String east(Player p) {
		if (p.getRoom() == 101) {
			p.moveRoom(-1);
			return "You casually wander back out of the house.";
		} else {
			p.moveRoom(-96);
			return "You move back through the clearing to the forest.";
		}
	}

	public String south(Player p) {
		if (p.getRoom() == 101) {
			return "Ms Hygard won\"t appreciate you denting her walls.";
		} else {
			return "The hedge extends around the garden, you can\"t go south.";
		}
	}

	public String west(Player p, ForestVar v) {
		if (v.doorOpen) {
			if (p.getRoom() == 100) {
				p.moveRoom(1);
				if (!v.monsterDefeated) {
					p.setCleared(false);
					return monsterHere(p);
				} else {
					return "You wander into the house.";
				}
			} else {
				return "Ms Hygard won\"t appreciate you denting her walls.";
			}
		} else {
			return "The door is closed.";
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
		if (c.endsWith("key on door") && p.hasKey("Hygard Residence")) {
			if (!v.doorUnlocked) {
				v.doorUnlocked = true;
				return "You use the Hygard Residence key on the door.";
			} else {
				return "The door is already unlocked.";
			}

		} else if (c.endsWith("door")) {
			if (v.doorUnlocked) {
				if (!v.doorOpen) {
					v.doorOpen = true;
					return "You cautiously open the door, and a terror awaits you.";
				} else {
					return "You already opened the door.";
				}
			} else {
				return "The door is locked.";
			}
		} else {
			return "Use what? Make some sense please!";
		}
	}

	public String take(Player p, String c, ForestVar v) {
		return "Take what? Make some sense please.";
	}

	public String examine(Player p, String c, ForestVar v) {
		if (c.endsWith("sign") && p.getRoom() == 100) {
			return "The sign reads \"Hygard Residence.\"";
		}
		if (c.equals("examine") || c.equals("look") || c.equals("l") && p.getRoom() == 100) {
			return "You are in a nice looking garden. There is a house"
					+ "\nright next to you to the west, with a nice door and a sign"
					+ "\nnext to it.";
		} else if (c.equals("examine") || c.equals("look") || c.equals("l") && p.getRoom() == 101) {
			return "The house interior looks wonderful, or at least it used"
					+ "\nto be before you sprayed the rat\"s guts all over the walls.";
		} else {
			return "Examine what? Make some sense please!";
		}
	}

	// SPECIAL BOSS COMMANDS
	public String monsterHere(Player p) {
		try {
			System.out.println("You tiptoe into the house.");
			SoundTrigger demo = new SoundTrigger("res\\bossstart.wav");
			p.setBoss(true);
			Thread.sleep(3000);
			System.out.println("Emerging out of the darkness is...");
			Thread.sleep(3500);
			bossLoop = new SoundTrigger("res\\boss1.wav");
			bossLoop.loop();
			return "";
		} catch (Exception e) {
			System.out.println("This boss battle brought to you by error ftp");
			return "Oops!";
		}
	}

}