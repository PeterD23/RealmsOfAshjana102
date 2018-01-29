package Objects.Armor;

public class ArmorBoots {

	private int id;
	private int[] attributes;
	private String name;
	
	public ArmorBoots(int[] attributes, String name) {
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
