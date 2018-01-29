package Characters;

import java.util.Random;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.*;
import Gameplay.SoundTrigger;
import Gameplay.Character.InventoryItem;

public class Monster {

	String name, attout, failout;
	int hitPoints, hitChance, minAttack, maxAttack, startHP;
	boolean exists;
	InventoryItem loot;

	public Monster(String name, String attout, String failout, int hitPoints, int hitChance, int minAttack,
			int maxAttack, boolean exists, InventoryItem loot) {
		this.loot = loot;
		this.name = name;
		this.attout = attout;
		this.failout = failout;
		this.startHP = hitPoints;
		this.hitPoints = hitPoints;
		this.hitChance = hitChance;
		this.minAttack = minAttack;
		this.maxAttack = maxAttack;
		this.exists = exists;
	}

	public Ansi attack(Player p, SoundTrigger bossLoop) {
		int damage;
		Random random = new Random();
		int chance = random.nextInt(100);
		if (chance <= getHitChance()) {
			int range = maxAttack - minAttack;
			if (range > 0) {
				damage = random.nextInt(range) + minAttack;
			} else {
				damage = minAttack;
			}
			p.setHealth(p.getHealth() - damage);
			if(p.isBoss() && p.getHealth() <= 0){
				bossLoop.stop();
			}

			// Roll for damage, return negatives
			String extra = "";
			InventoryItem[] equipped = p.getInv().getEquippedItem();
			int roll = random.nextInt(14) + 1;

			// Weaken armor and destroy if durability is critical
			if (roll <= 6 && !equipped[1].getName().equals("Nothing")) {
				equipped[1].weaken();
				if (equipped[1].getDurability() == 0) {
					extra = "Your chestplate was destroyed!";
					equipped[1].destroy();
				} else {
					extra = "Your chestplate was damaged!";
				}
				damage = damage - (equipped[1].getDefense());
			} else if (roll <= 10 && !equipped[2].getName().equals("Nothing")) {
				equipped[2].weaken();
				if (equipped[2].getDurability() == 0) {
					extra = "Your leggings were destroyed!";
					equipped[2].destroy();
				} else {
					extra = "Your leggings were damaged!";
				}
				damage = damage - (equipped[1].getDefense());
			} else if (roll <= 12 && !equipped[3].getName().equals("Nothing")) {
				equipped[3].weaken();
				if (equipped[3].getDurability() == 0) {
					extra = "Your boots were destroyed!";
					equipped[3].destroy();
				} else {
					extra = "Your boots were damaged!";
				}
				damage = damage - (equipped[1].getDefense());
			} else if (roll <= 14 && !equipped[0].getName().equals("Nothing")) {
				equipped[0].weaken();
				if (equipped[0].getDurability() == 0) {
					extra = "Your helmet was destroyed!";
					equipped[0].destroy();
				} else {
					extra = "Your helmet was damaged!";
				}
				damage = damage - (equipped[1].getDefense());
			} else {
				extra = "";
			}

			if (damage < 0) {
				damage = 0;
			}
			return ansi().render("@|bold,yellow "+ attout + damage + " hitpoints. " + extra + " |@");
		} else {
			return ansi().render("@|bold,green " + failout + " |@");
		}
	}

	public void makeExtinct() {
		this.exists = false;
	}

	public boolean doesExist() {
		return exists;
	}

	private int getHitChance() {
		return hitChance;
	}

	public void reduceHealth(int damage) {
		hitPoints = hitPoints - damage;

	}

	public int getHealth() {
		return hitPoints;
	}

	public int getStartHP() {
		return startHP;
	}

	public String getName() {
		return name;
	}

	public int getMaxDamage() {
		return maxAttack;
	}

	public String toString() {
		return "HP: " + startHP + ", HC: " + hitChance + ", MINA: " + minAttack
				+ ", MAXA: " + maxAttack;
	}

	public InventoryItem getLoot() {
		return loot;
	}

}
