import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Render {

	MainBody main;
	
	public Rectangle playButton = new Rectangle(MainBody.WIDTH/2-75, 250, 150, 50);
	public Rectangle helpButton = new Rectangle(MainBody.WIDTH/2-75, 350, 150, 50);
	public Rectangle quitButton = new Rectangle(MainBody.WIDTH/2-75, 450, 150, 50);

	public Render(MainBody main){
		this.main = main;
	}
	
	public void MENU(Graphics g){
		Graphics2D g2d =(Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD,75);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("HelplesslyWandering", MainBody.WIDTH/2-375, 200);
		Font fnt1 = new Font("arial", Font.BOLD,50);
		g.setFont(fnt1);
		g.setColor(Color.BLACK);
		g2d.draw(playButton);
		g.drawString("Play", playButton.x+20, playButton.y+40);
		g2d.draw(helpButton);
		g.drawString("Help", helpButton.x+20, helpButton.y+40);
		g2d.draw(quitButton);
		g.drawString("Quit", quitButton.x+20, quitButton.y+40);
	}
	
	public void INGAME(Graphics g,LinkedList<Message> mess,String Statement){
		g.setColor(Color.YELLOW);
		g.drawRect(20, 20, main.WIDTH-40, main.HEIGHT-20);
		g.drawLine(20, main.HEIGHT-40, main.WIDTH-20, main.HEIGHT-40);
		for(int i = 0; i < mess.size(); i++){
			if(mess.get(i).getFrom() == "AI"){
				g.setColor(Color.white);	
			}else if(mess.get(i).getFrom() == "PLAYER"){
				g.setColor(Color.red);
			}
			g.drawString(mess.get(i).getMessage(),30,(main.HEIGHT-5)-(mess.get(i).getRow()*25));	
		}
		g.setColor(Color.red);
		g.drawString("> "+Statement,30,(main.HEIGHT-15));	
	}		

	public void GAME_OVER(Graphics g){
		Graphics2D g2d =(Graphics2D) g;
		Font fnt1 = new Font("arial", Font.BOLD,100);
		g.setFont(fnt1);
		g.setColor(Color.red);
		g.drawString("GAME OVER", MainBody.WIDTH/2-315, 200);
		
	}
}

