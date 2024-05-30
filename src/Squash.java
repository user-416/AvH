import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Squash extends Autotroph {
	private Heterotroph target;
	
	public Squash(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Squash/squash2.png");
		hp = 500;
		cooldown = 200;

		// Animate the squash's jump and idle
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (ready) {
					img = new ImageIcon(AvH.projectPath + "/imgs/Squash/jump1.png");
					sleep(200);
					img = new ImageIcon(AvH.projectPath + "/imgs/Squash/jump2.png");
					sleep(200);
					img = new ImageIcon(AvH.projectPath + "/imgs/Squash/jump3.png");
					// Move the squash
					for(int i = 0; i<5; i++) {
						setY(getY()-50);
						setX(getX()+10);
						sleep(50);
					}
					sleep(500);
					target.makeDead(); // Remove the neighboring zombie
					img = new ImageIcon(AvH.projectPath + "/imgs/Squash/jump4.png");
					setY(getY()+250);
					sleep(200);
					hp = 0;
					
				}

				img = new ImageIcon(AvH.projectPath + "/imgs/Squash/squash1.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Squash/squash2.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Squash/squash3.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Squash/squash2.png");
				sleep(cooldown);
			}
		}, 0, 50);

	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	

	public Heterotroph getTarget() {
		return target;
	}

	public void setTarget(Heterotroph target) {
		this.target = target;
	}
}