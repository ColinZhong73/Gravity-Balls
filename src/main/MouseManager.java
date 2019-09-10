package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseManager extends MouseAdapter{
	private Main main;
	
	public MouseManager(Main main){
		this.main = main;
	}
			
	public void mouseReleased(MouseEvent e) {
		main.addGravityPoint(e.getX(), e.getY());
	}
	
}