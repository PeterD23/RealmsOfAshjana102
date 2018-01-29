package Objects.Armor;

public class ArmorHead {

	private int id;
	private String name;
	private int[] attributes;

	public ArmorHead(int[] attributes, String name) {
	this.name = name;
	this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getAttributes() {
		return attributes;
	}

	public void setAttributes(int[] attributes) {
		this.attributes = attributes;
	}


}
