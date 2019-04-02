
public class Items {

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
	
	private Item [] items = {engine, gas, boatKey, rudder, map, pocketKnife, wrench, sword, houseKey, shackKey, treeHouseKey,
			sandwich, chips, candyBar, peanuts, pretzel, blueberries, strawberries, raspberries, blackberries, pomegranate, 
			waterBottle, beerBottle, wineBottle, sodaCan, juiceBox, coffee, 
			newspaper, kidnote, journal, noteBook, flightCourse, mazeNotebook, 
			bandAids, wrap, firstaidkit};
	
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
