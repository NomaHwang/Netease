import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;



public class AnnoyInnerTest{	
	public static void main(String[] args){
		final boolean beep = true;
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date now = new Date();
				System.out.println("the current time is: " + now);
				if(beep){
					Toolkit.getDefaultToolkit().beep();
				}
			}
		};
		
		Timer t = new Timer(2000, listener);
		t.start();
		
		JOptionPane.showMessageDialog(null, "Quit?");
		System.exit(0);
	}
	
}
