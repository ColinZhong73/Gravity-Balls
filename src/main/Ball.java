package main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private int size;
	private double x;
	private double y;
	private double xVel;
	private double yVel;
	private double xAccel;
	private double yAccel;
	private int xLimit;
	private int yLimit;
	private double efficiency;
	private int xAccelBase;
	private int yAccelBase;
	private GravityPoint[] gravPoints;
	private Ball[] balls;
	
	
	public Ball(int xLimit, int yLimit){
		this.xLimit = xLimit;
		this.yLimit = yLimit;
		reset();
	}
	
	public void tick(){
		calcPosition();
		checkWallCollision();
		checkGravPointCollision();
		checkBallCollision();
		calcVelocity();
	}
	
	private void calcPosition(){
		x += xVel;
		y += yVel;
	}
	
	private void checkWallCollision(){
		if(x < size/2){	
			x = size/2;
			xVel = -xVel*efficiency;
		}
		if(x > xLimit - size/2){
			x = xLimit - size/2;
			xVel = -xVel*efficiency;
		}
		
		if(y < size/2){
			y = size/2;
			yVel = -yVel*efficiency;
		}
		if(y > yLimit - size/2){
			y = yLimit - size/2;
			yVel = -yVel*efficiency;
		}
	}
		
	private void checkGravPointCollision(){
		for(int i = 0; i < gravPoints.length; i++){
			if(gravPoints[i] != null){
				double xDiff = Math.abs(x-gravPoints[i].getX());
				double yDiff = Math.abs(y-gravPoints[i].getY());
				double distance = Math.sqrt(Math.pow(xDiff, 2)+Math.pow(yDiff, 2));
				
				if(distance < (gravPoints[i].getSize()+size)/2){
					xVel = -xVel;
					yVel = -yVel;
					if(x < gravPoints[i].getX()){
						x = gravPoints[i].getX() - xDiff/distance*(gravPoints[i].getSize() + size)/2;
					}
					else{
						x = gravPoints[i].getX() + xDiff/distance*(gravPoints[i].getSize() + size)/2;
					}
					if(y < gravPoints[i].getY()){
						y = gravPoints[i].getY() - yDiff/distance*(gravPoints[i].getSize() + size)/2;
					}
					else{
						y = gravPoints[i].getY() + yDiff/distance*(gravPoints[i].getSize() + size)/2;
					}
					
				}
			}
		}
	}
	
	public void checkBallCollision(){
		for(int i = 0; i < balls.length; i++){
			if(balls[i]!=null&&balls[i]!=this){
				double xDiff = Math.abs(x-balls[i].getX());
				double yDiff = Math.abs(y-balls[i].getY());
				if(xDiff < 1 && yDiff < 1){
					x += size/Math.sqrt(2);
					y += size/Math.sqrt(2);
					return;
				}
				
				double distance = Math.sqrt(Math.pow(xDiff, 2)+Math.pow(yDiff, 2));
				if(distance < (balls[i].getSize()+size)/2){
					xVel = -xVel;
					yVel = -yVel;
					if(x < balls[i].getX()){
						x = balls[i].getX() - xDiff/distance*(balls[i].getSize() + size)/2;
					}
					else{
						x = balls[i].getX() + xDiff/distance*(balls[i].getSize() + size)/2;
					}
					if(y < balls[i].getY()){
						y = balls[i].getY() - yDiff/distance*(balls[i].getSize() + size)/2;
					}
					else{
						y = balls[i].getY() + yDiff/distance*(balls[i].getSize() + size)/2;
					}
					
				}
			}
		}
	}
	
	private void calcVelocity(){
		xVel += xAccel;
		yVel += yAccel;
	}
	
	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval((int) (x-size/2), (int) (y-size/2), size, size);
	}

	public void reset(){
		size = 50;
		x = xLimit/2 - size/2;
		y = yLimit/2 - size/2;
		xVel = 0;
		yVel = 0;
		xAccel = 0;
		yAccel = 0;
		efficiency = 1;
		xAccelBase = 0;
		yAccelBase = 0;
	}
	
	public int getSize() {
		return size;
	}
//
//	public void setSize(int size) {
//		this.size = size;
//	}
//
	public double getX() {
		return x;
	}
//
//	public void setX(double x) {
//		this.x = x;
//	}
//
	public double getY() {
		return y;
	}
//
//	public void setY(double y) {
//		this.y = y;
//	}
//
//	public double getxVel() {
//		return xVel;
//	}
//
//	public void setxVel(double xVel) {
//		this.xVel = xVel;
//	}
//
//	public double getyVel() {
//		return yVel;
//	}
//
//	public void setyVel(double yVel) {
//		this.yVel = yVel;
//	}
//
	public double getxAccel() {
		return xAccel;
	}

//	public void setxAccel(double xAccel) {
//		this.xAccel = xAccel;
//	}

	public double getyAccel() {
		return yAccel;
	}

//	public void setyAccel(double yAccel) {
//		this.yAccel = yAccel;
//	}
	
	public void setxAccelBase(int xAccelBase) {
		this.xAccelBase = xAccelBase;
	}
	
	public void setyAccelBase(int yAccelBase) {
		this.yAccelBase = yAccelBase;
	}
	
	public int getxAccelBase() {
		return xAccelBase;
	}
	
	public int getyAccelBase() {
		return yAccelBase;
	}
	
//
//	public double getEfficiency() {
//		return efficiency;
//	}
	
	public void updateGravPoints(GravityPoint[] gravPoints){
		this.gravPoints = gravPoints;
		xAccel = xAccelBase;
		yAccel = yAccelBase;
		
		for(int i = 0; i < gravPoints.length; i++){
			if(gravPoints[i]!= null){
				xAccel += gravPoints[i].getxGravity();
				yAccel += gravPoints[i].getyGravity();
			}
		}
	}
	
	public void updateBalls(Ball[] balls){
		this.balls = balls;
	}
	
}