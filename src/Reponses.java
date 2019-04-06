import java.util.Random;

public class Reponses {

	/* This class manages the responses to the player's commands */
	
	private MainBody main;
	private Text t;
	private RoomManager rooms;
	private Random r = new Random();
	
	public Reponses(MainBody main, Text t, RoomManager rooms){
		this.main = main;
		this.t = t;
		this.rooms = rooms;
	}
	
	// Moves the player if it is a valid move
	public boolean move(String statement){
		Room oldRoom = main.getRoom();						// Room the player is leaving 
		Room newRoom = main.getRoom();						// Room the player is going to
		int luck = 1;										// Chance of making that move
		if (oldRoom.getID().substring(0,3) == "Maze") {		// Luck changes in the maze
			luck = r.nextInt(4);
		}
		if (luck != 3){
			if (main.findKeyword(statement, "north") >= 0){ 	  // Player moves to the north
				newRoom = oldRoom.getNorth();
			}else if (main.findKeyword(statement, "south") >= 0){ // Player moves to the south
				newRoom = oldRoom.getSouth();
			}else if (main.findKeyword(statement, "east") >= 0){  // Player moves to the east
				newRoom = oldRoom.getEast();
			}else if (main.findKeyword(statement, "west") >= 0){  // Player moves to the west
				newRoom = oldRoom.getWest();
			}else if (main.findKeyword(statement, "up") >= 0){    // Player moves up
				newRoom = oldRoom.getUp();
				if (main.getRoom().getID() == "tree") {
					main.additem("map");
				}
			}else if (main.findKeyword(statement, "down") >= 0){  // Player moves down
				if (oldRoom.getID() == "House Trapdoor Room") {
					main.addMessage("As you descend the ladder you slip off and fall for a while.", "AI");
					main.addMessage("After hitting the ground you open your eyes to a completely different room.", "AI");
					main.setHealth(main.getHealth() - 25);
				}
				newRoom = oldRoom.getDown();
			}
			
			// Invalid moves
			if (newRoom.getID() == "water") {
				newRoom = oldRoom;
				main.addMessage("There's no where to swim to", "AI");
			}else if (newRoom.getID() == "mountain") {
				newRoom = oldRoom;
				main.addMessage("There's no way to get up the mountain", "AI");
			}else if (newRoom.getID() == "wall") {
				newRoom = oldRoom;
				main.addMessage("There's a wall there", "AI");
			}else if (newRoom.getID() == "forest") {
				newRoom = oldRoom;
				main.addMessage("The woods are too dense to pass through", "AI");
			}else if (newRoom.getID() == "nothing") {
				newRoom = oldRoom;
				main.addMessage("There's nowhere to go", "AI");
			}else if (newRoom.getID() == "House Main Room" && !main.getLockedDoors()[0]) {
				newRoom = oldRoom;
				main.addMessage("The door is locked. If you have the key try using it.", "AI");
			}else if (newRoom.getID() == "Shack Main Room" && !main.getLockedDoors()[1]) {
				newRoom = oldRoom;
				main.addMessage("The door is locked. If you have the key try using it.", "AI");
			}else if (newRoom.getID() == "Tree House Tree House" && !main.getLockedDoors()[2]) {
				newRoom = oldRoom;
				main.addMessage("The door is locked. If you have the key try using it.", "AI");
			}else { // Was a valid move
				main.setPastRoom(oldRoom);
				main.setRoom(newRoom);
				return true;
			}
		}
		main.setRoom(oldRoom);
		return false;
	}
	
	// Displays the players bag
	public void checkbag(){
		if (rooms.player.getItems().size() == 0) {				// Bag is empty
			main.addMessage("You have nothing in your inventory", "AI");
		}else {
			main.addMessage("Inventory:", "AI");
			for (Item temp: rooms.player.getItems()) {			// For each item
				main.addMessage("   " + temp.getID(), "AI");	// Display item
			}
		}
	}
	
	// Pick up an item if it is a valid item
	public void takeitem(String statement){
		statement = (statement.substring(5, statement.length())).trim();
		if (rooms.player.getItems().size() >= 10) {				// Bag is already full
			main.addMessage("There's no room in your bag", "AI");
		}else {
			Item temp = main.getRoom().findItem(statement);		// Find the item in the room
			if (temp == null) {									// Item is not in the room
				main.addMessage("You can not find a " + statement, "AI");
			}else {
				rooms.player.addItem(temp);						// Add the item to the player's bag
				main.getRoom().removeItem(temp);				// Remove the item from the room
				temp.setPlace("onself");
				main.addMessage(temp.getID() + " has been added to your bag", "AI");
			}
		}
	}
	
	// Drop an item
	public void dropitem(String statement){
		statement = (statement.substring(5, statement.length())).trim();
		Item temp = rooms.player.findItem(statement);			// Find the item in the room
		if (temp == null) {										// There is nothing in the player's bag
			main.addMessage("You do not have a " + statement, "AI");
		}else {
			temp.setPlace("onground");
			rooms.player.removeItem(temp);						// Remove item from bag
			main.getRoom().addItem(temp);						// Add item to room
			main.addMessage("You've dropped the " + statement, "AI");
		}
	}
	
	// Checks the durability of an item
	private void DurabilityTest(Item temp) {
		main.addMessage(temp.getUseText(), "AI");
		temp.setDurability(temp.getDurability() - 1);
		if (temp.getDurability() == 0) {						// Used up an item
			main.addMessage("You used up the last of the " + temp.getID(), "AI");
			rooms.mountain.addItem(temp);						// Get rid of item
			rooms.player.removeItem(temp);						// Remove item from player
		}
	}
	
	// Use an item
	public void useitem(String statement){
		statement = (statement.substring(4, statement.length())).trim();
		Item temp = rooms.player.findItem(statement);			// Find the item in the room
		if (temp == null) {										// There is nothing in the player's bag
		   	main.addMessage("You don't have that in your inventory.","AI");
		}
		
		// Valid to use that item
		else if (temp.getUseRequirement() == null || temp.getUseRequirement() == main.getRoom().getID()) {
			int [] useResult = temp.getUseResult();
			
			// Special Item
			if (useResult[0] == 0 && useResult[1] == 0 && useResult[2] == 0) {
				if(temp.getID().equals("engine")){
					main.setGameEnding(true, 0);
				}else if(temp.getID().equals("gas")){
					main.setGameEnding(true, 1);
				}else if(temp.getID().equals("boat key")){
					main.setGameEnding(true, 2);
				}else if(temp.getID().equals("rudder")){
					main.setGameEnding(true, 3);
				//Keys
				}else if(temp.getID().equals("house key")){
					main.setLockedDoors(true, 0);
				}else if(temp.getID().equals("shack key")){
					main.setLockedDoors(true, 1);
				}else if(temp.getID().equals("treehouse key")){
					main.setLockedDoors(true, 2);
				}else if (temp.getID().equals("newspaper") || temp.getID().equals("note") || temp.getID().equals("journal") 
					   || temp.getID().equals("notebook") || temp.getID().equals("flight course") || temp.getID().equals("maze notebook")){
					t.text_text(temp.getID());
				}
				DurabilityTest(temp);
			}
			// Food or drink or medical supplies
			else {
				main.setHunger(main.getHunger() + useResult[0]);		// Increase player hunger stat
				main.setThirst(main.getThirst() + useResult[1]);		// Increase player thirsty stat
				main.setHealth(main.getHealth() + useResult[2]);		// Increase player health stat
				DurabilityTest(temp);
				if(main.getThirst() > 125){								// No overhydration
					main.setThirst(125);
				}
				if(main.getHunger() > 125){								// No eating
					main.setHunger(125);
				}
				if(main.getHealth() > 100){								// No healing
					main.setHealth(100);
				}
			}
		}else {
		   	main.addMessage("There's nothing to use it on.","AI");
		}
	}
	
	// Displays the description of an item
	public void examineitem(String statement){
		statement = (statement.substring(7, statement.length())).trim();
		Item temp = rooms.player.findItem(statement);			// Find the item in the room
		if (temp == null) {										// There is nothing in the player's bag
	   		main.addMessage("You don't have that in your inventory.","AI");
		}else if (temp.getID().equals("newspaper") || temp.getID().equals("note") || temp.getID().equals("journal") 
			   || temp.getID().equals("notebook") || temp.getID().equals("flight course") || temp.getID().equals("maze notebook")){
				t.text_text(temp.getID());					
		}else {
			main.addMessage(temp.getDescription(), "AI");		// Display description of the item
		}
	}
	
	// Display player's stats
	public void diagnose(){
		main.addMessage("Hunger: " + main.getHunger(), "AI");
		main.addMessage("Thirst: " + main.getThirst(), "AI");
		main.addMessage("Health: " + main.getHealth(), "AI");
	}

	// Display a list of commands
	public void help(){
		main.addMessage("Commands:", "AI");
		main.addMessage("   North          -  to move north", "AI");
		main.addMessage("   South          -  to move south", "AI");
		main.addMessage("   East           -  to move east", "AI");
		main.addMessage("   West           -  to move west", "AI");
		main.addMessage("   Up             -  to ascend", "AI");
		main.addMessage("   Down           -  to descend", "AI");
		main.addMessage("   Check Bag      -  to check your inventory", "AI");
		main.addMessage("   Take ______    -  to add _____ to your inventory", "AI");
		main.addMessage("   Drop ______    -  to drop _____ from your inventory", "AI");
		main.addMessage("   Use ______     -  to use _____ from your inventory", "AI");
		main.addMessage("   Examine ______ -  to examine _____ from your inventory", "AI");
		main.addMessage("   Diagnose       -  to check hunger, thirst, and health levels", "AI");
	}	
}