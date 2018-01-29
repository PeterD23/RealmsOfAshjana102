package Gameplay.Generation;

import java.util.Random;

public class ResponseGen {

	public String gameResponse(boolean flag) {
		String output;
		Random random = new Random();
		int var = random.nextInt(11);
		switch (var) {
		case 1:
			output = "Seriously?";
			break;
		case 2:
			output = "Okay, wrong answer but try again!";
			break;
		case 3:
			output = "Its not difficult you know.";
			break;
		case 4:
			output = "Hast thy interpreter broke?";
			break;
		case 5:
			if(flag){
			output = "Look at the choices! Say \"help\" if you wanna know.";
			} else {
				output = "\"new\",\"load\",\"intro\", or \"exit\". Pick one.";
			}
			break;
		case 6:
			output = "Of all the things you could have said, you picked that?";
			break;
		case 7:
			output = "Poor show.";
			break;
		case 8:
			output = "...";
			break;
		case 9:
			output = "Try again, you might get a right answer this time!";
			break;
		case 10:
			output = "Wrong.";
			break;
		default:
			output = "At least you tried.";
			break;
			
		}
		return output;
	}
}
