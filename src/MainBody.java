import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFrame;

public class MainBody extends Canvas{
	
	/* This class manages the game. It starts the game and connects the class.*/
	
	private static final long serialVersionUID = 1L;
	private static final int screenWidth = 790;
	private static final int screenHeight = 675;
	private final String TITLE = "Hopelessly Wandering";
	private BufferedImage BackGround;
	
	private MENU menu = MENU.MENU;		// What menu the player is looking at

	private RoomManager rooms;			// Class that creates all the rooms
	private ItemManager items;			// Class that creates all the items
	private MonsterManager mon;			// Class that creates all the monsters
	private Text text;					// Class that manages the what the AI says
	private Reponses rep;				// Responses to players commands
	private Render ren;					// Class that renders window
	
	private int hunger = 100, thirst = 100, health = 100;
	private boolean inCombat = false; 	// If the player is in combat with a beast
	private boolean GameEnding [] = {false, false, false, false};  	// Conditions to end the game
	private boolean LockedDoors [] = {false, false, false};			// If the 3 doors are unlocked
	
	private Room room;					// Current location of the player
	private Room pastRoom;				// Where the player just came from
	
	private LinkedList<Message> mess = new LinkedList<Message>();	// Messages on the screen
	private String Statement = "";		// Player's command
	
	public enum MENU{
		MENU, GAME, GAME_OVER
	};
	
	// Initializes all the variables in the game
	public void Init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();		// Class to load images
		try {
			BackGround = loader.loadImage("/Scroll Small.png");		// Load background
		} catch (IOException e) {e.printStackTrace();}
		
		text = new Text(this);
		ren = new Render(screenHeight, screenWidth);
		items = ItemManager.getInstance();
		mon = MonsterManager.getInstance(this, text);
		rooms = RoomManager.getInstance(items, mon);
		rep = new Reponses(this, text, rooms);
		this.addKeyListener(new KeyInput(this, mon));				// Keyboard input
		this.addMouseListener(new MouseInput(this, text, screenHeight, screenWidth)); // Mouse input
		
		room = rooms.x2y3;				// Set starting location
		
		render(); render(); render();	// Render screen
	}
	
	// Status of player
	public void Status(){
		if (hunger < 25){				// Player is hungry
	   		addMessage("You are very hungery. Try to eat something.","AI");
		}
		if (thirst < 25){				// Player is thirsty
	   		addMessage("You are very thirsty. Try to drink something.","AI");
		}
		if (health <= 50){				// Player is in pain
	   		addMessage("You are feeling very weak. Try using some first aid supplies on yourself.", "AI");
		}
		
		if (health <= 5 && (thirst < 25) || (hunger < 25)) { 		// Player will die if they move
	   		addMessage("You feel incredibly weak. If you take one more step you might collapse.", "AI");
		}else if (health <= 15 && (thirst < 25) && (hunger < 25)) { // Player will die if they move
	   		addMessage("You feel incredibly weak. If you take one more step you might collapse.", "AI");
		}
	}
	
	// Reduces hunger, thirst, and health and checks if the player has died
	private void Dead() {
		hunger -= 5;
		thirst -= 5;
		if ((thirst < 25) && (hunger < 25)){			// Player is very weak
			health -= 15;
		}else if ((thirst < 25) || (hunger < 25)){		// Player is kinda weak
			health -= 5;
		}
		if (health <= 0){								// Player is dead
			addMessage("Overcome by your weakness you succumb.", "AI");
	   		addMessage("You have died", "AI");
	   		menu = MENU.GAME_OVER;
		}
	}
	
	// Gets response to player's command
	public void GetResponse(String statement){	
		statement = statement.trim().toLowerCase();		// Removes spaces and converts to lower case
		
		if ((findKeyword(statement, "north") >= 0) || (findKeyword(statement, "south") >= 0) || 
			(findKeyword(statement, "east") >= 0)  || (findKeyword(statement, "west") >= 0)  ||
			(findKeyword(statement, "up") >= 0)    || (findKeyword(statement, "down") >= 0)  ){
			if (rep.move(statement)) {			// Checks if the player can move in that direction
				Dead();							// Reduces player status and checks if dead 
			}									// Could not move to new location
			mon.location();						// Checks if here is a monster at the new location
			if (!inCombat) {					// There is not a monster
				text.LocationText(room);		// Display text about the new location
				text.ItemText();				// Display items that are in the new location
				Status();						// Display status of player
			}
		}else if (findKeyword(statement, "check bag") >= 0){	// Check bag command
			rep.checkbag();
		}else if (findKeyword(statement, "take") >= 0){			// Pick up item command
			rep.takeitem(statement);
		}else if (findKeyword(statement, "drop") >= 0){			// Drop item command
			rep.dropitem(statement);
		}else if (findKeyword(statement, "use") >= 0){			// Use item command
			rep.useitem(statement);
			// Checks if you just won the game
			if (GameEnding[0] && GameEnding[1] && GameEnding[2] && GameEnding[3]){
		   		addMessage("","AI");
				addMessage("Congratulations. You've completed the game.", "AI");
				addMessage("You've escaped the island.", "AI");
			}
		}else if (findKeyword(statement, "examine") >= 0){		// Examine item command
			rep.examineitem(statement);
		}else if (findKeyword(statement, "diagnose") >= 0){		// Check status of the player command
			rep.diagnose();
		}else if (findKeyword(statement, "help") >= 0){			// Help command
			rep.help();
		}else{													// Invalid command
			addMessage("What are you trying to say?", "AI");
		}
		addMessage("","AI");
	}
 	
	// Returns the ID of the strongest item the player has
	public String StrongestWeapon() {
		String weapon = "fists";
		for (Item temp: rooms.player.getItems()) {				// Goes through all items in bag
			if (temp.getID() == "pocket knife" && !weapon.equals("wrench")){ // Has pocket knife
				weapon = "pocket knife";
			}else if (temp.getID() == "wrench"){				// Has wrench
				weapon = "wrench";
			}else if (temp.getID() == "sword"){					// Has sword
				return "sword";
			}					
		}
		return weapon;
	}

	// Returns is a phrase has a specific word in it
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
	
	// Renders the screen
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();			// Type of rendering
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();						// Type of graphics
		g.setFont(new Font("arial", Font.BOLD,20));				// Font
		g.clearRect(0, 0, screenWidth, screenHeight);						// Clears screen
		g.drawImage(BackGround, 0, 0, null);					// Draws background
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		if (menu == MENU.MENU){									// In the main menu
			ren.MENU(g);										// Renders the main Menu
		}else {
			ren.INGAME(g,mess,Statement);						// Renders game content
			if(menu == MENU.GAME_OVER){							// If the game is over
				ren.GAME_OVER(g);								// Render game over content
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}	
		
	// Add message to be displayed
	public void addMessage(String message, String from){
		mess.add(new Message(message, from, 0));				// Add message to be displayed
		//System.out.println(message);
		for(int i = mess.size() - 1; i >= 0; i--){				// For each message on screen
			mess.get(i).setRow(mess.get(i).getRow()+1);			// Move messages up the screen
			if (mess.get(i).getRow() >= (screenHeight-25)/25){		// If message is off screen
				mess.remove(i);									// Stop displaying message
			}
		}
		render();												// Render screen
	}
		
	// Add an item to player's inventory
	public void additem(String item){
		rep.takeitem("take " + item);
	}
	
	public static void main(String[] args) {
    	MainBody main = new MainBody();
    	// Set up Screen
    	main.setPreferredSize(new Dimension(screenWidth, screenHeight));
    	main.setMaximumSize(new Dimension(screenWidth, screenHeight));
    	main.setMinimumSize(new Dimension(screenWidth, screenHeight));
    	main.setBackground(Color.BLACK);
		JFrame frame = new JFrame(main.TITLE);
		frame.add(main);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
   		main.Init();				// Start Game
	}
	
	public MENU getMenu() {
		return menu;
	}
	public void setMenu(MENU menu) {
		this.menu = menu;
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
	public void setGameEnding(boolean gameEnding, int i) {
		this.GameEnding[i] = gameEnding;
	}
	public boolean[] getLockedDoors() {
		return LockedDoors;
	}
	public void setLockedDoors(boolean lockedDoors, int i) {
		this.LockedDoors[i] = lockedDoors;
	}
	public String getStatement() {
		return Statement;
	}
	public void setStatement(String statement) {
		Statement = statement;
	}
	public boolean isInCombat() {
		return inCombat;
	}
	public void setInCombat(boolean inCombat) {
		this.inCombat = inCombat;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Room getPastRoom() {
		return pastRoom;
	}

	public void setPastRoom(Room pastRoom) {
		this.pastRoom = pastRoom;
	}
	
}