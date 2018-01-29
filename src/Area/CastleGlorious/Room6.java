package Area.CastleGlorious;

import java.util.Scanner;

import Characters.Player;
import Gameplay.Character.InventoryItem;
import Gameplay.Generation.ResponseGen;
import Objects.Armor.ArmorChest;
import Objects.Armor.ArmorHead;

public class Room6 {
	public void check(CastleVar v, String c, Player p) {
		ResponseGen res = new ResponseGen();
		if (c.contains("examine") || c.contains("look") || c.startsWith("l")) {
			System.out.println(examine(c));
		} else if (c.contains("use")) {
			System.out.println(use(c, p));
		} else if (c.contains("take") || c.contains("get") || c.startsWith("g")
				|| c.contains("get")) {
			System.out.println(take());
		} else if (c.contains("talk to")) {
			System.out.println(talkTo(v, c, p));
		} else if (c.equals("north") || c.equals("n") || c.equals("n")) {
			System.out.println(north());
			p.moveArea(-99);
			p.moveRoom(2);
		} else if (c.equals("east") || c.equals("e") || c.equals("e")) {
			System.out.println(east());
		} else if (c.equals("west") || c.equals("w") || c.equals("w")) {
			System.out.println(west(p));
		} else if (c.equals("south") || c.equals("s") || c.equals("s")) {
			System.out.println(south());
		} else {
			System.out.println(res.gameResponse(true));
		}
	}

	public String talkTo(CastleVar v, String c, Player p) {
		if (c.contains("woman")) {
			if (!v.rv6[1]) {
				v.rv6[1] = true;
				p.getQuest().addQuest("The Missing Husband");
				return "\"You! Please, please find my husband!"
						+ "\nHe told me he was going to Tektik but he"
						+ "\nhasn't returned!\"";
			} else {
				return "\"PLEASE FIND MY HUSBAND! *sob*\"";
			}
		}
		if (c.contains("trader")) {
			if (c.endsWith("about trade")) {
				trade(p);
				return "\"Thank ye, come again!\"";
			} else if(c.endsWith("trader")){
				return "The trader looks at you and begins to speak."
						+ "\n\"Gid morning, stranger! I\"m a licensed trader"
						+ "\nfer tha kings village, and lately no been getting"
						+ "\nmuch business \"cause o thas evil wizard bollocks."
						+ "\nThat, and tha far superior town economy up north."
						+ "\nShid prabably mive up there, but I cannae be arsed.\"";
			} else {
				return "\"Sorry, I dinnae ken anything aboot tat.\"";
			}
				
			
		} else {
			return "There are two people standing there. Try talking to them?";
		}
	}

	public String north() {
		return "You head out into the forest, leaving the village behind.";
	}

	public String east() {
		return "Bunch of houses in the way, so no.";
	}

	public String south() {
		return "You move south towards the lovely beach.";
	}

	public String west(Player p) {
		p.moveRoom(-1);
		return "You head back into the castle to see the king.";
	}

	public String use(String c, Player p) {
		if (c.contains("well")) {
			return p.getCraft().fill(p);
		} else {
			return "Use what?";
		}
	}


	public String take() {
		return "Take what? Trader won't be happy if you steal his shit!";
	}

	public String examine(String command) {
		if (command.contains("entrance")) {
			return "It is a large arched door. Wait a second... How on earth is this"
					+ "\na castle?!!";
		} else if (command.contains("trader")) {
			return "He appears to be selling goods.";
		} else if (command.contains("woman")) {
			return "She looks a bit distraught. Maybe you should cheer her up?";
		} else if (command.equals("examine")) {
			return "You are standing in the village outside the castle."
					+ "\nThere are an array of houses covered by two in front"
					+"\nwith a well to the right of them. There are two people"
					+ "\npeople standing outside their homes. One appears to be"
					+ "\na trader and the other a woman.";
		} else {
			return "Sorry, examine what?";
		}
	}

	public void trade(Player p) {
		System.out
				.println("Absolutely! Wat wid ye like? Type inv for details!");
		Scanner scan = new Scanner(System.in);
		String command = "";
		while (!command.equals("exit")) {
			System.out.print("> ");
			command = scan.nextLine();
			if (command.equals("inv")) {
				System.out.println("1: Minor Health Potion, 15 gold"
						+ "\n2: Basic Cotton Jumper, Def 3, Dur 18, 90 gold"
						+ "\n3: Basic Cotton Hat, Def 1, Dur 6, 30 gold");
			} else if (command.equals("1")) {
				if (p.getInv().hasSpace() && p.getGold() >= 15) {
					p.addGold(-15);
					p.getInv()
							.addItem(new InventoryItem("Minor Health Potion"));
					System.out.println("Gid purchase sir!");
				} else {
					System.out.println("Sorry sir, ye can't purchase that!");
				}
			} else if (command.equals("2")) {
				if (p.getInv().hasSpace() && p.getGold() >= 15) {
					p.addGold(-15);
					int[] val = { 3, 18, 45 };
					p.getInv().addItem(
							new InventoryItem(new ArmorChest(val,
									"Basic Cotton Jumper")));
					System.out.println("Gid purchase sir!");
				} else {
					System.out.println("Sorry sir, ye can't purchase that!");
				}
			} else if (command.equals("3")) {
				if (p.getInv().hasSpace() && p.getGold() >= 15) {
					p.addGold(-15);
					int[] val = { 1, 6, 15 };
					p.getInv().addItem(
							new InventoryItem(new ArmorHead(val,
									"Basic Cotton Hat")));
					System.out.println("Gid purchase sir!");

				} else {
					System.out.println("Sorry sir, ye can't purchase that!");
				}
			} else if (command.equals("exit")) {
			} else {
				System.out.println("Sorry, I dinnae sell that!");
			}
		}
	}
}
