package Area.Tektik;

import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import org.fusesource.jansi.Ansi;

import Characters.Player;
import Gameplay.Character.InventoryItem;
import Gameplay.Generation.ResponseGen;

public class Shop2 {
	public void check(TektikVar v, String c, Player p) {
		ResponseGen res = new ResponseGen();
		if (c.contains("examine") || c.contains("look") || c.startsWith("l")) {
			System.out.println(examine(c));
		} else if (c.contains("use")) {
			System.out.println(use(c, p));
		} else if (c.contains("take") || c.contains("get") || c.startsWith("g")) {
			System.out.println(take());
		} else if (c.contains("talk to")) {
			AnsiConsole.systemInstall();
			System.out.println(talkTo(v, c, p));
			AnsiConsole.systemUninstall();
		} else if (c.equals("north") || c.equals("n")) {
			System.out.println(north());
		} else if (c.equals("east") || c.equals("e")) {
			System.out.println(east());
		} else if (c.equals("west") || c.equals("w")) {
			System.out.println(west(p));
		} else if (c.equals("south") || c.equals("s")) {
			System.out.println(south());
		} else {
			System.out.println(res.gameResponse(true));
		}
	}

	public Ansi talkTo(TektikVar v, String c, Player p) {
		if (c.contains("alchemist")) {
			if (c.endsWith("about trade")) {
				trade(p);
				return ansi().render(
						"Thank you, be sure to purchase from here again!");
			} else if (c.endsWith("about recipe")
					|| c.endsWith("about recipes")) {
				return ansi()
						.render("@|bold,cyan Recipes are the key to making potions. Its fair to say|@")
						.newline()
						.render("@|bold,cyan you can't recite the formula for an|@ @|bold,white elixir|@ @|bold,cyan in your|@")
						.newline()
						.render("@|bold,cyan head, so you need to make a note of it! I sell em,|@")
						.newline()
						.render("@|bold,cyan pretty standard ones for now, but there's a lot of|@")
						.newline()
						.render("@|bold,cyan recipes out there, some even hidden in dungeons!|@");
			} else if (c.endsWith("about elixir")
					|| c.endsWith("about elixirs")) {
				return ansi()
						.render("@|bold,cyan Elixirs... Oh, they are very special. Nearly impossible|@")
						.newline()
						.render("@|bold,cyan to create unless you are|@ @|bold,white rank|@ @|bold,cyan grandmaster of alchemy.|@")
						.newline()
						.render("@|bold,cyan They are known to permanently change you for the better.|@")
						.newline()
						.render("@|bold,cyan I know only of one. The Great Elixir of Alchemy, which|@")
						.newline()
						.render("@|bold,cyan ironically is useless since you have to be the best you|@")
						.newline()
						.render("@|bold,cyan can possibly be at alchemy to make it, so its given to|@")
						.newline()
						.render("@|bold,cyan those who aren't that great at it. Problem is they can|@")
						.newline()
						.render("@|bold,cyan cost upwards of near millions of gold to buy!|@");
			} else if (c.endsWith("about potion")
					|| c.endsWith("about potions")) {
				return ansi()
						.render("@|bold,cyan So, potions are a pretty big deal. First developed as |@")
						.newline()
						.render("@|bold,cyan curealls to pretty much every ailment, turns out warriors|@")
						.newline()
						.render("@|bold,cyan could use them to endure through battle, with health potions|@")
						.newline()
						.render("@|bold,cyan being able to heal near fatal wounds, of course it can't cure|@")
						.newline()
						.render("@|bold,cyan a grisly decapitation, but they can bring you from near death |@")
						.newline()
						.render("@|bold,cyan to healthy as the day you were born! Crazy stuff, huh?|@");
			} else if (c.endsWith("about rank")) {
				return ansi()
						.render("@|bold,cyan The alchemists located around Ashjana have five ranks|@")
						.newline()
						.render("@|bold,cyan of alchemy. It starts as Apprentice, then once the|@")
						.newline()
						.render("@|bold,cyan alchemist has learned enough to create more advanced|@")
						.newline()
						.render("@|bold,cyan potions, they become the Advanced rank. Then, once|@")
						.newline()
						.render("@|bold,cyan they have become an expert in creating the advanced|@")
						.newline()
						.render("@|bold,cyan potions, they become Expert rank. Then finally, with|@")
						.newline()
						.render("@|bold,cyan many more years of practise they become a Master, then|@")
						.newline()
						.render("@|bold,cyan finally a grandmaster. Time isn't really a factor, but|@")
						.newline()
						.render("@|bold,cyan it helps I guess. *shrug*|@");
			} else if (c.endsWith("alchemist")) {
				return ansi()
						.render("@|bold,cyan Hello! I'm the alchemist, pleasure to meet you!|@")
						.newline()
						.render("@|bold,cyan I deal with all sorts of ingredients to brew stuff|@")
						.newline()
						.render("@|bold,cyan and whilst I don't sell|@ @|bold,white potions,|@ @|bold,cyan I sell something|@")
						.newline()
						.render("@|bold,cyan even better!|@ @|bold,white Recipes|@ @|bold,cyan and ingredients! My old mortar|@")
						.newline()
						.render("@|bold,cyan and pestle on the counter is free to use, so give it|@")
						.newline().render("@|bold,cyan a try!|@");

			} else {
				return ansi()
						.render("@|bold,cyan Sorry, I don't know anything about that.|@");
			}
		} else {
			return ansi().render("Talk to who?");
		}
	}

	public String north() {
		return "That wood looks mighty tempting to mark your glorious"
				+"\n mug, go on, try it! No? Okay then.";
	}

	public String east() {
		return "As you start to casually walk into the counter, the"
				+"\n alchemist stares you down with a look of disgust."
				+"\n You awkwardly stop and saunter back.";
	}

	public String south() {
		return "That wood looks mighty tempting to mark your glorious"
				+"\n mug, go on, try it! No? Okay then.";
	}

	public String west(Player p) {
		p.moveRoom(-1);
		return "You leave the alchemist's, returning to the road.";
	}

	public String use(String c, Player p) {
		if (c.contains("mortar")) {
			p.getCraft().craft("Mortar and Pestle", p);
			return "You return the pestle to the mortar.";
		} else {
			return "Use what?";
		}
	}

	public String take() {
		return "That alchemist would probably pour hydrochloric acid on you" +
				"\n if you tried to take any of his items.";
	}

	public String examine(String command) {
		if (command.contains("alchemist")) {
			return "The alchemist is standing behind the counter. He has"
					+ "\na large grin on his face.";
		} else if (command.contains("jars")) {
			return "The jars seem to contain ingredients for making potions.";
		} else if (command.contains("mortar")) {
			return "The mortar looks shabby, but clearly hasn't been used for"
					+ "\na while. Theres no residue inside and the pestle looks"
					+ "\nvery clean.";
		} else if (command.equals("examine")) {
			return "You are standing in a shop, with jars of things behind a"
					+ "\ncounter with an alchemist there. There is a mortar with"
					+ "\npestle sitting on the counter as well.";
		} else {
			return "Sorry, examine what?";
		}
	}

	public void trade(Player p) {
		System.out.println("Sure thing! Pick something! Type inv for details.");
		Scanner scan = new Scanner(System.in);
		String command = "";
		while (!command.equals("exit")) {
			System.out.print("> ");
			command = scan.nextLine();
			if (command.equals("inv")) {
				System.out.println("1: Small Flask, 5 gold"
						+ "\n2: Base Reactant, 1 gold"
						+ "\n3: Barsh Root, 5 gold"
						+ "\n4: Secuit Powder, 10 gold"
						+ "\n5: Minor Healing Potion Recipe, 100 gold");
			} else if (command.equals("1")) {
				boolean checkedForItemExist = false;
				for (int i = 0; i < p.getInv().getLimit(); i++) {
					if (p.getInv().hasItem("Small Flask") != -1) {
						int element = p.getInv().hasItem("Small Flask");
						if (p.getGold() >= 5) {
							p.addGold(-5);
							p.getInv().stackIng(
									new InventoryItem("Small Flask",
											"An empty flask", 1), element);
							System.out.println("Thank you!");
						} else {
							System.out
									.println("Sorry, you can't purchase that!");
						}
						checkedForItemExist = true;
						break;
					}
				}
				if (!checkedForItemExist) {
					if (p.getInv().hasSpace() && p.getGold() >= 5) {
						p.addGold(-5);
						p.getInv().addItem(
								new InventoryItem("Small Flask",
										"An empty flask", 1));
						System.out.println("Thank you!");
					} else {
						System.out.println("Sorry, you can't purchase that!");
					}
				}
			} else if (command.equals("2")) {
				boolean checkedForItemExist = false;
				for (int i = 0; i < p.getInv().getLimit(); i++) {
					if (p.getInv().hasItem("Base Reactant") != -1) {
						int element = p.getInv().hasItem("Base Reactant");
						if (p.getGold() >= 1) {
							p.addGold(-1);
							p.getInv()
									.stackIng(
											new InventoryItem(
													"Base Reactant",
													"A low level reactant used to bind ingredients.",
													1), element);
							System.out.println("Thank you!");
						} else {
							System.out
									.println("Sorry, you can't purchase that!");
						}
						checkedForItemExist = true;
						break;
					}
				}
				if (!checkedForItemExist) {
					if (p.getInv().hasSpace() && p.getGold() >= 1) {
						p.addGold(-1);
						p.getInv()
								.addItem(
										new InventoryItem(
												"Base Reactant",
												"A low level reactant used to bind ingredients.",
												1));
						System.out.println("Thank you!");
					} else {
						System.out.println("Sorry, you can't purchase that!");
					}
				}
			} else if (command.equals("3")) {
				if (p.getInv().hasSpace() && p.getGold() >= 5) {
					p.addGold(-5);
					p.getInv()
							.addItem(
									new InventoryItem(
											"Barsh Root",
											"A root of the Barsh tree with healing properties.",
											1));
					System.out.println("Thank you!");

				} else {
					System.out.println("Sorry, you can't purchase that!");
				}
			} else if (command.equals("4")) {
				if (p.getInv().hasSpace() && p.getGold() >= 10) {
					p.addGold(-10);
					p.getInv()
							.addItem(
									new InventoryItem(
											"Secuit Powder",
											"A musty powder found on dungeon walls.",
											1));
					System.out.println("Thank you!");
				} else {
					System.out.println("Sorry, you can't purchase that!");
				}
			} else if (command.equals("5")) {
				if (p.getGold() >= 100 && !p.getCraft().recipeExists("1")) {
					p.addGold(-100);
					p.getCraft().addRecipe(1, "Minor Health Potion", 3,
							"Small Water Flask", 1, "Barsh Root", 1,
							"Base Reactant", 1, "Mortar and Pestle");
					System.out.println("Thank you!");

				} else {
					System.out.println("Sorry, you can't purchase that!");
				}
			} else if (command.equals("exit")) {
			} else {
				System.out.println("Sorry, what?");
			}
		}
	}
}
