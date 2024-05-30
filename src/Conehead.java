import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Conehead extends Heterotroph implements Runnable {
	private int thresh = 100; // threshold at which cone falls off

	public Conehead(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Conehead/coneWalk1.png");
		hp = 200;
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

	// Animate the zombie
	public void run() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (ready) {
					if (hp > thresh) {
						img = new ImageIcon(AvH.projectPath + "/imgs/Conehead/coneEat1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Conehead/coneEat2.png");
						sleep(cooldown);
					} else {
						img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/eat1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/eat2.png");
						sleep(cooldown);
					}

				} else {
					if (hp > thresh) {
						img = new ImageIcon(AvH.projectPath + "/imgs/Conehead/coneWalk1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Conehead/coneWalk2.png");
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