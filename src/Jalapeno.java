import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Jalapeno extends Autotroph {
	private Grid grid;
	
	public Jalapeno(int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Jalapeno/jalapeno2.png");
		hp = 150;
		cooldown = 200;

		// Animate the jalapeno
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				img = new ImageIcon(AvH.projectPath + "/imgs/Jalapeno/jalapeno1.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Jalapeno/jalapeno2.png");
				sleep(cooldown);
				img = new ImageIcon(AvH.projectPath + "/imgs/Jalapeno/jalapeno3.png");
				sleep(cooldown);
				hp = 0; // Remove jalapeno from the grid once it explodes
				grid.clearRow(y/grid.getS()); // Clear the jalapeno's row of zombies
			}
		}, 0);
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

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}