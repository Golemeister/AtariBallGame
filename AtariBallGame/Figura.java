package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;

public abstract class Figura {
	
	protected int x;
	protected int y;
	protected char symbol;
	protected Color color;
	protected Scena c;
	protected char type;
	protected int width;
	protected int height;
	
	
	public Figura(int x1,int y1,Scena c) {
		x = x1;
		y = y1;
		this.c = c;
	}
	
	public void changeColor(Color c) {
		color = c;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void move(double x1,double y1) {
		x+= x1;
		y+= y1;
	}
	
}
