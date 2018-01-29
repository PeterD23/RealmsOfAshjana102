package Area.CastleGlorious;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import org.fusesource.jansi.Ansi;
import Characters.Player;
import Gameplay.Character.InventoryItem;
import Gameplay.Generation.ResponseGen;
import Objects.Weapon;

public class Room5 {
	public void check(CastleVar v, String c, Player p) {
		ResponseGen res = new ResponseGen();
		if (c.contains("examine") || c.contains("look") || c.startsWith("l")) {
			System.out.println(examine(c));
		} else if (c.contains("use")) {
			System.out.println(use());
		} else if (c.contains("take") || c.contains("get") || c.startsWith("g")) {
			System.out.println(take());
		} else if (c.contains("talk to")) {
			AnsiConsole.systemInstall();
			System.out.println(talkTo(v, c, p));
			AnsiConsole.systemUninstall();
		} else if (c.equals("north") || c.equals("n")) {
			System.out.println(north());
		} else if (c.equals("east") || c.equals("e")) {
			System.out.println(east(v, p));
		} else if (c.equals("west") || c.equals("w")) {
			System.out.println(west());
		} else if (c.equals("south") || c.equals("s")) {
			System.out.println(south());
		} else {
			System.out.println(res.gameResponse(true));
		}
	}

	public Ansi talkTo(CastleVar v, String c, Player p) {
		if (c.contains("king")) {
			if (c.endsWith("about castle") && v.rv5[0]) { // If talked to king
				return ansi()
						.render("@|bold,cyan Oh, the castle? Its like a reverse TARDIS. Its bigger|@")
						.newline()
						.render("@|bold,cyan on the outside than on the inside, heh heh!|@");
			} else if (c.endsWith("about job") && v.rv5[0]) {
				if (!v.rv5[1]) { // If Havent talked to King about Job
					v.rv5[1] = true; // Talked to King about Job
					v.rv5[2] = true; // Door Opens
					p.getQuest().addQuest("Jeromah of Tektik");
					int[] temp = { 3, 15, 9 };
					p.getInv()
							.addItem(
									new InventoryItem(new Weapon(
											"Simple Sword", temp)));
					return ansi()
							.render("@|bold,cyan To stop Kurtash from potentially destroying the realm, he|@")
							.newline()
							.render("@|bold,cyan must be locked back into the magical prison which he escaped.|@")
							.newline()
							.render("@|bold,cyan You must find Jeromah, a bartender who last I heard was occupied|@")
							.newline()
							.render("@|bold,cyan in Tektik. He may be retired, but he can help you better than I can.|@")
							.newline()
							.render("@|bold,cyan The only thing I can offer you is a starter sword. Its better than|@")
							.newline()
							.render("@|bold,cyan your fists I guess. The door is now open, so go out there!|@");
				} else {
					return ansi()
							.render("@|bold,cyan I told you, you must find Jeromah. I already gave you the sword. |@");
				}

			} else {
				if (!v.rv5[0]) {
					v.rv5[0] = true;
					return ansi()
							.render("The king looks at you, and begins to speak.")
							.newline()
							.render("@|bold,cyan Good morning, " + p.getName()
									+ "! I see from the commotion |@")
							.newline()
							.render("@|bold,cyan you have had a run-in with my puppy? Unfortunately I did|@")
							.newline()
							.render("@|bold,cyan not expect you to punch its lights out. I'll let you off|@")
							.newline()
							.render("@|bold,cyan with that one. Liking the interior of the|@ @|bold,white castle|@ @|bold,cyan are we? Lovely.|@")
							.newline()
							.render("@|bold,cyan Let's get down to business shall we? I must regret to inform|@")
							.newline()
							.render("@|bold,cyan you that the once peaceful realms that inhabit Ashjana have|@")
							.newline()
							.render("@|bold,cyan become chaos, and regrettably I require you for an important|@ @|bold,white job.|@");
				} else if (c.endsWith("king")) {
					return ansi().render(
							"@|bold,cyan Good morning, " + p.getName()
									+ "! How are things?|@");
				} else {
					return ansi()
							.render("@|bold,cyan Uhh... yeah, definitely!|@");
				}
			}
		} else {
			return ansi()
					.render("The king is standing right there. Shouldn't you talk to him?");
		}
	}

	public String north() {
		return "I doubt the King wants to see you dent his walls with your face.";
	}

	public String east(CastleVar v, Player p) {
		if (v.rv5[2]) {
			p.moveRoom(1);
			return "The door magically opens, and you leave the castle to enter the tiny village"
					+ "\nof Castle Glorious.";
		} else {
			return "The door won't open like it should do.";
		}
	}

	public String south() {
		return "I doubt the King wants to see you dent his walls with your face.";
	}

	public String west() {
		return "The force now pushes you back, so you cannot walk back into the corridor.";
	}

	public String use() {
		return "Theres no point using anything here, its not like you will be coming back.";
	}

	public String take() {
		return "There\"s nothing in this corridor to take, and there never will be.";
	}

	public String examine(String command) {
		if (command.contains("dog")) {
			return "You can\"t see the dog anymore. However, I think the king would want a word.";
		} else if (command.contains("entrance")) {
			return "It is a large arched door. Wait a second... How on earth is this"
					+ "\na castle?!!";
		} else if (command.contains("king")) {
			return "He appears to be tapping his foot in annoyance.";
		} else if (command.contains("closet")) {
			return "A standard closet I assume. Filled with power tools that cannot possibly"
					+ "\nexist because this is a medieval fantasy setting!";
		} else if (command.equals("examine")) {
			return "You are standing at the entrance of the castle, where you will exit."
					+ "\nThe king is there next to the entrance. Behind you is the closet.";
		} else {
			return "Sorry, examine what?";
		}
	}
}
