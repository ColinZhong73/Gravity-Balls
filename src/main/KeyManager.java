package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter{
	private Main main;
	private int accelMulti;
	
	public KeyManager(Main main){
		this.main = main;
		accelMulti = 1;
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_SHIFT){
			accelMulti = 10;
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_P){
			main.togglePause();
		}
		if(e.getKeyCode()==KeyEvent.VK_SHIFT){
			accelMulti = 1;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			for(int i = 0; i < main.getBalls().length; i++){
				if(main.getBalls()[i]!=null){
					main.getBalls()[i].setyAccelBase(main.getBalls()[i].getyAccelBase()-accelMulti);
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			for(int i = 0; i < main.getBalls().length; i++){
				if(main.getBalls()[i]!=null){
					main.getBalls()[i].setyAccelBase(main.getBalls()[i].getyAccelBase()+accelMulti);
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			for(int i = 0; i < main.getBalls().length; i++){
				if(main.getBalls()[i]!=null){
					main.getBalls()[i].setxAccelBase(main.getBalls()[i].getxAccelBase()-accelMulti);
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			for(int i = 0; i < main.getBalls().length; i++){
				if(main.getBalls()[i]!=null){
					main.getBalls()[i].setxAccelBase(main.getBalls()[i].getxAccelBase()+accelMulti);
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			main.reset();
		}
		if(e.getKeyCode()==KeyEvent.VK_C){
			main.clearGravPoints();
		}
		
		
		if(e.getKeyCode()==KeyEvent.VK_1){
			main.setNumBalls(1);
		}
		if(e.getKeyCode()==KeyEvent.VK_2){
			main.setNumBalls(2);
		}
		if(e.getKeyCode()==KeyEvent.VK_3){
			main.setNumBalls(3);
		}
		
	}
		
}
