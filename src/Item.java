
public class Item {

	private String ID, place;
	private int durability;
	private boolean onPlayer;
	private Room location;
		
	public Item(String ID, Room location, boolean onPlayer, String place, int durability) {
		this.ID = ID;
		this.location = location;
		this.onPlayer = onPlayer;
		this.place = place;
		this.durability = durability;
	}
	
		
	public String getID() {
		return ID;
	}
	
	public boolean isOnPlayer() {
		return onPlayer;
	}	
	public void setOnPlayer(boolean onPlayer) {
		this.onPlayer = onPlayer;
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
	public Room getLocation() {
		return location;
	}
	public void setLocation(Room location) {
		this.location = location;
	}
}
