package driver;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Driver {

	static public void main( String[] args) {
		Frame f = new Frame();
		f.setSize(640,400);
		f.setTitle("Driver");
		f.setVisible(true);
		f.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Graphics2D g = (Graphics2D) f.getGraphics();
		g.setColor(Color.BLUE);
		g.drawRect(0, 300, 50, 50);
		g.fillRect(0, 300, 50, 50);
	}
}
