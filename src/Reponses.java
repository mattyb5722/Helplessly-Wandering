import java.util.Random;

public class Reponses {

	MainBody main;
	Text t;
	Monster mon;
	Items items;
	Rooms rooms;
	Random r = new Random();
	
	public Reponses(MainBody main, Text t, Monster mon, Items items, Rooms rooms){
		this.main = main;
		this.t= t;
		this.mon = mon;
		this.items = items;
		this.rooms = rooms;
	}
	
	public void move(String statement){
		Room old = main.room;
		int luck = 1;
		if (main.room.getID().substring(0,3) == "Maze") {
			luck = r.nextInt(4);
		}
		if (luck != 3){	
			if (main.findKeyword(statement, "north") >= 0){
				main.room = main.room.getNorth();
			}else if (main.findKeyword(statement, "south") >= 0){
				main.room = main.room.getSouth();
			}else if (main.findKeyword(statement, "east") >= 0){
				main.room = main.room.getEast();
			}else if (main.findKeyword(statement, "west") >= 0){
				main.room = main.room.getWest();
			}else if (main.findKeyword(statement, "up") >= 0){
				main.room = main.room.getUp();
			}else if (main.findKeyword(statement, "down") >= 0){
				if (old.getID() == "House Attic") {
					main.addMessage("As you descend the ladder you slip off and fall for a while.", "AI");
					main.addMessage("After hitting the ground you open your eyes to a completely different room.", "AI");
					main.setHealth(main.getHealth()-25);
				}
				main.room = main.room.getDown();
			}
			
			if (main.room.getID() == "water") {
				main.room = old;
				main.addMessage("There's no where to swim to", "AI");
			}else if (main.room.getID() == "mountain") {
				main.room = old;
				main.addMessage("There's a mountain in the way", "AI");
			}else if (main.room.getID() == "wall") {
				main.room = old;
				main.addMessage("There's a wall there", "AI");
			}else if (main.room.getID() == "forest") {
				main.room = old;
				main.addMessage("The woods are too dense to pass through", "AI");
			}else if (main.room.getID() == "nothing") {
				main.room = old;
				main.addMessage("There's nowhere to go", "AI");
			}else {
				main.pastRoom = old;
				for (Item temp: main.getBag()) {
					if (temp != null) {
						temp.setLocation(main.room);
						/*
						temp.getOutside().x = main.getOutside().x;
						temp.getOutside().y = main.getOutside().y;
						temp.getOutside().z = main.getOutside().z;
						
						temp.getInside().x = main.getInside().x;
						temp.getInside().y = main.getInside().y;
						temp.getInside().z = main.getInside().z;
						*/
					}
				}
			}
		}	
	}
	
	public void checkbag(){
		boolean checking = true;
		main.addMessage("Inventory:", "AI");
		for (Item temp: main.getBag()) {
			if (temp != null){
				main.addMessage("   " + temp.getID(), "AI");
				checking = false;
			}
		}
		if (checking){
			main.addMessage("You have nothing in your inventory", "AI");
		}
	}
	
	public void takeitem(String statement){
		statement = (statement.substring(5, statement.length())).trim();
			boolean taking = true;
			boolean location = false;
			boolean alreadyhad = true;
			
			for (Item temp: items.getItems()) {
				if((temp.isOnPlayer() == false) && temp.getLocation().getID() == main.room.getID()) {
					/*
				if ((main.findKeyword(statement, temp.getID()) >= 0) 
				 && (temp.getOutside().x == main.getOutside().x)  && (temp.getOutside().y == main.getOutside().y)
				 && (temp.getOutside().z == main.getOutside().z)  && (temp.getInside().x == main.getInside().x)
				 && (temp.getInside().y == main.getInside().y)    && (temp.getInside().z == main.getInside().z)){
				 */
					location = true;
					if (temp.isOnPlayer() == false){
						alreadyhad = false;
						for(int i = 0; i < main.getBag().length; i++){
							if ((taking) && (main.getBag()[i] == null)){
								main.setBag(temp, i);
								temp.setOnPlayer(true);
								temp.setPlace("onself");
								main.addMessage(temp.getID() + " has been added to your bag", "AI");
								taking = false;
							}
						}
					}
				}
			}if(location == false){
				main.addMessage("You can't find anything like that", "AI");
			}else if(alreadyhad == true){
				main.addMessage("You already picked that item up", "AI");
			}else if (taking == true){
				main.addMessage("There's no room in your bag", "AI");
			}
			
	}
	
	public void dropitem(String statement){
		statement = (statement.substring(5, statement.length())).trim();
		for (int i = 0; i < main.getBag().length; i++){
			Item temp = main.getBag()[i];
			if (temp != null){
				if (main.findKeyword(statement, temp.getID()) >= 0){
					temp.setOnPlayer(false);
					temp.setPlace("onground");
					/*
					for (int j = 0; j < main.getItems().length; j++){
						if (main.findKeyword(statement, main.getItems()[j][0]) >= 0){
							main.setItems("false", j, 7);
							main.setItems("onground", j, 8);
						}
					}
					*/
					main.setBag(null, i);
					main.addMessage("You've dropped the " + statement, "AI");
				}
			}
		}
	}
	
	public void useitem(String statement){
		statement = (statement.substring(4, statement.length())).trim();
		boolean abletouse = false;
		boolean have = false;
		for (int i = 0; i < main.getBag().length; i++){
			Item temp = main.getBag()[i];
			if (temp != null){
				if (main.findKeyword(statement, temp.getID()) >= 0){
					have = true;
					if (main.room.getID() == rooms.boatBoat.getID()){
						if(main.findKeyword(temp.getID(), "engine") >= 0){
					   		main.addMessage("The engine fits right onto the boat.","AI");
							main.setGameEnding(true, 0);
							abletouse = true;
						}else if(main.findKeyword(temp.getID(), "gas") >= 0){
					   		main.addMessage("You pour the full container of gasoline into the boat.","AI");
							main.setGameEnding(true, 1);
							abletouse = true;
						}else if(main.findKeyword(temp.getID(), "boat key") >= 0){
					   		main.addMessage("The key fits right into the ignition of the boat.","AI");
							main.setGameEnding(true, 2);
							abletouse = true;
						}else if(main.findKeyword(temp.getID(), "rudder") >= 0){
					   		main.addMessage("The rudder fits right onto the boat.","AI");
							main.setGameEnding(true, 3);
							abletouse = true;
						}
					//Keys
					}else if(main.findKeyword(temp.getID(), "house key") >= 0){
						if (main.room.getID() == rooms.x3y4.getID()){
							main.setLockedDoors(true, 0);
					   		main.addMessage("You unlock the door.","AI");
					   		abletouse = true;
						}
					}else if(main.findKeyword(temp.getID(), "shack key") >= 0){
						if (main.room.getID() == rooms.x1y6.getID()){
							main.setLockedDoors(true, 1);
					   		main.addMessage("You unlock the door.","AI");
					   		abletouse = true;
						}
					}else if(main.findKeyword(temp.getID(), "treehouse key") >= 0){
						if (main.room.getID() == rooms.treeHouseGround.getID()){
							main.setLockedDoors(true, 2);
					   		main.addMessage("You unlock the hach.","AI");
					   		abletouse = true;
						}					
					//Food
					}else if(main.findKeyword(temp.getID(), "sandwich") >= 0){
						main.setHunger(main.getHunger()+50);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "chips") >= 0){
						main.setHunger(main.getHunger()+25);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "candybar") >= 0){
						main.setHunger(main.getHunger()+15);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "peanuts") >= 0){
						main.setHunger(main.getHunger()+25);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "pretzels") >= 0){
						main.setHunger(main.getHunger()+25);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "blueberries") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "raspberries") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "strawberries") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "blackberries") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "pomegranate") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					//Drinks
					}else if(main.findKeyword(temp.getID(), "water bottle") >= 0){
						main.setThirst(main.getThirst()+50);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "bottle of beer") >= 0){
						main.setThirst(main.getThirst()+15);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "bottle of wine") >= 0){
						main.setThirst(main.getThirst()+15);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "sodacan") >= 0){
						main.setThirst(main.getThirst()+25);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "juicebox") >= 0){
						main.setThirst(main.getThirst()+25);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "coffee") >= 0){
						main.setThirst(main.getThirst()+15);
						abletouse = true;
					//Texts
					}else if((main.findKeyword(temp.getID(), "newspaper") >= 0)||(main.findKeyword(temp.getID(), "note") >= 0)
						   ||(main.findKeyword(temp.getID(), "journal") >= 0)||(main.findKeyword(temp.getID(), "notebook") >= 0)
						   ||(main.findKeyword(temp.getID(), "flight course") >= 0)||(main.findKeyword(temp.getID(), "maze notebook") >= 0)){
						t.text_text(temp.getID());
						abletouse = true;
					}//First Aid
					else if(main.findKeyword(temp.getID(), "band aids") >= 0){
						main.setHealth(main.getHealth()+10);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "wrap") >= 0){
						main.setHealth(main.getHealth()+20);
						abletouse = true;
					}else if(main.findKeyword(temp.getID(), "first aid kit") >= 0){
						main.setHealth(main.getHealth()+50);
						abletouse = true;
					}
				}		
			}	
			if (abletouse) {
				main.addMessage("You have used " + temp.getID(), "AI");
				temp.setDurability(temp.getDurability() - 1 );
				if (temp.getDurability() == 0) {
					//temp.getOutside().x = -1;
					temp.setLocation(rooms.mountain);
					temp.setOnPlayer(false);					
					main.setBag(null, i);
				}
				if(main.getThirst() > 125){
					main.setThirst(125);
				}
				if(main.getHunger() > 125){
					main.setHunger(125);
				}
				if(main.getHealth() > 100){
					main.setHealth(100);
				}
				return;
			}
		}
		/*
		if(abletouse){
			for (int j = 0; j < main.getItems().length; j++){
				if (main.findKeyword(statement, main.getItems()[j][0]) >= 0){
					main.setItems(Integer.toString(Integer.parseInt(main.getItems()[j][9])-1), j, 9);
					main.addMessage("You have used " + statement, "AI");
					if(Integer.parseInt(main.getItems()[j][9]) == 0){		
						main.setItems("false", j, 7);
						main.setItems("-1", j, 1);
						for (int k = 0; k < main.getBag().length; k++){
							if (main.getBag()[k] != null){
								if (main.findKeyword(statement, main.getBag()[k]) >= 0){
										main.setBag(null, k);
								}
							}
						}
					}
				}
			}
		}
		*/
		if (!have){
	   		main.addMessage("You don't have that in your inventory.","AI");
		}else if(!abletouse){
	   		main.addMessage("You can't use that here.","AI");
		}	
	}
	
	public void examineitem(String statement){
		statement = (statement.substring(7, statement.length())).trim();
		boolean have = false;
		//for (int i = 0; i < main.getBag().length; i++){
		for (Item temp: main.getBag()) {
			if (temp != null){
				if (main.findKeyword(statement, temp.getID()) >= 0){
					have = true;
					if(main.findKeyword(temp.getID(), "engine") >= 0){
				   		main.addMessage("It's an engine for a boat.","AI");
					}else if(main.findKeyword(temp.getID(), "gas") >= 0){
				   		main.addMessage("It's a container full of fuel.","AI");
					}else if(main.findKeyword(temp.getID(), "boat key") >= 0){
				   		main.addMessage("It's a key labeled boat.","AI");
					}else if(main.findKeyword(temp.getID(), "rudder") >= 0){
				   		main.addMessage("It's a rudder for a boat.","AI");
				   	//ETC	
					}else if(main.findKeyword(temp.getID(), "map") >= 0){
					   	main.addMessage("It's a map of the island.","AI");
					}else if(main.findKeyword(temp.getID(), "pocketknife") >= 0){
					   	main.addMessage("It's a standard pocketknife with scissors, a srewdriver and a knife.","AI");
					}else if(main.findKeyword(temp.getID(), "wrench") >= 0){
						main.addMessage("It's a wrench that looks like it could do a lot of damage if swung hard.","AI");	
					}else if(main.findKeyword(temp.getID(), "sword") >= 0){
						main.addMessage("It's a sword that looks like it's killed it's number of monsters.","AI");	
				   	//Keys
					}else if(main.findKeyword(temp.getID(), "house key") >= 0){
					   	main.addMessage("It's a key labeled house.","AI");
					}else if(main.findKeyword(temp.getID(), "shack key") >= 0){
					   	main.addMessage("It's a key labeled shack.","AI");
					}else if(main.findKeyword(temp.getID(), "treehouse key") >= 0){
					   	main.addMessage("It's a key labeled treehouse.","AI");
					//Food
					}else if(main.findKeyword(temp.getID(), "sandwich") >= 0){
					   	main.addMessage("It's a sandwhich that looks like it can fill you up a lot.","AI");
					}else if(main.findKeyword(temp.getID(), "chips") >= 0){
					   	main.addMessage("It's a bag of chips that looks like it can fill you up a medium amount.","AI");
					}else if(main.findKeyword(temp.getID(), "candybar") >= 0){
					   	main.addMessage("It's a candybar that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(temp.getID(), "peanuts") >= 0){
					   	main.addMessage("It's a bag od chips that looks like it can fill you up a medium amount.","AI");
					}else if(main.findKeyword(temp.getID(), "pretzels") >= 0){
					   	main.addMessage("It's a pretzel that looks like it can fill you up a medium amount.","AI");
					}else if(main.findKeyword(temp.getID(), "blueberries") >= 0){
					   	main.addMessage("It's a handfull of blueberries that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(temp.getID(), "raspberries") >= 0){
					   	main.addMessage("It's a handfull of raspberries that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(temp.getID(), "strawberries") >= 0){
					   	main.addMessage("It's a handfull of strawberries that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(temp.getID(), "blackberries") >= 0){
					   	main.addMessage("It's a handfull of blackberries that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(temp.getID(), "pomegranate") >= 0){
					   	main.addMessage("It's a handfull of pomegranate that looks like it can fill you up a little.","AI");
					//Drinks
					}else if(main.findKeyword(temp.getID(), "water bottle") >= 0){
					   	main.addMessage("It's a water bottle that looks like it can quench your thirst a lot.","AI");
					}else if(main.findKeyword(temp.getID(), "bottle of beer") >= 0){
					   	main.addMessage("It's a bottle of beer that looks like it can quench your thirst a little.","AI");
					}else if(main.findKeyword(temp.getID(), "bottle of wine") >= 0){
					   	main.addMessage("It's a bottle of beer that looks like it can quench your thirst a little.","AI");
					}else if(main.findKeyword(temp.getID(), "sodacan") >= 0){
					   	main.addMessage("It's a sodae that looks like it can quench your thirst a medium amount.","AI");
					}else if(main.findKeyword(temp.getID(), "juicebox") >= 0){
					   	main.addMessage("It's a juicebox that looks like it can quench your thirst a medium amount.","AI");
					}else if(main.findKeyword(temp.getID(), "coffee") >= 0){
					   	main.addMessage("It's a cup of coffie that looks like it can quench your thirst a little.","AI");
					//Texts
					}else if((main.findKeyword(temp.getID(), "newspaper") >= 0)||(main.findKeyword(temp.getID(), "note") >= 0)
							   ||(main.findKeyword(temp.getID(), "journal") >= 0)||(main.findKeyword(temp.getID(), "notebook") >= 0)
							   ||(main.findKeyword(temp.getID(), "flight course") >= 0)||(main.findKeyword(temp.getID(), "maze notebook") >= 0)){
							t.text_text(temp.getID());					
					}//First Aid
					else if(main.findKeyword(temp.getID(), "band aids") >= 0){
					   	main.addMessage("It's a band-aid that looks like it can heal a little.","AI");
					}else if(main.findKeyword(temp.getID(), "wrap") >= 0){
					   	main.addMessage("It's a wrap that looks like it can heal a medium amount.","AI");
					}else if(main.findKeyword(temp.getID(), "first aid kit") >= 0){
					   	main.addMessage("It's a wrap that looks like it can heal a lot.","AI");
					}	
				}
			}
		}if (!have){
	   		main.addMessage("You don't have that in your inventory.","AI");
		}
	}
	
	public void diagnose(){
		main.addMessage("Hunger: " + main.getHunger(), "AI");
		main.addMessage("Thirst: " + main.getThirst(), "AI");
		main.addMessage("Health: " + main.getHealth(), "AI");
	}

	public void help(){
		main.addMessage("Commands:", "AI");
		main.addMessage("   North          -  to move north", "AI");
		main.addMessage("   South          -  to move south", "AI");
		main.addMessage("   East           -  to move east", "AI");
		main.addMessage("   West           -  to move west", "AI");
		main.addMessage("   Up             -  to ascend", "AI");
		main.addMessage("   Down           -  to descend", "AI");
		main.addMessage("   Check Bag      -  to check inventory", "AI");
		main.addMessage("   Take ______    -  to add _____ to your inventory", "AI");
		main.addMessage("   Drop ______    -  to drop _____ from your inventory", "AI");
		main.addMessage("   Use ______     -  to use _____ from your inventory", "AI");
		main.addMessage("   Examine ______ -  to examine _____ from your inventory", "AI");
		main.addMessage("   Diagnose       -  to check hunger, thirst, and health levels", "AI");
	}	

}