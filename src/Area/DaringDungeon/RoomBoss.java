package Area.DaringDungeon;

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

	public void check(DungeonVar v, String c, Player p, Monster m) {
		ResponseGen res = new ResponseGen();

		// Use if needed
		if (!v.goneIn) {
			p.setCleared(false);
			monsterHere(p, v);
		} else if (!m.doesExist()) {
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
		return "The wall of fire stretches around the room.";
	}

	public String east(Player p) {
		return "The wall of fire stretches around the room.";
	}

	public String south(Player p) {
		p.moveRoom(-100);
		return "You move back into the dungeon.";
	}

	public String west(Player p, DungeonVar v) {
		return "The wall of fire stretches around the room.";
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
	public String use(Player p, String c, DungeonVar v) {
		return "Use what? Make some sense please!";
	}

	public String take(Player p, String c, DungeonVar v) {
		return "Take what? Make some sense please.";
	}

	public String examine(Player p, String c, DungeonVar v) {
		return "You see a lot of rocks, some of them on fire.";
	}

	// SPECIAL BOSS COMMANDS
	public void monsterHere(Player p, DungeonVar v) {
		try {
			v.goneIn = true;
			System.out.println("You walk into the light.");
			SoundTrigger demo = new SoundTrigger("res\\bossstart.wav");
			p.setBoss(true);
			Thread.sleep(3000);
			System.out.println("As your eyes refocus, you see...");
			Thread.sleep(3500);
			bossLoop = new SoundTrigger("res\\boss1.wav");
			bossLoop.loop();
		} catch (Exception e) {
			System.out.println("This boss battle brought to you by error ftp");
		}
	}

}