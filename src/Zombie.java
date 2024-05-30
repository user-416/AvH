import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Zombie extends Heterotroph implements Runnable {
	public Zombie(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/zombie1.png");
		hp = 100;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Animate the zombie
	public void run() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (ready) {
					img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/eat1.png");
					sleep(cooldown);
					img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/eat2.png");
					sleep(cooldown);
				} else {
					img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/zombie1.png");
					sleep(cooldown);
					img = new ImageIcon(AvH.projectPath + "/imgs/Zombie/zombie2.png");
					sleep(cooldown);
				}
			}
		}, 0, 50);
	}
}