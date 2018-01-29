package Gameplay.Character;

import java.io.Serializable;

public class Questlog implements Serializable {

	String[] quest;

	public Questlog() {
		this.quest = new String[100];
		for (int i = 0; i < quest.length; i++) {
			quest[i] = "Available";
		}
	}

	public void viewQuests() {
		System.out.println("Quest Log:");
		for (int i = 0; i < quest.length; i++) {
			if (quest[i].equals("Available")) {
				break;
			} else {
				System.out.println(quest[i]);
			}
		}
	}

	public void addQuest(String string) {
		for (int i = 0; i < quest.length; i++) {
			if (quest[i].equals("Available")) {
				quest[i] = string;
				break;
			}
		}

	}

	public void removeQuest(String string, int xp) {
		for (int i = 0; i < quest.length; i++) {
			if (quest[i].equals(string)) {
				quest[i] = "Available";
				System.out.println("You have completed a quest! You earn " + xp
						+ " XP!");
				for (int x = i; x < quest.length; x++) {
					quest[x + 1] = quest[x];
					if (quest[x].equals("Available")) {
						break;
					}
				}
			}
		}
	}

	public String detailQuest(String command) {
		String query = "";
		for (int i = 0; i < quest.length; i++) {
			if (command.contains(quest[i].toLowerCase())) {
				query = quest[i];
				break;
			}
		}
		if (query.equals("The Hangover")) {
			return "You woke up in a bed, reeking of alcohol"
					+ "\nand vomit stains on the pillow. Now, you"
					+ "\nmust find out how to leave this room and"
					+ "\ntalk to the king about your upcoming quest.";
		} else if (query.equals("Jeromah of Tektik")) {
			return "The king instructed you to seek out Jeromah,"
					+ "\na retired warrior now working as a bartender"
					+ "\nin Tektik, a town not far from here. He should"
					+ "\nhave information as to how to stop Kurtash from"
					+ "\ncausing chaos to the land.";
		} else if (query.equals("Rat Terror")) {
			return "Jeromah gave you a key to the Hygard residence,"
					+ "\nto the west of the Glorious Forest. It is apparently"
					+ "\ninfested with rats, but something seems off...";
		} else if (query.equals("The Peculiar Mr Jones")) {
			return "Jeromah spoke of a man named Mr Jones. He apparently"
					+ "\nholds some very important information about Kurtash that"
					+ "\nI will need to know if I want to lock him back into his"
					+ "\nprison. He lives in Slash City.";
		} else if (query.equals("The Missing Husband")) {
			return "The woman in the village is distraught about her husband"
					+ "\ngoing missing, so its up to you to find him! He was"
					+ "\nlast seen in Tektik, and his name is Jim.";
		} else {

			return "That quest does not exist.";
		}

	}
}
