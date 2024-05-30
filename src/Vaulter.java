import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Vaulter extends Heterotroph implements Runnable {
	private boolean hasPole = true;

	public Vaulter(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/vaulter.png");
		hp = 150;
		cooldown = 500;
		speed = 2;
	}

	public void run() {
		// Animate Vaulter
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (ready) {
					if (hasPole) {
						img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/poleJump.png");
						for(int i = 0; i<5; i++) { // Make the Vaulter jump
							setX(getX()-30);
							sleep(50);
						}
						img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/poleLand.png");
						sleep(cooldown);
						hasPole = false; // Remove the Vaulter's pole
						speed = 1; // Decrease its speed after it jumps
					} else {
						img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/eat1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/eat2.png");
						sleep(cooldown);
					}
				} else {
					if (hasPole) {
						img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/pole1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/pole2.png");
						sleep(cooldown);
					} else {
						img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/noPole1.png");
						sleep(cooldown);
						img = new ImageIcon(AvH.projectPath + "/imgs/Vaulter/noPole2.png");
						sleep(cooldown);
					}
				}
			}
		}, 0, 50);
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

	public boolean hasPole() {
		return hasPole;
	}

	public void setHasPole(boolean hasPole) {
		this.hasPole = hasPole;
	}
}