import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lawnmower extends Autotroph {
	public Lawnmower(Grid grid, int x, int y) {
		super(x, y);
		img = new ImageIcon(AvH.projectPath + "/imgs/Lawnmower/lawnmower.png");
		hp = 500;

		// Move the lawnmower if it's triggered
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (ready) {
					grid.move(getX(), getY());
					changeX(150);
				}
			}
		}, 0, 200);

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

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	public void changeX(int n) {
		x+=n;
	}
}