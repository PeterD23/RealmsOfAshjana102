package Area.Tektik;

import java.util.Random;

import org.fusesource.jansi.AnsiConsole;

import Characters.Monster;
import Characters.Player;
import Gameplay.Generation.ResponseGen;

// THIS IS A FUCKING TEMPLATE, DO WHAT YOU FUCKING WILL WITH IT, OKAY? YEAH, FUCKIN DO THAT

public class Shop3 {

	// Replace X with Room number

	TektikVar v;
	Monster m;

	public void check(TektikVar v, String c, Player p) {
		ResponseGen res = new ResponseGen();
		if (c.contains("examine") || c.contains("look") || c.startsWith("l")) {
			System.out.println(examine(c,v));
		} else if (c.contains("use")) {
			System.out.println(use(c,v));
		} else if (c.contains("take") || c.contains("get") || c.startsWith("g")) {
			System.out.println(take(p,c,v));
		} else if (c.contains("talk to")) {
			AnsiConsole.systemInstall();
			System.out.println(talkTo());
			AnsiConsole.systemUninstall();
		} else if (c.equals("north") || c.equals("n")) {
			System.out.println(north(p,v));
		} else if (c.equals("east") || c.equals("e")) {
			System.out.println(east(p,v));
		} else if (c.equals("west") || c.equals("w")) {
			System.out.println(west());
		} else if (c.equals("south") || c.equals("s")) {
			System.out.println(south(p,v));
		} else {
			System.out.println(res.gameResponse(true));
		}
	}

	public String talkTo() {
		return "There's no-one to talk to.";
	}

	public String north(Player p, TektikVar v) {
		return "";
	}

	public String east(Player p, TektikVar v) {
		return "";
	}

	public String south(Player p, TektikVar v) {
		return "";
	}

	public String west() {
		return "";
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
	public String use(String c, TektikVar v) {
		return "Use what? Make some sense please!";
	}

	public String take(Player p, String c, TektikVar v) {

		return "Take what? Make some sense please.";

	}

	public String examine(String c, TektikVar v) {
		if (c.equals("examine") || c.equals("look") || c.equals("l")) {
			return "";
		} else {
			return "Examine what? Make some sense please!";
		}
	}

}