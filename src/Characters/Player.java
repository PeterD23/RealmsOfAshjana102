package Characters;

import java.io.Serializable;
import static org.fusesource.jansi.Ansi.*;
import java.util.Random;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import Gameplay.SoundTrigger;
import Gameplay.Start;
import Gameplay.Character.Crafting;
import Gameplay.Character.Inventory;
import Gameplay.Character.Magic;
import Gameplay.Character.Map;
import Gameplay.Character.Questlog;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String[] keys;
	private String story;
	private Questlog quest;
	// TO DO
	// private BossLog bossLog;

	private int maxHealth;
	private int health;
	private int gold;
	private int level;
	private int experience;
	private int experienceToLevel;
	private int skillpoints;

	// STRENGTH: Governs your base and max attack and ability to use certain
	// items.
	//
	private int strength;
	// DEXTERITY: Governs your base chance to hit and chance to dodge.
	private int dexterity;
	// CONSTITUTION: Governs your maximum health.
	private int constitution;
	// WISDOM: Governs your maximum mana.
	private int wisdom;
	// ALCHEMY: Gives a boost to crafted potions.
	private int alchemy;
	// TRADING: Gives a boost to buy/sell.
	private int trading;
	// TINKERING: Gives a boost to repairing items.
	private int repair;

	private Inventory inv;
	private Magic magic;
	private Map map;
	private Crafting craft;

	private int area;
	private int room;
	private boolean cleared;
	private boolean boss;

	// Starting a new game;
	public Player(String name, int health, int level, int strength,
			int dexterity) {
		setCraft(new Crafting());
		setInv(new Inventory());
		setQuest(new Questlog());
		setMagic(new Magic(false, 0));
		setMap(new Map(false));
		setKeys(new String[100]);
		getQuest().addQuest("The Hangover");
		this.name = name;
		this.story = "Lo, it is you, " + name + " who woke up hungover.";
		this.gold = 0;
		this.area = 100;
		this.room = 1;
		this.health = health;
		setConstitution(5);
		this.level = level;
		this.strength = strength;
		this.dexterity = dexterity;
		this.alchemy = 0;
		this.trading = 0;
		this.repair = 0;
		this.experienceToLevel = (int) (100 * (Math.pow(level, 2)));
	}

	public Crafting getCraft() {
		return craft;
	}

	public void setCraft(Crafting crafting) {
		this.craft = crafting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStory() {
		return story;
	}

	public void addStory(String story) {
		this.story = this.story + story;
	}

	public Questlog getQuest() {
		return quest;
	}

	public void setQuest(Questlog quest) {
		this.quest = quest;
	}

	public boolean getCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}

	public int getArea() {
		return area;
	}

	public int getRoom() {
		return room;
	}

	public void moveRoom(int value) {
		room = room + value;
	}

	public void moveArea(int value) {
		area = area + value;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getGold() {
		return gold;
	}

	public void addGold(int value) {
		gold = gold + value;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		this.experienceToLevel = (int) (100 * (Math.pow(level, 2)));
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
		maxHealth = constitution * 5;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getBaseAttack() {
		return strength / 5;
	}

	public String addSkills(String command) {
		if (command.contains("str") && skillpoints > 0) {
			setStrength(strength + 2);
			skillpoints--;
			return "You applied 2 points to strength!";
		} else if (command.contains("dex") && skillpoints > 0) {
			setDexterity(dexterity + 2);
			skillpoints--;
			return "You applied 2 points to dexterity!";
		} else if (command.contains("con") && skillpoints > 0) {
			setConstitution(constitution + 1);
			skillpoints--;
			return "You applied a point to constitution!";
		} else if (command.contains("wis") && skillpoints > 0) {
			wisdom++;
			skillpoints--;
			return "You applied 2 points to wisdom!";
		} else if (command.contains("alc") && skillpoints > 0) {
			setAlchemy(alchemy + 1);
			skillpoints--;
			return "You applied a point to alchemy!";
		} else if (command.contains("tra") && skillpoints > 0) {
			setTrading(trading + 1);
			skillpoints--;
			return "You applied a point to trading!";
		} else if (command.contains("rep") && skillpoints > 0) {
			setRepair(repair + 1);
			skillpoints--;
			return "You applied a point to trading!";
		} else {
			return "You cannot train that.";
		}
	}

	public void checkSkills() {
		System.out.println("Lo, here are your stats!");
		System.out.println("+2 Strength: " + strength);
		System.out.println("+2 Dexterity: " + dexterity);
		System.out.println("+1 Constitution: " + constitution);
		System.out.println("+1 Wisdom: " + wisdom);
		System.out.println("+1 Alchemy: " + alchemy+"%");
		System.out.println("+1 Trading: " + trading);
		System.out.println("+1 Repair: " + repair);
		if (skillpoints > 0) {
			System.out.println("You have " + skillpoints
					+ " skill points to spend.");
		}
	}

	public void checkChar() {
		System.out.println("Lo, it is you! " + name + ", currently at level "
				+ level);
		System.out.println("Thou hath " + health + "/" + maxHealth
				+ " points of health!");
		System.out.println("Thou hath " + experience + "/" + experienceToLevel
				+ " points of experience!");
		System.out.println("Thou hath " + gold + " gold pieces!");

	}

	public void checkInv() {
		System.out.println("Lo, you are carrying the following:");
		for (int i = 1; i < inv.getLimit() + 1; i++) {
			String quantity = "";
			if (inv.getInvItem()[i - 1].getName().equals("Nothing")) {
				System.out.println("#" + i + ": " + "Nothing.");
			} else {
				if (inv.getInvItem()[i - 1].getType() == 6) {
					quantity = Integer.toString(inv.getInvItem()[i - 1]
							.getQuantity()) + " ";
				}
				System.out.println("#" + i + ": " + quantity
						+ inv.getInvItem()[i - 1].getName());

			}
		}
	}

	public void checkEquip() {
		System.out.println("Lo, you are wielding the following:");
		System.out.println("Helmet: " + inv.getEquippedItem()[0].getName()
				+ ". Def: " + inv.getEquippedItem()[0].getDefense() + " Dur: "
				+ inv.getEquippedItem()[0].getDurability());
		System.out.println("Chestplate: " + inv.getEquippedItem()[1].getName()
				+ ". Def: " + inv.getEquippedItem()[1].getDefense() + " Dur: "
				+ inv.getEquippedItem()[1].getDurability());
		System.out.println("Leggings: " + inv.getEquippedItem()[2].getName()
				+ ". Def: " + inv.getEquippedItem()[2].getDefense() + " Dur: "
				+ inv.getEquippedItem()[2].getDurability());
		System.out.println("Boots: " + inv.getEquippedItem()[3].getName()
				+ ". Def: " + inv.getEquippedItem()[3].getDefense() + " Dur: "
				+ inv.getEquippedItem()[3].getDurability());
		System.out.println("-----");
		System.out.println("Weapon: " + inv.getEquippedItem()[4].getName()
				+ ". Att: " + inv.getEquippedItem()[4].getAttack() + " Dur: "
				+ inv.getEquippedItem()[4].getDurability());
		System.out.println("-----");
		System.out.println("Total Attack: " + strength / 5 + " + "
				+ inv.getEquippedItem()[4].getAttack());

	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public void addKey(String string) {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].equals("null")) {
				keys[i] = string;
			}
		}

	}

	public boolean hasKey(String string) {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].equals(string)) {
				return true;
			}
		}
		return false;
	}

	private void setKeys(String[] strings) {
		this.keys = strings;
		for (int i = 0; i < keys.length; i++) {
			keys[i] = "null";
		}

	}

	public int getExperienceToLevel() {
		return experienceToLevel;
	}

	public int getExperience() {
		return experience;
	}

	public void addXP(int value) {
		this.experience = this.experience + value;
	}

	public void levelUp() {
		setLevel(level + 1);
		System.out.println("Thou hast leveled up to level " + level + "!");
		setConstitution(constitution + 1);
		skillpoints = skillpoints + 3;
		setHealth(maxHealth);
	}

	public Inventory getInv() {
		return inv;
	}

	public void setInv(Inventory inv) {
		this.inv = inv;
	}

	public Magic getMagic() {
		return magic;
	}

	private void setMagic(Magic magic) {
		this.magic = magic;
	}

	public Map getMap() {
		return map;
	}

	private void setMap(Map map) {
		this.map = map;
	}

	public void useItem(String command) {
		boolean used = false;
		for (int i = 0; i < inv.getLimit(); i++) {
			if (command.contains(Integer.toString(i + 1))
					&& !inv.getInvItem()[i].getName().equals("Nothing")
					&& inv.getInvItem()[i].getType() == 5) {
				inv.getInvItem()[i].useItem(this);
				inv.removeItem(inv.getInvItem()[i]);
				used = true;
			}
		}
		if (!used) {
			System.out.println("You cannot use that.");
		}

	}

	public void equipItem(String command) {
		boolean equipped = false;
		for (int i = 0; i < inv.getLimit(); i++) {
			if (command.contains(Integer.toString(i + 1))
					&& !inv.getInvItem()[i].getName().equals("Nothing")
					&& inv.getInvItem()[i].getType() <= 4 && !equipped) {
				System.out.println(inv.equipItem(inv.getInvItem()[i]));
				inv.removeItem(inv.getInvItem()[i]);
				equipped = true;

			}
		}
		if (!equipped) {
			System.out.println("You cannot equip that.");
		}
	}

	public void examineInv(String command) {
		boolean isItem = false;
		for (int i = 0; i < inv.getLimit(); i++) {
			if (command.contains(Integer.toString(i + 1))
					&& !inv.getInvItem()[i].getName().equals("Nothing")) {
				inv.examine(i);
				isItem = true;
			}
		}
		if (!isItem) {
			System.out.println("There is nothing in that slot.");
		}
	}

	public void die() throws InterruptedException {
		System.out.println("You have become the recently deceased. Good job.");
		Thread.sleep(2000);
		System.out.println("You end the game at level " + getLevel());
		System.out.println("and only " + getExperience()
				+ " points of experience.");
		Thread.sleep(2000);
		System.out.print("Looks like its");
		Thread.sleep(2000);
		System.out.print(".");
		Thread.sleep(500);
		System.out.print(".");
		Thread.sleep(500);
		System.out.println(".");
		Thread.sleep(500);
		System.out.print("GAME");
		Thread.sleep(1000);
		System.out.println(" OVER.");
		@SuppressWarnings("unused")
		// Variable isn\"t used
		SoundTrigger demo = new SoundTrigger("res\\death.wav");
		Thread.sleep(5000);
		try {
		   Start.main(null);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	public Ansi attack(Monster m) {
		Random random = new Random();
		double hitchance = this.getDexterity();
		if (random.nextInt(100) <= (int) hitchance) {
			int damage = random.nextInt((getStrength() / 5)
					+ getInv().getEquippedItem()[4].getAttack()) + 1;
			if (damage == (getStrength() / 5)
					+ getInv().getEquippedItem()[4].getAttack()
					&& !getInv().getEquippedItem()[4].getName().equals(
							"Nothing")) {
				getInv().getEquippedItem()[4].weaken();
			}
			m.reduceHealth(damage);
			if (m.getHealth() <= 0) {
				m.makeExtinct();
				this.setCleared(true);
				if (isBoss()) {
					setBoss(false);
				}
				int XP = m.getStartHP() * 5 + m.getMaxDamage() * 5;
				this.addXP(XP);
				String extra = "";
				if (m.getLoot().getType() == 8) {
					addGold(m.getLoot().getValue());
					extra = "\nYou find " + m.getLoot().getValue()
							+ " gold pieces on the corpse.";
				} else {
					extra = "\nWhats this? The corpse dropped some loot!";
				}

				return ansi().render("@|bold,green Thou hath dealt a killing blow of " + damage
						+ " hitpoints and slain the enemy! You gain " + XP
						+ " XP!|@" + "@|bold,magenta "+ extra +" |@");
			}
			return ansi().render("@|bold,yellow You land a convincing strike on the " + m.getName()
					+ ", damaging it for " + damage + " hitpoints.|@");
		} else {
			return ansi().render("@|bold,red You flail around, missing your target by miles. |@");
		}
	}

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	public int getAlchemy() {
		return alchemy;
	}

	public void setAlchemy(int alchemy) {
		this.alchemy = alchemy;
	}

	public int getRepair() {
		return repair;
	}

	public void setRepair(int repair) {
		this.repair = repair;
	}

	public int getTrading(){
		return trading;
	}
	
	public void setTrading(int trading) {
		this.trading = trading;
		
	}

}
