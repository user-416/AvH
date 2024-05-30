import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Sunflower extends Autotroph {

	private Sun s;
	
	public Sunflower(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Sunflower/sunflower1.png");
		hp = 100;
		cooldown = 8000;

		// Generate sun
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (!ready) {
					sleep(cooldown);
					ready = true;
					System.out.println("Sun ready");
				};
			}
		}, 0, 50);
		
		// Animate sunflower
		Timer timer2 = new Timer();
		timer2.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				img = new ImageIcon(AvH.projectPath + "/imgs/Sunflower/sunflower1.png");
				sleep(500);
				img = new ImageIcon(AvH.projectPath + "/imgs/Sunflower/sunflower2.png");
				sleep(500);
				img = new ImageIcon(AvH.projectPath + "/imgs/Sunflower/sunflower3.png");
				sleep(500);
				img = new ImageIcon(AvH.projectPath + "/imgs/Sunflower/sunflower2.png");
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

	public Sun getS() {
		return s;
	}

	public void setS(Sun s) {
		this.s = s;
	}
}