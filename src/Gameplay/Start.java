package Gameplay;

import java.io.IOException;

import Area.CastleGlorious.Castle;
import Area.DaringDungeon.Dungeon;
import Area.GloriousForest.Forest;
import Area.Tektik.Tektik;
import Gameplay.Intepreter.Interpreter;
import Multiplayer.Lobby;

/**
 * The start class for the game. Calls the main method which sets up the
 * objects.
 * 
 * @author Peter
 * 
 */

public class Start {

	/**
	 * Initialises the game by creating the rooms, and creates the interpreter.
	 * Messy shit, but it works.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {
		if(args == null){
			Interpreter text = new Interpreter();
			text.menu();
		} else {
			if(args.length > 0){
				if (args[0].contains("-multi")) {
					Lobby multi = new Lobby();
					String ipAddress = args[1];
					int port = Integer.parseInt(args[2]);
					multi.go(ipAddress, port);
				}
				} else {
					Interpreter text = new Interpreter();
					text.menu();
				}	
		}
		
	}
}
