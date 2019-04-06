
public class ItemManager {
	
	/* This class creates and contains all the items in the game. */
	
	private static ItemManager instance = null;		// Instance of class

	public Item engine, gas, boatKey, rudder;
	
	public Item map, pocketKnife, wrench, sword;
	
	public Item houseKey, shackKey, treeHouseKey;
	
	public Item sandwich, chips, candyBar, peanuts, pretzel, blueberries, 
	strawberries, raspberries, blackberries, pomegranate;

	public Item waterBottle, beerBottle, wineBottle, sodaCan, juiceBox, coffee;
	
	public Item newspaper, kidnote, journal, noteBook, flightCourse, mazeNotebook;

	public Item bandAids, wrap, firstaidkit;
	
	// Creates an instance of this class if not already set
	public static ItemManager getInstance() { 
        if (instance == null) { 					// If instance is not set
        	instance = new ItemManager(); 			// Create new instance
        }
        return instance; 							// Return instance to this class
    } 
	
	// Creates all the item objects
	private ItemManager() {
		// Game ending items
		engine = new Item("engine", "engine", 1);
		gas = new Item("gas", "gas", 1);
		boatKey = new Item("boat key", "key", 1);
		rudder = new Item("rudder", "rudder", 1);
		
		// Weapons and map
		map = new Item("map", "onself", -1);
		pocketKnife = new Item("pocket knife",  "onself", -1);
		wrench = new Item("wrench", "onground", -1);
		sword = new Item("sword", "onground", -1);
		
		// Keys
		houseKey = new Item("house key", "onground", 1);
		shackKey = new Item("shack key", "key", 1);
		treeHouseKey = new Item("treehouse key", "key", 1);
		
		// Food
		sandwich = new Item("sandwich", "onself", 3);
		chips = new Item("chips", "onground", 1);
		candyBar = new Item("candybar", "onground", 1);
		peanuts =  new Item("peanuts", "onground", 1);
		pretzel = new Item("pretzels", "onground", 1);
		blueberries = new Item("blueberries", "berry", 1);
		strawberries = new Item("strawberries", "berry", 1);
		raspberries = 	new Item("raspberries", "berry", 1);
		blackberries = new Item("blackberries", "berry", 1);
		pomegranate = new Item("pomegranate", "berry", 1);

		// Drinks
		waterBottle = new Item("water bottle",  "onself", 5);
		beerBottle = new Item("bottle of beer", "ontable", 1);
		wineBottle = new Item("bottle of wine", "ontable", 1);
		sodaCan = new Item("sodacan", "onground", 1);
		juiceBox = new Item("juicebox", "onground", 1);
		coffee = new Item("coffee", "ontable", 1);
		
		// Text
		newspaper = new Item("newspaper", "ontable", -1);
		kidnote = new Item("note", "ontable", -1);
		journal = new Item("journal", "onground", -1);
		noteBook = new Item("notebook", "ontable", -1);
		flightCourse = new Item("flight course", "ontable", -1);	
		mazeNotebook = new Item("maze notebook", "ontable", -1);	
		
		// Medical supplies
		bandAids = new Item("band aids", "onground", 5);
		wrap = new Item("wrap", "ontable", 2);
		firstaidkit = new Item("first aid kit", "ontable", 1);
		
		setDescription();		// Set description of all items
		SetUseText();			// Set text displayed when used for all items
		SetUseRequirement();	// Set the location requirement for certain items
		SetUseResult();			// Set stats gained from using certain item
	}	
	
	// Set stats gained from using certain item
	private void SetUseResult() {
		sandwich.setUseResult(new int []{50, 0, 0});
		chips.setUseResult(new int []{25, 0, 0});
		candyBar.setUseResult(new int []{15, 0, 0});
		peanuts.setUseResult(new int []{25, 0, 0});
		pretzel.setUseResult(new int []{25, 0, 0});
		blueberries.setUseResult(new int []{15, 10, 0});
		strawberries.setUseResult(new int []{15, 10, 0});
		raspberries.setUseResult(new int []{15, 10, 0});
		blackberries.setUseResult(new int []{15, 10, 0});
		pomegranate.setUseResult(new int []{15, 10, 0});
		
		waterBottle.setUseResult(new int []{0, 50, 0});
		beerBottle.setUseResult(new int []{0, 15, 0});
		wineBottle.setUseResult(new int []{0, 15, 0});
		sodaCan.setUseResult(new int []{0, 25, 0});
		juiceBox.setUseResult(new int []{0, 25, 0});
		coffee.setUseResult(new int []{0, 15, 0});
				
		bandAids.setUseResult(new int []{0, 0, 10});
		wrap.setUseResult(new int []{0, 0, 20});
		firstaidkit.setUseResult(new int []{0, 0, 50});
	}
	
	// Set the location requirement for certain items
	private void SetUseRequirement() {
		engine.setUseRequirement("Boat Boat");
		gas.setUseRequirement("Boat Boat");
		boatKey.setUseRequirement("Boat Boat");
		rudder.setUseRequirement("Boat Boat");
		
		houseKey.setUseRequirement("x3y4");
		shackKey.setUseRequirement("x1y6");
		treeHouseKey.setUseRequirement("Tree House Ground");
	}
	
	// Set text displayed when used for all items
	private void SetUseText() {
		engine.setUseText("The engine fits right onto the boat.");
		gas.setUseText("You pour the full container of gasoline into the boat.");
		boatKey.setUseText("The key fits right into the ignition of the boat.");
		rudder.setUseText("The rudder fits right onto the boat.");
		
		map.setUseText("");
		pocketKnife.setUseText("There is nothing to use it on.");
		wrench.setUseText("There is nothing to use it on.");
		sword.setUseText("There is nothing to use it on.");
		
		houseKey.setUseText("You unlock the door.");
		shackKey.setUseText("You unlock the door.");
		treeHouseKey.setUseText("You unlock the door.");
		
		sandwich.setUseText("You feel a lot less hungry.");
		chips.setUseText("You feel less hungry.");
		candyBar.setUseText("You feel a little less hungry.");
		peanuts.setUseText("You feel less hungry.");
		pretzel.setUseText("You feel less hungry.");
		blueberries.setUseText("You feel a little less hungry.");
		strawberries.setUseText("You feel a little less hungry.");
		raspberries.setUseText("You feel a little less hungry.");
		blackberries.setUseText("You feel a little less hungry.");
		pomegranate.setUseText("You feel a little less hungry.");
		
		waterBottle.setUseText("You feel a lot less thirst.");
		beerBottle.setUseText("You feel a little less thirst.");
		wineBottle.setUseText("You feel a little less thirst.");
		sodaCan.setUseText("You feel less thirst.");
		juiceBox.setUseText("You feel less thirst.");
		coffee.setUseText("You feel a little less thirst.");
		
		newspaper.setUseText("");
		kidnote.setUseText("");
		journal.setUseText("");
		noteBook.setUseText("");
		flightCourse.setUseText("");
		mazeNotebook.setUseText("");
		
		bandAids.setUseText("You feel a little better.");
		wrap.setUseText("You feel better.");
		firstaidkit.setUseText("You feel a lot better.");
	}
	
	// Set description of all items
	private void setDescription() {
		engine.setDescription("It's an engine for a boat.");
		gas.setDescription("It's a container full of fuel.");
		boatKey.setDescription("It's a key labeled boat.");
		rudder.setDescription("It's a rudder for a boat.");
		
		map.setDescription("It's a map of the island.");
		pocketKnife.setDescription("It's a standard pocketknife with scissors, a srewdriver and a knife.");
		wrench.setDescription("It's a wrench that looks like it could do a lot of damage if swung hard.");
		sword.setDescription("It's a sword that looks like it's killed it's number of monsters.");
		
		houseKey.setDescription("It's a key labeled house.");
		shackKey.setDescription("It's a key labeled shack.");
		treeHouseKey.setDescription("It's a key labeled treehouse.");
		
		sandwich.setDescription("It's a sandwhich that looks like it can fill you up a lot.");
		chips.setDescription("It's a bag of chips that looks like it can fill you up a medium amount.");
		candyBar.setDescription("It's a candybar that looks like it can fill you up a little.");
		peanuts.setDescription("It's a bag of peanuts that looks like it can fill you up a medium amount.");
		pretzel.setDescription("It's a pretzel that looks like it can fill you up a medium amount.");
		blueberries.setDescription("It's a handfull of blueberries that looks like it can fill you up a little.");
		strawberries.setDescription("It's a handfull of strawberries that looks like it can fill you up a little.");
		raspberries.setDescription("It's a handfull of raspberries that looks like it can fill you up a little.");
		blackberries.setDescription("It's a handfull of blackberries that looks like it can fill you up a little.");
		pomegranate.setDescription("It's a handfull of pomegranate that looks like it can fill you up a little.");
		
		waterBottle.setDescription("It's a water bottle that looks like it can quench your thirst a lot.");
		beerBottle.setDescription("It's a bottle of beer that looks like it can quench your thirst a little.");
		wineBottle.setDescription("It's a bottle of wine that looks like it can quench your thirst a little.");
		sodaCan.setDescription("It's a soda that looks like it can quench your thirst a medium amount.");
		juiceBox.setDescription("It's a juicebox that looks like it can quench your thirst a medium amount.");
		coffee.setDescription("It's a cup of coffie that looks like it can quench your thirst a little.");
		
		newspaper.setDescription("");
		kidnote.setDescription("");
		journal.setDescription("");
		noteBook.setDescription("");
		flightCourse.setDescription("");
		mazeNotebook.setDescription("");
		
		bandAids.setDescription("It's a band-aid that looks like it can heal a little.");
		wrap.setDescription("It's a wrap that looks like it can heal a medium amount.");
		firstaidkit.setDescription("It's a wrap that looks like it can heal a lot.");
	}
}
