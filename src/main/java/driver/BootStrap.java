package driver;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class BootStrap {
	static public void main(String[] args) throws InterruptedException {
		new Jumper( x -> false ).run();
	}
}


class Jumper {
	
	interface GuyReaction {
		boolean hasToJump( Jumper jumper ); 
	}
	
	private static final int BLEED_TIME = 5;
	private static final int TURN_TIME = 3;
	private static final int ROCK_STEP = 2;
	
	Frame frame = new Frame();
	Canvas guy = new Canvas();
	Canvas rock = new Canvas();
	private GuyReaction guyReaction;

	public Jumper(GuyReaction guyReaction ) {
		super();
		this.guyReaction = guyReaction;
		createFrame();
		createGuy();
		createRock();
	}

	private void createRock() {
		rock.setSize(50, 50);
		rock.setLocation(0, frame.getHeight() - rock.getHeight());
		rock.setBackground(Color.BLUE);
		frame.add(rock);
	}

	private void createGuy() {
		guy.setSize(  25, 25 );
		guy.setLocation(frame.getWidth()/2, frame.getHeight() - guy.getHeight());
		guy.setBackground(Color.GRAY);
		frame.add(guy);
	}

	private void createFrame() {
		frame.setSize(1000, 300);
		frame.setTitle("Driver");
		frame.setLayout(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	void run() throws InterruptedException {
		while (true) {
			if ( guyReaction.hasToJump(this) ) {
				
			}
			Thread.sleep(TURN_TIME);
			moveRock();
			if (isGuySmashed()) {
				bleed();
			}
		}
	}

	private void bleed() throws InterruptedException {
		guy.setBackground(Color.RED);
		Thread.sleep(BLEED_TIME);
		guy.setBackground(Color.GRAY);
	}

	public boolean isGuySmashed() {
		return rock.getBounds().intersects(guy.getBounds());
	}

	private void moveRock() {
		if (rock.getX() > frame.getWidth())
			rock.setLocation(0, rock.getY());
		else
			rock.setLocation(rock.getX() + ROCK_STEP, rock.getY());
		frame.getToolkit().sync();
	}

}
