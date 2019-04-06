
public class Text {

	/* This class manages when to display certain text*/
	
	private MainBody main;
	
	public Text(MainBody main){
		this.main = main;
	}
	
	// Display the text at the beginning of the game
	public void StartText(){
		main.addMessage("You wake up to find yourself laying in a field. Surrounding you is a parachute.", "AI");
		main.addMessage("Looking around you notice a burning plane north of you and a bag near by.", "AI");
		main.addMessage("Questions flood the mind: What happened? Did I cause this?", "AI");
		main.addMessage("Pushing the question away you deattach the parachute.", "AI");
		main.addMessage("In the bag you find a pocket knife, a bottle of water and a sandwhich.", "AI");
		main.addMessage("Somehow you have to find a way off this island.", "AI");
		main.addMessage("Type 'help' for a list of commands", "AI");
   		main.addMessage("","AI");
   		main.addMessage("Welcome to the Hopelessy Wandering", "AI");	
   		main.addMessage("Created By: Matt Brown", "AI");	
   		main.addMessage("","AI");
   		main.render();
	}
	
	// Displays the text of a certain location
	public void LocationText(Room room){
		if (room.getID().substring(0,4).equals("Maze")){		// Player is in the maze
			main.addMessage("You find yourself in the middle of a dark room.", "AI");
			main.addMessage("In the middle of the room is a ladder going upward.", "AI");
			main.addMessage("There are doorways in each direction", "AI");
		}else {
			for (String temp: room.getDescription()) {			// Display description of room
				main.addMessage(temp, "AI");
			}
		}
	}
	
	// Displays the items in the current room
	public void ItemText(){
		for(Item temp: main.getRoom().getItems()){				// For ech item in the room
			if (temp.getPlace() == "onground"){
				main.addMessage("Lying on the ground is a " + temp.getID() + ".", "AI");
			}else if (temp.getPlace() == "ontable"){
				main.addMessage("Lying on the ground is a " + temp.getID() + ".", "AI");
			}else if (temp.getPlace() == "text_onground"){
				String ID = temp.getID();
				main.addMessage("Lying on the ground is a " + ID.substring(0, ID.length()-1) + ".", "AI");
			}else if (temp.getPlace() == "text_ontable"){
				String ID = temp.getID();
				main.addMessage("Lying on the ground is a " + ID.substring(0, ID.length()-1) + ".", "AI");
			}else if (temp.getPlace() == "berry"){
				main.addMessage("There is " + temp.getID() + " growing on a bush near by.", "AI");
			}else if (temp.getPlace() == "key"){
				String ID = temp.getID();
				main.addMessage("Lying on the table is a key labled " + ID.substring(0, ID.length()-4) + ".", "AI");
			}else if (temp.getPlace() == "engine"){
				main.addMessage("Lying in the corner is a boat engine.", "AI");
			}else if (temp.getPlace() == "gas"){
				main.addMessage("Lying in the corner is a container of gas.", "AI");
			}else if (temp.getPlace() == "rudder"){
				main.addMessage("Lying in the corner is a piece of wood that could act as a rudder of a boat.", "AI");
			}
		}
	}

	// Displays the text of text items
	public void text_text(String Text){
		if(main.findKeyword(Text, "newspaper") >= 0){
	   		main.addMessage("                                   DAILY NEWS                                   ","AI");
	   		main.addMessage("05.05.14                                                               Wednesday","AI");
		}else if(main.findKeyword(Text, "note") >= 0){
	   		main.addMessage("NOT HERE YET","AI");
		}else if(main.findKeyword(Text, "journal") >= 0){
	   		main.addMessage("NOT HERE YET","AI");
		}else if(main.findKeyword(Text, "notebook") >= 0){
	   		main.addMessage("NOT HERE YET","AI");
		}else if(main.findKeyword(Text, "flight course") >= 0){
	   		main.addMessage("NOT HERE YET","AI");
		}else if(main.findKeyword(Text, "maze notebook") >= 0){
	   		main.addMessage("This temple seems to be very strange. While this upper floor is relatively","AI");
	   		main.addMessage("normal the basement is a maze. Every room is exactly the same. They all","AI");
	   		main.addMessage("have 4 doorways on each side and a ladder in the middle.","AI");
	   		main.addMessage("No matter where you are in the maze the ladder will always bring you back","AI");
	   		main.addMessage("to the main room of the temple. The really strange part of the maze is that","AI");
	   		main.addMessage("sometimes you enter back into the room you just exited. While down there,","AI");
	   		main.addMessage("I tried to keep track of my position with a set of old keys I had, but","AI");
	   		main.addMessage("I lost them almost instantly.","AI");
		}
	}
}