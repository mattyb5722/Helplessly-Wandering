
public class Monster {
	
	/* This class creates monster objects and organizes their data.*/
	
	private final String description;
	private final int strength;
	
	public Monster(int strength, String description){
		this.strength = strength;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	public int getStrength() {
		return strength;
	}
	
}