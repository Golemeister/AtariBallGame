package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Scena extends Canvas{

	private Simulator simulator;
	private Igrac player = null;
	private ArrayList<Cigla> cigle = null;
	private ArrayList<Loptica> loptice = null;
	private Image offScreenImage;
    private Graphics offScreenGraphics;
	
	public Scena(Simulator s) {
		simulator = s;
        setBackground(Color.WHITE);
        player = new Igrac(simulator.getWidth()/2 - 30 ,simulator.getHeight() - 70,this, 60, 10);
        int tmpWidth = simulator.getWidth()/5 - 4;
        int tmpHeight = 20;
        int startX = 1;
        int startY = 1;
        cigle = new ArrayList<>();
        loptice = new ArrayList<>();
        for(int i = 0;i< 15;i++) {
        	cigle.add(new Cigla(startX,startY,this,tmpWidth,tmpHeight));
        	if(i % 5 == 4) {
        		startX = 1;
        		startY += tmpHeight + 1;
        	}else {
        		startX += tmpWidth + 1;
        	}
        }
        addMouseListener(new MouseAdapter() {
		
        	@Override
        	public void mousePressed(MouseEvent e) {
        		int r = 10;
        		synchronized(loptice) {
        			loptice.add(new Loptica(player.getX() + player.getWidth()/2, player.getY() - 2*r, (Scena) e.getSource(), r));
        		}
        		repaint();
        	}
        
        });
        addKeyListener(new KeyAdapter() {
        	
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        			player.moveLeft();
        		}
        		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        			player.moveRight();
        		}
        		repaint();
        	}
        });
        
	}
	
	public synchronized void stop() {
		for (Cigla cigla : cigle) {
			cigla.stop();
		}
		for (Loptica loptica : loptice) {
			loptica.stop();
		}
	}
	
	public Igrac getPlayer() {
		return player;
	}
	
	public ArrayList<Cigla> getCigle(){
		return cigle;
	}
	
	public synchronized void removeLoptica(Loptica l) {
		loptice.remove(l);
	}
	
	public synchronized void removeCigla(Cigla cigla) {
		cigle.remove(cigla);
	}
	
	@Override
	public void paint(Graphics g) {
		
		if (offScreenImage == null) {
            offScreenImage = createImage(getWidth(), getHeight());
            offScreenGraphics = offScreenImage.getGraphics();
        }
        offScreenGraphics.setColor(getBackground());
        offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
		
		synchronized(cigle) {
			player.paint(offScreenGraphics);
			for (Cigla cigla : cigle) {
				cigla.paint(offScreenGraphics);
			}
			for (Loptica loptica : loptice) {
				loptica.paint(offScreenGraphics);
			}
		}
		
		
		g.drawImage(offScreenImage, 0, 0, this);
	}

	@Override
    public void update(Graphics g) {
        paint(g);
    }
	
}
