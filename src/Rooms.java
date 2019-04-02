
public class Rooms {
	
	// Other
	private Room water = new Room("water");
	private Room mountain = new Room("mountain");
	private Room wall = new Room("wall");
	private Room forest = new Room("forest");
	private Room nothing = new Room("nothing");

	// Over World
	private Room x0y3 = new Room("x0y3"); private Room x0y4 = new Room("x0y4");
	private Room x0y5 = new Room("x0y5"); private Room x0y6 = new Room("x0y6");
	
	private Room x1y1 = new Room("x1y1"); private Room x1y2 = new Room("x1y2");
	private Room x1y3 = new Room("x1y3"); private Room x1y6 = new Room("x1y6");
	
	private Room x2y0 = new Room("x2y0"); private Room x2y1 = new Room("x2y1");
	private Room x2y3 = new Room("x2y3"); private Room x2y6 = new Room("x2y6");
	private Room x2y7 = new Room("x2y7");
	
	private Room x3y1 = new Room("x3y1"); private Room x3y2 = new Room("x3y2");
	private Room x3y3 = new Room("x3y3"); private Room x3y4 = new Room("x3y4");
	private Room x3y6 = new Room("x3y6"); private Room x3y7 = new Room("x3y7");
	
	private Room x4y1 = new Room("x4y1"); private Room x4y3 = new Room("x4y3");
	private Room x4y4 = new Room("x4y4"); private Room x4y5 = new Room("x4y5");
	private Room x4y6 = new Room("x4y6");
	
	private Room x5y3 = new Room("x5y3"); private Room x5y4 = new Room("x5y4");
	private Room x5y5 = new Room("x5y5");
	
	private Room tree = new Room("tree");
	
	// Boat:
	private Room boatBoat = new Room("Boat Boat");
	private Room boatLand = new Room("Boat Land");
	
	// Plane
	private Room planeCockpit = new Room("Plane Cockpit");
	private Room planePassenger = new Room("Plane Passenger");
	private Room planeStorage = new Room("Plane Sotrage");
	
	// Shack 
	private Room shackMainRoom = new Room ("Shack Main Room");
	private Room shackCellar = new Room ("Shack Cellar");
	
	// Temple
	private Room templeMainRoom = new Room("Temple Main Room");
	private Room templeWestRoom = new Room("Temple West Room");
	private Room templeEastRoom = new Room("Temple East Room");
	
	private Room MAZE_x0y0 = new Room("Maze x0y0"); private Room MAZE_x0y1 = new Room("Maze x0y1");
	private Room MAZE_x0y2 = new Room("Maze x0y2"); private Room MAZE_x0y3 = new Room("Maze x0y3");
	
	private Room MAZE_x1y0 = new Room("Maze x1y0"); private Room MAZE_START = new Room("Maze x1y1");
	private Room MAZE_x1y2 = new Room("Maze x1y2"); private Room MAZE_x1y3 = new Room("Maze x1y3");
	
	private Room MAZE_x2y0 = new Room("Maze x2y0"); private Room MAZE_x2y1 = new Room("Maze x2y1");
	private Room MAZE_x2y2 = new Room("Maze x2y2"); private Room MAZE_x2y3 = new Room("Maze x2y3");
	
	private Room MAZE_x3y0 = new Room("Maze x3y0"); private Room MAZE_x3y1 = new Room("Maze x3y1");
	private Room MAZE_x3y2 = new Room("Maze x3y2"); private Room MAZE_x3y3 = new Room("Maze x3y3");
	
	// House 
	private Room houseMainRoom = new Room("House Main Room");
	private Room houseAttic = new Room("House Attic");
	private Room houseTrapdoorRoom = new Room("House Trapdoor Room");
	private Room houseBoxRoom = new Room("House Box Room");
	
	// Tree House 
	private Room treeHouseGround = new Room("Tree House Ground");
	private Room treeHouseTreeHouse = new Room("Tree House Tree House");

	public Rooms() {
		UpdateConnections();
	}
	
	public void UpdateConnections() {
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
		templeMainRoom.setConnections(x4y1, x4y3, templeEastRoom, templeWestRoom, MAZE_START, nothing);
		templeWestRoom.setConnections(wall, wall, templeMainRoom, wall, nothing, nothing);
		templeEastRoom.setConnections(wall, wall, wall, templeMainRoom, nothing, nothing);
		
		MAZE_x0y0.setConnections(wall,      MAZE_x0y1, MAZE_x1y0,  wall, templeMainRoom, nothing);
		MAZE_x0y1.setConnections(MAZE_x0y0, MAZE_x0y2, MAZE_START, wall, templeMainRoom, nothing);
		MAZE_x0y2.setConnections(MAZE_x0y1, MAZE_x0y3, MAZE_x1y2,  wall, templeMainRoom, nothing);
		MAZE_x0y3.setConnections(MAZE_x0y2, wall,      MAZE_x1y3,  wall, templeMainRoom, nothing);
		
		MAZE_x1y0.setConnections(wall,       MAZE_START, MAZE_x2y0, MAZE_x0y0, templeMainRoom, nothing);
		MAZE_START.setConnections(MAZE_x1y0, MAZE_x1y2,  MAZE_x2y1, MAZE_x0y1, templeMainRoom, nothing);
		MAZE_x1y2.setConnections(MAZE_START, MAZE_x1y3,  MAZE_x2y2, MAZE_x0y2, templeMainRoom, nothing);
		MAZE_x1y3.setConnections(MAZE_x1y2,  wall,       MAZE_x2y3, MAZE_x0y3, templeMainRoom, nothing);
		
		MAZE_x2y0.setConnections(wall,      MAZE_x2y1, MAZE_x3y0, MAZE_x1y0,  templeMainRoom, nothing);
		MAZE_x2y1.setConnections(MAZE_x2y0, MAZE_x2y2, MAZE_x3y1, MAZE_START, templeMainRoom, nothing);
		MAZE_x2y2.setConnections(MAZE_x2y1, MAZE_x2y3, MAZE_x3y2, MAZE_x1y2,  templeMainRoom, nothing);
		MAZE_x2y3.setConnections(MAZE_x2y2, wall,      MAZE_x3y3, MAZE_x1y3,  templeMainRoom, nothing);
		
		MAZE_x3y0.setConnections(wall,      MAZE_x3y1, wall, MAZE_x2y0, templeMainRoom, nothing);
		MAZE_x3y1.setConnections(MAZE_x3y0, MAZE_x3y2, wall, MAZE_x2y1, templeMainRoom, nothing);
		MAZE_x3y2.setConnections(MAZE_x3y1, MAZE_x3y3, wall, MAZE_x2y2, templeMainRoom, nothing);
		MAZE_x3y3.setConnections(MAZE_x3y2, wall,      wall, MAZE_x2y3, templeMainRoom, nothing);
		
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

	public Room getX2Y3() {
		return x2y3;
	}
}
