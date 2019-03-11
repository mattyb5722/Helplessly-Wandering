
public class Reponses {

	MainBody main;
	Movement m;
	Text t;
	Monster mon;
	
	public Reponses(MainBody main, Movement m, Text t, Monster mon){
		this.main = main;
		this.m = m;
		this.t= t;
		this.mon = mon;
	}
	
	public void move(String statement){
		if (main.getInsides() != main.getInsides().NONE){
			if(m.insidemove(statement)==true){
				if (main.findKeyword(statement, "north")>=0){
					main.setInTileY(main.getInTileY()-1);
				}else if (main.findKeyword(statement, "south") >= 0){
					main.setInTileY(main.getInTileY()+1);
				}else if (main.findKeyword(statement, "east") >= 0){
					main.setInTileX(main.getInTileX()+1);
				}else if (main.findKeyword(statement, "west") >= 0){
					main.setInTileX(main.getInTileX()-1);
				}else if (main.findKeyword(statement, "up") >= 0){
					main.setInTileZ(main.getInTileZ()+1);
				}else if (main.findKeyword(statement, "down") >= 0){
					main.setInTileZ(main.getInTileZ()-1);
				}	
			}
		}else if (main.getInsides() == main.getInsides().NONE){
			if(m.checkmove(statement)==true){
				if (main.findKeyword(statement, "north")>=0){
					main.setTileY(main.getTileY()-1);
				}else if (main.findKeyword(statement, "south") >= 0){
					main.setTileY(main.getTileY()+1);
				}else if (main.findKeyword(statement, "east") >= 0){
					main.setTileX(main.getTileX()+1);
				}else if (main.findKeyword(statement, "west") >= 0){
					main.setTileX(main.getTileX()-1);
				}else if (main.findKeyword(statement, "up") >= 0){
					if((main.getTileX() == 3 && main.getTileY() == 2 && main.getTileZ() == 0)){
						main.setTileZ(main.getTileZ()+1);
					}else{
						main.addMessage("There's nothing to climb", "AI");
					}
				}else if (main.findKeyword(statement, "down") >= 0){
					if((main.getTileX() == 3 && main.getTileY() == 2 && main.getTileZ() == 1)){
						main.setTileZ(main.getTileZ()-1);
					}else{
						main.addMessage("There's nothing to climb", "AI");
					}
				}
			}
		}
		if (main.getInsides() == main.getInsides().NONE){
			main.setInTileX(0);
			main.setInTileY(0);
			main.setInTileZ(0);
		}
			
	}
	
	public void checkbag(){
		boolean checking = true;
		main.addMessage("Inventory:", "AI");
		for(int i = 0; i < main.getBag().length; i++){
			if (main.getBag()[i] != null){
				main.addMessage("   " + main.getBag()[i], "AI");
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
			for (int j = 0; j < main.getItems().length; j++){
				if ((main.findKeyword(statement, main.getItems()[j][0]) >= 0)
						&&(Integer.parseInt(main.getItems()[j][1]) == main.getTileX())	&&(Integer.parseInt(main.getItems()[j][2]) == main.getTileY())
						&&(Integer.parseInt(main.getItems()[j][3]) == main.getTileZ())	&&(Integer.parseInt(main.getItems()[j][4]) == main.getInTileX())
						&&(Integer.parseInt(main.getItems()[j][5]) == main.getInTileY())&&(Integer.parseInt(main.getItems()[j][6]) == main.getInTileZ())){
					location = true;
					if (main.getItems()[j][7] == "false"){
						alreadyhad = false;
						for(int i = 0; i < main.getBag().length; i++){
							if ((taking) && (main.getBag()[i] == null)){
								main.setBag(statement, i);
								main.setItems("true", j, 7);
								main.setItems("onself", j, 8);
								main.addMessage(statement + " has been added to your bag", "AI");
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
			if (main.getBag()[i] != null){
				if (main.findKeyword(statement, main.getBag()[i]) >= 0){
					for (int j = 0; j < main.getItems().length; j++){
						if (main.findKeyword(statement, main.getItems()[j][0]) >= 0){
							main.setItems("false", j, 7);
							main.setItems("onground", j, 8);
						}
					}
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
			if (main.getBag()[i] != null){
				if (main.findKeyword(statement, main.getBag()[i]) >= 0){
					have = true;
					if((main.getTileX() == 1)&&(main.getTileY() == 0)&&(main.getTileZ() == 0)){
						if(main.findKeyword(main.getBag()[i], "engine") >= 0){
					   		main.addMessage("The engine fits right onto the boat.","AI");
								main.setGameEnding(true, 0);
								abletouse = true;
						}else if(main.findKeyword(main.getBag()[i], "gas") >= 0){
					   		main.addMessage("You pour the full container of gasoline into the boat.","AI");
							main.setGameEnding(true, 1);
							abletouse = true;
						}else if(main.findKeyword(main.getBag()[i], "boat key") >= 0){
					   		main.addMessage("The key fits right into the ignition of the boat.","AI");
							main.setGameEnding(true, 2);
							abletouse = true;
						}else if(main.findKeyword(main.getBag()[i], "rudder") >= 0){
					   		main.addMessage("The rudder fits right onto the boat.","AI");
							main.setGameEnding(true, 3);
							abletouse = true;
						}
					//Keys
					}else if(main.findKeyword(main.getBag()[i], "house key") >= 0){
						if((main.getTileX() == 3)&&(main.getTileY() == 4)&&(main.getTileZ() == 0)){
							main.setLockedDoors(true, 0);
					   		main.addMessage("You unlock the door.","AI");
					   		abletouse = true;
						}
					}else if(main.findKeyword(main.getBag()[i], "shack key") >= 0){
						if((main.getTileX() == 1)&&(main.getTileY() == 7)&&(main.getTileZ() == 0)){
							main.setLockedDoors(true, 1);
					   		main.addMessage("You unlock the door.","AI");
					   		abletouse = true;
						}
					}else if(main.findKeyword(main.getBag()[i], "treehouse key") >= 0){
						if((main.getTileX() == 4)&&(main.getTileY() == 7)&&(main.getTileZ() == 0)){
							main.setLockedDoors(true, 2);
					   		main.addMessage("You unlock the hach.","AI");
					   		abletouse = true;
						}					
					//Food
					}else if(main.findKeyword(main.getBag()[i], "sandwich") >= 0){
						main.setHunger(main.getHunger()+50);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "chips") >= 0){
						main.setHunger(main.getHunger()+25);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "candybar") >= 0){
						main.setHunger(main.getHunger()+15);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "peanuts") >= 0){
						main.setHunger(main.getHunger()+25);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "pretzels") >= 0){
						main.setHunger(main.getHunger()+25);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "blueberries") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "raspberries") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "strawberries") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "blackberries") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "pomegranate") >= 0){
						main.setHunger(main.getHunger()+15);
						main.setThirst(main.getThirst()+10);
						abletouse = true;
					//Drinks
					}else if(main.findKeyword(main.getBag()[i], "water bottle") >= 0){
						main.setThirst(main.getThirst()+50);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "bottle of beer") >= 0){
						main.setThirst(main.getThirst()+15);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "bottle of wine") >= 0){
						main.setThirst(main.getThirst()+15);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "sodacan") >= 0){
						main.setThirst(main.getThirst()+25);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "juicebox") >= 0){
						main.setThirst(main.getThirst()+25);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "coffee") >= 0){
						main.setThirst(main.getThirst()+15);
						abletouse = true;
					//Texts
					}else if((main.findKeyword(main.getBag()[i], "newspaper") >= 0)||(main.findKeyword(main.getBag()[i], "note") >= 0)
						   ||(main.findKeyword(main.getBag()[i], "journal") >= 0)||(main.findKeyword(main.getBag()[i], "notebook") >= 0)
						   ||(main.findKeyword(main.getBag()[i], "flight course") >= 0)||(main.findKeyword(main.getBag()[i], "maze notebook") >= 0)){
						t.text_text(main.getBag()[i]);
						abletouse = true;
					}//First Aid
					else if(main.findKeyword(main.getBag()[i], "band aids") >= 0){
						main.setHealth(main.getHealth()+10);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "wrap") >= 0){
						main.setHealth(main.getHealth()+20);
						abletouse = true;
					}else if(main.findKeyword(main.getBag()[i], "first aid kit") >= 0){
						main.setHealth(main.getHealth()+50);
						abletouse = true;
					}
					
				}		
			}	
		}if(abletouse){
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
		if (!have){
	   		main.addMessage("You don't have that in your inventory.","AI");
		}else if(!abletouse){
	   		main.addMessage("You can't use that here.","AI");
		}if(main.getThirst()>125){
			main.setThirst(125);
		}if(main.getHunger()>125){
			main.setHunger(125);
		}if(main.getHealth()>100){
			main.setHealth(100);
		}	
	}
	
	public void examineitem(String statement){
		statement = (statement.substring(7, statement.length())).trim();
		boolean have = false;
		for (int i = 0; i < main.getBag().length; i++){
			if (main.getBag()[i] != null){
				if (main.findKeyword(statement, main.getBag()[i]) >= 0){
					have = true;
					if(main.findKeyword(main.getBag()[i], "engine") >= 0){
				   		main.addMessage("It's an engine for a boat.","AI");
					}else if(main.findKeyword(main.getBag()[i], "gas") >= 0){
				   		main.addMessage("It's a container full of fuel.","AI");
					}else if(main.findKeyword(main.getBag()[i], "boat key") >= 0){
				   		main.addMessage("It's a key labeled boat.","AI");
					}else if(main.findKeyword(main.getBag()[i], "rudder") >= 0){
				   		main.addMessage("It's a rudder for a boat.","AI");
				   	//ETC	
					}else if(main.findKeyword(main.getBag()[i], "map") >= 0){
					   	main.addMessage("It's a map of the island.","AI");
					}else if(main.findKeyword(main.getBag()[i], "pocketknife") >= 0){
					   	main.addMessage("It's a standard pocketknife with scissors, a srewdriver and a knife.","AI");
					}else if(main.findKeyword(main.getBag()[i], "wrench") >= 0){
						main.addMessage("It's a wrench that looks like it could do a lot of damage if swung hard.","AI");	
					}else if(main.findKeyword(main.getBag()[i], "sword") >= 0){
						main.addMessage("It's a sword that looks like it's killed it's number of monsters.","AI");	
				   	//Keys
					}else if(main.findKeyword(main.getBag()[i], "house key") >= 0){
					   	main.addMessage("It's a key labeled house.","AI");
					}else if(main.findKeyword(main.getBag()[i], "shack key") >= 0){
					   	main.addMessage("It's a key labeled shack.","AI");
					}else if(main.findKeyword(main.getBag()[i], "treehouse key") >= 0){
					   	main.addMessage("It's a key labeled treehouse.","AI");
					//Food
					}else if(main.findKeyword(main.getBag()[i], "sandwich") >= 0){
					   	main.addMessage("It's a sandwhich that looks like it can fill you up a lot.","AI");
					}else if(main.findKeyword(main.getBag()[i], "chips") >= 0){
					   	main.addMessage("It's a bag of chips that looks like it can fill you up a medium amount.","AI");
					}else if(main.findKeyword(main.getBag()[i], "candybar") >= 0){
					   	main.addMessage("It's a candybar that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(main.getBag()[i], "peanuts") >= 0){
					   	main.addMessage("It's a bag od chips that looks like it can fill you up a medium amount.","AI");
					}else if(main.findKeyword(main.getBag()[i], "pretzels") >= 0){
					   	main.addMessage("It's a pretzel that looks like it can fill you up a medium amount.","AI");
					}else if(main.findKeyword(main.getBag()[i], "blueberries") >= 0){
					   	main.addMessage("It's a handfull of blueberries that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(main.getBag()[i], "raspberries") >= 0){
					   	main.addMessage("It's a handfull of raspberries that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(main.getBag()[i], "strawberries") >= 0){
					   	main.addMessage("It's a handfull of strawberries that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(main.getBag()[i], "blackberries") >= 0){
					   	main.addMessage("It's a handfull of blackberries that looks like it can fill you up a little.","AI");
					}else if(main.findKeyword(main.getBag()[i], "pomegranate") >= 0){
					   	main.addMessage("It's a handfull of pomegranate that looks like it can fill you up a little.","AI");
					//Drinks
					}else if(main.findKeyword(main.getBag()[i], "water bottle") >= 0){
					   	main.addMessage("It's a water bottle that looks like it can quench your thirst a lot.","AI");
					}else if(main.findKeyword(main.getBag()[i], "bottle of beer") >= 0){
					   	main.addMessage("It's a bottle of beer that looks like it can quench your thirst a little.","AI");
					}else if(main.findKeyword(main.getBag()[i], "bottle of wine") >= 0){
					   	main.addMessage("It's a bottle of beer that looks like it can quench your thirst a little.","AI");
					}else if(main.findKeyword(main.getBag()[i], "sodacan") >= 0){
					   	main.addMessage("It's a sodae that looks like it can quench your thirst a medium amount.","AI");
					}else if(main.findKeyword(main.getBag()[i], "juicebox") >= 0){
					   	main.addMessage("It's a juicebox that looks like it can quench your thirst a medium amount.","AI");
					}else if(main.findKeyword(main.getBag()[i], "coffee") >= 0){
					   	main.addMessage("It's a cup of coffie that looks like it can quench your thirst a little.","AI");
					//Texts
					}else if((main.findKeyword(main.getBag()[i], "newspaper") >= 0)||(main.findKeyword(main.getBag()[i], "note") >= 0)
							   ||(main.findKeyword(main.getBag()[i], "journal") >= 0)||(main.findKeyword(main.getBag()[i], "notebook") >= 0)
							   ||(main.findKeyword(main.getBag()[i], "flight course") >= 0)||(main.findKeyword(main.getBag()[i], "maze notebook") >= 0)){
							t.text_text(main.getBag()[i]);					
					}//First Aid
					else if(main.findKeyword(main.getBag()[i], "band aids") >= 0){
					   	main.addMessage("It's a band-aid that looks like it can heal a little.","AI");
					}else if(main.findKeyword(main.getBag()[i], "wrap") >= 0){
					   	main.addMessage("It's a wrap that looks like it can heal a medium amount.","AI");
					}else if(main.findKeyword(main.getBag()[i], "first aid kit") >= 0){
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