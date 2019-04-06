import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Render {
	
	/* This class renders the screen */
	
	private final Rectangle playButton, helpButton, quitButton; // Buttons on main menu
	
	private final int height, width;							// Dimension of the screen

	public Render(int height, int width){
		this.height = height;
		this.width = width;
		playButton = new Rectangle(width/2-75, 250, 150, 50);
		helpButton = new Rectangle(width/2-75, 350, 150, 50);
		quitButton = new Rectangle(width/2-75, 450, 150, 50);
	}
	
	// Renders the main menu
	public void MENU(Graphics g){
		Graphics2D g2d =(Graphics2D) g;
		g.setFont(new Font("arial", Font.BOLD,75));
		g.setColor(Color.WHITE);
		g.drawString("HelplesslyWandering", width/2-375, 200);
		g.setFont(new Font("arial", Font.BOLD,50));
		g.setColor(Color.BLACK);
		g2d.draw(playButton);
		g.drawString("Play", playButton.x+20, playButton.y+40);
		g2d.draw(helpButton);
		g.drawString("Help", helpButton.x+20, helpButton.y+40);
		g2d.draw(quitButton);
		g.drawString("Quit", quitButton.x+20, quitButton.y+40);
	}
	
	// Renders game content
	public void INGAME(Graphics g,LinkedList<Message> mess,String Statement){
		g.setColor(Color.YELLOW);
		g.drawRect(20, 20, width-40, height-20);
		g.drawLine(20, height-40, width-20, height-40);
		for(int i = 0; i < mess.size(); i++){			// Goes through every message
			if(mess.get(i).getFrom() == "AI"){			// If the AI said the message
				g.setColor(Color.white);				// Set text color to white
			}else if(mess.get(i).getFrom() == "PLAYER"){ // If the player said the message
				g.setColor(Color.red);					// Set text color to red
			}
			g.drawString(mess.get(i).getMessage(),30,(height-5)-(mess.get(i).getRow()*25));	// Display message
		}
		g.setColor(Color.red);
		g.drawString("> "+Statement,30,(height-15));	
	}		
	
	// Renders game over content
	public void GAME_OVER(Graphics g){
		g.setFont(new Font("arial", Font.BOLD,100));
		g.setColor(Color.red);
		g.drawString("GAME OVER", MainBody.WIDTH/2-315, 200);
	}
}

