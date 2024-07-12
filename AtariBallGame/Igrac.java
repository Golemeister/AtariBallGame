package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends Figura{

	public Igrac(int x1, int y1, Scena c,int width,int height) {
		super(x1, y1, c);
		color = Color.BLUE;
		type = 'I';
		this.width = width;
		this.height = height;
	}
	
	public void moveLeft() {
		if(x > 0)
		x+= -10;
	}
	
	public void moveRight() {
		if(x < c.getWidth() - this.getWidth())
		x+= 10;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
}
