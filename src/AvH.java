public class AvH {
	private static StartScreen s;
	private static MapScreen map; // Child MapScreen
	public static String projectPath = System.getProperty("user.dir"); // Directory of project

	// For each level, there's one array of times, and a corresponding array of
	// zombie spawns
	public static int[][] level1 = {
			{ 5000, 10000, 11000, 15000, 16000, 23000, 26000, 30000, 31000, 35000, 37000, 40000, 42000, 43000, 45000,
					46000, 46050, 47000, 47050, 48000, 48050, 49000, 49050 },
			{ 1, 1, 2, 1, 1, 2, 1, 3, 1, 1, 3, 1, 2, 1, 2, 3, 3, 2, 2, 3, 3, 2, 3 } };
	public static int[][] level2 = {
			{ 5000, 10000, 11000, 13000, 15000, 20000, 22000, 23000, 24000, 25000, 26000, 30000, 31000, 32000, 33000,
					34000, 34050, 35000, 35050, 36000, 36050, 38000, 39050, 41000, 42000, 43000, 44000, 46000, 46050,
					47000, 47050, 48000, 48050, 49000, 49050, 50000 },
			{ 1, 1, 1, 2, 1, 3, 4, 3, 2, 2, 3, 4, 1, 1, 3, 1, 3, 1, 4, 2, 1, 4, 2, 2, 2, 1, 3, 1, 4, 4, 3, 2, 3, 4, 3,
					2 } };
	public static int[][] level3 = {
			{ 5000, 10000, 11000, 13000, 15000, 20000, 20050, 21000, 21050, 22000, 23000, 24000, 25000, 26000, 30000,
					31000, 32000, 33000, 34000, 34050, 35000, 35050, 36000, 36050, 37000, 37050, 38000, 39050, 41000,
					42000, 43000, 44000, 46000, 46050, 47000, 47050, 48000, 48050, 49000, 49050, 50000, 50050, 51000,
					51050, 52000, 52050, 53000, 53050, 54000, 54050, 55000, 55050, 56000, 57000, 57050, 58000, 58050,
					59000, 59050, 60000, 61000, 63000, 63050, 64000, 65050, 66000, 67050, 68000, 68050, 69000, 70050,
					73000, 74050, 79000, 80050, 81000, 81050 },
			{ 3, 3, 4, 4, 2, 3, 2, 1, 4, 2, 4, 1, 2, 3, 1, 4, 3, 3, 2, 4, 1, 4, 2, 1, 3, 2, 4, 2, 1, 3, 3, 2, 4, 3, 2,
					3, 2, 4, 2, 1, 3, 3, 2, 3, 1, 3, 4, 2, 4, 3, 3, 2, 4, 3, 3, 2, 2, 3, 2, 1, 1, 4, 3, 3, 3, 2, 4, 3,
					2, 3, 4, 4, 4, 4, 3, 3, 2 } };

	public static void main(String[] args) {
		// Create main menu screen
		s = new StartScreen();

		while (true) {
			play();
			// Run while the mode isn't over
			while (!(map.getGrid().isGameOver() || map.getGrid().isVictory())) {
				sleep(10);
			}
			
			sleep(4000);
			map.getFrame().dispose();
		}
	}

	public static void play() {

		// Open menu
		s.setVisible(true);

		while (!s.mode1 && !s.mode2) {
			sleep(10);
		}
		
		// Close menu
		s.setVisible(false);
		if (s.mode1) {
			map = new MapScreen(level1, 1);
		} else {
			map = new MapScreen(1);
		}

		s.setMode1(false);
		s.setMode2(false);
	}

	// Code to advance a level - close the current level and open the next
	public static void advance(int[][] level, int levelnum) {
		map.getFrame().dispose();
		map = new MapScreen(level, levelnum);
	}

	public static void infadvance(int levelnum) {
		map.getFrame().dispose();
		map = new MapScreen(levelnum);
	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}