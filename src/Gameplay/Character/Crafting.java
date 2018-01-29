package Gameplay.Character;

import java.io.Serializable;
import java.util.Scanner;

import Characters.Player;

public class Crafting implements Serializable {

	String[][] rStr;
	int[][] rInt;
	int id;

	public Crafting() {
		rStr = new String[8][100];
		rInt = new int[7][100];
		for (int i = 0; i < 100; i++) {
			rStr[0][i] = "";
		}
	}

	public void viewRecipes() {
		System.out.println("Recipe List:");
		for (int i = 0; i < 100; i++) {
			if (rStr[0][i].equals("")) {
				break;
			} else {
				System.out.println(rStr[0][i]);
				for (int j = 1; j < rInt[0][i] + 1; j++) {
					System.out.println(rInt[j][i] + " " + rStr[j][i]);
				}
				System.out.println("Tool Required: "+rStr[7][i]);
				System.out.println("-------");
			}
		}
	}

	public void addRecipe(int id, String name, int noIng, String ing1,
			int qty1, String ing2, int qty2, String ing3, int qty3,
			String ing4, int qty4, String ing5, int qty5, String ing6,
			int qty6, String tool) {
		for (int i = 0; i < 100; i++) {
			if (!rStr[0][i].startsWith("ID")) {
				rStr[0][i] = "ID "+ id + ": " + name;
				rInt[0][i] = noIng;
				rStr[1][i] = ing1;
				rInt[1][i] = qty1;
				rStr[2][i] = ing2;
				rInt[2][i] = qty2;
				rStr[3][i] = ing3;
				rInt[3][i] = qty3;
				rStr[4][i] = ing4;
				rInt[4][i] = qty4;
				rStr[5][i] = ing5;
				rInt[5][i] = qty5;
				rStr[6][i] = ing6;
				rInt[6][i] = qty6;
				rStr[7][i] = tool;
				break;
			}
		}
	}

	public void addRecipe(int id, String name, int noIng, String ing1,
			int qty1, String ing2, int qty2, String ing3, int qty3,
			String ing4, int qty4, String ing5, int qty5, String tool) {
		for (int i = 0; i < 100; i++) {
			if (rStr[i][0].equals("")) {
				rStr[0][i] = "ID "+ id + ": " + name;
				rInt[0][i] = noIng;
				rStr[1][i] = ing1;
				rInt[1][i] = qty1;
				rStr[2][i] = ing2;
				rInt[2][i] = qty2;
				rStr[3][i] = ing3;
				rInt[3][i] = qty3;
				rStr[4][i] = ing4;
				rInt[4][i] = qty4;
				rStr[5][i] = ing5;
				rInt[5][i] = qty5;
				rStr[7][i] = tool;
				break;
			}
		}
	}

	public void addRecipe(int id, String name, int noIng, String ing1,
			int qty1, String ing2, int qty2, String ing3, int qty3,
			String ing4, int qty4, String tool) {
		for (int i = 0; i < 100; i++) {
			if (rStr[i][0].equals("")) {
				rStr[0][i] = "ID "+ id + ": " + name;
				rInt[0][i] = noIng;
				rStr[1][i] = ing1;
				rInt[1][i] = qty1;
				rStr[2][i] = ing2;
				rInt[2][i] = qty2;
				rStr[3][i] = ing3;
				rInt[3][i] = qty3;
				rStr[4][i] = ing4;
				rInt[4][i] = qty4;
				rStr[7][i] = tool;
				break;
			}
		}
	}

	public void addRecipe(int id, String name, int noIng, String ing1,
			int qty1, String ing2, int qty2, String ing3, int qty3, String tool) {
		for (int i = 0; i < 100; i++) {
			if (rStr[0][i].equals("")) {
				rStr[0][i] = "ID "+ id + ": " + name;
				rInt[0][i] = noIng;
				rStr[1][i] = ing1;
				rInt[1][i] = qty1;
				rStr[2][i] = ing2;
				rInt[2][i] = qty2;
				rStr[3][i] = ing3;
				rInt[3][i] = qty3;
				rStr[7][i] = tool;
				break;
			}
		}
	}

	public void craft(String tool, Player p) {
		System.out.print("You start to use the " + tool + "."
				+ "\nType the ID of the item you wish to craft."
				+ " \nType 'exit' to leave, 'recipes' for recipes. \n> ");
		Scanner scan = new Scanner(System.in);
		String command = "";
		while (!command.equals("exit")) {
			command = scan.nextLine();
			if (command.equals("exit")) {
				// BLANK LINE :O
			} else if (command.equals("recipes")){
				p.getCraft().viewRecipes();
				System.out.print("\n> ");
			} else if (recipeExists(command)) {
				int ingNo = getIngNo(command);
				System.out.println("No of Ingredients: " + ingNo);
				if (canCraft(p, command, ingNo)) {
					p.getInv().addItem(new InventoryItem(getName(command)));
					System.out.print("You successfully crafted a " + getName(command) +"!\n> ");
				} else {
					System.out
							.print("You dont have the ingredients to make that. \n> ");
				}
			} else {
				System.out.print("You don't have that recipe. \n> ");
			}
		}
	}

	private String getName(String command) {
		int start = 6;
		if (Integer.parseInt(command) > 9){
			start = 7;
		}
		for(int i = 0; i < 100; i++){
			if(rStr[0][i].startsWith("ID "+command)){
				return rStr[0][i].substring(start, rStr[0][i].length());
			}
		}
		return null;
	}

	public boolean recipeExists(String name) {
		String ID = "ID "+name;
		for (int i = 0; i < 100; i++) {
			if (rStr[0][i].startsWith(ID)) {
				return true;
			}
		}
		return false;
	}

	public int getIngNo(String name) {
		String ID = "ID "+name;
		for (int i = 0; i < 100; i++) {
			if (rStr[0][i].startsWith(ID)) {
				return rInt[0][i];
			}
		}
		return 0;
	}

	public boolean canCraft(Player p, String name, int ingNo) {
		String ID = "ID "+name;
		for (int i = 0; i < 100; i++) {
			if (rStr[0][i].startsWith(ID)) {
				String[] ingredients = { rStr[1][i], rStr[2][i], rStr[3][i],
						rStr[4][i] };
				int[] quantities = { rInt[1][i], rInt[2][i], rInt[3][i],
						rInt[4][i] };
				for (int j = 0; j < ingNo; j++) {
					if (!p.getInv().hasEnoughIng(ingredients[j], quantities[j])) {
						return false;
					}
				}
				for (int j = 0; j < ingNo; j++) {
					p.getInv().removeIng(ingredients[j], quantities[j]);
				}
				return true;
			}
		}
		return false;
	}

	public String fill(Player p) {
		Inventory inv = p.getInv();
		InventoryItem[] invItem = p.getInv().getInvItem();
		for (int i = 0; i < inv.getLimit(); i++) {
			if (invItem[i].getName().equals("Small Flask")) {
				inv.removeItem(invItem[i]);
				inv.addItem(new InventoryItem("Small Water Flask",
						"A small flask filled with water.", invItem[i]
								.getQuantity()));
				return "You filled " + invItem[i].getQuantity()
						+ " small flasks with water.";
			} else if (invItem[i].getName().equals("Medium Flask")) {
				inv.removeItem(invItem[i]);
				inv.addItem(new InventoryItem("Medium Water Flask",
						"A medium flask filled with water.", invItem[i]
								.getQuantity()));
				return "You filled " + invItem[i].getQuantity()
						+ " medium flasks with water.";
			}
		}
		return "You have nothing to fill with water.";
	}

}
