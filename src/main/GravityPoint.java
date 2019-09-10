package main;

import java.awt.Color;
import java.awt.Graphics;

public class GravityPoint {
	private int x;
	private int y;
	private int size;
	private double xGravity;
	private double yGravity;
	
	public GravityPoint(int x, int y){
		this.x = x;
		this.y = y;
		size = 8;
	}
	
	public void tick(double x2, double y2){
		calcGravity(x2,y2);
	}
	
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(x-size/2, y-size/2, size, size);
	}
	
	public void calcGravity(double x2, double y2){
		double xDiff = Math.abs(x2-x);
		double yDiff = Math.abs(y2-y);
		double distance = Math.sqrt(Math.pow(xDiff, 2)+Math.pow(yDiff, 2));
		
		if(x2 < x){
			xGravity = 2000*xDiff/Math.pow(distance, 3);
		}
		else if(x2 > x){
			xGravity = -2000*xDiff/Math.pow(distance, 3);
		}
		else{
			xGravity = 0;
		}
		
		if(y2 < y){
			yGravity = 2000*yDiff/Math.pow(distance, 3);
		}
		else if(y2 > y){
			yGravity = -2000*yDiff/Math.pow(distance, 3);
		}
		else{
			yGravity = 0;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public double getxGravity(){
		return xGravity;
	}
	
	public double getyGravity(){
		return yGravity;
	}
	
}
