package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Main {
	private static Main main;
	
	private Display display;
	private Graphics g;
	private BufferStrategy bs;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private Ball[] balls;
	private final int ballsNum = 3;
	private GravityPoint[] gravPoints;
	private final int gravPointsNum = 5;
	private boolean pause;
	
	public static void main(String args[]){
		main = new Main();
		main.init();
		main.run();
	}
	
	private void init(){
		display = new Display("Gravity Balls", 1000, 800);
		keyManager = new KeyManager(main);
		mouseManager = new MouseManager(main);
		
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addMouseListener(mouseManager);
		
		balls = new Ball[ballsNum];
		balls[0] = new Ball(display.getWidth(), display.getHeight());
		gravPoints = new GravityPoint[gravPointsNum];
		
		pause = false;
	}
	
	private void run(){
		long timer = System.currentTimeMillis();
		while(true){
			if(System.currentTimeMillis()-timer>=(1000/60)){
				if(!pause){
					tick();
				}
				render();
				timer = System.currentTimeMillis();
			}
		}
	}
	
	private void tick(){
		for(int i = 0; i < ballsNum; i++){
			if(balls[i]!= null){
				for(int j = 0; j < gravPointsNum; j++){
					if(gravPoints[j]!=null){
						gravPoints[j].tick(balls[i].getX(),balls[i].getY());
					}
				}
				balls[i].updateGravPoints(gravPoints);
				balls[i].updateBalls(balls);
				balls[i].tick();
			}
		}
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, display.getWidth(), display.getHeight());
		
		for(int i = 0; i < ballsNum; i++){
			if(balls[i]!= null){
				balls[i].render(g);
			}
		}
		for(int i = 0; i < gravPointsNum; i++){
			if(gravPoints[i]!=null){
				gravPoints[i].render(g);
			}
		}
		
		
		g.setColor(Color.BLACK);
//		g.drawString("x Acceleration: " + ball.getxAccel(), 1, 10);
//		g.drawString("y Acceleration: " + ball.getyAccel(), 1, 20);
		if(pause){
			g.drawString("Paused!", display.getWidth()/2-50, display.getHeight()/2-10);
		}
		
		
		bs.show();
		g.dispose();
		
	}
	
	public void addGravityPoint(int x, int y){
		boolean full = true;
		for(int i = 0; i < gravPointsNum; i++){
			if(gravPoints[i] == null){
				full = false;
				gravPoints[i] = new GravityPoint(x,y);
				i = gravPointsNum;
			}
		}
		if(full){
			for(int i = 0; i < gravPointsNum - 1; i++){
				gravPoints[i] = gravPoints[i+1];
			}
			gravPoints[gravPointsNum - 1] = new GravityPoint(x,y);
		}
	}
	
	public void reset(){
		for(int i = 0; i < ballsNum; i++){
			if(balls[i]!=null){
				balls[i].reset();
			}
		}
		clearGravPoints();
		pause = false;
	}
	
	public void clearGravPoints(){
		for(int i = 0; i < gravPointsNum; i++){
			gravPoints[i] = null;
		}
	}
	
	public Ball[] getBalls(){
		return balls;
	}
	
	public void setNumBalls(int num){
		for(int i = 0; i < num; i++){
			if(balls[i]==null){
				balls[i] = new Ball(display.getWidth(),display.getHeight());
			}
		}
		for(int i = num; i < ballsNum; i++){
			balls[i] = null;
		}
	}
	
	public void togglePause(){
		pause = !pause;
	}
}
