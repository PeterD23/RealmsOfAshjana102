package Area.CastleGlorious;

import Characters.Player;
import Gameplay.Generation.ResponseGen;

public class Room3 {

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
				System.out.println(east());
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

		public String east() {
			return "Your common sense dictates that you should NOT go this way.";
		}

		public String south(Player p) {
			p.moveRoom(1);
			return "You stroll past the second door, and stop next to the third with the corner opposite.";
		}

		public String west() {
			return "Thats the kings quarters. It wouldn\"t be a good idea to go in there.";
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
			} else if (command.contains("corner")) {
				return "You get the feeling the King will be standing around the corner,"
						+ "\ntapping his foot in annoyance.";
			} else if (command.contains("door")) {
				return "The doors look a lot better from the outside. Guess the King is a"
						+ "\ncheapskate that way.";
			} else if (command.equals("examine")) {
				return "You are standing in a relatively plush corridor. "
						+ "\nYou peek to the south, looking west to see one more door."
						+ "\nYou see a corner opposite the furthest down door heading east."
						+ "\nTo the north you look to see the dog, slumped on the floor.";
			} else {
				return "Sorry, examine what?";
			}
		}
	}


