package driver;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import driver.Jumper.GuyReaction;


public class BootStrap {
	static public void main(String[] args) throws Exception {
		GuyReaction alwaysJump = x -> true;
		new Jumper( alwaysJump ).run();
	}
}


class Jumper {
	
	class KeySensor extends KeyAdapter {
		public boolean pressed;
		@Override 
		public void keyPressed(KeyEvent e) {
			pressed = e.getKeyCode()==KeyEvent.VK_UP; 
		}
	}

	interface GuyReaction {
		boolean hasToJump( Jumper jumper ); 
	}
	
	static final int BLEED_TIME = 5;
	static final int TURN_TIME = 5;
	static final int ROCK_STEP = 2;
	static final int JUMP_HIGH = 150;
	
	Frame frame = new Frame();
	Component guy = new Canvas();
	Component rock = new Canvas();
	Label round;
	Label damage;
	GuyReaction guyReaction;
	KeySensor keySensor;
	int jumpingStep =0;
	int roundCount;
	int damageCount;

	public Jumper(GuyReaction guyReaction ) throws Exception {
		super();
		this.guyReaction = guyReaction;
		keySensor = new KeySensor();
		createFrame();
		createGuy();
		createRock();
		createRank();
		frame.setVisible(true);
	}

	private void createRank() throws Exception {
		round = new Label("Round: 0");
		round.setSize(  100, 15 );
		round.setLocation(0,0);
		frame.add(round);
		damage = new Label("Damage: 0");
		damage.setSize(  100, 15 );
		damage.setLocation(0,15);
		frame.add(damage);
	}

	void incDamage() {
		damage.setText("Damage: "+(++damageCount));
	}
	
	void incRound() {
		round.setText("Round: "+(++roundCount));
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
		frame.addKeyListener( keySensor);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setSize(1000, 300);
		frame.setTitle("Driver");
		frame.setLayout(null);
	}

	void run() throws InterruptedException {
		while (true) {
			if ( guyReaction.hasToJump(this) ) { keySensor.pressed = true; }
			Thread.sleep(TURN_TIME);
			moveRock();
			moveGuy();
			if (isGuySmashed()) {
				bleed();
			}
		}
	}

	private void bleed() throws InterruptedException {
		guy.setBackground(Color.RED);
		Thread.sleep(BLEED_TIME);
		guy.setBackground(Color.GRAY);
		incDamage();
	}

	public boolean isGuySmashed() {
		return rock.getBounds().intersects(guy.getBounds());
	}

	private void moveGuy() {
		if ( jumpingStep > 0 ) {
			guy.setLocation(guy.getX(), jumpingStep > JUMP_HIGH/2 ? guy.getY()-1 : guy.getY()+1);
			jumpingStep--;
		} else if ( keySensor.pressed ) {
			jumpingStep = JUMP_HIGH; }
		keySensor.pressed = false;
	}

	private void moveRock() {
		if (rock.getX() > frame.getWidth()) {
			rock.setLocation(0, rock.getY());
			incRound();
		}
		else
			rock.setLocation(rock.getX() + ROCK_STEP, rock.getY());
		frame.getToolkit().sync();
	}

}
