package Area.CastleGlorious;

import java.util.Random;

import Characters.Dog;
import Characters.Player;
import Gameplay.RoomNoHostile;
import Gameplay.Character.InventoryItem;
import Gameplay.Generation.ResponseGen;

public class Room1 implements RoomNoHostile {

	Dog d;

	/**
	 * Checks the input and responds appropriately.
	 * @param var 
	 * 
	 * @param command
	 *            The command given by the interpreter.
	 * @param roomArea
	 *            The current area. If a move is possible, change value
	 *            appropriately.
	 * @param player
	 *            The player character. If a command will manipulate player in
	 *            some fashion, change appropriate values.
	 * @param text
	 *            The interpreter. Required to adjust the room area.
	 */
	public void check(CastleVar v, String c, Player p) {
		ResponseGen res = new ResponseGen();
		if (!v.rv1[11] && v.rv1[8] && !v.rv1[12]) {
			d = new Dog(10,35);
			v.rv1[11] = true;
		}
		if (!v.rv1[11]) {
			if (c.contains("examine") || c.contains("look") || c.startsWith("l")) {
				System.out.println(examine(c, v));
			} else if (c.contains("use")) {
				System.out.println(use(c, v));
			} else if (c.contains("take") || c.contains("get") || c.startsWith("g")) {
				System.out.println(take(p, c, v));
			} else if (c.contains("talk to")) {
				System.out.println(talkTo());
			} else if (c.equals("north") || c.equals("n")) {
				System.out.println(north(p, v));
			} else if (c.equals("east") || c.equals("e")) {
				System.out.println(east(p, v));
			} else if (c.equals("west") || c.equals("w")) {
				System.out.println(west());
			} else if (c.equals("south") || c.equals("s")) {
				System.out.println(south(p, v));
			} else {
				System.out.println(res.gameResponse(true));
			}
		} else if (c.contains("attack") && v.rv1[11]) {
			System.out.println(attack(p, d, v));
			if (!v.rv1[12]) {
				System.out.println(d.attack(p));
			}
		} else {
			System.out
					.println("You cannot do anything else right now! Regardless of invalid syntax!");
		}
	}

	public String talkTo() {
		return "There\"s no-one to talk to.";
	}

	public String attack(Player p, Dog d, CastleVar v) {
		Random random = new Random();
		double hitchance = 100;//p.getDexterity() * (p.getLevel() * 1.1);
		if (random.nextInt(100) <= (int) hitchance) {
			int damage = random.nextInt((p.getStrength() / 5)) + 1;
			d.reduceHealth(damage);
			if (d.getHealth() <= 0) {
				v.rv1[11] = false;
				v.rv1[12] = true;
				return "You knock out the monster with your drunken fists,"
						+ "\nOnly to find you accidentally knocked out the king\"s dog."
						+ "\nOops. Ah well, at least the scare has cured your hangover!";
			}
			return "You drunkenly swing at the beast, knocking it for "
					+ damage + " hit points";
		} else {
			return "You flail around, missing your target by miles.";
		}
	}

	/**
	 * Attempts to move north, to no avail due to a wall.
	 * 
	 * @param player
	 *            The player character. If a command will manipulate player
	 *            object, change appropriate values.
	 * @return Response Text response returned from the command to the
	 *         interpreter.
	 */
	public String north(Player p, CastleVar v) {
		if (!v.rv1[4]) {
			v.rv1[4] = true;
			p.addXP(10);
			return "You eagerly jump onto the bed but discover that this wall is not breakable."
					+ "\nYou realise your mistake, and hopefully you shan\"t do it against lest you"
					+ "\nend up hurting yourself. You earn 10 XP for senseless violence.";
		} else {
			p.setHealth(p.getHealth() - 1);
			return "You run into the wall again, and this time injure yourself for 1 hit point.";
		}

	}

	public String south(Player p, CastleVar v) {
		if (!v.rv1[6]) {
			String out;
			v.rv1[6] = true;
			if(v.rv1[3]){
				out = "You run into the cupboard, knocking it over. Good thing you took"
						+ "\nthe potion! You earn 10 XP for collateral damage.";
			} else {
				out = "You run into the cupboard, knocking it over and causing a potion to fall off,"
						+ "\nsmashing on the floor and making a fizzing noise all over the stone floor."
						+ "\nYou earn 10 XP for collateral damage.";
			}
			v.rv1[2] = true;
			v.rv1[3] = true;
			v.rv1[10] = true;
			p.addXP(10);
			return out;
		} else {
			return "Remembering your brutishness knocked over a heavy cupboard, you decide not to.";
		}

	}

	public String west() {
		return "You run into the wall, and bounce off as you discover it is padded with a nice thick layer of elastic.";
	}

	/**
	 * Attempts to move east, output depends on whether the player opens the
	 * door.
	 * 
	 * @param player
	 *            The player character. If a command will manipulate player
	 *            object, change appropriate values.
	 * @param text
	 *            The interpreter. Required to adjust the room area.
	 * @return Response Text response returned from the command to the
	 *         interpreter.
	 */
	public String east(Player p, CastleVar v) {
		if (!v.rv1[5] && !v.rv1[8]) {
			v.rv1[5] = true;
			p.setHealth(p.getHealth() - 1);
			return "You run at the door, hoping your body will be able to force"
					+ "\nit open. Alas, your attempt fails as you smash your face"
					+ "\ninto the door, injuring yourself for 1 hp.";
		} else if (v.rv1[5] && !v.rv1[8]) {
			p.setHealth(p.getHealth() - 1);
			return "You try again. Same result. 1 hp removed for your idiocy.";
		} else {
			p.getQuest().removeQuest("The Hangover", 100);
			p.addStory("\nWho bravely beat the shit out an innocent dog");
			p.moveRoom(1);
			p.addXP(100);
			return "You walk through the door, congratulations on your inevitable escape!";
		}
	}

	/**
	 * Activates the "use" command. Checks for possible outputs with extra
	 * parameters.
	 * 
	 * @param command
	 *            Parameter interpretation. Required for appropriate output and
	 *            progress.
	 * @return Response Text response returned from the command to the
	 *         interpreter.
	 */
	public String use(String c, CastleVar v) {
		if (c.endsWith("key on door")) {
			v.rv1[7] = true;
			return "You hungoverly jimmy the key in the lock until the door unlocks.";
		} else if (c.endsWith("door") && !v.rv1[7]) {
			return "You pathetically attempt to open a locked door. Nice try.";
		} else if (c.endsWith("door") && v.rv1[7] && !v.rv1[8]) {
			v.rv1[8] = true;
			return "You opened the door, when suddenly, a wild beast appears!";
		} else if (c.endsWith("door") && v.rv1[8]) {
			return "The door is already open, why are you trying to open it again?!";
		} else {
			return "Use what? Make some sense please!";
		}
	}

	/**
	 * Activates the "take" command. Checks for possible outputs with extra
	 * parameters.
	 * 
	 * @param c
	 *            Parameter interpretation. Required for appropriate output and
	 *            progress.
	 * @return Response Text response returned from the command to the
	 *         interpreter.
	 */
	public String take(Player p, String c, CastleVar v) {
		if (c.endsWith("key") && v.rv1[0] && !v.rv1[1]) {
			v.rv1[1] = true;
			return "You take the key. Luckily, it did not disintegrate from the contents of your stomach.";
		} else if (c.endsWith("potion") && v.rv1[2] && !v.rv1[3]) {
			v.rv1[3] = true;
			// ADD INVENTORY HERE
			p.getInv().addItem(new InventoryItem("Minor Health Potion"));
			return "You take the potion. It glistens like a boss.";
		} else {
			return "Take what? Make some sense please.";
		}
	}

	/**
	 * Activates the "examine" command. Checks for possible outputs with extra
	 * parameters.
	 * 
	 * @param c
	 *            Parameter interpretation. Required for appropriate output and
	 *            progress.
	 * @return Response Text response returned from the command to the
	 *         interpreter.
	 */
	public String examine(String c, CastleVar v) {
		if (c.contains("bed")) {
			return "The bed appears to be pretty crappy. Especially that pillow.";
		} else if (c.contains("cupboard closely") && !v.rv1[10]
				&& !v.rv1[3]) {
			v.rv1[2] = true;
			return "Oh, a potion! Interesting.";
		} else if (c.contains("cupboard") && !v.rv1[10]) {
			return "The cupboard seems to have literally nothing on it. Why did you even bother looking at it?"
					+ "\nThen again, maybe you need to look more closely?";
		} else if (c.contains("pillow")) {
			v.rv1[0] = true;
			return "The pillow has stains of alcohol from your interior. You notice a key poking its head from the edge.";
		} else if (c.contains("door")) {
			return "The door seems to be made of a strong wood.";
		} else if (c.contains("potion") && v.rv1[2] && !v.rv1[3] && !v.rv1[10]) {
			return "Your knowledge of potions is fuzzy, but thats a Minor Health Potion for sure.";
		} else if (c.contains("cupboard") && v.rv1[10]) {
			return "The cupboard lies in shambles. Good job.";
		} else if (c.contains("dog") && v.rv1[12]) {
			return "The labrador lies on the floor, exhausted."
					+ "\nIt doesn\"t look that hurt, but you are a douchebag.";
		} else if (c.equals("examine") || c.equals("look") || c.equals("l")) {
			return "You appear to be standing in a dank quarters, with a door to the east. "
					+ "\nThere is a bed with a pillow north of you, and a cupboard behind you to the south.";
		} else {
			return "Examine what? Make some sense please!";
		}
	}

}
