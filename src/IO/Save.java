package IO;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Area.CastleGlorious.Castle;
import Area.CastleGlorious.CastleVar;
import Area.DaringDungeon.Dungeon;
import Area.DaringDungeon.DungeonVar;
import Area.GloriousForest.Forest;
import Area.GloriousForest.ForestVar;
import Area.Tektik.Tektik;
import Area.Tektik.TektikVar;
import Characters.Player;

public class Save {
	public static void saveToFile(Player p) throws IOException {
		writePlayer(p);
		writeVar(p);
	}

	private static void writeVar(Player p) {
		CastleVar v = Castle.getVar();
		ForestVar v2 = Forest.getVar();
		TektikVar v3 = Tektik.getVar();
		DungeonVar v4 = Dungeon.getVar();
		try { 
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save\\" + p.getName()
				+ ".castle"));
		oos.writeObject(v);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream("save\\" + p.getName()
				+ ".forest"));
		oos.writeObject(v2);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream("save\\" + p.getName()
				+ ".tektik"));
		oos.writeObject(v3);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream("save\\" + p.getName()
				+ ".dungeon"));
		oos.writeObject(v4);
		oos.close();
		
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}

	public static void writePlayer(Player p) {
		try {

			FileOutputStream fout = new FileOutputStream("save\\" + p.getName()
					+ ".player");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(p);
			oos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
