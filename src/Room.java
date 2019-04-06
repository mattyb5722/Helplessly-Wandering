import java.util.ArrayList;

public class Room {
	
	/* This class organizes room information */ 
	
	private final String ID;
	private Room north, south, east, west, up, down;			// Connections from this room
	private ArrayList<Item> items = new ArrayList<Item>();		// Items in the room
	private Monster monster = null;								// Monster in the room
	
	private ArrayList<String> description = new ArrayList<String>(); // Description of this room
	
	public Room(String ID) {
		this.ID = ID;
	}
	
	// Set connection with other rooms
	public void setConnections(Room north, Room south, Room east, Room west, Room up, Room down) {
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
		this.up = up;
		this.down = down;
	}
	
	// Finds an item in this room
	public Item findItem(String ID) {
		for (Item temp: items) {
			if (temp.getID().equals(ID)){
				return temp;
			}
		}
		return null;
	}
	
	public String getID() {
		return ID;
	}
	public Room getNorth() {
		return north;
	}
	public Room getSouth() {
		return south;
	}
	public Room getWest() {
		return west;
	}
	public Room getEast() {
		return east;
	}
	public Room getUp() {
		return up;
	}
	public Room getDown() {
		return down;
	}
	public void addItem(Item item) {
		items.add(item);
	}
	public void removeItem(Item item) {
		items.remove(item);
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public ArrayList<String> getDescription() {
		return description;
	}
	public void addDescription(String description) {
		this.description.add(description);
	}
	public Monster getMonster() {
		return monster;
	}
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
}
