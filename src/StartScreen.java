import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartScreen extends JFrame {

	int startx, starty;
	boolean mode1, mode2;
	CustomButton c1;

	public StartScreen() {
		startx = 0;
		starty = 135;

		setSize(1600, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		ImageIcon icon = new ImageIcon(AvH.projectPath + "/imgs/Interface/title.png");
		JLabel backgroundLabel = new JLabel(icon);
		backgroundLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		add(backgroundLabel);

		setVisible(true);

		int x = startx;
		int y = starty;

		// Create custom buttons
		int[] x1 = new int[] { x, x, x + 495, x + 495, x + 490, x + 450, x + 400, x + 350, x + 300, x + 250, x + 200,
				x + 150, x + 100, x + 50, x + 10 };
		int[] y1 = new int[] { y, y + 100, y + 100, y, y - 10, y - 60, y - 88, y - 105, y - 120, y - 135, y - 120,
				y - 105, y - 88, y - 60, y - 30 };

		Polygon p = new Polygon(x1, y1, x1.length);
		c1 = new CustomButton("Story", p, "Story");
		c1.setBounds(900, 95, 495, 220);
		add(c1);

		y = starty + 35;

		int[] x2 = new int[] { x, x, x + 545, x + 545 };
		int[] y2 = new int[] { y, y + 110, y + 110, y };

		Polygon p2 = new Polygon(x2, y2, x2.length);
		CustomButton c2 = new CustomButton("Infinite", p2, "Infinite");
		c2.setBounds(885, 170, 545, 440);
		add(c2);

		y = starty + 200;
		p2 = new Polygon(x2, y2, x2.length);
		CustomButton c3 = new CustomButton("Maps", p2, "Coming Soon");
		c3.setBounds(885, 310, 545, 440);
		add(c3);

		y = starty + 200;
		p2 = new Polygon(x2, y2, x2.length);
		CustomButton c4 = new CustomButton("Difficulty", p2, "Coming Soon");
		c4.setBounds(885, 450, 545, 440);
		add(c4);

		x2 = new int[] { x, x, x + 545, x + 545 };
		y2 = new int[] { y, y + 150, y + 150, y };

		p2 = new Polygon(x2, y2, x2.length);
		CustomButton c5 = new CustomButton("Creators", p2, "Rak and Tony");
		c5.setBounds(885, 430, 545, 800);
		add(c5);

		c1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Story");
				mode1 = true;
			}
		});

		c2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Infinite");
				mode2 = true;
			}
		});

		getContentPane().setComponentZOrder(c5, 0);
		getContentPane().setComponentZOrder(c4, 0);
		getContentPane().setComponentZOrder(c3, 0);
		getContentPane().setComponentZOrder(c2, 0);
		getContentPane().setComponentZOrder(c1, 0);
		setVisible(true);
	}

	public CustomButton getb1() {
		return c1;
	}

	public boolean isMode1() {
		return mode1;
	}

	public void setMode1(boolean mode1) {
		this.mode1 = mode1;
	}

	public boolean isMode2() {
		return mode2;
	}

	public void setMode2(boolean mode2) {
		this.mode2 = mode2;
	}
}