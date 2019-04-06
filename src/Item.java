
public class Item {

	/* This class organizes Item information */
	
	private final String ID;
	private String place;			// Location in the room that the item is
	private int durability;
	
	private String description = ""; // Text used when examining the item
	private String useText = "";	// Text used when using an item
	
	private String useRequirement = null; // Location requirement to use the item
	private int [] useResult = new int [] {0, 0, 0}; // Stats gained from using the item

	public Item(String ID, String place, int durability) {
		this.ID = ID;
		this.place = place;
		this.durability = durability;
	}
	
	public String getID() {
		return ID;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public String getUseText() {
		return useText;
	}
	public void setUseText(String useText) {
		this.useText = useText;
	}
	public String getUseRequirement() {
		return useRequirement;
	}
	public void setUseRequirement(String useRequirement) {
		this.useRequirement = useRequirement;
	}
	public int[] getUseResult() {
		return useResult;
	}
	public void setUseResult(int[] useResult) {
		this.useResult = useResult;
	}
}
