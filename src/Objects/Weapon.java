package Objects;

public class Weapon {

	private String name;
	private int attributes[];
			
	public Weapon(String name, int[] attributes){
		this.name = name;
		this.attributes = attributes;
	}
	
	public String getName() {
		return name;
	}

	public int[] getAttributes() {
		return attributes;
	}


}
