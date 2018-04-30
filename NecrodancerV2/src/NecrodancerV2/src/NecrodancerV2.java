import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class NecrodancerV2 {

	static final int row = 13;
	static final int col = 13;
	static int[][] map = new int[row][col];
	static int beat = 0;
	static int enemyCounter = 0;
	static Timer timer = new Timer();
	static Taulell t = new Taulell();
	static Finestra f = new Finestra(t);
	static Scanner sc = new Scanner(System.in);
	static List<Entity> entities = new ArrayList<Entity>();
	static List<Character> characters = new ArrayList<Character>();
	static List<Enemy> enemies = new ArrayList<Enemy>();
	static List<Slime> slimes = new ArrayList<Slime>();
	static List<Ske> skeletons = new ArrayList<Ske>();
	static List<Proyectile> proyectiles = new ArrayList<Proyectile>();
	static Player p = new Player(row - 2, col / 2);

	public static void main(String[] args) {

		inicialitza();
		initgfx();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				play();
			}
		}, 0, 250);

	}

	public static void inicialitza() {
		int i, j;

		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
					map[i][j] = 1;
				}

				else {
					map[i][j] = 0;
				}
			}
		}

		new Slime(5, 4, 0);
		new Slime(5, 9, 0);
		new Ske(1, 6, 0, 'v');
		new Ske(8, 1, 0, 'h');

	}

	private static void initgfx() {
		// background
		t.setActimgbackground(true);
		t.setImgbackground("ground.png");
		t.setPAD(0);

		t.setActimatges(true);
		// 0: suelo
		// 1: pared
		// 2: slime
		// 3: 
		// 4: ske
		// 5: jugador
		// 6: metronomo
		// 7: cadaver de jugador
		// 8:
		// 9:
		// 10: Salida
		// 11: Proyectil horizontal hacia la izquierda
		// 12: Proyectil Horizontal hacia la derecha
		// 13: Proyectil vertical hacia abajo
		// 14: Proyectil vertical hacia arriba
		String[] imatges = { "ground.png", "wall.png", "slime.png", "", "ske.png", "player.png", "invWall.png",
				"corpse.png", "", "", "staircase.png", "proyectileHL.png", "proyectileHR.png", "proyectileVD.png", "proyectileVU.png" };
		t.setImatges(imatges);

	}

	public static void play() {
		int k;
		beat++;

		if (beat % 4 == 0) {
			map[row - 1][col / 2] = 6;
			for(k=0;k<entities.size();k++) {
				entities.get(k).refreshPs();
			}
			view();
			p.dir = ' ';

		} else if (beat % 4 == 2) {

			for (k = 0; k < entities.size(); k++) {
				entities.get(k).mv();
				entities.get(k).refreshPs();
				System.out.println(entities.get(k).id);
			}
			map[row - 1][col / 2] = 1;
			for(k=0;k<entities.size();k++) {
				entities.get(k).refreshPs();
			}
			view();
		}
	}

	public static void view() {
		t.dibuixa(map);
	}

	public static void printMap() {
		int i, j;

		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
