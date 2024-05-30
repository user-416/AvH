import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sun extends JPanel {
	private Grid grid;
	private Sunflower s;
	private int x, y;
	private boolean falling;
	private ImageIcon img;

	public Sun(Grid grid, int x, int y) {
		this.grid = grid;
		this.x = x;
		this.y = y;
		img = new ImageIcon(AvH.projectPath + "/imgs/Sunflower/sunIcon.png");
		JLabel display = new JLabel(img);
		display.setBounds(0, 0, 80, 80);
		setOpaque(false);
		add(display);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				grid.getM().changeSun(25);
				
				// Reset cooldown on sunflower
				if (s!=null) {
					s.setReady(false);
					s.setS(null);
					remove(display);
					setBounds(0, 0, 0, 0);
				} else {
					setY(2000); // Reset the regenerating falling sun
				}
				System.out.println("Sun collected");
			}
		});
	}

	public Sun(Grid grid, int x, int y, Sunflower s) {
		this(grid, x, y);
		this.s = s;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public int getX() {
		return x;
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
