package Gameplay.Character;

import java.io.Serializable;

import Characters.Player;
import Objects.Weapon;
import Objects.Armor.ArmorBoots;
import Objects.Armor.ArmorChest;
import Objects.Armor.ArmorHead;
import Objects.Armor.ArmorLegs;

/**
 * Class for creating Inventory Item objects based from object type.
 * 
 * @author Peter
 * 
 */
public class InventoryItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * All the attributes across possible item types.
	 */
	private int type, defense, durability, attack, value, effectValue,
			quantity;
	private String effect, desc;

	/**
	 * TYPES: 0: Armor (head) 1: Armor (chest) 2: Armor (legs) 3: Armor (boots)
	 * 4: Weapon 5: Item 6: Ingredient 8: Gold 9: NULL
	 */

	/**
	 * Set the InvItem type Head Armor.
	 * 
	 * @param head
	 */
	public InventoryItem(ArmorHead head) {
		this.type = 0;
		this.name = head.getName();
		int[] tempAttribs = head.getAttributes();
		this.defense = tempAttribs[0];
		this.durability = tempAttribs[1];
		this.value = tempAttribs[2];
	}

	/**
	 * Set the InvItem type Chest Armor.
	 * 
	 * @param chest
	 */
	public InventoryItem(ArmorChest chest) {
		this.type = 1;
		this.name = chest.getName();
		int[] tempAttribs = chest.getAttributes();
		this.defense = tempAttribs[0];
		this.durability = tempAttribs[1];
		this.value = tempAttribs[2];
	}

	/**
	 * Set the InvItem type Leg Armor.
	 * 
	 * @param legs
	 */
	public InventoryItem(ArmorLegs legs) {
		this.type = 2;
		this.name = legs.getName();
		int[] tempAttribs = legs.getAttributes();
		this.defense = tempAttribs[0];
		this.durability = tempAttribs[1];
		this.value = tempAttribs[2];
	}

	/**
	 * Set the InvItem type Boots Armor.
	 * 
	 * @param boots
	 */
	public InventoryItem(ArmorBoots boots) {
		this.type = 3;
		this.name = boots.getName();
		int[] tempAttribs = boots.getAttributes();
		this.defense = tempAttribs[0];
		this.durability = tempAttribs[1];
		this.value = tempAttribs[2];
	}

	/**
	 * Set the InvItem type Weapon.
	 * 
	 * @param weapon
	 */
	public InventoryItem(Weapon weapon) {
		this.type = 4;
		this.name = weapon.getName();
		int[] tempAttribs = weapon.getAttributes();
		this.attack = tempAttribs[0];
		this.durability = tempAttribs[1];
		this.value = tempAttribs[2];
	}

	/**
	 * Set the invItem type Item.
	 * 
	 * @param setItem
	 */
	public InventoryItem(String setItem) {
		if (setItem.equals("Nothing")) {
			this.type = 9; // Type 9 = NULL
			this.defense = 0;
			this.durability = 0;
			this.attack = 0;
			this.value = 0;
		} else {
			this.type = 5; // Type 5 = ITEM
		}
		this.name = setItem;
		this.effect = setEffect(setItem);
	}

	/**
	 * Sets the effect based on the item name.
	 * 
	 * @param setItem
	 * @return The effect of the item.
	 */
	private String setEffect(String setItem) {
		if (setItem.equals("Minor Health Potion")) {
			this.effectValue = 5;
			this.value = 5;
			return "health";
		}
		return setItem;

	}

	public InventoryItem(String setIng, String setDesc, int quantity) {
		this.type = 6;
		this.name = setIng;
		this.desc = setDesc;
		this.addQuantity(quantity);
	}

	public InventoryItem(int value) {
		this.type = 8;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void useItem(Player player) {
		if (effect.equals("health")
				&& player.getHealth() < player.getMaxHealth()) {
			player.setHealth(player.getHealth() + effectValue);
			System.out
					.println("You drink the potion, and add 5 points of health!");
		}
	}

	public int getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getEffect() {
		return effect;
	}

	public int getEffectValue() {
		return effectValue;
	}

	public String toString() {
		return "T:" + type + " N:" + name + " D1:" + defense + " D2:"
				+ durability + " A:" + attack;
	}

	public void weaken() {
		durability--;
	}

	public void destroy() {
		this.attack = 0;
		this.defense = 0;
		this.durability = 0;
		this.name = "Nothing";
		this.value = 0;

	}

	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int add) {
		this.quantity += add;
	}

	public String getDesc() {
		return desc;
	}

}
