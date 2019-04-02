import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JFrame;

public class MainBody extends Canvas{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 790;
	public static final int HEIGHT = 675;
	public final String TITLE = "Hopelessly Wandering";
	private BufferedImage BackGround;
	
	private boolean GameEnding [] = {false,false,false,false};
	private boolean LockedDoors [] = {false,false,false};
	//private boolean LockedDoors [] = {true,true,true};
	private boolean Beasts [] = {true,true,true,true,true,true,true,true,true,true};

	Rooms rooms;
	Items items;
	Text text;
	Reponses rep;
	Render ren;
	Monster mon;
	Random r = new Random();

	private MENU menu = MENU.MENU;
	private PHASE Phase = PHASE.MOVING;
	
	private Location outside = new Location(2, 3, 0);
	private Location inside = new Location(0, 0, 0);
	
	public Room room;
	public Room pastRoom;

	private int hunger = 100, thirst = 100, health = 100;

	private Item [] bag = new Item [10];

	/*
	private String[] Engine =	{"engine",	"1", "0", "0", "0", "0", "0", "false", "engine", "1"};
	private String[] Gas =		{"gas",		"1", "0", "0", "0", "0", "0", "false", "gas", 	 "1"};
	private String[] BoatKey =	{"boat key","1", "0", "0", "0", "0", "0", "false", "key",	 "1"};
	private String[] Rudder =	{"rudder",	"1", "0", "0", "0", "0", "0", "false", "rudder", "1"};
	*/
	
	private LinkedList<Message> mess = new LinkedList<Message>();
	private String Statement = "";
	
	public enum MENU{
		MENU,GAME,GAME_OVER
	};
	
	public enum PHASE{
		MOVING,COMBAT
	};
			
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			BackGround = loader.loadImage("/Scroll Small.png");
		} catch (IOException e) {e.printStackTrace();}
		
		rooms = new Rooms();
		items = new Items();
		ren = new Render(this);
		text = new Text(this, items);
		mon = new Monster(this,text);
		rep = new Reponses(this, text, mon, items);
		this.addKeyListener(new KeyInput(this,mon));
		this.addMouseListener(new MouseInput(this,text));
		
		room = rooms.getX2Y3();
		
		bag[0] = items.getItem("pocket knife");
		bag[1] = items.getItem("sandwich");
		bag[2] = items.getItem("water bottle");
		
		render();
		render();
		render();
	}
	
	public void dying(){
		hunger -= 5;
		thirst -= 5;
		if(thirst < 25){
	   		addMessage("You are very thirsty. Try to drink something.","AI");
		}if(hunger < 25){
	   		addMessage("You are very hungery. Try to eat something.","AI");
		}
		if((thirst < 25) && (hunger < 25)){
			health -= 25;
		}else if ((thirst < 25)||(hunger < 25)){
			health -=10;
		}
		if((health <= 50) && (health > 0)){
	   		addMessage("You are feeling very weak. Try using some first aid supplies on yourself.","AI");
		}else if(health <= 0){
	   		addMessage("You have died","AI");
	   		menu = MENU.GAME_OVER;
			System.exit(1);
		}
	}
		
	public void getResponse(String statement){	
		
		statement = statement.trim().toLowerCase();
		if ((findKeyword(statement, "north") >= 0) || (findKeyword(statement, "south") >= 0) || 
			(findKeyword(statement, "east") >= 0)  || (findKeyword(statement, "west") >= 0)  ||
			(findKeyword(statement, "up") >= 0)    || (findKeyword(statement, "down") >= 0)){
			rep.move(statement);
			//FindLocation();
			text.location_text();
			text.item_text();
			//move.itemsmoving();
			dying();
			mon.location();
		}else if ((statement.indexOf("check bag") >= 0)){
			rep.checkbag();
		}else if (findKeyword(statement, "take") >= 0){
			rep.takeitem(statement);
		}else if (findKeyword(statement, "drop") >= 0){
			rep.dropitem(statement);
		}else if (findKeyword(statement, "use") >= 0){
			rep.useitem(statement);
			if((GameEnding[0] == true) && (GameEnding[1] == true) && 
			   (GameEnding[2] == true) && (GameEnding[3] == true)){
		   		addMessage("","AI");
				addMessage("Congratulations. You've completed the game.", "AI");
				addMessage("You've escaped the island.", "AI");
			}
		}else if (findKeyword(statement, "examine") >= 0){
			rep.examineitem(statement);
		}else if (findKeyword(statement, "diagnose") >= 0){
			rep.diagnose();
		}else if (findKeyword(statement, "help") >= 0){
			rep.help();
		}else{
			addMessage("What are you trying to say?", "AI");
		}addMessage("","AI");
		
		System.out.println("room.getID(): " + room.getID());
		if (room.getNorth() == null) {
			System.out.println("No North");
		}
		if (room.getSouth() == null) {
			System.out.println("No South");
		}
		if (room.getEast() == null) {
			System.out.println("No East");
		}
		if (room.getWest() == null) {
			System.out.println("No West");
		}
		System.out.println();
	}
 		
	public int findKeyword(String phrase, String goal){
		if (phrase != null){
			int psn = phrase.indexOf(goal, 0);
			while (psn >= 0){
				String before = " ", after = " ";
				if (psn > 0){
					before = phrase.substring(psn - 1, psn);
				}if (psn + goal.length() < phrase.length()){
					after = phrase.substring(
							psn + goal.length(),
							psn + goal.length() + 1);
				}if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))){
					return psn;
				}
				psn = phrase.indexOf(goal, psn + 1);
			}
		}
		return -1;
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Font fnt0 = new Font("arial", Font.BOLD,20);
		g.setFont(fnt0);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(BackGround, 0, 0, null);
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		 if(menu == MENU.MENU){
			 ren.MENU(g);
		 }else if(menu == MENU.GAME){
			ren.INGAME(g,mess,Statement);	
	    }else if(menu == MENU.GAME_OVER){
			 ren.GAME_OVER(g);
		 }
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}	
			
	public void addMessage(String message, String from){
		addEntity(new Message(message,from,0));
		//System.out.println(message);
		for(int i = mess.size()-1; i >= 0; i--){
			mess.get(i).setRow(mess.get(i).getRow()+1);
			if(mess.get(i).getRow() >= (HEIGHT-25)/25){
				mess.remove(i);
			}
		}
		render();
	}
		    	    	
	public void additemwithoutdamage(String item){
		rep.takeitem("take " + item);
	}
	
	public static void main(String[] args) {
    	MainBody main = new MainBody();
    	main.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	main.setMaximumSize(new Dimension(WIDTH, HEIGHT));
    	main.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    	main.setBackground(Color.BLACK);
		JFrame frame = new JFrame(main.TITLE);
		frame.add(main);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
   		main.init();
	}
	
	public void addEntity(Message block){
		mess.add(block);
	}
	public void removeEntity(Message block){
		mess.remove(block);
	} 

	public MENU getMenu() {
		return menu;
	}
	public void setMenu(MENU menu) {
		this.menu = menu;
	}
	public PHASE getPhase() {
		return Phase;
	}
	public void setPhase(PHASE phase) {
		Phase = phase;
	}
	public Item[] getBag() {
		return bag;
	}
	public void setBag(Item bag, int i) {
		this.bag[i] = bag;
	}
	public int getHunger() {
		return hunger;
	}
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	public int getThirst() {
		return thirst;
	}
	public void setThirst(int thirst) {
		this.thirst = thirst;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean[] getGameEnding() {
		return GameEnding;
	}
	public void setGameEnding(boolean gameEnding, int a) {
		this.GameEnding[a] = gameEnding;
	}
	public boolean[] getLockedDoors() {
		return LockedDoors;
	}
	public void setLockedDoors(boolean lockedDoors, int a) {
		this.LockedDoors[a] = lockedDoors;
	}
	public boolean[] getBeasts() {
		return Beasts;
	}
	public void setBeasts(boolean beasts, int a) {
		Beasts[a] = beasts;
	}
	public String getStatement() {
		return Statement;
	}
	public void setStatement(String statement) {
		Statement = statement;
	}
	public Location getOutside() {
		return outside;
	}
	public Location getInside() {
		return inside;
	}
}