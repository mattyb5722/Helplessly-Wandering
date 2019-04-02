import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	MainBody main;
	Text t;
	
	public MouseInput(MainBody main,Text t){
		this.main = main;
		this.t = t;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(main.getMenu() == main.getMenu().MENU){
			if(mx < MainBody.WIDTH/2+125 && mx > MainBody.WIDTH/2-125){
				if(my < 350 && my > 250){
					main.setMenu(main.getMenu().GAME);
					t.StartText();
				}
			}
			if(mx < MainBody.WIDTH/2+125 && mx > MainBody.WIDTH/2-125){
				if(my < 750 && my > 650){
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
