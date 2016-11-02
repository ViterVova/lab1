package stu.cn.ua.lab1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Ball extends RoundButton implements Runnable{
	
	@Override
	public void run() {
		while(true){
			try{
				moveShape();
				Thread.sleep(speed);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private volatile int radius;
	private Color color;
	private int x=0;
	private int y=0;
	private int dx=1;
	private int dy=1;
	private int speed=0;
	private int move=0;
	
	public Ball(int x,int y,int size, int speed,JComponent parent,Color color){
		super(color, size);
		setSize(new Dimension(size, size));
		this.color=color;
		this.x=x;
		this.y=y;
		this.radius=size;
		this.speed=speed;
		parent.add(this);
		setLocation(x, y);
		parent.validate();
	}

	public void moveShape() {
		move+=1;
		x+=dx;
		y+=dy;
		if (x<0){
			x=0;
			dx=-dx;
		}
		if (x+radius>=getParent().getWidth()){
			x=getParent().getWidth()-radius;
			dx=-dx;
		}
		if (y<0){
			y=0;
			dy=-dy;
		}
		if (y+radius>=getParent().getHeight()){
			y=getParent().getHeight()-radius;
			dy=-dy;
		}
		this.setLocation(new Point(x, y));
	}

	public void drawShape(Graphics graphics) {
		graphics.setColor(color);
		graphics.fillOval(x, y,radius,radius);
	}
	

	public void logShapeStatistic(){
		System.out.println("Ball color="+color.toString()+"move count="+move);
	}


	public void changeShapeSize(int size) {
		this.radius=size;
	}
	
	
}
