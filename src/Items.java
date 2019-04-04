
public class Items {
	
	Rooms rooms;
	/*
	private Item engine = new Item("engine", 1, 7, 0, 0, 0, 0, false, "engine", 1);
	private Item gas = new Item("gas", 3, 5, 0, 1, 0, 1, false, "gas", 1);
	private Item boatKey = new Item("boat key", 3, 5, 0, 2, 0, 0, false, "key", 1);
	private Item rudder = new Item("rudder", 4, 7, 0, 0, 0, 1, false, "rudder", 1);
	
	private Item map = new Item("map", 3, 2, 1, 0, 0, 0, false, "onself", -1);
	private Item pocketKnife = new Item("pocket knife", 2, 3, 0, 0, 0, 0, true,  "onself", -1);
	private Item wrench = new Item("wrench", 2, 2, 0, 0, 2, 0, false, "onground", -1);
	private Item sword = new Item("sword", 4, 2, 0, 3, 0, 0, false, "onground", -1);
	
	private Item houseKey = new Item("house key", 0, 5, 0, 0, 0, 0, false, "onground", 1);
	private Item shackKey = new Item("shack key", 3, 5, 0, 2, 0, 0, false, "key", 1);
	private Item treeHouseKey = new Item("treehouse key", 4, 2, 0, 2, 0, 1, false, "key", 1);
	
	private Item sandwich = new Item("sandwich", 2, 3, 0, 0, 0, 0, true, "onself", 3);
	private Item chips = new Item("chips", 3, 5, 0, 1, 0, 0, false, "onground", 1);
	private Item candyBar = new Item("candybar", 2, 2, 0, 0, 1, 0, false, "onground", 1);
	private Item peanuts =  new Item("peanuts", 4, 2, 0, 0, 0, 1, false, "onground", 1);
	private Item pretzel = new Item("pretzels", 2, 2, 0, 0, 2, 0, false, "onground", 1);
	private Item blueberries = new Item("blueberries", 3, 1, 0, 0, 0, 0, false, "berry", 1);
	private Item strawberries = new Item("strawberries", 3, 3, 0, 0, 0, 0, false, "berry", 1);
	private Item raspberries = 	new Item("raspberries", 3, 4, 0, 0, 0, 0, false, "berry", 1);
	private Item blackberries = new Item("blackberries", 4, 3, 0, 0, 0, 0, false, "berry", 1);
	private Item pomegranate = new Item("pomegranate", 4, 4, 0, 0, 0, 0, false, "berry", 1);

	private Item waterBottle = new Item("water bottle", 2, 3, 0, 0, 0, 0, true,  "onself", 5);
	private Item beerBottle = new Item("bottle of beer", 4, 5, 0, 0, 0, 1, false, "ontable", 1);
	private Item wineBottle = new Item("bottle of wine", 3, 5, 0, 1, 0, 0, false, "ontable", 1);
	private Item sodaCan = new Item("sodacan", 4, 2, 0, 2, 0, 1, false, "onground", 1);
	private Item juiceBox = new Item("juicebox", 2, 2, 0, 0, 1, 0, false, "onground", 1);
	private Item coffee = new Item("coffee", 1, 7, 0, 0, 0, 1, false, "ontable", 1);
	
	private Item newspaper = new Item("newspaper", 2, 2, 0, 0, 0, 0, false, "ontable", -1);
	private Item kidnote = new Item("note", 2, 2, 0, 0, 1, 0, false, "ontable", -1);
	private Item journal = new Item("journal", 3, 5, 0, 0, 0, 1, false, "onground", -1);
	private Item noteBook = new Item("notebook", 1, 7, 0, 0, 0, 1, false, "ontable", -1);
	private Item flightCourse = new Item("flight course", 2, 2, 0, 0, 0, 0, false, "ontable", -1);	
	private Item mazeNotebook = new Item("maze notebook", 4, 2, 0, 0, 0, 1, false, "ontable", -1);	
	
	private Item bandAids = new Item("band aids", 2, 2, 0, 0, 2, 0, false, "onground", 5);
	private Item wrap = new Item("wrap", 4, 7, 0, 0, 0, 1, false, "ontable", 2);
	private Item firstaidkit = new Item("first aid kit", 4, 2, 0, 2, 3, 0, false, "ontable", 1);
	*/

	private Item engine, gas, boatKey, rudder;
	
	private Item map, pocketKnife, wrench, sword;
	
	private Item houseKey, shackKey, treeHouseKey;
	
	private Item sandwich, chips, candyBar, peanuts, pretzel, blueberries, 
	strawberries, raspberries, blackberries, pomegranate;

	private Item waterBottle, beerBottle, wineBottle, sodaCan, juiceBox, coffee;
	
	private Item newspaper, kidnote, journal, noteBook, flightCourse, mazeNotebook;

	private Item bandAids, wrap, firstaidkit;

	private Item [] items;
	
	public Items(Rooms rooms) {
		this.rooms = rooms;
		Init();
	}
	
	private void Init() {
		engine = new Item("engine", rooms.shackCellar, false, "engine", 1);
		gas = new Item("gas", rooms.houseAttic, false, "gas", 1);
		boatKey = new Item("boat key", rooms.houseBoxRoom, false, "key", 1);
		rudder = new Item("rudder", rooms.treeHouseTreeHouse, false, "rudder", 1);
		
		map = new Item("map", rooms.tree, false, "onself", -1);
		pocketKnife = new Item("pocket knife", rooms.x2y3, true,  "onself", -1);
		wrench = new Item("wrench", rooms.planeStorage, false, "onground", -1);
		sword = new Item("sword", rooms.MAZE_x3y0, false, "onground", -1);
		
		houseKey = new Item("house key", rooms.x0y5, false, "onground", 1);
		shackKey = new Item("shack key", rooms.houseBoxRoom, false, "key", 1);
		treeHouseKey = new Item("treehouse key", rooms.templeEastRoom, false, "key", 1);
		
		sandwich = new Item("sandwich", rooms.x2y3, true, "onself", 3);
		chips = new Item("chips", rooms.houseMainRoom, false, "onground", 1);
		candyBar = new Item("candybar", rooms.planePassenger, false, "onground", 1);
		peanuts =  new Item("peanuts", rooms.templeWestRoom, false, "onground", 1);
		pretzel = new Item("pretzels", rooms.planeStorage, false, "onground", 1);
		blueberries = new Item("blueberries", rooms.x3y1, false, "berry", 1);
		strawberries = new Item("strawberries", rooms.x3y3, false, "berry", 1);
		raspberries = 	new Item("raspberries", rooms.x3y4, false, "berry", 1);
		blackberries = new Item("blackberries", rooms.x4y3, false, "berry", 1);
		pomegranate = new Item("pomegranate", rooms.x4y4, false, "berry", 1);

		waterBottle = new Item("water bottle", rooms.x2y3, true,  "onself", 5);
		beerBottle = new Item("bottle of beer", rooms.houseAttic, false, "ontable", 1);
		wineBottle = new Item("bottle of wine", rooms.houseMainRoom, false, "ontable", 1);
		sodaCan = new Item("sodacan", rooms.templeEastRoom, false, "onground", 1);
		juiceBox = new Item("juicebox", rooms.planePassenger, false, "onground", 1);
		coffee = new Item("coffee", rooms.shackMainRoom, false, "ontable", 1);
		
		newspaper = new Item("newspaper", rooms.planeCockpit, false, "ontable", -1);
		kidnote = new Item("note", rooms.planePassenger, false, "ontable", -1);
		journal = new Item("journal", rooms.houseAttic, false, "onground", -1);
		noteBook = new Item("notebook", rooms.shackMainRoom, false, "ontable", -1);
		flightCourse = new Item("flight course", rooms.planeCockpit, false, "ontable", -1);	
		mazeNotebook = new Item("maze notebook", rooms.templeWestRoom, false, "ontable", -1);	
		
		bandAids = new Item("band aids", rooms.planeStorage, false, "onground", 5);
		wrap = new Item("wrap", rooms.treeHouseTreeHouse, false, "ontable", 2);
		firstaidkit = new Item("first aid kit", rooms.MAZE_x2y3, false, "ontable", 1);

		
		items = new Item[]{engine, gas, boatKey, rudder, map, pocketKnife, wrench, sword, houseKey, shackKey, treeHouseKey,
				sandwich, chips, candyBar, peanuts, pretzel, blueberries, strawberries, raspberries, blackberries, pomegranate, 
				waterBottle, beerBottle, wineBottle, sodaCan, juiceBox, coffee, 
				newspaper, kidnote, journal, noteBook, flightCourse, mazeNotebook, 
				bandAids, wrap, firstaidkit};
	}
	
	
	public Item getItem(String ID) {
		for (Item temp: items) {
			if (temp.getID() == ID) {
				return temp;
			}
		}
		return null;
	}

	public Item[] getItems() {
		return items;
	}
	
}
