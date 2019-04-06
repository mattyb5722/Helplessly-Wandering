
public class RoomManager {
	
	/* This class contains all the rooms in the game. */
	
	private static RoomManager instance = null;		// Instance of class
	
	// Other
	public Room water = new Room("water"); 	public Room mountain = new Room("mountain");
	public Room wall = new Room("wall"); 	public Room forest = new Room("forest");
	public Room nothing = new Room("nothing");
	
	public Room player = new Room("player");

	// Over World
	public Room x0y3 = new Room("x0y3"); public Room x0y4 = new Room("x0y4");
	public Room x0y5 = new Room("x0y5"); public Room x0y6 = new Room("x0y6");
	
	public Room x1y1 = new Room("x1y1"); public Room x1y2 = new Room("x1y2");
	public Room x1y3 = new Room("x1y3"); public Room x1y6 = new Room("x1y6");
	
	public Room x2y0 = new Room("x2y0"); public Room x2y1 = new Room("x2y1");
	public Room x2y3 = new Room("x2y3"); public Room x2y6 = new Room("x2y6");
	public Room x2y7 = new Room("x2y7");
	
	public Room x3y1 = new Room("x3y1"); public Room x3y2 = new Room("x3y2");
	public Room x3y3 = new Room("x3y3"); public Room x3y4 = new Room("x3y4");
	public Room x3y6 = new Room("x3y6"); public Room x3y7 = new Room("x3y7");
	
	public Room x4y1 = new Room("x4y1"); public Room x4y3 = new Room("x4y3");
	public Room x4y4 = new Room("x4y4"); public Room x4y5 = new Room("x4y5");
	public Room x4y6 = new Room("x4y6");
	
	public Room x5y3 = new Room("x5y3"); public Room x5y4 = new Room("x5y4");
	public Room x5y5 = new Room("x5y5");
	
	public Room tree = new Room("tree");
	
	// Boat:
	public Room boatBoat = new Room("Boat Boat");
	public Room boatLand = new Room("Boat Land");
	
	// Plane
	public Room planeCockpit = new Room("Plane Cockpit");
	public Room planePassenger = new Room("Plane Passenger");
	public Room planeStorage = new Room("Plane Sotrage");
	
	// Shack 
	public Room shackMainRoom = new Room ("Shack Main Room");
	public Room shackCellar = new Room ("Shack Cellar");
	
	// Temple
	public Room templeMainRoom = new Room("Temple Main Room");
	public Room templeWestRoom = new Room("Temple West Room");
	public Room templeEastRoom = new Room("Temple East Room");
	
	public Room MAZE_x0y0 = new Room("Maze x0y0"); public Room MAZE_x0y1 = new Room("Maze x0y1");
	public Room MAZE_x0y2 = new Room("Maze x0y2"); public Room MAZE_x0y3 = new Room("Maze x0y3");
	
	public Room MAZE_x1y0 = new Room("Maze x1y0"); public Room MAZE_START = new Room("Maze x1y1");
	public Room MAZE_x1y2 = new Room("Maze x1y2"); public Room MAZE_x1y3 = new Room("Maze x1y3");
	
	public Room MAZE_x2y0 = new Room("Maze x2y0"); public Room MAZE_x2y1 = new Room("Maze x2y1");
	public Room MAZE_x2y2 = new Room("Maze x2y2"); public Room MAZE_x2y3 = new Room("Maze x2y3");
	
	public Room MAZE_x3y0 = new Room("Maze x3y0"); public Room MAZE_x3y1 = new Room("Maze x3y1");
	public Room MAZE_x3y2 = new Room("Maze x3y2"); public Room MAZE_x3y3 = new Room("Maze x3y3");
	
	// House 
	public Room houseMainRoom = new Room("House Main Room");
	public Room houseAttic = new Room("House Attic");
	public Room houseTrapdoorRoom = new Room("House Trapdoor Room");
	public Room houseBoxRoom = new Room("House Box Room");
	
	// Tree House 
	public Room treeHouseGround = new Room("Tree House Ground");
	public Room treeHouseTreeHouse = new Room("Tree House Tree House");

	// Creates an instance of this class if not already set
	public static RoomManager getInstance(ItemManager items, MonsterManager mon) { 
        if (instance == null) { 					// If instance is not set
        	instance = new RoomManager(items, mon); // Create new instance
        }
        return instance; 							// Return instance to this class
    } 
	
	private RoomManager(ItemManager items, MonsterManager mon) {
		AddConnections();							// Connects rooms
		AddItems(items);							// Adds items to rooms
		AddDescription();							// Set description of rooms
		AddMonster(mon);							// Adds monsters to room
	}
	
	// Adds items to rooms
	private void AddItems(ItemManager items) {
		shackCellar.addItem(items.engine);
		houseAttic.addItem(items.gas);
		houseBoxRoom.addItem(items.boatKey);
		treeHouseTreeHouse.addItem(items.rudder);
		
		tree.addItem(items.map);
		player.addItem(items.pocketKnife);
		planeStorage.addItem(items.wrench);
		MAZE_x3y0.addItem(items.sword);
		x0y5.addItem(items.houseKey);		
		
		houseBoxRoom.addItem(items.shackKey);
		templeEastRoom.addItem(items.treeHouseKey);
		
		player.addItem(items.sandwich);
		houseMainRoom.addItem(items.chips);
		planePassenger.addItem(items.candyBar);
		templeWestRoom.addItem(items.peanuts);
		planeStorage.addItem(items.pretzel);
		x3y1.addItem(items.blueberries);
		x3y3.addItem(items.strawberries);
		x3y4.addItem(items.raspberries);
		x4y3.addItem(items.blackberries);
		x4y4.addItem(items.pomegranate);
		
		player.addItem(items.waterBottle);
		houseAttic.addItem(items.beerBottle);
		houseMainRoom.addItem(items.wineBottle);
		templeEastRoom.addItem(items.sodaCan);
		planePassenger.addItem(items.juiceBox);
		shackMainRoom.addItem(items.coffee);
		
		planeCockpit.addItem(items.newspaper);
		planePassenger.addItem(items.kidnote);
		houseAttic.addItem(items.journal);
		shackMainRoom.addItem(items.noteBook);
		planeCockpit.addItem(items.flightCourse);
		templeWestRoom.addItem(items.mazeNotebook);
		
		planeStorage.addItem(items.bandAids);
		treeHouseTreeHouse.addItem(items.wrap);
		MAZE_x2y3.addItem(items.firstaidkit);
	}
	
	// Connects rooms
	private void AddConnections() {
		// Plane
		planeCockpit.setConnections(x2y1, planePassenger, x3y2, x1y2, nothing, nothing);
		planePassenger.setConnections(planeCockpit, planeStorage, x3y2, x1y2, nothing, nothing);
		planeStorage.setConnections(planePassenger, x2y3, x3y2, x1y2, nothing, nothing);
		
		// Boat
		boatBoat.setConnections(water, boatLand, water, water, nothing, nothing);
		boatLand.setConnections(boatBoat, x1y1,  x2y0, water, nothing, nothing);
		
		// Shack
		shackMainRoom.setConnections(x1y6, wall, wall, wall, nothing, shackCellar);
		shackCellar.setConnections(wall, wall, wall, wall, shackMainRoom, nothing);
		
		// Tree House
		treeHouseGround.setConnections(x4y6, x3y7, water, wall, treeHouseTreeHouse, nothing);
		treeHouseTreeHouse.setConnections(wall, wall, wall, wall, nothing, treeHouseGround);
				
		// House
		houseMainRoom.setConnections(x3y4, wall, houseBoxRoom, houseTrapdoorRoom, houseAttic, nothing);
		houseAttic.setConnections(wall, wall, wall, wall, nothing, houseMainRoom);
		houseTrapdoorRoom.setConnections(wall, wall, houseMainRoom, wall, nothing, MAZE_x0y3);
		houseBoxRoom.setConnections(wall, wall, wall, houseMainRoom, nothing, nothing);
		
		// Temple
		templeMainRoom.setConnections(x4y1, x4y3, templeEastRoom, templeWestRoom, nothing, MAZE_START);
		templeWestRoom.setConnections(wall, wall, templeMainRoom, wall, nothing, nothing);
		templeEastRoom.setConnections(wall, wall, wall, templeMainRoom, nothing, nothing);
		
		MAZE_x0y0.setConnections(MAZE_x0y0, MAZE_x0y1, MAZE_x1y0,  MAZE_x0y0, templeMainRoom, nothing);
		MAZE_x0y1.setConnections(MAZE_x0y0, MAZE_x0y2, MAZE_START, MAZE_x0y1, templeMainRoom, nothing);
		MAZE_x0y2.setConnections(MAZE_x0y1, MAZE_x0y3, MAZE_x1y2,  MAZE_x0y2, templeMainRoom, nothing);
		MAZE_x0y3.setConnections(MAZE_x0y2, MAZE_x0y3, MAZE_x1y3,  MAZE_x0y3, templeMainRoom, nothing);
		
		MAZE_x1y0.setConnections(MAZE_x1y0,  MAZE_START, MAZE_x2y0, MAZE_x0y0, templeMainRoom, nothing);
		MAZE_START.setConnections(MAZE_x1y0, MAZE_x1y2,  MAZE_x2y1, MAZE_x0y1, templeMainRoom, nothing);
		MAZE_x1y2.setConnections(MAZE_START, MAZE_x1y3,  MAZE_x2y2, MAZE_x0y2, templeMainRoom, nothing);
		MAZE_x1y3.setConnections(MAZE_x1y2,  MAZE_x1y3,  MAZE_x2y3, MAZE_x0y3, templeMainRoom, nothing);
		
		MAZE_x2y0.setConnections(MAZE_x2y0, MAZE_x2y1, MAZE_x3y0, MAZE_x1y0,  templeMainRoom, nothing);
		MAZE_x2y1.setConnections(MAZE_x2y0, MAZE_x2y2, MAZE_x3y1, MAZE_START, templeMainRoom, nothing);
		MAZE_x2y2.setConnections(MAZE_x2y1, MAZE_x2y3, MAZE_x3y2, MAZE_x1y2,  templeMainRoom, nothing);
		MAZE_x2y3.setConnections(MAZE_x2y2, MAZE_x2y3, MAZE_x3y3, MAZE_x1y3,  templeMainRoom, nothing);
		
		MAZE_x3y0.setConnections(wall,      MAZE_x3y1, MAZE_x3y0, MAZE_x2y0, templeMainRoom, nothing);
		MAZE_x3y1.setConnections(MAZE_x3y0, MAZE_x3y2, MAZE_x3y1, MAZE_x2y1, templeMainRoom, nothing);
		MAZE_x3y2.setConnections(MAZE_x3y1, MAZE_x3y3, MAZE_x3y2, MAZE_x2y2, templeMainRoom, nothing);
		MAZE_x3y3.setConnections(MAZE_x3y2, MAZE_x3y3, MAZE_x3y3, MAZE_x2y3, templeMainRoom, nothing);
		
		// Over World
		x0y3.setConnections(water, x0y4,  x1y3,  	water, nothing, nothing);
		x0y4.setConnections(x0y3,  x0y5,  mountain, water, nothing, nothing);
		x0y5.setConnections(x0y4,  x0y6,  mountain, water, nothing, nothing);
		x0y6.setConnections(x0y5,  water, x1y6,  	water, nothing, nothing);
		
		x1y1.setConnections(boatLand, x1y2,  		 x2y1,  		 water, nothing, nothing);
		x1y2.setConnections(x1y1,  	  x1y3,  		 planePassenger, water, nothing, nothing);
		x1y3.setConnections(x1y2,     mountain, 	 x2y3,  		 x0y3,  nothing, nothing);
		x1y6.setConnections(mountain, shackMainRoom, x2y6, 		 	 x0y6,  nothing, nothing);
		
		x2y0.setConnections(water, 		  x2y1,  		water, boatLand, nothing, nothing);
		x2y1.setConnections(x2y0,  		  planeCockpit, x3y2,  x1y1, 	 nothing, nothing);
		x2y3.setConnections(planeStorage, mountain, 	x3y3,  x1y3, 	 nothing, nothing);
		x2y6.setConnections(mountain, 	  x2y7,  		x3y6,  x1y6, 	 nothing, nothing);
		x2y7.setConnections(x2y6,  		  water, 		x3y7,  wall, 	 nothing, nothing);
		
		x3y1.setConnections(water,  forest,  	   x4y1,  			x2y1, 		 	nothing, nothing);
		x3y2.setConnections(forest, x3y3,  		   wall,  			planePassenger, tree, 	 nothing);
		x3y3.setConnections(x3y2,   x3y4,  		   x4y3,  			x2y3, 		 	nothing, nothing);
		x3y4.setConnections(x3y3,   houseMainRoom, x4y4,  			mountain, 	 	nothing, nothing);
		x3y6.setConnections(wall,   x3y7,  		   x4y6,  			x2y6, 		 	nothing, nothing);
		x3y7.setConnections(x3y6,   water, 		   treeHouseGround, x2y7, 			nothing, nothing);
		
		x4y1.setConnections(water, 			templeMainRoom,  water, x3y1, nothing, nothing);
		x4y3.setConnections(templeMainRoom, x4y4, 			 x5y3,  x3y3, nothing, nothing);
		x4y4.setConnections(x4y3,  			x4y5, 			 x5y4,  x3y4, nothing, nothing);
		x4y5.setConnections(x4y4,  			x4y6,  			 x5y5,  wall, nothing, nothing);
		x4y6.setConnections(x4y5,  			treeHouseGround, water, x3y6, nothing, nothing);
		
		x5y3.setConnections(water, x5y4,  water, x4y3, nothing, nothing);
		x5y4.setConnections(x5y3,  x5y5,  water, x4y4, nothing, nothing);
		x5y5.setConnections(x5y4,  water, water, x4y5, nothing, nothing);
		
		tree.setConnections(nothing, nothing, nothing, nothing, nothing, x3y2);
	}
	
	// Set description of rooms
	private void AddDescription() {
		
		// Plane
		planeCockpit.addDescription("To the east is a beach. To the west is forst.");
		planeCockpit.addDescription("You find yourself in the cockpit of the plane.");
		planeCockpit.addDescription("To the south looks like the passenger compartment.");
		
		planePassenger.addDescription("You find yourself in the passenger compartment of the plane.");
		planePassenger.addDescription("To the north looks like the cockpit.");
		planePassenger.addDescription("To the south looks like the storage compartment.");

		planeStorage.addDescription("To the east is a beach. To the west is forst.");
		planeStorage.addDescription("You find yourself in the storage compartment of the plane.");
		planeStorage.addDescription("To the north looks like the passenger compartment.");

		// Boat
		boatBoat.addDescription("You are surrounded by water except for the island south of you.");
		boatBoat.addDescription("You find yourself on a boat.");

		boatLand.addDescription("You find yourself on the beach.");
		boatLand.addDescription("To the west is the ocean. To the north is a boat.");

		// Shack
		shackMainRoom.addDescription("You find yourself in the main room of the shack.");
		shackMainRoom.addDescription("To the north is the door out. In the corner is a ladder going downward");
		shackMainRoom.addDescription("The door to go out is to the north.");

		shackCellar.addDescription("You decend the ladder into a dark musty cellar.");

		// Tree House
		treeHouseGround.addDescription("You find yourself on the beach.");
		treeHouseGround.addDescription("To the south and east is the ocean. Above you is a tree house.");

		treeHouseTreeHouse.addDescription("You find yourself in a treehouse.");
				
		// House
		houseMainRoom.addDescription("You find yourself in the main room of the house. To the north is the door out.");
		houseMainRoom.addDescription("There is a door to the west and east and a set of stairs in the corner.");
		houseMainRoom.addDescription("The door to go out is to the north.");

		houseAttic.addDescription("You find yourself in the attic of the house.");
		houseAttic.addDescription("There is a set of stairs in the corner of the room.");

		houseTrapdoorRoom.addDescription("You find yourself in the west side room of the house.");
		houseTrapdoorRoom.addDescription("In the corner of the room is a trap door. The ladder looks dangerous.");
		houseTrapdoorRoom.addDescription("There is a door to the east.");

		houseBoxRoom.addDescription("You find yourself in the east side room of the house.");
		houseBoxRoom.addDescription("There is a door to the west.");

		// Temple
		templeMainRoom.addDescription("You find yourself in the main room of the temple.");
		templeMainRoom.addDescription("To the north and south is the way out. There is a door to the west and east.");
		templeMainRoom.addDescription("There is a decending ladder in the middle of the room");
		templeMainRoom.addDescription("The door to go out is to the north and south.");

		templeWestRoom.addDescription("You find yourself in the west side room of the temple.");
		templeWestRoom.addDescription("There is a door to the east.");

		templeEastRoom.addDescription("You find yourself in the east side room of the temple.");
		templeEastRoom.addDescription("There is a door to the west.");

		MAZE_x0y0.addDescription("Maze"); MAZE_x0y1.addDescription("Maze");
		MAZE_x0y2.addDescription("Maze"); MAZE_x0y3.addDescription("Maze");
		
		MAZE_x1y0.addDescription("Maze"); MAZE_START.addDescription("Maze");
		MAZE_x1y2.addDescription("Maze"); MAZE_x1y3.addDescription("Maze");
		
		MAZE_x2y0.addDescription("Maze"); MAZE_x2y1.addDescription("Maze");
		MAZE_x2y2.addDescription("Maze"); MAZE_x2y3.addDescription("Maze");
		
		MAZE_x3y0.addDescription("Maze"); MAZE_x3y1.addDescription("Maze");
		MAZE_x3y2.addDescription("Maze"); MAZE_x3y3.addDescription("Maze");
		
		// Over World
		x0y3.addDescription("You find yourself on the beach.");
		x0y3.addDescription("To the north and west is the ocean.");

		x0y4.addDescription("You find yourself on the beach.");
		x0y4.addDescription("To the west is the ocean.  To the east is a mountain.");

		x0y5.addDescription("You find yourself on the beach.");
		x0y5.addDescription("To the west is the ocean. To the east is a mountain.");

		x0y6.addDescription("You find yourself on the beach.");
		x0y6.addDescription("To the south and west is the ocean.");
		
		x1y1.addDescription("You find yourself on the beach.");
		x1y1.addDescription("To the west is the ocean.");

		x1y2.addDescription("You find yourself on the beach.");
		x1y2.addDescription("To the west is the ocean.");

		x1y3.addDescription("You find yourself on the dunes of the beach.");
		x1y3.addDescription("To the south is a mountain.");

		x1y6.addDescription("You find yourself on the dunes of the beach.");
		x1y6.addDescription("To the south is a shack. To the north is a mountain.");

		x2y0.addDescription("You find yourself on the beach.");
		x2y0.addDescription("To the north and east is the ocean. To the west is a boat");

		x2y1.addDescription("You find yourself on the dunes.");
		x2y1.addDescription("To the south is a plane wreck.");

		x2y3.addDescription("You find yourself in an endless field.");
		x2y3.addDescription("To the north is a plane wreck. To the south is a mountain.");

		x2y6.addDescription("You find yourself in an endless field.");
		x2y6.addDescription("To the north is a mountain.");

		x2y7.addDescription("You find yourself in on the beach.");
		x2y7.addDescription("To the south is the ocean. To the west is a shack.");
		
		x3y1.addDescription("You find yourself in the woods.");
		x3y1.addDescription("To the north is the ocean. To the south is dense forest.");

		x3y2.addDescription("You find yourself in the woods.");
		x3y2.addDescription("To the north is dense forest. To the west is a plane wreck.");
		x3y2.addDescription("To the east is a temple. There looks like there's a climbable tree nearby.");

		x3y3.addDescription("You find yourself in the woods.");

		x3y4.addDescription("You find yourself in the woods.");
		x3y4.addDescription("To the west is a mountain. To the south is a house.");

		x3y6.addDescription("You find yourself in an open field.");
		x3y6.addDescription("To the north is a house.");

		x3y7.addDescription("You find yourself on the beach.");
		x3y7.addDescription("To the south is the ocean.");

		x4y1.addDescription("You find yourself in the woods.");
		x4y1.addDescription("To the north and east is the ocean. To the south is a temple.");

		x4y3.addDescription("You find yourself in the woods.");
		x4y3.addDescription("To the north is a temple.");

		x4y4.addDescription("You find yourself in the woods.");

		x4y5.addDescription("You find yourself in an endless field.");
		x4y5.addDescription("To the west is a house.");

		x4y6.addDescription("You find yourself in an endless field.");
		x4y6.addDescription("To the east is the ocean.");

		x5y3.addDescription("You find yourself on the beach.");
		x5y3.addDescription("To the north and east is the ocean.");

		x5y4.addDescription("You find yourself on the beach.");
		x5y4.addDescription("To the east is the ocean.");

		x5y5.addDescription("You find yourself on the beach.");
		x5y5.addDescription("To the south and east is the ocean.");

		tree.addDescription("You find yourself at the top of the tallest tree on the island.");
		tree.addDescription("You can see the whole island and so you jot it down on a leaf");
	}
	
	// Adds monsters to room
	private void AddMonster(MonsterManager mon) {
		x0y4.setMonster(mon.cougar);
		x0y6.setMonster(mon.wolf);
		x1y3.setMonster(mon.boar);
		x2y0.setMonster(mon.leopardSeal);
		x2y6.setMonster(mon.coyote);
		tree.setMonster(mon.monkey);
		x4y4.setMonster(mon.bear);
		MAZE_x0y0.setMonster(mon.giantRat);
		MAZE_x1y3.setMonster(mon.giantSpider);
		MAZE_x2y1.setMonster(mon.giantScorpion);
	}
}
