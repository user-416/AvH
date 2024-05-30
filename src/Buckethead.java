import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Buckethead extends Heterotroph implements Runnable {
	private int thresh = 100; // threshold at which bucket falls off

	public Buckethead(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Buckethead/bucketWalk1.png");
		hp = 300;
		cooldown = 500;
		speed = 1;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Animate the buckethead
	public void run() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (ready) {
					if (hp > thresh) {
						img = new ImageIcon(AvH.projectPath + "/imgs/Buckethead/bucketEat1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Buckethead/bucketEat2.png");
						sleep(cooldown);
					} else {
						img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/eat1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/eat2.png");
						sleep(cooldown);
					}

				} else {
					if (hp > thresh) {
						img = new ImageIcon(AvH.projectPath + "/imgs/Buckethead/bucketWalk1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Buckethead/bucketWalk2.png");
						sleep(cooldown);
					} else {
						img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/zombie1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/zombie2.png");
						sleep(cooldown);
					}
				}
			}
		}, 0, 50);
	}
}