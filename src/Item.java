
public class Item {

	private String ID, place;
	private int durability;
	private boolean pickedUp;
	private Location inside, outside;
	
	public Item(String ID, int xOut, int yOut, int zOut, int xIn, int yIn, int zIn, boolean pickedUp, String place, int durability) {
		this.ID = ID;
		this.outside = new Location(xOut, yOut, zOut);
		this.inside = new Location(xIn, yIn, zIn);
		this.pickedUp = pickedUp;
		this.place = place;
		this.durability = durability;
	}
		
	public String getID() {
		return ID;
	}
	
	public boolean isPickedUp() {
		return pickedUp;
	}	
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	public Location getInside() {
		return inside;
	}
	public void setInside(Location inside) {
		this.inside = inside;
	}
	public Location getOutside() {
		return outside;
	}
	public void setOutside(Location outside) {
		this.outside = outside;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	}
	


}
