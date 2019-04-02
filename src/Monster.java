
public class Monster {

	MainBody main;
	Text text;
	
	public Monster(MainBody main,Text text){
		this.main = main;
		this.text = text;
	}
	
	public void location(){
		if ((main.room.getID() == "x0y4") && (main.getBeasts()[0] == true)){
				main.addMessage("In the middle of the beach is a cougar.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "x0y6") && (main.getBeasts()[1] == true)){
				main.addMessage("In the middle of the beach is a wolf.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "x1y3") && (main.getBeasts()[2] == true)){
				main.addMessage("In the middle of the clearing is a boar.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "x2y0") && (main.getBeasts()[3] == true)){
				main.addMessage("In the middle of the beach is a leopard seal.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "x2y6") && (main.getBeasts()[4] == true)){
				main.addMessage("In the middle of the clearing is a coyote.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "tree") && (main.getBeasts()[5] == true)){
				main.addMessage("As you climb you spot a vicious monkey above you.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "x4y4") && (main.getBeasts()[6] == true)){
				main.addMessage("In the middle of the clearing is a bear.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "Maze x0y0") && (main.getBeasts()[7] == true)){
				main.addMessage("In the middle of the room is a Giant Rat.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "Maze x1y3") && (main.getBeasts()[8] == true)){
				main.addMessage("In the middle of the room is a Giant Spider.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}else if ((main.room.getID() == "Maze x2y1") && (main.getBeasts()[9] == true)){
				main.addMessage("In the middle of the room is a Giant Scorpion.", "AI");
				main.setPhase(main.getPhase().COMBAT);
			}
		if(main.getPhase() == main.getPhase().COMBAT){
			main.addMessage("Do you want to fight or run?", "AI");
		}
	}
	
	public void combat(String Statement){
		Statement = Statement.trim().toLowerCase();
		if (main.findKeyword(Statement, "fight")>=0){
			boolean dead = false;
			boolean haspocketknife = false;
			boolean haswrench = false;
			boolean hassword = false;
			for (Item temp: main.getBag()) {
				if (temp != null){
					if (temp.getID() == "pocket knife"){
						haspocketknife = true;
					}else if (temp.getID() == "wrench"){
						haswrench = true;
					}else if (temp.getID() == "sword"){
						hassword = true;
					}					
				}
			}
			if (main.room.getID() == "x0y4"){
				if (hassword){
					main.addMessage("You killed the cougar.", "AI");
					main.setBeasts(false,0);dead = true;	
				}else if (haswrench){
					main.setHealth(main.getHealth()-15);
				}else if (haspocketknife){
					main.setHealth(main.getHealth()-25);
				}else{
					main.setHealth(main.getHealth()-50);
				}
			}else if (main.room.getID() == "x0y6"){
				if (haswrench||hassword){
					main.addMessage("You killed the wolf.", "AI");
					main.setBeasts(false,1);dead = true;	
				}else if (haspocketknife){
					main.setHealth(main.getHealth()-15);
				}else{
					main.setHealth(main.getHealth()-25);
				}
			}else if (main.room.getID() == "x1y3"){
				if (haspocketknife||haswrench||hassword){
					main.addMessage("You killed the boar.", "AI");
					main.setBeasts(false,2);dead = true;	
				}else{
					main.setHealth(main.getHealth()-15);
				}
			}else if (main.room.getID() == "x2y0"){
				if (hassword){
					main.addMessage("You killed the leadard seal.", "AI");
					main.setBeasts(false,3);dead = true;	
				}else if (haswrench){
					main.setHealth(main.getHealth()-15);
				}else if (haspocketknife){
					main.setHealth(main.getHealth()-25);
				}else{
					main.setHealth(main.getHealth()-50);
				}
			}else if (main.room.getID() == "x2y6"){
				if (haspocketknife||haswrench||hassword){
					main.addMessage("You killed the coyote.", "AI");
					main.setBeasts(false,4);dead = true;	
				}else{
					main.setHealth(main.getHealth()-15);
				}
			}else if (main.room.getID() == "tree"){
				if (haspocketknife||haswrench||hassword){
					main.addMessage("You killed the vicious monkey.", "AI");
					main.setBeasts(false,5);dead = true;	
				}else{
					main.setHealth(main.getHealth()-15);
				}
			}else if (main.room.getID() == "x4y4"){
				if (hassword){
					main.addMessage("You killed the bear.", "AI");
					main.setBeasts(false,6);dead = true;	
				}else if (haswrench){
					main.setHealth(main.getHealth()-15);
				}else if (haspocketknife){
					main.setHealth(main.getHealth()-25);
				}else{
					main.setHealth(main.getHealth()-50);
				}
			}
			else if (main.room.getID() == "Maze x0y0"){
				if (haswrench||hassword){
					main.addMessage("You killed the Giant Rat.", "AI");
					main.setBeasts(false,7);dead = true;	
				}else if (haspocketknife){
					main.setHealth(main.getHealth()-15);
				}else{
					main.setHealth(main.getHealth()-25);
				}
			}else if (main.room.getID() == "Maze x1y3"){
				if (haswrench||hassword){
					main.addMessage("You killed the Giant Spider.", "AI");
					main.setBeasts(false,8);dead = true;	
				}else if (haspocketknife){
					main.setHealth(main.getHealth()-15);
				}else{
					main.setHealth(main.getHealth()-25);
				}
			}else if (main.room.getID() == "Maze x2y1"){
				if (haswrench||hassword){
					main.addMessage("You killed the Giant Scorpion.", "AI");
					main.setBeasts(false,9);dead = true;	
				}else if (haspocketknife){
					main.setHealth(main.getHealth()-15);
				}else{
					main.setHealth(main.getHealth()-25);
				}
			}			
			if(!dead){
				main.addMessage("You try to fight the beast, but you don't have the right equipment to fight it.", "AI");
				if(main.getHealth() <= 0){
					main.addMessage("The beast is just too strong. It overpowers you and rips you apart.", "AI");
			   		main.addMessage("You have died","AI");
			   		main.setMenu(main.getMenu().GAME_OVER);
					System.exit(1);
				}
				main.addMessage("You take a nasty scratch across the chest causing you to run in fear.", "AI");
				main.room = main.pastRoom;
			}
			main.setPhase(main.getPhase().MOVING);
			text.location_text();
		}else if (main.findKeyword(Statement, "run")>=0){
			main.addMessage("You ran away.", "AI");
			main.room = main.pastRoom;
			main.setPhase(main.getPhase().MOVING);
			text.location_text();
		}else{
			main.addMessage("What are you trying to say?", "AI");
		}main.addMessage("","AI");
	}	

}