package Gameplay.Character;

import java.io.Serializable;

public class Magic implements Serializable {

	// alpha burn
	// beta cold bolt
	// epsilon cold bolt mk2
	// lambda poison
	// tau teleport
	// rho fireball
	// psi brain explode

	private boolean magicCapable;
	private int mana;

	public Magic(boolean magicCapable, int mana) {
		this.magicCapable = magicCapable;
		this.mana = mana;
	}

	public boolean isMagicCapable() {
		return magicCapable;
	}
	
	public int getMana(){
		return mana;
	}
}
