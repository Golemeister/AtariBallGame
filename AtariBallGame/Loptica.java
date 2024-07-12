package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Loptica extends AktivnaFigura{

	private int R;
	private double vX;
	private double vY;
	private int tickCount = 0;
	
	public Loptica(int x1, int y1, Scena canvas,int R) {
		super(x1, y1, canvas);
		color = Color.GREEN;
		this.R = R;
		type='L';
		vX = Math.round( Math.random() -1);
		vY = (int) Math.random() - 1;
		start();
	}
	
	public synchronized void moveLoptica() {
		
	    if (this.getX() <= 0) {
	    	setX(1);
	    	vX = -vX ; 
	    }
	    else if (this.getX() >= c.getWidth()-R) {
	    	vX = -vX ; 
	    	setX(c.getWidth()-R - 1);
	    }else if(this.getY() <= 0) {
	    	setY(1);
	    	vY = -vY ;
	    }else if(this.getY() >= c.getHeight() - R) {
	    	c.removeLoptica(this);
	    	nit.interrupt();
	    }
	    else if(collidesWithPlayer(c.getPlayer())) {
	    	setY(c.getPlayer().getY() - R - 1);
	    	vY = -vY;
	    }
	    else {
	    	
	    	this.move(vX, vY);
	    }
	    
	}
	
	public synchronized void increaseByPercentage(double percentage) {
        this.vX += this.vX * percentage / 100.0;
        this.vY += this.vY * percentage / 100.0;
    }
	
	public synchronized void tick() {
        tickCount++;
        if (tickCount >= 100) {
            increaseByPercentage(10.0);
            tickCount = 0;
        }
    }
	
	public synchronized void moveAfterCiglaHit(ArrayList<Cigla> cg) {
		for (Cigla cigla : cg) {
			if(collidesWithCigla(cigla) && !cigla.isHit()) {
				cigla.setHit();
				vY = -vY;
				return;
			}
		}
	}
	
	public  synchronized boolean collidesWithPlayer(Igrac i) {
		return (this.getX() >= i.getX() && this.getX() <= i.getX()+i.getWidth() - R && this.getY() >= i.getY() - R && this.getY() <= i.getY() - R + 10);
			
	}
	
	public synchronized boolean collidesWithCigla(Cigla cg) {
		return (this.getX() >= cg.getX() && this.getX() <= cg.getX()+cg.getWidth() && this.getY() >= cg.getY() && this.getY() <= cg.getY() + cg.getHeight());
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
                moveAfterCiglaHit(c.getCigle());
                moveLoptica();
                tick();
                c.repaint();
            }
        } catch (InterruptedException e) {
            System.out.println("uhvaćen exception");
        }
		
	}
	
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, R, R);
	}

}
