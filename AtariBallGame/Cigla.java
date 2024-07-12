package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Cigla extends AktivnaFigura{

	private boolean hit = false;
	
	public Cigla(int x1, int y1, Scena canvas, int width, int height) {
		super(x1, y1, canvas);
		this.height = height;
		this.width = width;
		color = Color.RED;
		type = 'C';
		nit.start();
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public synchronized void setHit() {
		hit = true;
		
	}
	
	public void paint(Graphics g) {
		if(hit) {
			g.setColor(Color.GRAY);
		}else {
			g.setColor(color);
		}
		
		g.fillRect(x, y, width, height);
	}
	
	@Override
	public void run() {
			try {
	            while (!nit.isInterrupted()) {
	                synchronized (nit) {
	                    while (pauza) {
	                        nit.wait();
	                    }
	                }
	                Thread.sleep(10);
	                if(hit) {
	                	this.move(0, 4);
	                	if(this.getY() >= c.getHeight() - this.height/2) {
	                		c.removeCigla(this);
	                		this.stop();
	                	}
	                }
	                c.repaint();
	            }
	        } catch (InterruptedException e) {
	            System.out.println("uhvaćen exception");
	        }
	}

}
