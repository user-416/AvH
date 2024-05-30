import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Heterotroph extends JPanel {
	protected int x, y; // Coordinates on the grid
	protected int hp;
	protected int speed;
	protected int cooldown;
	protected volatile boolean ready;
	protected ImageIcon img;
	
	public Heterotroph(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void makeDead() {
		hp = 0;
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

	public int getHp() {
		return hp;
	}

	public void changeHp(int n) {
		hp+=n;
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

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}