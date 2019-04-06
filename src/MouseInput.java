import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	/* This class controls mouse inputs */
	
	private MainBody main;
	private Text t;
	private final int height, width;							// Dimension of the screen

	
	public MouseInput(MainBody main, Text t, int height, int width){
		this.main = main;
		this.t = t;
		this.height = height;
		this.width = width;
	}
	
	// Mouse has been pressed
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();							// X location of click
		int my = e.getY();							// Y location of click
		if(main.getMenu() == main.getMenu().MENU){	// Player in on the main menu
			if(mx < width/2+125 && mx > width/2-125){
				if(my < 300 && my > 250){			// Player pressed the start button
					main.setMenu(main.getMenu().GAME);
					t.StartText();
				}
			}
			if(mx < width/2+125 && mx > width/2-125){
				if(my < 500 && my > 450){			// Player pressed the quit button
					System.exit(1);				
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
