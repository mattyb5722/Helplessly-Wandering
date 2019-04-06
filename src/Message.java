public class Message {
	
	/* This class organizes the messages on the screen */
	
	private final String message;		// What is in the message
	private final String from;			// Who the message is from
	private int row;					// Where to display the message
	
	public Message(String message, String from, int row){
		this.message = message;
		this.from = from;
		this.row = row;
	}

	public String getMessage() {
		return message;
	}
	public String getFrom() {
		return from;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
}
