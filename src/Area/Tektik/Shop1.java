package Area.Tektik;

import Characters.Player;
import Gameplay.Generation.ResponseGen;

public class Shop1 {
	public void check(TektikVar v, String c, Player p) {
		ResponseGen res = new ResponseGen();
		if (c.contains("examine") || c.contains("look") || c.startsWith("l")) {
			System.out.println(examine(c));
		} else if (c.contains("use")) {
			System.out.println(use());
		} else if (c.contains("take ") || c.contains("get ")
				|| c.startsWith("g ")) {
			System.out.println(take());
		} else if (c.contains("talk to")) {
			System.out.println(talkTo(v, c, p));
		} else if (c.equals("north") || c.equals("n")) {
			System.out.println(north());
		} else if (c.equals("east") || c.equals("e")) {
			System.out.println(east(p));
		} else if (c.equals("west") || c.equals("w")) {
			System.out.println(west());
		} else if (c.equals("south") || c.equals("s")) {
			System.out.println(south());
		} else {
			System.out.println(res.gameResponse(true));
		}
	}

	public String talkTo(TektikVar v, String c, Player p) {
		if (c.contains("people")) {
			if (c.endsWith("about kurtash")) {
				System.out
						.println("\"KURTASH?!?!\" The people freak out, and one"
								+ "\nbreaks his bottle and stabs you in the neck.");
				try {
					p.die();
				} catch (Exception e) {

				}
				return "";
			} else if (c.endsWith("about jim") && !v.talkedToJim) {
				v.talkedToJim = true;
				p.getQuest().removeQuest("The Missing Husband", 200);
				p.addStory("Who accepted a bribe to keep their mouth shut");
				p.addXP(200);
				p.addGold(60);
				return "In the crowd, a man goes \"Oi Jim! There's someone asking"
						+ "\nfor you!\" to which a man curses quietly to himself then"
						+ "\nyells \"Don't tell my wife I'm here! Just take this gold"
						+ "\nand piss aff!\"";
			}
			return "They ignore you.";
		}
		if (c.contains("bartender")) {
			if (c.endsWith("about job") && v.talkedToJeromah) {
				if (!v.talkedAboutJob) {
					v.talkedAboutJob = true;
					p.getQuest().removeQuest("Jeromah of Tektik", 250);
					p.addStory("Who tried to get information and ended up having \n to trek across the land to find a more \n useful source");
					p.addXP(250);
					p.getQuest().addQuest("The Peculiar Mr Jones");
					return "\"A man of business are we? Alright, alright. So"
							+ "\n Kurtash is causing chaos, and funnily enough"
							+ "\nbusiness here is booming! How so? People are too"
							+ "\nafraid to go places outside the town since critters"
							+ "\nand monsters are causing havok in the forests. I\"ve"
							+ "\nhad poor Ms Hygard living upstairs for a week "
							+ "\nbecause some bloody rats got in and started chewing"
							+ "\nup the place! Anyhow, theres a man you need to talk"
							+ "\nto, his name is Mr Jones. A peculiar fellow, hes got"
							+ "\nthe information you need. Hes holed up in Slash City.\"";
				} else {
					return "Go talk to Mr Jones. Hes in Slash City. If you want"
							+ "\nsome action, you should see to clearing out the rats.";
				}
			} else if (c.endsWith("about rats") && v.talkedToJeromah) {
				if (!v.talkedAboutRats) {
					v.talkedAboutRats = true;
					if (!v.talkedAboutJob) {
						System.out
								.println("\"You must be psychic! How\"d you know?\"");
						System.out.println("You: \"Previous life.\"");
					}
					p.getQuest().addQuest("Rat Terror");
					p.addKey("Hygard Residence");
					return "\"Ms Hygard lives west of Glorious Forest. Here\"s the key.\"";
				} else {
					return "\"Ms Hygard lives west of Glorious Forest.\"";
				}
			} else if (c.endsWith("bartender")) {
				v.talkedToJeromah = true;
				return "The bartender looks at you and smiles. \"You must be\n"
						+ p.getName()
						+ " right? Word passed that you\"d be coming round \"ere."
						+ "\nSo, what can I do you for?";
			} else {
				return "\"I\"m sorry, I don\"t know anything about that.";
			}
		} else {
			return "Talk to who?";
		}
	}

	public String north() {
		return "I dont think you would want the bartender thinking you\"re"
				+ "\ndrunk again.";
	}

	public String east(Player p) {
		p.moveRoom(1);
		return "You leave the bar, returning to the road.";
	}

	public String south() {
		return "I dont think you would want the bartender thinking you\"re"
				+ "\ndrunk again.";
	}

	public String west() {
		return "I dont think you would want the bartender thinking you\"re"
				+ "\ndrunk again.";
	}

	public String use() {
		return "Nothing in here to use.";
	}

	public String take() {
		return "Take what? Bartender won\"t be happy if you steal shit!";
	}

	public String examine(String command) {
		if (command.contains("bartender")) {
			return "The bartender is rubbing glasses with a towel. He has"
					+ "a nametag that says \"HI! I\"M Jeromah.\"";
		} else if (command.contains("people")) {
			return "All these people look shaken up. Probably the news.";
		} else if (command.equals("examine")) {
			return "You are standing in the bar and it seems to be pretty full."
					+ "\nThere are lots of people sitting at tables enjoying pints."
					+ "\nStanding at the bar is the bartender.";
		} else {
			return "Sorry, examine what?";
		}
	}
}
