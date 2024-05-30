import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class CherryBomb extends Autotroph {
	private Grid grid;

	public CherryBomb(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/CherryBomb/cherrybomb2.png");
		hp = 150;
		cooldown = 200;

		// Animate the cherry bomb's explosion
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				img = new ImageIcon(AvH.projectPath + "/imgs/CherryBomb/cherrybomb1.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/CherryBomb/cherrybomb2.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/CherryBomb/cherryExplode.png");
				sleep(cooldown);
				hp = 0; // remove the cherry bomb from grid
				grid.clearArea(x, y); // call method to clear nearby zombies
			}
		}, 0);
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

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}