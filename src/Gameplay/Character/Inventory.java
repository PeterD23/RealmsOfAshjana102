package Gameplay.Character;

import java.io.Serializable;

import Objects.Weapon;
import Objects.Armor.ArmorHead;

public class Inventory implements Serializable {

	private Weapon weapon;
	private ArmorHead armHead;
	private InventoryItem[] invItem;
	protected InventoryItem[] equippedItem;
	private int limit;

	public Inventory() {
		equippedItem = new InventoryItem[5];
		invItem = new InventoryItem[20];
		limit = 5;
		for (int i = 0; i < 20; i++) {
			invItem[i] = new InventoryItem("Nothing");
		}
		for (int i = 0; i < 5; i++) {
			equippedItem[i] = new InventoryItem("Nothing");
		}
	}

	public boolean hasSpace() {
		for (int i = 0; i < limit; i++) {
			if (getInvItem()[i].getName().equals("Nothing")) {
				return true;
			}
		}
		return false;
	}

	public boolean hasEnoughIng(String name, int quantity) {
		for (int i = 0; i < limit; i++) {
			if (invItem[i].getName().equals(name)
					&& invItem[i].getQuantity() >= quantity) {
				return true;
			}
		}
		return false;
	}

	public void removeIng(String name, int quantity) {
		for (int i = 0; i < limit; i++) {
			if (name.equals(invItem[i].getName())) {
				invItem[i].addQuantity(-quantity);
				if (invItem[i].getQuantity() == 0) {
					invItem[i].setName("Nothing");
				}
			}
		}
	}

	public void addItem(InventoryItem invItem) {
		boolean isIn = false;
		for (int i = 0; i < limit; i++) {
			if (getInvItem()[i].getName().equals(invItem.getName())
					&& invItem.getType() == 6 && !isIn) {
				getInvItem()[i].addQuantity(invItem.getQuantity());
				isIn = true;
			}
			if (getInvItem()[i].getName().equals("Nothing") && !isIn) {
				getInvItem()[i] = invItem;
				isIn = true;
			}
		}
		if (!isIn) {
			System.out.println("Sorry, no room!");
		}
	}
	
	public void stackIng(InventoryItem invItem, int i) {
		getInvItem()[i].addQuantity(invItem.getQuantity());
	}

	public void removeItem(InventoryItem invItem) {
		for (int i = 0; i < limit; i++) {
			if (getInvItem()[i].getName().equals(invItem.getName())) {
				getInvItem()[i].setName("Nothing");
			}
		}
	}

	public String equipItem(InventoryItem invItem) {
		if (invItem.getType() == 0) {
			equippedItem[0].setName(invItem.getName());
			equippedItem[0].setDefense(invItem.getDefense());
			equippedItem[0].setDurability(invItem.getDurability());
			equippedItem[0].setValue(invItem.getValue());
		} else if (invItem.getType() == 1) {
			equippedItem[1].setName(invItem.getName());
			equippedItem[1].setDefense(invItem.getDefense());
			equippedItem[1].setDurability(invItem.getDurability());
			equippedItem[1].setValue(invItem.getValue());
		} else if (invItem.getType() == 2) {
			equippedItem[2].setName(invItem.getName());
			equippedItem[2].setDefense(invItem.getDefense());
			equippedItem[2].setDurability(invItem.getDurability());
			equippedItem[2].setValue(invItem.getValue());
		} else if (invItem.getType() == 3) {
			equippedItem[3].setName(invItem.getName());
			equippedItem[3].setDefense(invItem.getDefense());
			equippedItem[3].setDurability(invItem.getDurability());
			equippedItem[3].setValue(invItem.getValue());
		} else {
			equippedItem[4].setName(invItem.getName());
			equippedItem[4].setAttack(invItem.getAttack());
			equippedItem[4].setDurability(invItem.getDurability());
			equippedItem[4].setValue(invItem.getValue());
		}
		return "You equip the " + invItem.getName() + ".";
	}

	public int getLimit() {
		return limit;
	}

	public InventoryItem[] getInvItem() {
		return invItem;
	}

	public InventoryItem[] getEquippedItem() {
		return equippedItem;
	}

	public void examine(int i) {
		InventoryItem item = getInvItem()[i];
		int type = item.getType();
		String typeName;
		if (type == 0) {
			typeName = "Head Armor";
		} else if (type == 1) {
			typeName = "Chest Armor";
		} else if (type == 2) {
			typeName = "Leg Armor";
		} else if (type == 3) {
			typeName = "Foot Armor";
		} else if (type == 4) {
			typeName = "Weapon";
		} else if (type == 5) {
			typeName = "Item";
		} else {
			typeName = "Ingredient";
		}
		System.out.println(item.getName() + ", type " + typeName);
		if (type <= 3) {
			System.out.println("Defense: " + item.getDefense());
			System.out.println("Durability: " + item.getDurability());
			System.out.println("Value: " + item.getValue());
		} else if (type == 4) {
			System.out.println("Attack: " + item.getAttack());
			System.out.println("Durability: " + item.getDurability());
			System.out.println("Value: " + item.getValue());
		} else if (type == 5) {
			System.out.println("Effect: " + item.getEffect());
			System.out.println("Effect Value: " + item.getEffectValue());
			System.out.println("Value: " + item.getValue());
		} else {
			System.out.println("Description: " + item.getDesc());
			System.out.println("Quantity: " + item.getQuantity());
		}
	}

	public int hasItem(String name) {
		for(int i = 0; i < limit; i++) {
			if(invItem[i].getName().equals(name)){
				return i;
			}
		}
		return -1;
	}

}