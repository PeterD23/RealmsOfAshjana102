package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Area.CastleGlorious.Castle;
import Area.CastleGlorious.CastleVar;
import Area.DaringDungeon.Dungeon;
import Area.DaringDungeon.DungeonVar;
import Area.GloriousForest.Forest;
import Area.GloriousForest.ForestVar;
import Area.Tektik.Tektik;
import Area.Tektik.TektikVar;
import Characters.Player;

public class Load {

	public static Player loadFromFile(String fileName) {
		File p = new File("save\\" + fileName + ".player");
		if (p.exists()) {
			try {
				Player player = readPlayer(p);
				readVar(fileName);
				return player;
			} catch (IOException e) {
				System.out.println("Sorry, cannot load. Error ftp");
			}
			System.out.println("Your character has regenerated!");

		} else {
			System.out
					.print("Thy character does not exist! Perhaps type \"new\"? \n > ");
		}
		Player player = new Player("null", 0, 0, 0, 0);
		return player;
	}

	public static Player readPlayer(File p) {
		Player player;

		try {
			FileInputStream fin = new FileInputStream(p);
			ObjectInputStream ois = new ObjectInputStream(fin);
			player = (Player) ois.readObject();
			ois.close();
			System.out.println("Your character has regenerated!");
			return player;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// FOR MULTIPLAYER PURPOSES
	public static Player readPlayerMP(File p) {
		Player player;

		try {
			FileInputStream fin = new FileInputStream(p);
			ObjectInputStream ois = new ObjectInputStream(fin);
			player = (Player) ois.readObject();
			ois.close();
			System.out.println("Your character has generated!");
			return player;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	private static void readVar(String fileName) throws IOException {
		try {
			FileInputStream fin = new FileInputStream("save\\" + fileName
					+ ".castle");
			ObjectInputStream ois = new ObjectInputStream(fin);
			CastleVar v = (CastleVar) ois.readObject();
			ois.close();

			ois = new ObjectInputStream(new FileInputStream("save\\" + fileName
					+ ".forest"));
			ForestVar v2 = (ForestVar) ois.readObject();
			ois.close();

			ois = new ObjectInputStream(new FileInputStream("save\\" + fileName
					+ ".tektik"));
			TektikVar v3 = (TektikVar) ois.readObject();
			ois.close();

			ois = new ObjectInputStream(new FileInputStream("save\\" + fileName
					+ ".dungeon"));
			DungeonVar v4 = (DungeonVar) ois.readObject();
			ois.close();

			Castle.init(true, v);
			Forest.init(true, v2);
			Tektik.init(true, v3);
			Dungeon.init(true, v4);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
