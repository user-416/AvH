import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Peashooter extends Autotroph {
	public Peashooter(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Peashooter/peashooter2.png");
		hp = 150;
		cooldown = 2000;

		// Animate the peashooter
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (!ready) {
					sleep(cooldown);
					ready = true;
				}
				;
			}
		}, 0, 50);

		Timer timer2 = new Timer();
		timer2.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				img = new ImageIcon(AvH.projectPath + "/imgs/Peashooter/peashooter1.png");
				sleep(500);
				img = new ImageIcon(AvH.projectPath + "/imgs/Peashooter/peashooter2.png");
				sleep(500);
				img = new ImageIcon(AvH.projectPath + "/imgs/Peashooter/peashooter3.png");
				sleep(500);
				img = new ImageIcon(AvH.projectPath + "/imgs/Peashooter/peashooter2.png");
				sleep(500);
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
		g.setColor(Color.white);
		g.fillRect(x, y, 50, 50);
	}
}