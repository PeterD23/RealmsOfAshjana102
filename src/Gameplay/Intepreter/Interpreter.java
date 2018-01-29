package Gameplay.Intepreter;

import java.io.File;
import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;

import Area.CastleGlorious.Castle;
import Area.DaringDungeon.Dungeon;
import Area.GloriousForest.Forest;
import Area.Tektik.Tektik;
import Characters.Monster;
import Characters.Player;
import Gameplay.SoundTrigger;
import Gameplay.Generation.MonsterGen;
import Gameplay.Generation.ResponseGen;
import IO.Load;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Interpreter {

	/**
	 * The game menu. Three options available through user input.
	 * 
	 * @param text
	 *            The interpreter. Required to allow access to room and area
	 *            variables.
	 * @throws InterruptedException
	 */
	public void menu() throws InterruptedException {
		AnsiConsole.systemInstall(); // START COLOUR
		@SuppressWarnings("unused")
		SoundTrigger demo = new SoundTrigger("res\\welcome.wav");
		ResponseGen gen = new ResponseGen();
		System.out.println(ansi().render("@|bold Welcome to Realms of Ashjana!|@"));
		AnsiConsole.systemUninstall(); // STOP COLOUR
		Thread.sleep(3000);
		Scanner scan = new Scanner(System.in);
		System.out.print("Type either new, intro, load or exit.\n> ");
		while (true) {
			String response = scan.nextLine().toLowerCase();
			if (response.equals("new")) {
				newgame();
			} else if (response.equals("intro")) {
				intro();
			} else if (response.equals("load")) {
				loadgame();
			} else if (response.equals("help")){
				System.out.print("Type either new, intro, load or exit.\n> ");
			} else if (response.equals("exit")) {;
				System.exit(0);
			} else {
				System.out.print(gen.gameResponse(false) + " \n> ");
			}
		}

	}

	public void intro() throws InterruptedException {
		@SuppressWarnings("unused")
		SoundTrigger demo = new SoundTrigger("res\\intro.wav");
		System.out.println("You were summoned here from a far away land,");
		Thread.sleep(4000);
		System.out.println("and uh, you kinda got a bit drunk, and,");
		Thread.sleep(4000);
		System.out.print("collapsed on the kings guest room");
		Thread.sleep(3000);
		System.out.println(".. floor. ");
		Thread.sleep(1000);
		System.out.println("Um... yeah. So um, hmm...");
		Thread.sleep(7000);
		System.out.print(">");
	}

	public void newgame() throws InterruptedException {
		Castle.init(false, null);
		Forest.init(false, null);
		Dungeon.init(false, null);
		Tektik.init(false, null);
		
		Scanner scan = new Scanner(System.in);
		System.out
				.print("Thou hath becomst a new player! What be thy name?\n> ");
		Player player = new Player(scan.nextLine(), 20, 1, 10, 35);
		System.out.println("Really? You are going with that?");
		Thread.sleep(1000);
		System.out.println("...");
		Thread.sleep(2000);
		System.out
				.println("Anyway, your adventure shall begin, in the realm of Ashjana! (duh)");
		Thread.sleep(1000);
		System.out
				.println("You will wake up in a bed right about now, pretty hungover. Type help for commands.");
		interpret(player);
	}

	public void loadgame() throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		System.out
				.print("Thou must provide a name for thy regeneration process!\n> ");
		String fileName = scan.nextLine().toLowerCase();
		Player player = Load.loadFromFile(fileName);
		if (!player.getName().equals("null")) {
			interpret(player);
		}

	}

	/**
	 * This is the main interpreter method. This globally takes your command and
	 * divides it into sections.
	 * 
	 * @param player
	 *            The player character. Can be manipulated by objects in the
	 *            game.
	 * @param text
	 *            The interpreter. Required to allow access to room and area
	 *            variables.
	 * @throws InterruptedException
	 */
	public void interpret(Player player) throws InterruptedException {
		Monster monster = new Monster("none", "success!", "fail", 0, 0, 0, 0, false, null);
		SendResponse res = new SendResponse();
		Scanner scan = new Scanner(System.in);
		String command = "";

		while (!command.equals("exit")) {
			if (player.getHealth() <= 0) {
				player.die();
			}
			// Check after each command if player has leveled up
			if (player.getExperience() >= player.getExperienceToLevel()) {
				player.levelUp();
			}

			if (player.isBoss() && !player.getCleared() && !monster.doesExist()) {
				monster = MonsterGen.boss(player);
				System.out.println("HOLY SHIT! A " + monster.getName() +" APPEARED!");
			} else if (player.getArea() < 100 && !player.getCleared()
					&& !monster.doesExist()) {
				monster = MonsterGen.generate(player.getArea(),
						player.getLevel());
				if (monster.doesExist()) {
					AnsiConsole.systemInstall();
					System.out.println(ansi().render("@|red OH NO! A wild " + monster.getName()
							+ " has appeared!|@"));
					AnsiConsole.systemUninstall();
				} else {
					player.setCleared(true);
				}
			}

			// DEBUG with this
			// System.out.println("Area: "+player.getArea()+", Room: "+player.getRoom());
			// System.out.println(monster);

			// Three lines of joy. These three control the entire interpretation
			// of the game through method calls.
			System.out.print("> ");
			command = scan.nextLine().toLowerCase();
			res.check(command, player, monster);

		}
	}
}
