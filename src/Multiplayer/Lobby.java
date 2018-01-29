package Multiplayer;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import Characters.Player;
import Gameplay.Character.InventoryItem;
import IO.Load;

public class Lobby {

	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	private static Socket client;
	private static String charname;
	public Player player;
	public int id;
	boolean combat;
	public boolean yourturn;
	public boolean fighting;

	boolean challenging;
	boolean challenged;
	int challengeID;

	public Lobby() {
		yourturn = false;
	}

	public void go(String ip, int port) { // Change to main if disjointed from program
		Scanner scan = new Scanner(System.in);
		System.out
				.println("Supply thy characters name so that thee may enter the Arena!");
		String name = scan.nextLine();
		if (checkCharExists(name)) {
			player = Load.readPlayerMP(new File("save\\" + name + ".player"));
			charname = player.getName();
			id = connect(player, ip, port);
			System.out.println("Welcome to the Arena of Ashjana!");
			lobby(player);
		} else {
			System.out
					.println("You have supplied an invalid name! Begone from here!");
			System.exit(0);
		}

	}

	private boolean checkCharExists(String fileName) {
		File p = new File("save\\" + fileName + ".player");
		if (p.exists()) {
			return true;
		}
		return false;
	}

	public static int connect(Player p, String ip, int port) {
		try {
			client = new Socket(ip, port); // Local computer only
			// client = new Socket("77.97.229.78", 12345);
			System.out.println("You connected!");
		} catch (IOException e) {
			System.out.println("Cannot connect! Quitting...");
			System.exit(0);
		}
		try {

			in = new ObjectInputStream(client.getInputStream()); // Create a
																	// socket
																	// input
																	// stream
			out = new ObjectOutputStream(client.getOutputStream()); // Create a
																	// socket
																	// output
																	// stream
			out.writeObject(charname); // Write character name to server as
										// username
			out.writeObject(p.getLevel()); // Write player level to server
			try {
				int tempid = (Integer) in.readObject(); // Get ID from server
				System.out.println("Your ID is " + tempid);
				return tempid;
			} catch (Exception e) {
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void arena(Player p) {
		yourturn = false;
		if (challenged) {
			yourturn = true;
			System.out.println("You go first!");
		} else {
			yourturn = false;
			System.out.println("You go second!");
		}
		combat = true;
		while (combat) {
			System.out.println("Waiting...");
			try {
				Thread.sleep(3000);
			} catch (Exception ex) {
			}
			while (yourturn) {
				System.out.println("Your turn!");
				System.out.print("> ");
				Scanner scan = new Scanner(System.in);
				String s = scan.nextLine();
				try {
					InventoryItem[] inv = p.getInv().getEquippedItem();
					if (s.equals("attack")) {
						out.writeObject("f:@");
						out.writeObject(1);
						out.writeObject(p.getDexterity());
						out.writeObject((p.getStrength() / 5)
								+ inv[4].getAttack());
						out.writeObject(id);
						out.writeObject(challengeID);
						yourturn = false;
					} else {
						System.out.println("That is not valid!");
					}

				} catch (Exception e) {
				}
			}
		}
	}

	private void disconnect() {
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		} catch (Exception e) {

		}

	}

	public void lobby(Player p) {
		new GetOutput().start();
		System.out.print("> ");
		while (true) {
			if (fighting) {
				arena(p);
			}
			Scanner scan = new Scanner(System.in);
			String s = "";
			if (!challenging) {
				s = scan.nextLine();
			}
			String f = charname + ": " + s;
			if (s.equals("exit")) {
				break;
			} else if (s.startsWith("examine ")) {
				sendExamine(s);
			} else if (challenged && s.equals("accept")) {
				try {
					System.out.println("You accepted the challenge!");
					fighting = true;
					yourturn = true;
					out.writeObject("combat");
					out.writeObject(id);
					out.writeObject(challengeID);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (s.startsWith("challenge ")) {
				challenging = true;
				sendChallenge(s);
			} else if (s.equals("")) {
			} else {
				writeMsg(f);
			}

		}

		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void sendChallenge(String s) {
		try {
			out.writeObject(s);
			out.writeObject(id);
		} catch (Exception e) {
		}
	}

	public void sendExamine(String s) {
		try {
			out.writeObject(s);
			out.writeObject(id);
		} catch (Exception e) {
		}
	}

	public void writeMsg(String s) {
		try {
			out.writeObject(s);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to send message");
		}
	}

	class GetOutput extends Thread {
		public void run() {
			while (true) {
				if (fighting) {
					arenaBattle();
				} else {
					/**
					 * Run through different message types.
					 */
					try {
						if (!fighting) {
							String msg = (String) in.readObject();
							if (msg.startsWith("duel")) {
								challengeID = (Integer) in.readObject();
								System.out.println(msg.substring(4)
										+ " has challenged you to a duel!");
								challenged = true;
							} else if (msg.equals("combat")) {
								System.out.println("Prepare to fight!");
								fighting = true;
							} else if (msg.equals("challenge")) {
								challengeID = (Integer) in.readObject();
							} else {
								System.out.println(msg);
							}
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
						System.out
								.println("Arena has closed un/expectedly. Begone!");
						System.exit(0);

					}
				}

			}
		}

		public void arenaBattle() {
			player.setHealth(player.getMaxHealth());
			boolean battle = true;
			while (battle) {
				try {
					int type = (Integer) in.readObject(); // Type of message
															// received
					if (type == 1) {
						String loc = (String) in.readObject(); // Body part
						int damage = (Integer) in.readObject(); // How much
																// damage
																// dealt
						String damageMsg = (String) in.readObject(); // The
																		// attack
																		// message
						int attackerID = (Integer) in.readObject(); // Attacker
																	// ID in
																	// case you
																	// die
						InventoryItem[] inv = player.getInv().getEquippedItem();
						if (loc.equals("head")) {
							damage = damage - inv[0].getDefense();
						} else if (loc.equals("chest")) {
							damage = damage - inv[1].getDefense();
						} else if (loc.equals("legs")) {
							damage = damage - inv[2].getDefense();
						} else {
							damage = damage - inv[3].getDefense();
						}
						System.out.println(damageMsg);
						yourturn = true;
						System.out.println("Your turn is " + yourturn);

						player.setHealth(player.getHealth() - damage);
						if (player.getHealth() <= 0) {
							out.writeObject("DEAD");
							out.writeObject(attackerID);
							battle = false;
							combat = false;
							fighting = false;
							challenged = false;
							challenging = false;
							System.out.println("You were slain!");
						}

					} else if (type == 2) { // Receiving failed hit
						String msg = (String) in.readObject();
						System.out.println(msg);
						yourturn = true;
						System.out.println("Your turn is" + yourturn);
					} else if (type == 3) { // Receiving success/failed attack
						String msg = (String) in.readObject();
						System.out.println(msg);
					} else {
						/*
						 * MESSAGE TYPE 4: YOU HAVE WON THE BATTLE
						 */
						battle = false; // Close the battle listener
						combat = false; // Close the combat
						fighting = false; // Close the fighting
						challenged = false;
						challenging = false;
						System.out.println("You have won the battle!");
						player.addXP(10000);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					System.out
							.println("Arena has closed un/expectedly. Begone!");
					System.exit(0);
				}
			}
		}
	}

}