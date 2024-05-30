import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Boss extends Heterotroph implements Runnable {
	private MapScreen map;

	public Boss(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Boss/boss1.png");
		hp = 1000;
		cooldown = 500;
		speed = 0;
	}

	public void run() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				synchronized (Boss.this) {
					if (ready) {
						img = new ImageIcon(AvH.projectPath + "/imgs/Boss/bossAbility.png");
						if (map.getSun()>=100) {
							map.changeSun(-50);
						}
						sleep(2*cooldown);
						ready = false;
					} else {
						img = new ImageIcon(AvH.projectPath + "/imgs/Boss/boss1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Boss/boss2.png");
						sleep(cooldown);
					}
				}
			}
		}, 0, 50);
		
		Timer timer2 = new Timer();
		timer2.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				synchronized (Boss.this) { // Synchronize on the instance
					ready = true;
				}
			}
		}, 0, 3000);
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

	public MapScreen getMap() {
		return map;
	}

	public void setMap(MapScreen map) {
		this.map = map;
	}
}