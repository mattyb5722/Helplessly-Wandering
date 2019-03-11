
public class Item {

	String ID, place;
	int xOut, yOut, zOut, xIn, yIn, zIn, durability;
	boolean taken;
	
	public Item(String ID, int xOut, int yOut, int zOut, int xIn, int yIn, int zIn, boolean taken, String place, int durability) {
		this.ID = ID;
		this.xOut = xOut;
		this.yOut = yOut;
		this.zOut= zOut;
		this.xIn = xIn;
		this.yIn = yIn;
		this.zIn = zIn;
		this.taken = taken;
		this.place = place;
		this.durability = durability;
	}

}
