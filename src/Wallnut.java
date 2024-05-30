import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Wallnut extends Autotroph {
	public Wallnut(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Wallnut/wallnut2.png");
		hp = 600;
		cooldown = 500;

		// Animate the wallnut
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				img = new ImageIcon(AvH.projectPath + "/imgs/Wallnut/wallnut1.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Wallnut/wallnut2.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Wallnut/wallnut3.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Wallnut/wallnut2.png");
				sleep(cooldown);
			}
		}, 0, 50);
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}