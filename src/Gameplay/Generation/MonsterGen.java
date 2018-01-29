package Gameplay.Generation;

import java.util.Random;

import Characters.Monster;
import Characters.Player;
import Gameplay.Character.InventoryItem;

public class MonsterGen {

	public static Monster generate(int area, int plvl) {
		if (area == 1) {
			return genForest(area, plvl);
		} else {
			return genDungeon(area, plvl);
		}
	}

	public static Monster genForest(int area, int plvl) {
		LootGen lgen = new LootGen();
		InventoryItem loot = new InventoryItem("Nothing");
		
		boolean here = false;
		Random random = new Random();
		String[] setName = { "Dog", "Sentient Twig", "Rat",
				"Horde of Angry Bees" };
		String[] att = { "The dog bites you, injuring you for ",
				"The twig smacks you, injuring you for ",
				"The rat scratches you, injuring you for " };
		String[] fail = { "The dog misses and bites a tree.",
				"The twig fidgets on the ground.",
				"The rat slips and scratches the ground in front of you." };
		int level = random.nextInt(3)+1;
		if(plvl < level){
			level = plvl;
		}
		int x = random.nextInt(3);
		int hp = random.nextInt(level * 5) + 4;
		int hc = random.nextInt(30) + 25;
		int mina = random.nextInt(level) + 1;
		int maxa = random.nextInt(2) + mina;
		int chance = random.nextInt(100);
		if (chance <= 35) {
			here = true;
		}
		
		try {
			loot = lgen.generate(area, level * 2, 1, 1, 33, 5, 60);
		} catch (Exception e) {
			System.out.println("failed to generate loot for monster. err ftp");
			e.printStackTrace();
		}
		
		Monster monster = new Monster(setName[x], att[x], fail[x], hp, hc,
				mina, maxa, here, loot);
		return monster;
	}

	public static Monster genDungeon(int area, int plvl) {
		LootGen lgen = new LootGen();
		InventoryItem loot = new InventoryItem("Nothing");
		
		boolean here = false;
		Random random = new Random();
		String[] setName = { "Hound", "Imp", "Thief", "Skeleton", "Rogue",
				"Blob of Goo" };
		String[] att = { "The hound bites you, injuring you for ",
				"The imp jumps on your face, injuring you for ",
				"The thief strikes you, damaging you for ",
				"The skeleton knocks you around for ",
				"The rogue slashes you, injuring you for ",
				"The blob of goo squeezes you for " };
		String[] fail = { "The hound trips and hits the floor.",
				"The imp pounces at you and fails.",				
				"The thief swings and misses.",
				"The skeleton fumbles its swing.",
				"The rogue trips and hits a wall.",
				"The blob of goo flops around you." };
		int level = random.nextInt(10)+5;
		if(plvl < 5){
			level = 5;
		} else if(plvl < level){
			level = plvl;
		}
		int x = random.nextInt(5);
		int hp = random.nextInt(level * 7) + 8;
		int hc = random.nextInt(50) + 25;
		int mina = random.nextInt(level) + 4;
		int maxa = random.nextInt(6) + mina;
		int chance = random.nextInt(100);
		if (chance <= 45) {
			here = true;
		}
		
		try {
			loot = lgen.generate(area, level * 2, 1, 1, 33, 5, 60);
		} catch (Exception e) {
			System.out.println("failed to generate loot for monster. err ftp");
			e.printStackTrace();
		}
		
		Monster monster = new Monster(setName[x], att[x], fail[x], hp, hc,
				mina, maxa, here, loot);
		return monster;
	}

	public static Monster boss(Player p) {
		LootGen lgen = new LootGen();
		InventoryItem loot = new InventoryItem("Nothing");
		if (p.getArea() == 1) {
			try {
				loot = lgen.generate(1, 15, 30, 30, 0, 0, 40);
			} catch (Exception e) {
				System.out
						.println("failed to generate loot for monster. err ftp");
				e.printStackTrace();
			}
			String att = "The giant rat thumps you with its tail, hurting you for ";
			String fail = "The giant rat swings its tail and misses you, hitting the wall instead.";
			Monster boss = new Monster("Giant Rat", att, fail, 60, 60, 3, 8,
					true, loot);
			return boss;
		} else {
			try {
				loot = lgen.generate(1, 50, 40, 40, 0, 0, 20);
			} catch (Exception e) {
				System.out
						.println("failed to generate loot for monster. err ftp");
				e.printStackTrace();
			}
			String att = "The fire demon blasts you with fire, hurting you for ";
			String fail = "You niftily dodge the fireball of death.";
			Monster boss = new Monster("Fire Demon", att, fail, 200, 75, 10, 20,
					true, loot);
			return boss;
		}
	}
}
