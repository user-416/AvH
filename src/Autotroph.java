import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Autotroph extends JPanel {
	protected int x, y; // coordinates on the grid
	protected int hp; 
	protected int cooldown; // custom delay
	protected volatile boolean ready; // if the plant can perform its action
	protected ImageIcon img;
	
	public Autotroph(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void makeDead() {
		hp = 0;
	}

	public ImageIcon getImg() {
		return img;
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

	public int getCooldown() {
		return cooldown;
	}
	
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
}