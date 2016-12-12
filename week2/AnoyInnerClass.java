import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;



public class AnoyInnerClass implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Date now = new Date();
		System.out.println("now the time is: " + now);
		Toolkit.getDefaultToolkit().beep();
	}
	
	public static void main(String[] args){
		AnoyInnerClass  tp = new AnoyInnerClass ();
		Timer t = new Timer(2000, tp);
		t.start();
		
		JOptionPane.showMessageDialog(null, "Quit?");
		System.exit(0);
	}
	
}
