import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class HitAlphabet {
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.setSize(900, 700);
		
		HitAlpPanel hp = new HitAlpPanel(900, 700, 10);
		f.add(hp);
		Thread t = new Thread(hp);
		t.start();
		
		f.addKeyListener(hp);
		hp.addKeyListener(hp);
		
		f.getContentPane().setBackground(new Color(204, 221, 242));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

class HitAlpPanel extends Panel implements KeyListener, Runnable{
	int width, height, count;
	int[] xpos;
	int[] ypos;	
	char[] chars;
	Image offScreenImage;
	ArrayList<Color> colors = new ArrayList<Color>();
	int score = 1000;
	int countTime = 30;
	int fontSize = 80;
	
	int getRandom(int range){
		return (int)(Math.random() * range);
	}
	
	void change(int i){		
		char c = 'A' ;
		c += getRandom(26);
		chars[i] = c;
		xpos[i] = getRandom(width - fontSize);
		ypos[i] = getRandom(height / 2);
	} 
	
	public HitAlpPanel(int w, int h, int n){
		width = w;
		height = h;
		count = n;
		
		xpos = new int[count];
		ypos = new int[count];
		chars = new char[count];
		
		for(int i = 0; i < n; ++i){
			change(i);
			colors.add(new Color(getRandom(256), getRandom(256), getRandom(256)));
		}
	}
	
	@Override
	public void update(Graphics g){
		if (offScreenImage == null) { 
            offScreenImage = this.createImage(width, height);     
        }  
		
        Graphics gImage = offScreenImage.getGraphics();        
        gImage.clearRect(0, 0, width, height); 
        paint(gImage); 
        g.drawImage(offScreenImage, 0, 0, width, height, null);
	}
	
	@Override
	public void paint(Graphics g){
		g.setFont(new Font("", 0, fontSize));		
		
		for(int i = 0; i < count; ++i){
			g.setColor(colors.get(i));			
			g.drawString("" + chars[i], xpos[i], ypos[i]);	
			
		}
		
		g.setColor(Color.RED);
		g.drawString("Yor score is: " + Integer.toString(score), 5, fontSize);
		g.drawString("Time left:" + countTime + "s", 100, fontSize << 1);
		
	}
	/*the newly created thread will run this method all the time*/
	public void run(){
		Timer timer = new Timer();
		/*periodically run the task every 1s */
		timer.schedule(new MyTask(), 0, 1000);
		
		while(true){
			int speed = 1;			
			for(int i = 0; i < count; ++i){
				ypos[i] += speed;
				if(ypos[i] >= height + fontSize){
					change(i);
					score -= 100;
				}
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			repaint();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();		
		
		for(int i = 0; i < count; ++i){
			if(c == chars[i] || c == (chars[i] + 32)){
				score += 10;
				change(i);				
				break;
			}
		}	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}
  
	class MyTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub			
			countTime = countTime - 1;
			if(countTime <= 0){
				System.exit(0);
			}	
					
		}
		
	}

}

