package Characters;

import java.util.Random;

public class Dog {

	private int health;
	private int hitchance;
	
	public Dog(int health, int hitchance) {
		this.health = health;
		this.hitchance = hitchance;
	}
	
	public void reduceHealth(int damage) {
		this.health = this.health - damage;
	}

	public int getHitChance() {
		return hitchance;
	}

	public int getHealth() {
		return health;
	}
	
	public String attack(Player player) {
		Random random = new Random();
		int chance = random.nextInt(100);
		if (chance <= getHitChance()) {
			return "The monster scuffs you, but deals no damage.";
		} else {
			return "The monster pathetically strokes your leg.";
		}
	}
}
