import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pea extends JPanel {
	private int type;
	private int x, y;
	private ImageIcon img;

	public Pea(Grid grid, int x, int y) {
		this.x = x + 50;
		this.y = y + 50;

		if (type == 0)
			img = new ImageIcon(AvH.projectPath + "/imgs/Peashooter/GreenPea.png");

		Pea temp = this;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				while (grid.peas.indexOf(temp) != -1) {
					if (getX() > 1600) { // Remove peas once they go outside the grid
						grid.peas.remove(grid.peas.indexOf(temp));
					} else if (grid.collided(temp)) { // Remove collided peas
						grid.peas.remove(grid.peas.indexOf(temp));
					} else {
						setX(getX() + 10);
					}
					sleep(50);
				}

				;
			}
		}, 0, 50);
		
		
				
		
	}
	public void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public int getX() {
		return x;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}