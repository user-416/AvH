import java.awt.Color;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

public class MapScreen {
	private JFrame frame;
	private Grid grid;
	private int[][] level;
	private String selected;
	private int sun; // Starting out sun
	private int levelnum;
	public ArrayList<Integer> wave = new ArrayList<>();

	public MapScreen(int[][] level, int levelnum) {
		this.level = level;
		this.levelnum = levelnum;
		sun = 300;
	
		JFrame frame = new JFrame();
		frame.setTitle("AvH");
		frame.setSize(1600, 1000);
		frame.setDefaultCloseOperation(3);
		frame.setLayout((LayoutManager) null);
		frame.getContentPane().setBackground(new Color(194, 227, 186));
		this.frame = frame;

		// Create the lawn
		Grid grid = new Grid(frame, this, 10, 5, 150);
		grid.setBounds(50, 200, 1500, 750);
		this.grid = grid;

		Shop shop = new Shop(frame, this, 1300, 180);
		shop.setBounds(0, 0, 1300, 180);

		// Create the shovel
		Shovel shovel = new Shovel(frame, this);
		shovel.setBounds(1380, 20, 140, 140);

		// Add all components to JFrame
		frame.getContentPane().add(grid);
		frame.getContentPane().add(shop);
		frame.getContentPane().add(shovel);
		frame.setVisible(true);
	}
	
	// For infinite mode
	public MapScreen(int levelnum) {
		level = null;
		this.levelnum = levelnum;
		sun = 500;
		wave = new ArrayList<Integer>();
		// Spawn zombies in waves
		
		for(int i = 0; i<(5*levelnum); i++) {
			wave.add(1);
		}
		for(int i = 0; i<(2*levelnum); i++) {
			wave.add(2);
			wave.add(3);
		}
		for(int i = 0; i<(levelnum); i++) {
			wave.add(4);
		}
		if(levelnum%5 == 0) {
			wave.add(5);
		}
		
		Collections.shuffle(wave);
		
		JFrame frame = new JFrame();
		frame.setTitle("AvH");
		frame.setSize(1600, 1000);
		frame.setDefaultCloseOperation(3);
		frame.setLayout((LayoutManager) null);
		frame.getContentPane().setBackground(new Color(194, 227, 186));
		this.frame = frame;

		// Create the lawn
		Grid grid = new Grid(frame, this, 10, 5, 150);
		grid.setBounds(50, 200, 1500, 750);
		this.grid = grid;

		Shop shop = new Shop(frame, this, 1300, 180);
		shop.setBounds(0, 0, 1300, 180);

		// Create the shovel
		Shovel shovel = new Shovel(frame, this);
		shovel.setBounds(1380, 20, 140, 140);

		// Add all components to JFrame
		frame.getContentPane().add(grid);
		frame.getContentPane().add(shop);
		frame.getContentPane().add(shovel);
		frame.setVisible(true);
	}

	public int getSum() {
		int sum = 0;
		for(int i = 0; i<wave.size(); i++) {
			sum+=wave.get(i);
		}
		return sum;
	}
	
	
	// Close the frame when the player loses
	public void gameOver() {
		grid.setGameOver(true);
	}
	
	public void victory() {
		grid.setVictory(true);
	}
	
	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getLevelNum() {
		return levelnum;
	}

	public String getSelected() {
		return this.selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public int getSun() {
		return this.sun;
	}

	public void changeSun(int n) {
		sun += n;
	}

	public int[][] getLevel() {
		return level;
	}

	public void setLevel(int[][] level) {
		this.level = level;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}