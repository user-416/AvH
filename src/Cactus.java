import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Cactus extends Autotroph {
	public Cactus(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Cactus/cactus2.png");
		hp = 400;
		cooldown = 500;

		// Animate the cactus
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				img = new ImageIcon(AvH.projectPath + "/imgs/Cactus/cactus1.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Cactus/cactus2.png");
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
}