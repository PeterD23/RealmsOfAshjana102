package Gameplay.Generation;

import java.io.IOException;
import java.util.Random;

import Gameplay.Character.InventoryItem;
import Objects.Weapon;
import Objects.Armor.ArmorBoots;
import Objects.Armor.ArmorChest;
import Objects.Armor.ArmorHead;
import Objects.Armor.ArmorLegs;

public class LootGen {

	// THIS WILL GO WHERE THE MONSTER DIES
	public InventoryItem generate(int area, int level, int arm, int wep, int ing,
			int ite, int gld) throws IOException {
		int type = castType(arm, wep, ing, ite, gld);
		return typeGen(area, type, level);
	}

	// THIS WILL ^^

	public int castType(int w, int a, int in, int it, int g) {
		int type;
		w = w + a;
		in = in + w;
		it = it + in;
		g = g + it;
		// w is Weapon
		// a is Armor
		// in is Ingredient
		// it is Item
		// g is Gold
		Random random = new Random();
		int cast = random.nextInt(99) + 1;
		if (cast <= w) {
			type = 1;
		} else if (cast <= a) {
			type = 2;
		} else if (cast <= in) {
			type = 3;
		} else if (cast <= it) {
			type = 4;
		} else {
			type = 5;
		}
		return type;
	}

	public InventoryItem typeGen(int area, int type, int level) throws IOException {
		if (type == 1) {
			InventoryItem armor = armorGen(level);
			return armor;
		} else if (type == 2) {
			InventoryItem weapon = weaponGen(level);
			return weapon;
		} else if (type == 3) {
			InventoryItem ingred = ingGen(area, level);
			return ingred;
		} else if (type == 4) {
			// InventoryItem item = itemGen(level);
			InventoryItem gold = goldGen(level);
			return gold;
		} else {
			InventoryItem gold = goldGen(level);
			return gold;
		}
	}

	public InventoryItem armorGen(int level) throws IOException {
		Random random = new Random();
		int type = random.nextInt(3);
		if (type == 0) {
			int[] attrib = armorAttribGen(3, level);
			String name = armorNameGen(attrib, type);

			ArmorHead head = new ArmorHead(attrib, name);
			InventoryItem invHead = new InventoryItem(head);
			return invHead;

		} else if (type == 1) {
			int[] attrib = armorAttribGen(1, level);
			String name = armorNameGen(attrib, type);

			ArmorChest chest = new ArmorChest(attrib, name);
			InventoryItem invHead = new InventoryItem(chest);
			return invHead;

		} else if (type == 2) {
			int[] attrib = armorAttribGen(2, level);
			String name = armorNameGen(attrib, type);
			ArmorLegs legs = new ArmorLegs(attrib, name);

			// THIS LINE OF CODE I COMPLETELY FORGOT ABOUT
			InventoryItem invLegs = new InventoryItem(legs);
			// NO WONDER I COULDNT SEE ANY LEG ARMOR hurr crash

			return invLegs;

		} else {
			int[] attrib = armorAttribGen(3, level);
			String name = armorNameGen(attrib, type);

			ArmorBoots boots = new ArmorBoots(attrib, name);
			InventoryItem invBoots = new InventoryItem(boots);
			return invBoots;
		}

	}

	public InventoryItem weaponGen(int level) {
		int[] attrib = { 1000, 1000, 100000 };
		Weapon newWeap = new Weapon("Sword of Awesome", attrib);
		InventoryItem weapon = new InventoryItem(newWeap);
		return weapon;
	}

	public void itemGen() {
		System.out.println("Generating an item!");
	}

	public InventoryItem ingGen(int area, int level) {
		InventoryItem ing;
		if (level == 1) {
			ing = new InventoryItem("Barsh Root", "A root of the Barsh Tree\nwith healing properties.", 1);
		} else {
			ing = new InventoryItem("Barsh Root", "A root of the Barsh Tree\nwith healing properties.", 2);
		}
		return ing;
	}

	public InventoryItem goldGen(int level) {
		Random random = new Random();
		int gold = random.nextInt(level * 5) + level;
		InventoryItem disposeGold = new InventoryItem(gold);
		return disposeGold;
	}

	public String armorNameGen(int[] attrib, int type) throws IOException {
		Random random = new Random();
		String defenseMod = "";
		String armorType = "";

		NameGen nameGenerator = new NameGen("res\\chars.txt");
		ModifierNameGen mod = new ModifierNameGen();
		if (type == 0) {
			defenseMod = mod.defenseModHelm(attrib[0]);
			if (defenseMod.equals("Cotton") || defenseMod.equals("Leather")) {
				armorType = "Hat";
			} else {
				armorType = "Helm";
			}
		} else if (type == 1) {
			defenseMod = mod.defenseModChest(attrib[0]);
			if (defenseMod.equals("Cotton")) {
				armorType = "Jumper";
			} else {
				armorType = "Chest";
			}
		} else if (type == 2) {
			defenseMod = mod.defenseModLegs(attrib[0]);
			if (defenseMod.equals("Cotton") || defenseMod.equals("Leather")) {
				armorType = "Jeans";
			} else {
				armorType = "Leggings";
			}
		} else if (type == 3) {
			defenseMod = mod.defenseModBoots(attrib[0]);
			if (defenseMod.equals("Cotton")) {
				armorType = "Shoes";
			} else {
				armorType = "Boots";
			}
		}
		String name = defenseMod + " " + armorType + " of "
				+ nameGenerator.compose(random.nextInt(3) + 1);
		/**
		 * THIS DEBUGS THE ARMOR GENERATION STATS System.out.println(name +
		 * "\n Attributes: \n " + attrib[0] + " Defense");
		 * System.out.println(attrib[1] + " Durability" );
		 * System.out.println(attrib[3] + " Value" );
		 **/

		return name;
	}

	public int[] armorAttribGen(int divisor, int level) {
		Random random = new Random();
		int[] attrib = new int[4];
		attrib[0] = (random.nextInt(level * 5) + 1) / divisor;
		attrib[1] = attrib[0] * 6;
		attrib[2] = attrib[0] * 30;
		attrib[3] = random.nextInt(200);
		// 0 is defense
		// 1 is durability
		// 2 is value
		// 3 is modifier
		return attrib;
	}

}
