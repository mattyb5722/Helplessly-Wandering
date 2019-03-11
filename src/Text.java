
public class Text {

	MainBody main;
	
	public Text(MainBody main){
		this.main = main;
	}
	
	public void start_text(){
		if (main.getPlace() == main.getPlace().START){
			main.addMessage("You wake up to find yourself laying in a field. Surrounding you is a parachute.", "AI");
			main.addMessage("Looking around you notice a burning plane due north of you.", "AI");
			main.addMessage("Questions flood the mind: What happened? Did I cause this?", "AI");
			main.addMessage("Pushing the question away you deattach the parachute.", "AI");
			main.addMessage("In the bag you find a pocket knife, a bottle of water and a sandwhich.", "AI");
			main.addMessage("Somehow you have to find a way off this island.", "AI");
			main.addMessage("Enter 'Help' for a list of commands", "AI");
	   		main.addMessage("","AI");
	   		main.addMessage("Welcome to the HopelessyWandering", "AI");	
	   		main.addMessage("Created By: Matt Brown", "AI");	
	   		main.addMessage("","AI");
	   		main.render();
		}
	}
	
	public void location_text(){
		if (main.getPlace() == main.getPlace().x0y3){
			main.addMessage("You find yourself on the beach.", "AI");	
			main.addMessage("To the north and west is the ocean.", "AI");
			//main.addMessage("x0y3", "AI");
		}else if (main.getPlace() == main.getPlace().x0y4){
			main.addMessage("You find yourself on the beach.", "AI");	
			main.addMessage("To the west is the ocean.", "AI");
			//main.addMessage("x0y4", "AI");
		}else if (main.getPlace() == main.getPlace().x0y5){
			main.addMessage("You find yourself on the beach.", "AI");	
			main.addMessage("To the west is the ocean.", "AI");
			//main.addMessage("x0y5", "AI");
		}else if (main.getPlace() == main.getPlace().x0y6){
			main.addMessage("You find yourself on the beach.", "AI");	
			main.addMessage("To the south and west is the ocean.", "AI");
			//main.addMessage("x0y6", "AI");
		}else if (main.getPlace() == main.getPlace().BOAT){
			if (main.getInsides() == main.getInsides().BOAT_LAND){
				main.addMessage("You find yourself on the beach.", "AI");	
				main.addMessage("To the west is the ocean. To the north is a boat.", "AI");
				//main.addMessage("Boat Beach", "AI");
			}else if (main.getInsides() == main.getInsides().BOAT_BOAT){
				main.addMessage("You find yourself on a boat.", "AI");
				if(main.getGameEnding()[0] == false){
					main.addMessage("It seems to be missing an engine.", "AI");
				}if(main.getGameEnding()[1] == false){
					main.addMessage("It seems to be out of gas.", "AI");
				}if(main.getGameEnding()[2] == false){
					main.addMessage("It seems to be missing a key.", "AI");
				}if(main.getGameEnding()[3] == false){
					main.addMessage("It seems to be missing a rudder.", "AI");
				}//main.addMessage("boat", "AI");
			}
		}else if (main.getPlace() == main.getPlace().x1y1){
			main.addMessage("You find yourself on the beach.", "AI");	
			main.addMessage("To the west is the ocean.", "AI");
			//main.addMessage("x1y1", "AI");
		}else if (main.getPlace() == main.getPlace().x1y2){
			main.addMessage("You find yourself on the beach.", "AI");	
			main.addMessage("To the west is the ocean.", "AI");
			//main.addMessage("x1y2", "AI");
		}else if (main.getPlace() == main.getPlace().x1y3){
			main.addMessage("You find yourself on the dunes of the beach.", "AI");	
			//main.addMessage("x1y3", "AI");
		}else if (main.getPlace() == main.getPlace().x1y6){
			main.addMessage("You find yourself on the dunes of the beach.", "AI");
			main.addMessage("To the south is a shack.", "AI");
			//main.addMessage("x1y6", "AI");
		}else if (main.getPlace() == main.getPlace().SHACK){
			if (main.getInsides() == main.getInsides().SHACK_MAINROOM){
				main.addMessage("You find yourself in the main room of the shack.", "AI");
				main.addMessage("To the north is the door out. In the corner is a ladder going downward", "AI");
				main.addMessage("The door to go out is to the north.", "AI");
				//main.addMessage("MainRoom", "AI");
			}else if (main.getInsides() == main.getInsides().SHACK_CELLAR){
				main.addMessage("You decend the ladder into a dark musty cellar.", "AI");
				//main.addMessage("Cellar", "AI");
			}
		}else if (main.getPlace() == main.getPlace().x2y0){
			main.addMessage("You find yourself on the beach.", "AI");
			main.addMessage("To the north and east is the ocean. To the west is a boat", "AI");
			//main.addMessage("x2y0", "AI");
		}else if (main.getPlace() == main.getPlace().x2y1){
			main.addMessage("You find yourself on the dunes.", "AI");
			main.addMessage("To the south is a plane wreck.", "AI");
			//main.addMessage("x2y1", "AI");
		}else if (main.getPlace() == main.getPlace().PLANE){
			if (main.getInsides() == main.getInsides().PLANE_COCKPIT){
				main.addMessage("To the east is a beach. To the west is forst.", "AI");
				main.addMessage("You find yourself in the cockpit of the plane.", "AI");
				main.addMessage("To the south looks like the passenger compartment.", "AI");
				//main.addMessage("CockPit", "AI");
			}else if (main.getInsides() == main.getInsides().PLANE_PASSENGER){
				main.addMessage("To the east is a beach. To the west is forst.", "AI");
				main.addMessage("You find yourself in the passenger compartment of the plane.", "AI");
				main.addMessage("To the north looks like the cockpit.", "AI");
				main.addMessage("To the south looks like the storage compartment.", "AI");
				//main.addMessage("Passenger", "AI");
			}else if (main.getInsides() == main.getInsides().PLANE_STORAGE){
				main.addMessage("To the east is a beach. To the west is forst.", "AI");
				main.addMessage("You find yourself in the storage compartment of the plane.", "AI");
				main.addMessage("To the north looks like the passenger compartment.", "AI");
				//main.addMessage("Storage", "AI");
			} 
		}else if (main.getPlace() == main.getPlace().x2y3){
			main.addMessage("You find yourself in an endless field.", "AI");	
			main.addMessage("To the north is a plane wreck. To the south is a mountain.", "AI");
			//main.addMessage("x2y3", "AI");
		}else if (main.getPlace() == main.getPlace().x2y6){
			main.addMessage("You find yourself in an endless field.", "AI");	
			//main.addMessage("x2y6", "AI");
		}else if (main.getPlace() == main.getPlace().x2y7){
			main.addMessage("You find yourself in on the beach. ", "AI");	
			main.addMessage("To the south is the ocean. To the west is a shack.", "AI");
			//main.addMessage("x2y7", "AI");
		}else if (main.getPlace() == main.getPlace().x3y1){
			main.addMessage("You find yourself in the woods.", "AI");	
			main.addMessage("To the north is the ocean. To the south is dense forest.", "AI");
			//main.addMessage("x3y1", "AI");
		}else if (main.getPlace() == main.getPlace().TREE_GROUND){			
			main.addMessage("You find yourself in the woods.", "AI");	
			main.addMessage("To the north is dense forest. To the west is a plane wreck.", "AI");
			main.addMessage("To the east is a temple. There looks like there's a climbable tree nearby.", "AI");
			//main.addMessage("x3y2", "AI");
		}else if (main.getPlace() == main.getPlace().TREE_TREE){			
				main.addMessage("You find yourself at the top of the tallest tree on the island.", "AI");	
				main.addMessage("You can see the whole island and so you jot it down on a leaf", "AI");
				main.additemwithoutdamage("map");
				//main.addMessage("x3y2", "AI");
		}else if (main.getPlace() == main.getPlace().x3y3){
			main.addMessage("You find yourself in the woods.", "AI");	
			//main.addMessage("x3y3", "AI");
		}else if (main.getPlace() == main.getPlace().x3y4){
			main.addMessage("You find yourself in the woods.", "AI");
			main.addMessage("To the west is a mountain. To the south is a house.", "AI");
			//main.addMessage("x3y4", "AI");
		}else if (main.getPlace() == main.getPlace().HOUSE){
			if (main.getInsides() == main.getInsides().HOUSE_MAINROOM){
				main.addMessage("You find yourself in the main room of the house. To the north is the door out.", "AI");
				main.addMessage("There is a door to the west and east and a set of stairs in the corner.", "AI");
				main.addMessage("The door to go out is to the north.", "AI");
				//main.addMessage("MainRoom", "AI");
			}else if (main.getInsides() == main.getInsides().HOUSE_TRAPDOORROOM){
				main.addMessage("You find yourself in the west side room of the house.", "AI");
				main.addMessage("In the corner of the room is a trap door. The ladder looks dangerous.", "AI");
				main.addMessage("There is a door to the west.", "AI");
				//main.addMessage("TrapDoorRoom", "AI");
			}else if (main.getInsides() == main.getInsides().HOUSE_BOXROOM){
				main.addMessage("You find yourself in the east side room of the house.", "AI");
				main.addMessage("There is a door to the east.", "AI");
				//main.addMessage("BoxRoom", "AI");
			}else if (main.getInsides() == main.getInsides().HOUSE_ATTIC){
				main.addMessage("You find yourself in the attic of the house.", "AI");
				main.addMessage("There is a set of stairs in the corner of the room.", "AI");
				//main.addMessage("Attic", "AI");
			}
		}else if (main.getPlace() == main.getPlace().x3y6){
			main.addMessage("You find yourself in an open field.", "AI");
			main.addMessage("To the north is a house.", "AI");
			//main.addMessage("x3y6", "AI");
		}else if (main.getPlace() == main.getPlace().x3y7){
			main.addMessage("You find yourself on the beach.", "AI");
			main.addMessage("To the south is the ocean.", "AI");
			//main.addMessage("x3y7", "AI");
		}else if (main.getPlace() == main.getPlace().x4y1){
			main.addMessage("You find yourself in the woods.", "AI");
			main.addMessage("To the north and east is the ocean. To the south is a temple.", "AI");
			//main.addMessage("x4y1", "AI");
		}else if (main.getPlace() == main.getPlace().TEMPLE){
			if (main.getInsides() == main.getInsides().TEMPLE_MAINROOM){
				main.addMessage("You find yourself in the main room of the temple.", "AI");
				main.addMessage("To the north and south is the way out. There is a door to the west and east.", "AI");
				main.addMessage("There is a decending ladder in the middle of the room", "AI");
				main.addMessage("The door to go out is to the north and south.", "AI");
				//main.addMessage("MainRoom", "AI");
			}else if (main.getInsides() == main.getInsides().TEMPLE_WESTROOM){
				main.addMessage("You find yourself in the west side room of the temple.", "AI");
				main.addMessage("There is a door to the east.", "AI");
				//main.addMessage("WestRoom", "AI");
			}else if (main.getInsides() == main.getInsides().TEMPLE_EASTROOM){
				main.addMessage("You find yourself in the east side room of the house.", "AI");
				main.addMessage("There is a door to the west.", "AI");
				//main.addMessage("EastRoom", "AI");
			}else if ((main.getPlace() == main.getPlace().TEMPLE)&&(main.getInTileZ()==0)){
				main.addMessage("You find yourself in the middle of a dark room.", "AI");
				main.addMessage("In the middle of the room is a ladder going upward.", "AI");
				main.addMessage("There are doorways in each direction", "AI");
				//main.addMessage("Maze ( " + main.getInTileX() +" , "+ main.getInTileY() +" , "+ main.getInTileZ() +" )", "AI");
			}
		}else if (main.getPlace() == main.getPlace().x4y3){
			main.addMessage("You find yourself in the woods.", "AI");
			main.addMessage("To the north is a temple.", "AI");
			//main.addMessage("x4y3", "AI");
		}else if (main.getPlace() == main.getPlace().x4y4){
			main.addMessage("You find yourself in the woods.", "AI");
			//main.addMessage("x4y4", "AI");
		}else if (main.getPlace() == main.getPlace().x4y5){
			main.addMessage("You find yourself in an endless field.", "AI");
			main.addMessage("To the west is a house.", "AI");
			//main.addMessage("x4y5", "AI");
		}else if (main.getPlace() == main.getPlace().x4y6){
			main.addMessage("You find yourself in an endless field.", "AI");
			main.addMessage("To the east is the ocean.", "AI");
			//main.addMessage("x4y6", "AI");			
		}else if (main.getPlace() == main.getPlace().TREEHOUSE){
			if (main.getInsides() == main.getInsides().TREEHOUSE_GROUND){
				main.addMessage("You find yourself on the beach.", "AI");
				main.addMessage("To the south and east is the ocean. Above you is a tree house.", "AI");
				//main.addMessage("x4y7", "AI");			
			}else if (main.getInsides() == main.getInsides().TREEHOUSE_TREEHOUSE){
				main.addMessage("You find yourself in a treehouse.", "AI");
				//main.addMessage("Treehouse", "AI");
			}
		}else if (main.getPlace() == main.getPlace().x5y3){
			main.addMessage("You find yourself on the beach.", "AI");
			main.addMessage("To the north and east is the ocean.", "AI");
			//main.addMessage("x5y3", "AI");
		}else if (main.getPlace() == main.getPlace().x5y4){
			main.addMessage("You find yourself on the beach.", "AI");
			main.addMessage("To the east is the ocean.", "AI");
			//main.addMessage("x5y4", "AI");
		}else if (main.getPlace() == main.getPlace().x5y5){
			main.addMessage("You find yourself on the beach.", "AI");
			main.addMessage("To the south and east is the ocean.", "AI");
			//main.addMessage("x5y5", "AI");
		}
	}
	
	public void item_text(){
		for(int i = 0; i < main.getItems().length; i++){
			if((main.getItems()[i][7] == "false")
			 &&(Integer.parseInt(main.getItems()[i][1]) == main.getTileX())	 &&(Integer.parseInt(main.getItems()[i][2]) == main.getTileY())
			 &&(Integer.parseInt(main.getItems()[i][3]) == main.getTileZ())	 &&(Integer.parseInt(main.getItems()[i][4]) == main.getInTileX())
			 &&(Integer.parseInt(main.getItems()[i][5]) == main.getInTileY())&&(Integer.parseInt(main.getItems()[i][6]) == main.getInTileZ())){
				if(main.getItems()[i][8] == "onground"){
					main.addMessage("Lying on the ground is a " + main.getItems()[i][0] + ".", "AI");
				}else if(main.getItems()[i][8] == "ontable"){
					main.addMessage("Lying on the ground is a " + main.getItems()[i][0] + ".", "AI");
				}else if(main.getItems()[i][8] == "text_onground"){
					main.addMessage("Lying on the ground is a " + main.getItems()[i][0].substring(0, main.getItems()[i][0].length()-1) + ".", "AI");
				}else if(main.getItems()[i][8] == "text_ontable"){
					main.addMessage("Lying on the ground is a " + main.getItems()[i][0].substring(0, main.getItems()[i][0].length()-1) + ".", "AI");
				}else if(main.getItems()[i][8] == "berry"){
					main.addMessage("There is " + main.getItems()[i][0] + " growing on a bush near by.", "AI");
				}else if(main.getItems()[i][8] == "key"){
					main.addMessage("Lying on the table is a key labled " + main.getItems()[i][0].substring(0, main.getItems()[i][0].length()-4) + ".", "AI");
				}else if(main.getItems()[i][8] == "engine"){
					main.addMessage("Lying in the corner is a boat engine.", "AI");
				}else if(main.getItems()[i][8] == "gas"){
					main.addMessage("Lying in the corner is a container of gas.", "AI");
				}else if(main.getItems()[i][8] == "rudder"){
					main.addMessage("Lying in the corner is a piece of wood that could act as a rudder of a boat.", "AI");
				}
			}
		}
		
	}

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
	   		main.addMessage("have 4 doorways main.addMessage(on each side and a ladder in the middle.","AI");
	   		main.addMessage("No matter where you arein the maze the ladder will always bring you back","AI");
	   		main.addMessage("to the main room of the temple.The really strange part of the maze is that","AI");
	   		main.addMessage("sometimes you enter back into the room you just exited. While down there,","AI");
	   		main.addMessage("I tried to keep track of my position with a set of old keys I had, but","AI");
	   		main.addMessage("I lost them almost instantly.","AI");
		}
	}

}