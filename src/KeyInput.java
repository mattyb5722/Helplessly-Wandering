import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	MainBody main;
	Monster mon;
	
	public KeyInput(MainBody main, Monster mon){
		this.main = main;
		this.mon = mon;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
    	if(main.getMenu() == main.getMenu().GAME){
			if (key == KeyEvent.VK_A){
				main.setStatement(main.getStatement()+"a"); 
			}else if (key == KeyEvent.VK_B){
				main.setStatement(main.getStatement()+"b");
			}else if (key == KeyEvent.VK_C){
				main.setStatement(main.getStatement()+"c");
			}else if (key == KeyEvent.VK_D){
				main.setStatement(main.getStatement()+"d");
			}else if (key == KeyEvent.VK_E){
				main.setStatement(main.getStatement()+"e");
			}else if (key == KeyEvent.VK_F){
				main.setStatement(main.getStatement()+"f");
			}else if (key == KeyEvent.VK_G){
				main.setStatement(main.getStatement()+"g");
			}else if (key == KeyEvent.VK_H){
				main.setStatement(main.getStatement()+"h");
			}else if (key == KeyEvent.VK_I){
				main.setStatement(main.getStatement()+"i");
			}else if (key == KeyEvent.VK_J){
				main.setStatement(main.getStatement()+"j");
			}else if (key == KeyEvent.VK_K){
				main.setStatement(main.getStatement()+"k");
			}else if (key == KeyEvent.VK_L){
				main.setStatement(main.getStatement()+"l");
			}else if (key == KeyEvent.VK_M){
				main.setStatement(main.getStatement()+"m");
			}else if (key == KeyEvent.VK_N){
				main.setStatement(main.getStatement()+"n");
			}else if (key == KeyEvent.VK_O){
				main.setStatement(main.getStatement()+"o");
			}else if (key == KeyEvent.VK_P){
				main.setStatement(main.getStatement()+"p");
			}else if (key == KeyEvent.VK_Q){
				main.setStatement(main.getStatement()+"q");
			}else if (key == KeyEvent.VK_R){
				main.setStatement(main.getStatement()+"r"); 
			}else if (key == KeyEvent.VK_S){
				main.setStatement(main.getStatement()+"s");
			}else if (key == KeyEvent.VK_T){
				main.setStatement(main.getStatement()+"t"); 
			}else if (key == KeyEvent.VK_U){
				main.setStatement(main.getStatement()+"u");
			}else if (key == KeyEvent.VK_V){
				main.setStatement(main.getStatement()+"v");
			}else if (key == KeyEvent.VK_W){
				main.setStatement(main.getStatement()+"w");
			}else if (key == KeyEvent.VK_X){
				main.setStatement(main.getStatement()+"x");
			}else if (key == KeyEvent.VK_Y){ 
				main.setStatement(main.getStatement()+"y");
			}else if (key == KeyEvent.VK_Z){
				main.setStatement(main.getStatement()+"z");
			}else if (key == KeyEvent.VK_0){
				main.setStatement(main.getStatement()+"0");
			}else if (key == KeyEvent.VK_1){
				main.setStatement(main.getStatement()+"1");
			}else if (key == KeyEvent.VK_2){
				main.setStatement(main.getStatement()+"2");
			}else if (key == KeyEvent.VK_3){
				main.setStatement(main.getStatement()+"3");
			}else if (key == KeyEvent.VK_4){
				main.setStatement(main.getStatement()+"4");
			}else if (key == KeyEvent.VK_5){
				main.setStatement(main.getStatement()+"5");
			}else if (key == KeyEvent.VK_6){
				main.setStatement(main.getStatement()+"6");
			}else if (key == KeyEvent.VK_7){
				main.setStatement(main.getStatement()+"7");
			}else if (key == KeyEvent.VK_8){
				main.setStatement(main.getStatement()+"8");
			}else if (key == KeyEvent.VK_9){
				main.setStatement(main.getStatement()+"9");
			}else if (key == KeyEvent.VK_SPACE){
				main.setStatement(main.getStatement()+" ");
			}else if (key == KeyEvent.VK_BACK_SPACE){
				if(main.getStatement().length()-1 >= 0){
					main.setStatement(main.getStatement().substring(0,main.getStatement().length()-1));}
			}else if (key == KeyEvent.VK_ENTER){			
				main.addMessage(main.getStatement(), "PLAYER");
				if(main.getPhase() == main.getPhase().MOVING){
					main.getResponse(main.getStatement());
				}else if(main.getPhase() == main.getPhase().COMBAT){
					mon.combat(main.getStatement());
				}
				main.setStatement("");		
			}
			main.render();
			main.render();
    	}	}

}
