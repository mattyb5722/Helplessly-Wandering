import java.util.Random;

public class Movement {

	MainBody main;
	Random r = new Random();

	public Movement(MainBody main){
		this.main = main;
	}
	
	public boolean insidemove(String statement){
		if(main.getPlace() == main.getPlace().PLANE){
			int y = main.getInTileY();
			if (main.findKeyword(statement, "north")>=0){
				y -= 1;
				if(y < 0){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()-1);
					return false;
				}else{
					return true;
				}
			}else if (main.findKeyword(statement, "south") >= 0){
				y+=1;
				if(y>2){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()+1);
					return false;
				}else{
					return true;
				}
			}else if (main.findKeyword(statement, "east") >= 0){
				main.setInsides(main.getInsides().NONE);
				main.setTileX(main.getTileX()+1);
				return false;
			}else if (main.findKeyword(statement, "west") >= 0){
				main.setInsides(main.getInsides().NONE);
				main.setTileX(main.getTileX()-1);
				return false;
			}else if (main.findKeyword(statement, "up") >= 0){
				main.addMessage("There's nothing to climb", "AI");
				return false;
			}else if (main.findKeyword(statement, "down") >= 0){
				main.addMessage("There's nothing to climb", "AI");
				return false;
			}
		}else if(main.getPlace() == main.getPlace().HOUSE){
			int x = main.getInTileX();
			if (main.findKeyword(statement, "north")>=0){
				if(main.getInTileX() == 1  && (main.getInTileZ() == 0)){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()-1);
					return false;
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;	
				}
			}else if (main.findKeyword(statement, "south") >= 0){
				main.addMessage("There's something in the way", "AI");
				return false;
			}else if (main.findKeyword(statement, "east") >= 0){
				x+=1;
				if((x>2)||(main.getInTileZ() == 1)){
					main.addMessage("There's something in the way", "AI");
					return false;
				}else{
					return true;
				}
			}else if (main.findKeyword(statement, "west") >= 0){
				x-=1;
				if((x<0)||(main.getInTileZ() == 1)){
					main.addMessage("There's something in the way", "AI");
					return false;
				}else{
					return true;
				}
			}else if (main.findKeyword(statement, "up") >= 0){
				if((main.getInTileX() == 1) && (main.getInTileY() == 0) && (main.getInTileZ() == 0)){
					return true;
				}else{
					main.addMessage("There's nothing to climb", "AI");
					return false;
				}
			}else if (main.findKeyword(statement, "down") >= 0){
				if((main.getInTileX() == 0) && (main.getInTileY() == 0) && (main.getInTileZ() == 0)){
					main.addMessage("As you descend the ladder you slip off and fall for a while.", "AI");
					main.addMessage("After hitting the ground you open your eyes to a completely different room.", "AI");
					main.setHealth(main.getHealth()-25);
					main.setPlace(main.getPlace().TEMPLE);
					main.setInsides(main.getInsides().MAZE_x0y3);
					main.setInTileX(0);main.setInTileY(3);main.setInTileZ(0);
					return false;
				}else if((main.getInTileX() == 1) && (main.getInTileY() == 0) && (main.getInTileZ() == 1)){
					return true;
				}else{	
					main.addMessage("There's nothing to climb", "AI");
					return false;
				}
			}
		}else if(main.getPlace() == main.getPlace().TREEHOUSE){
			if (main.findKeyword(statement, "north")>=0){
				if(main.getInTileZ() == 0){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()-1);
					return false;
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;	
				}
			}else if (main.findKeyword(statement, "south") >= 0){
				if(main.getInTileZ() == 0){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()+1);
					return false;
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;	
				}
			}else if (main.findKeyword(statement, "east") >= 0){
				if(main.getInTileZ() == 0){
					main.setInsides(main.getInsides().NONE);
					main.setTileX(main.getTileX()+1);
					return false;
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;	
				}
			}else if (main.findKeyword(statement, "west") >= 0){
				if(main.getInTileZ() == 0){
					main.setInsides(main.getInsides().NONE);
					main.setTileX(main.getTileX()-1);
					return false;
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;	
				}
			}else if (main.findKeyword(statement, "up") >= 0){
				if((main.getInTileZ() == 0)&&(main.getLockedDoors()[2] == false)){
					main.addMessage("The hatch is locked. Try using a key.", "AI");
					return false;
				}else if(main.getInTileZ() == 0){
					return true;
				}else{
					main.addMessage("There's nothing to climb", "AI");
					return false;
				}
			}else if (main.findKeyword(statement, "down") >= 0){
				if(main.getInTileZ() == 1){
					return true;
				}else{
					main.addMessage("There's nothing to climb", "AI");
					return false;
				}
			}
		}else if(main.getPlace() == main.getPlace().SHACK){
			if (main.findKeyword(statement, "north")>=0){
				if(main.getInTileZ() == 1){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()-1);
					return false;
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;	
				}
			}else if (main.findKeyword(statement, "south") >= 0){
				main.addMessage("There's something in the way", "AI");
				return false;	
			}else if (main.findKeyword(statement, "east") >= 0){
				main.addMessage("There's something in the way", "AI");
				return false;
			}else if (main.findKeyword(statement, "west") >= 0){
				main.addMessage("There's something in the way", "AI");
				return false;
			}else if (main.findKeyword(statement, "up") >= 0){
				if(main.getInTileZ() == 0){
					return true;
				}else{
					main.addMessage("There's nothing to climb", "AI");
					return false;
				}
			}else if (main.findKeyword(statement, "down") >= 0){
				if(main.getInTileZ() == 1){
					return true;
				}else{
					main.addMessage("There's nothing to climb", "AI");
					return false;
				}
			}
		}else if(main.getPlace() == main.getPlace().TEMPLE){
			int x = main.getInTileX();
			int y = main.getInTileY();
			int luck = r.nextInt(4);
			if (main.findKeyword(statement, "north")>=0){
				y -= 1;
				if(main.getInTileX() == 1 && main.getInTileY() == 0 && main.getInTileZ() == 1){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()-1);
					return false;
				}else if(main.getInTileZ() == 0){
					if(y>=0){
						if((luck==0)||(luck==1)||(luck==2)){
							return true;
						}else if(luck==3){
							return false;
						}
					}else{
						return false;
					}
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;	
				}
			}else if (main.findKeyword(statement, "south") >= 0){
				y += 1;
				if(main.getInTileX() == 1 && main.getInTileY() == 0 && main.getInTileZ() == 1){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()+1);
					return false;
				}else if(main.getInTileZ() == 0){
					if(y<=3){
						if((luck==0)||(luck==1)||(luck==2)){
							return true;
						}else if(luck==3){
							return false;
						}
					}else{
						return false;
					}
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;	
				}	
			}else if (main.findKeyword(statement, "east") >= 0){
				x += 1;
				if((x<=2)&&(main.getInTileZ() == 1)){
					return true;	
				}else if(main.getInTileZ() == 0){
					if(x<=3){
						if((luck==0)||(luck==1)||(luck==2)){
							return true;
						}else if(luck==3){
							return false;
						}
					}else{
						return false;
					}
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;
				}
			}else if (main.findKeyword(statement, "west") >= 0){
				x -= 1;
				if((x>=0)&&(main.getInTileZ() == 1)){
					return true;	
				}else if(main.getInTileZ() == 0){
					if(x>=0){
						if((luck==0)||(luck==1)||(luck==2)){
							return true;
						}else if(luck==3){
							return false;
						}
					}else{
						return false;
					}
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;
				}
			}else if (main.findKeyword(statement, "up") >= 0){
				if(main.getInTileZ() == 0){
					main.setInTileX(1);
					main.setInTileY(0);
					return true;
				}else{
					main.addMessage("There's nothing to climb", "AI");
					return false;
				}
			}else if (main.findKeyword(statement, "down") >= 0){
				if(main.getInTileX() == 1 && main.getInTileY() == 0 && main.getInTileZ() == 1){
					main.setInTileX(1);
					main.setInTileY(1);
					return true;
				}else{
					main.addMessage("There's nothing to climb", "AI");
					return false;
				}
			}
		}else if(main.getPlace() == main.getPlace().BOAT){
			int y = main.getInTileY();
			if (main.findKeyword(statement, "north")>=0){
				y -= 1;
				if(y < 0){
					main.addMessage("There's something in the way", "AI");
					return false;	
				}else{
					return true;
				}
			}else if (main.findKeyword(statement, "south") >= 0){
				y+=1;
				if(y>1){
					main.setInsides(main.getInsides().NONE);
					main.setTileY(main.getTileY()+1);
					return false;
				}else{
					return true;
				}
			}else if (main.findKeyword(statement, "east") >= 0){
				if(main.getInTileY() == 1){
					main.setInsides(main.getInsides().NONE);
					main.setTileX(main.getTileX()+1);
					return false;
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;
				}
			}else if (main.findKeyword(statement, "west") >= 0){
				if(main.getInTileY() == 1){
					main.setInsides(main.getInsides().NONE);
					main.setTileX(main.getTileX()-1);
					return false;
				}else{
					main.addMessage("There's something in the way", "AI");
					return false;
				}
			}else if (main.findKeyword(statement, "up") >= 0){
				main.addMessage("There's nothing to climb", "AI");
				return false;
			}else if (main.findKeyword(statement, "down") >= 0){
				main.addMessage("There's nothing to climb", "AI");
				return false;
			}
		}
		return false;
	}
	
	public boolean checkmove(String statement){
		int x = main.getTileX();
		int y = main.getTileY();
		if (main.findKeyword(statement, "north")>=0){
			y -= 1;
		}else if (main.findKeyword(statement, "south") >= 0){
			y+=1;
		}else if (main.findKeyword(statement, "east") >= 0){
			x+=1;
		}else if (main.findKeyword(statement, "west") >= 0){
			x-=1;
		}
		if((x == 1 && y == 4)||(x == 1 && y == 5)||(x == 2 && y == 4)||(x == 2 && y == 5)){
			main.addMessage("There's a mountain in the way", "AI");
			return false;
		}else if((x<0)||(y<0)||(x == 0 && ((y == 0)||(y == 1)||(y == 2)||(y == 7)))
				||(x == 3 && y == 0)||(x == 4 && y == 0)
				||(x == 5 && ((y == 0)||(y == 1)||(y == 2)||(y == 6)||(y == 7)))){	
			main.addMessage("There's no where to swim to", "AI");
			return false;
		}else if((main.getTileX() == 3 && main.getTileY() == 2 && x == 3 && y == 1)||(main.getTileX() == 3 && main.getTileY() == 1 && x == 3 && y == 2)){
			main.addMessage("The woods are too dense to pass through", "AI");
			return false;
		}else if(((main.getTileX() == 3)&&(main.getTileY() == 4)&&(x == 3)&&(y == 5)&&(main.getLockedDoors()[0]==false))
				||((main.getTileX() == 1)&&(main.getTileY() == 6)&&(x == 1)&&(y == 7)&&(main.getLockedDoors()[1]==false))){	
			main.addMessage("The door is locked. Try using a key.", "AI");
			return false;
		}
		else if((x == 1 && y == 0)){/**Boat*/
			main.setPlace(main.getPlace().BOAT);
			main.setInsides(main.getInsides().BOAT_LAND);
			main.setInTileX(0);
			main.setInTileY(1);
			return true;
		}else if((x == 2 && y == 2)){/**Plane*/
			main.setPlace(main.getPlace().PLANE);
				if(y > main.getTileY()){
					main.setInsides(main.getInsides().PLANE_COCKPIT);
					main.setInTileX(0);
					main.setInTileY(0);
				}else if(y < main.getTileY()){
					main.setInsides(main.getInsides().PLANE_STORAGE);
					main.setInTileX(0);
					main.setInTileY(2);
				}else if(y == main.getTileY()){
					main.setInsides(main.getInsides().PLANE_PASSENGER);
					main.setInTileX(0);
					main.setInTileY(1);
			}
			return true;	
		}else if(x == 4 && y == 2){/**Temple*/
			if((main.getTileX() == 4 && main.getTileY() == 3) || (main.getTileX() == 4 && main.getTileY() == 1)){
				main.setPlace(main.getPlace().TEMPLE);
				main.setInsides(main.getInsides().TEMPLE_MAINROOM);
				main.setInTileX(1);
				main.setInTileY(0);
				main.setInTileZ(1);
				return true;
			}
			main.addMessage("There's something in the way", "AI");
			return false;
		}else if(x == 3 && y == 5){/**House*/
			if(main.getTileX() == 3 && main.getTileY() == 4){
				main.setPlace(main.getPlace().HOUSE);
				main.setInsides(main.getInsides().HOUSE_MAINROOM);
				main.setInTileX(1) ;
				main.setInTileY(0);
				return true;
			}
			main.addMessage("There's something in the way", "AI");
			return false;
		}else if(x == 1 && y == 7){/**Shack*/
			if(main.getTileX() == 1 && main.getTileY() == 6){	
				main.setPlace(main.getPlace().SHACK);
				main.setInsides(main.getInsides().SHACK_MAINROOM);
				main.setInTileX(0);
				main.setInTileY(0);
				main.setInTileZ(1);
				return true;
			}
			main.addMessage("There's something in the way", "AI");
			return false;
		}else if((x == 4 && y == 7)){/**TreeHouse*/
			main.setPlace(main.getPlace().TREEHOUSE);
			main.setInsides(main.getInsides().TREEHOUSE_GROUND);
			main.setInTileX(0);
			main.setInTileY(0);
			return true;
		}return true;
	}

	public void itemsmoving(){
		for(int j = 0; j < main.getItems().length; j++){
			if(main.getItems()[j][7] == "true"){
				main.getItems()[j][1] = Integer.toString(main.getTileX());
				main.getItems()[j][2] = Integer.toString(main.getTileY());
				main.getItems()[j][3] = Integer.toString(main.getTileZ());
				main.getItems()[j][4] = Integer.toString(main.getInTileX());
				main.getItems()[j][5] = Integer.toString(main.getInTileY());
				main.getItems()[j][6] = Integer.toString(main.getInTileZ());
			}
		}
	}
}