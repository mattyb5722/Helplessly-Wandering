
public class MonsterManager {
	
	/* This class contains all the monsters in the game and manages combat.*/
	
	private MainBody main;
	private Text text;
	
	private static MonsterManager instance = null;		// Instance of class

	public Monster cougar = new Monster(3, "In the middle of the beach is a cougar.");
	public Monster wolf = new Monster(2, "In the middle of the beach is a wolf.");
	public Monster boar = new Monster(1, "In the middle of the clearing is a boar.");
	public Monster leopardSeal = new Monster(3, "In the middle of the beach is a leopard seal.");
	public Monster coyote = new Monster(1, "In the middle of the clearing is a coyote.");
	public Monster monkey = new Monster(1, "As you climb you spot a vicious monkey above you.");
	public Monster bear = new Monster(3, "In the middle of the clearing is a bear.");
	public Monster giantRat = new Monster(2, "In the middle of the room is a Giant Rat.");
	public Monster giantSpider = new Monster(2, "In the middle of the room is a Giant Spider.");
	public Monster giantScorpion = new Monster(2, "In the middle of the room is a Giant Scorpion.");
		
	// Creates an instance of this class if not already set
	public static MonsterManager getInstance(MainBody main, Text text) { 
        if (instance == null) { 						// If instance is not set
        	instance = new MonsterManager(main, text); 	// Create new instance
        }
        return instance; 								// Return instance to this class
    } 
	
	private MonsterManager(MainBody main, Text text) {
		this.main = main;
		this.text = text;
	}
	
	// Detects if there is a monster in the same location as the player
	public void location(){
		if (main.getRoom().getMonster() != null) {		// There is a monster
			main.addMessage(main.getRoom().getMonster().getDescription(), "AI"); // Display description
			main.setInCombat(true);						// Play is now in combat
			main.addMessage("Do you want to fight or run?", "AI");
		}
	}
	
	// Handles combat
	public void combat(String Statement) {
		if (main.findKeyword(Statement, "fight") >= 0){	// Player wants to fight
			Monster monster = main.getRoom().getMonster();
			int playerStrength = 0;
			String weapon = main.StrongestWeapon();
			if (weapon.equals("pocket knife")) {		// Player only has the pocket knife
				playerStrength = 1;
			}else if (weapon.equals("wrench")) {		// Player only has the wrench
				playerStrength = 2;
			}else if (weapon.equals("sword")) {			// Plater has the sword
				playerStrength = 3;
			}
			if (playerStrength >= monster.getStrength()) {	// Player beats the beast
				main.addMessage("You kill the beast.", "AI");
				main.getRoom().setMonster(null);			// Get rid of the beast
				text.LocationText(main.getRoom());			// Displays new location text
				text.ItemText();							// Displays items in that area
				main.Status();								// Displays the status of the player
			}else {											// Player lost to the beast
				int lostHealth = 15 * (monster.getStrength() - playerStrength); 
				main.setHealth(main.getHealth() - lostHealth);	// Player losses health connected to their equipment
				main.addMessage("You try to fight the beast, but you don't have the right equipment to fight it.", "AI");
				if(main.getHealth() <= 0){					// Player is dead
					main.addMessage("The beast is too strong. It overpowers you and rips to shreds.", "AI");
			   		main.addMessage("You have died","AI");
			   		main.setMenu(main.getMenu().GAME_OVER);
				}else {										// Player is not dead
					main.addMessage("You take a nasty scratch across the chest causing you to run in fear.", "AI");
					main.setRoom(main.getPastRoom());		// Player goes back to the last room
					text.LocationText(main.getRoom());		// Displays items in that area
					text.ItemText();						// Displays items in that area
					main.Status();							// Displays the status of the player
				}
			}
			main.setInCombat(false);						// Not in combat anymore
		}else if (main.findKeyword(Statement, "run") >= 0){ // Player runs
			main.addMessage("You run away.", "AI");
			main.setRoom(main.getPastRoom());				// Player goes back to the last room
			main.setInCombat(false);						// Not in combat anymore
			text.LocationText(main.getRoom());				// Displays items in that area
			text.ItemText();								// Displays items in that area
			main.Status();									// Displays the status of the player
		}else{												// Invalid command
			main.addMessage("What are you trying to say?", "AI");
			main.addMessage(main.getRoom().getMonster().getDescription(), "AI");
			main.addMessage("Do you want to fight or run?", "AI");
		}
		main.addMessage("","AI");
	}
}