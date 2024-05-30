import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shop extends JPanel {
	private MapScreen m; // Parent MapScreen
	private int w; // width of shop
	private int h; // height of shop

	public Shop(JFrame frame, MapScreen map, int width, int height) {
		m = map;
		w = width;
		h = height;

		setLayout(null);

		// Draw sun counter
		ImageIcon sun = new ImageIcon(AvH.projectPath + "/imgs/Sunflower/sunIcon.png");
		JLabel sunDisplay = new JLabel(sun);

		sunDisplay.setBounds(20, 40, 100, 80);

		// Draw autotrophs and add mouse listener
		ImageIcon peashooter = new ImageIcon(AvH.projectPath + "/imgs/Shop/PeashooterShop.png");
		JLabel peaDisplay = new JLabel(peashooter);

		peaDisplay.setBounds(160, 20, 100, 140);
		peaDisplay.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (m.getSun() < 100) {
					System.out.println("Can't buy Peashooter");
					return;
				}
				m.setSelected("Peashooter");
				frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(AvH.projectPath + "/imgs/Peashooter/peashooter2.png").getImage(), new Point(0, 0),
						"Peashooter Cursor"));
			}
		});

		ImageIcon sunflower = new ImageIcon(AvH.projectPath + "/imgs/Shop/SunflowerShop.png");
		JLabel sunfDisplay = new JLabel(sunflower);

		sunfDisplay.setBounds(320, 20, 100, 140);
		sunfDisplay.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (m.getSun() < 50) {
					System.out.println("Can't buy Sunflower");
					return;
				}
				m.setSelected("Sunflower");
				frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(AvH.projectPath + "/imgs/Sunflower/sunflower2.png").getImage(), new Point(0, 0),
						"Sunflower Cursor"));
			}
		});

		ImageIcon wallnut = new ImageIcon(AvH.projectPath + "/imgs/Shop/WallnutShop.png");
		JLabel wallDisplay = new JLabel(wallnut);

		wallDisplay.setBounds(480, 20, 100, 140);
		wallDisplay.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (m.getSun() < 50) {
					System.out.println("Can't buy Wallnut");
					return;
				}
				m.setSelected("Wallnut");
				frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(AvH.projectPath + "/imgs/Wallnut/wallnut2.png").getImage(), new Point(0, 0),
						"Wallnut Cursor"));
			}
		});

		ImageIcon squash = new ImageIcon(AvH.projectPath + "/imgs/Shop/SquashShop.png");
		JLabel squashDisplay = new JLabel(squash);

		squashDisplay.setBounds(640, 20, 100, 140);
		squashDisplay.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (m.getSun() < 50) {
					System.out.println("Can't buy Squash");
					return;
				}
				m.setSelected("Squash");
				frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(AvH.projectPath + "/imgs/Squash/squash2.png").getImage(), new Point(0, 0),
						"Squash Cursor"));
			}
		});

		ImageIcon jala = new ImageIcon(AvH.projectPath + "/imgs/Shop/JalapenoShop.png");
		JLabel jalaDisplay = new JLabel(jala);

		jalaDisplay.setBounds(800, 20, 100, 140);
		jalaDisplay.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (m.getSun() < 125) {
					System.out.println("Can't buy Jalapeno");
					return;
				}
				m.setSelected("Jalapeno");
				frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(AvH.projectPath + "/imgs/Jalapeno/jalapeno2.png").getImage(), new Point(0, 0),
						"Jalapeno Cursor"));
			}
		});

		ImageIcon cactus = new ImageIcon(AvH.projectPath + "/imgs/Shop/CactusShop.png");
		JLabel cactusDisplay = new JLabel(cactus);

		cactusDisplay.setBounds(960, 20, 100, 140);
		cactusDisplay.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (m.getSun() < 125) {
					System.out.println("Can't buy Cactus");
					return;
				}
				m.setSelected("Cactus");
				frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(AvH.projectPath + "/imgs/Cactus/cactus2.png").getImage(), new Point(0, 0),
						"Cactus Cursor"));
			}
		});
		
		ImageIcon cherry = new ImageIcon(AvH.projectPath + "/imgs/Shop/CherryBombShop.png");
		JLabel cherryDisplay = new JLabel(cherry);

		cherryDisplay.setBounds(1120, 20, 100, 140);
		cherryDisplay.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (m.getSun() < 150) {
					System.out.println("Can't buy Cherry Bomb");
					return;
				}
				m.setSelected("Cherry Bomb");
				frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(AvH.projectPath + "/imgs/CherryBomb/cherrybomb2.png").getImage(), new Point(0, 0),
						"Cherry Bomb Cursor"));
			}
		});

		add(sunDisplay);
		add(peaDisplay);
		add(sunfDisplay);
		add(wallDisplay);
		add(squashDisplay);
		add(jalaDisplay);
		add(cactusDisplay);
		add(cherryDisplay);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (m.getSelected() == null)
					return;

				frame.setCursor(Cursor.DEFAULT_CURSOR);
				m.setSelected(null);
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g.setColor(new Color(135, 111, 97));
		g.fillRect(0, 0, this.w, this.h);

		g.setColor(new Color(210, 180, 140));
		g.fillRect(20, 120, 100, 32);

		// Display number of suns
		g.setColor(Color.black);
		g2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 28));
		g.drawString("" + m.getSun(), 50, 145);

		repaint();
	}
}