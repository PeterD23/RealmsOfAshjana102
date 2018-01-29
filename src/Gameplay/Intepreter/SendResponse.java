package Gameplay.Intepreter;

import java.io.IOException;

import Area.CastleGlorious.Castle;
import Area.DaringDungeon.Dungeon;
import Area.GloriousForest.Forest;
import Area.Tektik.Tektik;
import Characters.Monster;
import Characters.Player;
import Gameplay.Character.Map;
import IO.Save;

public class SendResponse {

	public void check(String command, Player player, Monster monster) {
		if (command.equals("map")) {
			if (player.getMap().hasAnyPieces()) {
				System.out.println(Map.getLocation(player.getArea()));
			} else {
				System.out.println("You don't have one!");
			}
		} else if (command.equals("wingame")) {
			player.addGold(100000);
			player.setStrength(10000);
			player.setDexterity(100);
			player.setAlchemy(100);
			player.setConstitution(1000);
			player.setAlchemy(100);
			player.setTrading(100);
			player.setRepair(100);
			player.setHealth(player.getConstitution() * 5);
			player.setLevel(100);
			System.out.println("Cheating bastard");
		}

		else if (command.equals("story")) {
			System.out.println(player.getStory());
		}

		else if (command.equals("recipe")) {
			player.getCraft().viewRecipes();
		}

		else if (command.equals("char")) {

			player.checkChar();

		} else if (command.equals("save")) {
			if (!monster.doesExist() && !Castle.getVar().rv1[11]) {
				try {
					Save.saveToFile(player);
					System.out.println("You have saved the game!");
				} catch (IOException e) {
					System.out.println("Cannot save. Make sure thy save"
							+ "\nfolder existh within thy game data dir.");
				}
			} else {
				System.out.println("You cannot save during combat!");
			}

		} else if (command.equals("die")) {

			try {
				player.die();
			} catch (InterruptedException e) {
				System.out.println("You cannot die, wat");
			}
		} else if (command.equals("quest")) {

			player.getQuest().viewQuests();

		} else if (command.contains("quest")) {

			System.out.println(player.getQuest().detailQuest(command));

		} else if (command.equals("skill")) {

			player.checkSkills();

		} else if (command.contains("skill")) {

			System.out.println(player.addSkills(command));

		} else if (command.equals("equip")) {

			player.checkEquip();

		} else if (command.contains("equip #")) {

			player.equipItem(command);

		} else if (command.equals("inv")) {

			player.checkInv();

		} else if (command.contains("use #")) {

			player.useItem(command);
			if (monster.doesExist()) {
				System.out.println(monster.attack(player, null));
			}

		} else if (command.contains("examine #")) {

			player.examineInv(command);
		}

		else if (command.contains("help")) {

			System.out.println(help(command, player));

		} else if (command.equals("exit")) {

			System.out
					.print("Thou hast returned to the main menu! A shame really. \n> ");

		} else {
			if (player.getArea() == 100) {
				Castle.checkArea(command, player);
			} else if (player.getArea() == 101) {
				Tektik.checkArea(command, player);
			} else if (player.getArea() == 1) {
				Forest.checkArea(command, player, monster);
			} else if (player.getArea() == 2) {
				Dungeon.checkArea(command, player, monster);
			}
		}

		/**
		 * Debugger for getting room and area, test if things go tits up 
		 */
		// System.out.println("Area: " + player.getArea() + ", Room: "
		// + player.getRoom());
	}

	public String help(String command, Player player) {
		if (command.endsWith("attack")) {
			return "Attack: Attack an enemy only if present. If not, not supported.";
		} else if (command.endsWith("cast")) {
			if (player.getMagic().isMagicCapable()) {
				return "Cast: Cast a magic spell from your spellbook."
						+ "\nSyntax is \"cast #spellcode\" e.g \"cast phi\"";
			} else {
				return "Shh... this command has not been unlocked yet!";
			}
		} else if (command.endsWith("char")) {
			return "Char: Display your characters basic stats.";
		} else if (command.endsWith("east")) {
			return "East: If possible, go east";
		} else if (command.endsWith("equip")) {
			return "Equip: Equips an item from your inventory. Use"
					+ "\n syntax \"equip #<slot>\"";
		} else if (command.endsWith("examine")) {
			return "Examine/Look: Look at a specific object. "
					+ "\nUse #<slot> to see inventory items in detail."
					+ "\nNo parameters to see the area as a whole.";
		} else if (command.endsWith("exit")) {
			return "Exit: Returns to the main menu.";
		} else if (command.endsWith("inv")) {
			return "Inv: Displays your characters inventory.";
		} else if (command.endsWith("map")) {
			if (player.getMap().hasAnyPieces()) {
				return "Map: Displays your current location with the map for that area.";
			} else {
				return "You don't have a map, so why ask for help?";
			}
		} else if (command.endsWith("north")) {
			return "North: If possible, go north";
		} else if (command.endsWith("quest")) {
			return "Quest: Displays your quest log. Syntax for indepth is"
					+ "\n\"quest #questname\"";
		}else if (command.endsWith("recipe")){
			return "Recipe: Displays all your recipes.";
		} else if (command.endsWith("save")) {
			return "Save: Saves the game.";
		} else if (command.endsWith("skill")) {
			return "Skill: Displays your characters advanced stats."
					+ "\nApply skillpoints with \"skill <skill>\" where"
					+ "\n<skill> corresponds to the first three letters";
		} else if (command.endsWith("south")) {
			return "South: If possible, go south";
		} else if (command.endsWith("story")) {
			return "Story: Displays the story of the character so far!";
		} else if (command.endsWith("take")) {
			return "Take: Take an item in the immediate area.";
		} else if (command.endsWith("talk to")) {
			return "Talk to: Talk to a character. Specific subject syntax is"
					+ "\n\"talk to #npc about #subject";
		} else if (command.endsWith("use")) {
			return "Use: Activate objects."
					+ "\nUse inventory items with #<slot>";
		} else if (command.endsWith("west")) {
			return "West: If possible, go west";
		}
		return "Supported keywords: quest, story, char, skill, inv, recipe, examine/look/l, use, north/n, east/e, south/s, west/w,"
				+ "\n take/get/g, equip, attack, cast, map, talk to, save, exit.";
	}
}
