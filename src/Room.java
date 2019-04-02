
public class Room {
	
	private String ID;
	private Room north, south, east, west, up, down;
	
	public Room(String ID) {
		this.ID = ID;
	}
	
	public void setConnections(Room north, Room south, Room east, Room west, Room up, Room down) {
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
		this.up = up;
		this.down = down;
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
	
}
