package igra;

import java.awt.Canvas;
import java.awt.Color;

public abstract class AktivnaFigura extends Figura implements Runnable{
	
	protected Thread nit = null;
	protected boolean pauza = false;

	public AktivnaFigura(int x1, int y1,Scena canvas) {
		super( x1,y1,canvas);
		nit = new Thread(this);
		
	}

	public synchronized void start() {
		nit.start();
	}
	
	public synchronized void stop() {
        nit.interrupt();
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
