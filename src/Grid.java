import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grid extends JPanel {
	private MapScreen m; // Parent MapScreen
	private JFrame f;
	private int w; // width of grid
	private int h; // height of grid
	private int s; // unit side length
	private Autotroph[][] spaces; // Array that represents the grid spaces
	private Sun falling = new Sun(this, (int) (Math.random() * 1200), -100); // Regenerating sun that falls
	public ArrayList<Pea> peas = new ArrayList<>(); // ArrayList of peas
	private ArrayList<Heterotroph> zombies = new ArrayList<>(); // ArrayList of zombies
	private ArrayList<Thread> threads = new ArrayList<Thread>(); // ArrayList of parallel Threads
	private int time; // Game clock
	private int ind; // Level stage
	private boolean gameOver;
	private boolean victory;

	public Grid(JFrame frame, MapScreen map, int width, int height, int side) {
		w = width;
		h = height;
		s = side;
		f = frame;
		m = map;
		spaces = new Autotroph[height][width];
		threads = new ArrayList<Thread>();

		setLayout(null);

		// Create the lawnmowers on the leftmost/pavement part of the grid
		for (int i = 0; i < spaces.length; i++) {
			spaces[i][0] = new Lawnmower(this, 0, i * s);
		}

		// Add mouse listener to grid
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (map.getSelected() == null)
					return;

				// Do something based on the object selected when clicking
				switch (map.getSelected()) {
				case "shovel":
					int tileR = e.getY() / s;
					int tileC = e.getX() / s;

					// Dig up anything except lawnmowers
					if (!(spaces[tileR][tileC] instanceof Lawnmower)) {
						spaces[tileR][tileC] = null;
					}

					frame.setCursor(Cursor.DEFAULT_CURSOR);
					break;
				case "Peashooter":
					Peashooter peashooter = new Peashooter(e.getX() / s * s, e.getY() / s * s);

					if (plant(peashooter))
						map.changeSun(-100);

					System.out.println("Planted Peashooter");
					frame.setCursor(Cursor.DEFAULT_CURSOR);
					break;
				case "Sunflower":
					Sunflower sunflower = new Sunflower(e.getX() / s * s, e.getY() / s * s);

					if (plant(sunflower))
						map.changeSun(-50);

					System.out.println("Planted Sunflower");
					frame.setCursor(Cursor.DEFAULT_CURSOR);
					break;
				case "Wallnut":
					Wallnut wallnut = new Wallnut(e.getX() / s * s, e.getY() / s * s);

					if (plant(wallnut))
						map.changeSun(-50);

					System.out.println("Planted Wallnut");
					frame.setCursor(Cursor.DEFAULT_CURSOR);
					break;
				case "Squash":
					Squash squash = new Squash(e.getX() / s * s, e.getY() / s * s);

					if (plant(squash))
						map.changeSun(-50);

					System.out.println("Planted Squash");
					frame.setCursor(Cursor.DEFAULT_CURSOR);
					break;
				case "Jalapeno":
					Jalapeno jala = new Jalapeno(e.getX() / s * s, e.getY() / s * s);

					if (plant(jala))
						map.changeSun(-125);

					System.out.println("Planted Jalapeno");
					frame.setCursor(Cursor.DEFAULT_CURSOR);
					break;
				case "Cactus":
					Cactus cactus = new Cactus(e.getX() / s * s, e.getY() / s * s);

					if (plant(cactus))
						map.changeSun(-125);

					System.out.println("Planted Cactus");
					frame.setCursor(Cursor.DEFAULT_CURSOR);
					break;
				case "Cherry Bomb":
					CherryBomb cherry = new CherryBomb(e.getX() / s * s, e.getY() / s * s);

					if (plant(cherry)) {
						map.changeSun(-150);
					}

					System.out.println("Planted Cherry Bomb");
					frame.setCursor(Cursor.DEFAULT_CURSOR);
					break;
				}

				map.setSelected(null);
				f.repaint();
			}
		});

		// Repaint every 50 ms
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (map.getLevel() != null) {
					if (ind == map.getLevel()[0].length) { // Prevent out of bounds
						if (zombies.size() == 0) { // If there are no more zombies to spawn and no more zombies on the
													// field, end the level
							timer.cancel();
							if (Arrays.deepEquals(map.getLevel(), AvH.level1))
								AvH.advance(AvH.level2, 2);
							else if (Arrays.deepEquals(map.getLevel(), AvH.level2))
								AvH.advance(AvH.level3, 3);
							else
								m.victory();
						}
					} else { // Spawn zombies
						if (time == map.getLevel()[0][ind]) {
							switch (map.getLevel()[1][ind]) {
							case 1:
								Zombie z = new Zombie(1450, 150 * (int) (5 * Math.random()));
								zombies.add((Heterotroph) z);
								Thread t1 = new Thread(z);
								threads.add(t1);
								t1.start();
								break;
							case 2:
								Conehead c = new Conehead(1450, 150 * (int) (5 * Math.random()));
								zombies.add((Heterotroph) c);
								Thread t2 = new Thread(c);
								threads.add(t2);
								t2.start();
								break;
							case 3:
								Buckethead b = new Buckethead(1450, 0);
								zombies.add((Heterotroph) b);
								Thread t3 = new Thread(b);
								threads.add(t3);
								t3.start();
								break;
							case 4:
								Vaulter v = new Vaulter(1450, 0);
								zombies.add((Heterotroph) v);
								Thread t4 = new Thread(v);
								threads.add(t4);
								t4.start();
								break;
							}
							ind++;
						}
					}
				} else { // Spawn in zombies for infinite waves
					if (map.getSum() == 0 && zombies.size() == 0) {
						timer.cancel();
						AvH.infadvance(map.getLevelNum() + 1);
					} else if ((time + 500) % 5000 <= 10) {
						int n = map.getLevelNum() / 5 + 5;
						if (map.wave.size() < n) {
							for (int i = 0; i < map.wave.size(); i++) {
								switch (map.wave.get(0)) {
								case 1:
									Zombie z = new Zombie(1450, 150 * (int) (5 * Math.random()));
									zombies.add((Heterotroph) z);
									Thread t1 = new Thread(z);
									threads.add(t1);
									t1.start();
									break;
								case 2:
									Conehead c = new Conehead(1450, 150 * (int) (5 * Math.random()));
									zombies.add((Heterotroph) c);
									Thread t2 = new Thread(c);
									threads.add(t2);
									t2.start();
									break;
								case 3:
									Buckethead b = new Buckethead(1450, 0);
									zombies.add((Heterotroph) b);
									Thread t3 = new Thread(b);
									threads.add(t3);
									t3.start();
									break;
								case 4:
									Vaulter v = new Vaulter(1450, 0);
									zombies.add((Heterotroph) v);
									Thread t4 = new Thread(v);
									threads.add(t4);
									t4.start();
									break;
								case 5:
									Boss bo = new Boss(1300, 150);
									bo.setMap(map);
									zombies.add((Heterotroph) bo);
									Thread t5 = new Thread(bo);
									t5.start();
									break;
								}
								map.wave.remove(0);
							}
						} else {
							for (int i = 0; i < n; i++) {
								switch (map.wave.get(0)) {
								case 1:
									Zombie z = new Zombie(1450, 150 * (int) (5 * Math.random()));
									zombies.add((Heterotroph) z);
									Thread t1 = new Thread(z);
									threads.add(t1);
									t1.start();
									break;
								case 2:
									Conehead c = new Conehead(1450, 150 * (int) (5 * Math.random()));
									zombies.add((Heterotroph) c);
									Thread t2 = new Thread(c);
									threads.add(t2);
									t2.start();
									break;
								case 3:
									Buckethead b = new Buckethead(1450, 0);
									zombies.add((Heterotroph) b);
									Thread t3 = new Thread(b);
									threads.add(t3);
									t3.start();
									break;
								case 4:
									Vaulter v = new Vaulter(1450, 0);
									zombies.add((Heterotroph) v);
									Thread t4 = new Thread(v);
									threads.add(t4);
									t4.start();
									break;
								case 5:
									Boss bo = new Boss(1000, 150);
									bo.setMap(map);
									zombies.add((Heterotroph) bo);
									Thread t5 = new Thread(bo);
									t5.start();
									break;
								}
								map.wave.remove(0);
							}
						}
					}
				}
				// Draw falling sun
				if (falling.getY() > 750) {
					falling.setX((int) (Math.random() * 1200));
					falling.setY(-200);
				} else {
					falling.setY(falling.getY() + 1);
				}
				falling.setBounds(falling.getX(), falling.getY(), s, s);
				add(falling);

				repaint();
				time += 50;
			}
		}, 0, 50);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Iterate through each grid spot
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				// Draw the grid
				if (c == 0)
					g.setColor(new Color(220, 220, 220));
				else if (r % 2 == c % 2)
					g.setColor(new Color(58, 148, 82));
				else
					g.setColor(new Color(34, 199, 56));
				g.fillRect(c * s, r * s, s, s);

				// Draw whatever's in the grid
				Autotroph a = spaces[r][c];
				if (a != null) {
					if (a.getHp() <= 0) {
						spaces[r][c] = null;
					} else if (a instanceof Squash) {
						ImageIcon icon = a.getImg();
						icon.paintIcon(this, g, a.getX(), a.getY());
					} else {
						ImageIcon icon = a.getImg();
						icon.paintIcon(this, g, c * s, r * s);
						if (a instanceof Sunflower) {
							Sunflower sf = (Sunflower) a;
							if (sf.isReady() && sf.getS() == null) { // Generate sun
								Sun sun = new Sun(this, c * s, r * s, sf);
								sun.setBounds(c * s, r * s, s, s);
								add(sun);
								sf.setS(sun);
								System.out.println("Created sun");
							}
						} else if (a instanceof Peashooter) {
							Peashooter ps = (Peashooter) a;
							if (ps.isReady()) { // Generate peas
								ps.setReady(false);
								Pea pea = new Pea(this, ps.getX(), ps.getY());
								peas.add(pea);
							}
						} else if (a instanceof Jalapeno) { // Let them access grid for its methods
							((Jalapeno) a).setGrid(this);
						} else if (a instanceof CherryBomb) {
							((CherryBomb) a).setGrid(this);
						}
					}
				}
			}
			// Display the level number
			if (time < 4000) {
				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 100));
				g.drawString("Level: " + m.getLevelNum(), 600, 350);
			}
		}

		// Update Pea positions
		for (Pea p : peas) {
			if (p != null) {
				ImageIcon icon = p.getImg();
				icon.paintIcon(this, g, p.getX(), p.getY());
			}
		}

		// Update Zombies
		for (int i = zombies.size() - 1; i >= 0; i--) {
			Heterotroph h = zombies.get(i);

			if (h.getHp() <= 0) {
				zombies.remove(i); // Remove dead zombies
			} else if (inRange(h)) { // Handle interactions with plants
				h.setReady(true);
				if (!(h instanceof Vaulter && ((Vaulter) h).hasPole())) {
					Autotroph a = spaces[h.getY() / 150][(h.getX() + 50) / 150];
					if (a instanceof Cactus) {
						h.changeHp(-1);
					} else if (a instanceof Squash) {
						a.setReady(true);
						((Squash) a).setTarget(h);
					} else if (a instanceof Lawnmower) {
						h.makeDead();
						a.setReady(true);
					}
					a.setHp(a.getHp() - 1); // Eating
				}
			} else { // If there are no plants in range, just move
				h.setReady(false);
				h.setX(h.getX() - h.getSpeed());
			}

			// If any zombie makes it beyond the left edge of the grid, end the game
			if (h.getX() < -150)
				m.gameOver();

			ImageIcon icon = h.getImg();
			icon.paintIcon(this, g, h.getX(), h.getY());
		}

		if (gameOver) {
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("GAME OVER", 600, 350);
		}

		if (victory) {
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("VICTORY", 600, 350);
		}
	}

	// Move the lawnmower over one grid space
	public void move(int x, int y) {
		Lawnmower l = (Lawnmower) spaces[y / 150][x / 150];
		spaces[y / 150][x / 150] = null;
		if (l.getX() == 1350) {
			l.setReady(false);
		} else {
			spaces[y / 150][x / 150 + 1] = l;
		}

	}

	// Clear a 200px radius around a cherry bomb
	public void clearArea(int x, int y) {
		for (int i = zombies.size() - 1; i >= 0; i--) {
			if (Math.abs(zombies.get(i).getX() - x) < 200 && Math.abs(zombies.get(i).getY() - y) < 200)
				if (!(zombies.get(i) instanceof Boss))
					zombies.get(i).makeDead();
		}
	}

	// Clear the row the jalapeno is in
	public void clearRow(int r) {
		for (int i = zombies.size() - 1; i >= 0; i--) {
			if (zombies.get(i).getY() / s == r)
				if (!(zombies.get(i) instanceof Boss))
					zombies.get(i).makeDead();
		}
	}

	// Check if there are plants near the zombie for it to interact with
	public boolean inRange(Heterotroph h) {
		if (h instanceof Boss)
			return false;

		if (h.getX() >= 1300 || h.getX() < 0) {
			return false;
		}

		return spaces[h.getY() / 150][(h.getX() + 50) / 150] != null;
	}

	// Check collisions with pea
	public boolean collided(Pea p) {
		for (int i = 0; i < zombies.size(); i++) {
			Heterotroph z = zombies.get(i);
			if (z instanceof Boss) {
				if (Math.abs(p.getX() - z.getX() - 100) < 10 && ((p.getY() == z.getY() + 50)
						|| (p.getY() == z.getY() + 200) || (p.getY() == z.getY() + 350))) {
					z.changeHp(-50);
					return true;
				}
			} else {
				if (Math.abs(p.getX() - z.getX()) < 10 && p.getY() == z.getY() + 50) {
					z.changeHp(-50);
					return true;
				}
			}
		}
		return false;
	}

	// Check if there's an open space to plant a plant
	public boolean plant(Autotroph a) {
		int tileR = a.getY() / s;
		int tileC = a.getX() / s;

		if (spaces[tileR][tileC] == null) {
			spaces[tileR][tileC] = a;
			return true;
		}

		return false;
	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MapScreen getM() {
		return m;
	}

	public void setM(MapScreen m) {
		this.m = m;
	}

	public JFrame getF() {
		return f;
	}

	public void setF(JFrame f) {
		this.f = f;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public Autotroph[][] getSpaces() {
		return spaces;
	}

	public void clearSpace(int x, int y) {
		spaces[x][y] = null;
	}

	public void setSpaces(Autotroph[][] spaces) {
		this.spaces = spaces;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}
}