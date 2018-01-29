package Area.CastleGlorious;

import Characters.Player;
import Gameplay.Generation.ResponseGen;

public class Room4 {

	public void check(CastleVar v, String c, Player p) {
		ResponseGen res = new ResponseGen();
		if (c.contains("examine") || c.contains("look") || c.startsWith("l")) {
			System.out.println(examine(c));
		} else if (c.contains("use")) {
			System.out.println(use());
		} else if (c.contains("take") || c.contains("get") || c.startsWith("g")) {
			System.out.println(take());
		} else if (c.contains("talk to")) {
			System.out.println(talkTo());
		} else if (c.equals("north") || c.equals("n")) {
			System.out.println(north());
		} else if (c.equals("east") || c.equals("e")) {
			System.out.println(east(p));
		} else if (c.equals("west") || c.equals("w")) {
			System.out.println(west());
		} else if (c.equals("south") || c.equals("s")) {
			System.out.println(south(p));
		} else {
			System.out.println(res.gameResponse(true));
		}
	}
	
	public String talkTo() {
		return "There\"s no-one to talk to.";
	}

	public String north() {
		return "You feel a force pushing you back, almost like some kind"
				+"\nof invisible wall. Perhaps this is some contrived system"
				+"\nthat the designer of this game added because he\"s lazy.";
	}

	public String east(Player p) {
		p.moveRoom(1);
		return "You walk towards the entrance, and notice this place is really small for a castle.";
	}

	public String south(Player p) {
		return "Unless you want to patent the \"facewall\" I recommend you don\"t go this way.";
	}

	public String west() {
		return "Thats a closet. I doubt you\"d be needing an electric drill.";
	}

	public String use() {
		return "Theres no point using anything here, its not like you will be coming back.";
	}

	public String take() {
		return "There\"s nothing in this corridor to take, and there never will be.";
	}

	public String examine(String command) {
		if (command.contains("dog")) {
			return "The labrador lies on the floor, exhausted."
					+ "\nIt doesn\"t look that hurt, but you are a nasty person.";
		} else if (command.contains("corridor")) {
			return "You begin to realise this is the King\"s corridor, and most likely"
					+ "\nonce you leave here, you can never return, due to his magic.";
		} else if (command.contains("king")) {
			return "He appears to be tapping his foot in annoyance.";
		} else if (command.contains("door")) {
			return "The doors look a lot better from the outside. Guess the King is a"
					+ "\ncheapskate that way.";
		} else if (command.equals("examine")) {
			return "You are standing in a relatively plush corridor. "
					+ "\nYou look to the east to see the entrance, and the King."
					+ "\nThe dog is now a lot smaller looking.";
		} else {
			return "Sorry, examine what?";
		}
	}
}
