import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shovel extends JPanel {
	private JFrame f;
	private MapScreen m;
	
	public Shovel(JFrame frame, MapScreen map) {
		f = frame;
		m = map;
		
		setLayout(null);
				
		ImageIcon shovel = new ImageIcon(AvH.projectPath + "/imgs/Shovel/shovel.png");
		
		JLabel shovelDisplay = new JLabel(shovel);
		shovelDisplay.setBounds(0, 0, 140, 140);
		
		// Handle user selecting the shovel
		shovelDisplay.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				m.setSelected("shovel");
				f.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(AvH.projectPath + "/imgs/Shovel/shovelCursor.png").getImage(), new Point(0, 0), "Shovel Cursor"));
			}
		});
		
		add(shovelDisplay);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}