import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NecrodancerV2 {

	static final int row = 13;
	static final int col = 13;
	static int[][] map = new int[row][col];
	static int hp = 3;
	static int beat = 0;
	static int gold = 0;
	static int combo =0;
	static char pDir;
	static Timer timer = new Timer();
	static Taulell t = new Taulell();
	static Finestra f = new Finestra(t);
	static Scanner sc = new Scanner(System.in);
	// static List<Slime> slimes = new ArrayList<Slime>();
	static Slime sl1 = new Slime(5, 4);
	static Slime sl2 = new Slime(5, 9);

	public static void main(String[] args) {

		inicialitza();
		initgfx();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				play();
			}
		}, 0, 250);

		play();
	}

	public static void inicialitza() {
		int i, j;

		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
					map[i][j] = 1;
				}

				else if (i == row - 2 && j == col / 2) {
					map[i][j] = 5;
				}

				else {
					map[i][j] = 0;
				}
			}
		}

	}

	private static void initgfx() {
		// background
		t.setActimgbackground(true);
		t.setImgbackground("ground.png");
		t.setPAD(0);

		// imagenes
		t.setActimatges(true);
		// 0:suelo
		// 1:pared
		// 2:slime
		// 3:TODO slime herido
		// 4:TODO cadaver de slime
		// 5:jugador
		// 6:metronomo
		// 7:cadaver de jugador
		// 8:
		// 9:
		// 10:Salida
		String[] imatges = { "ground.png", "wall.png", "slime.png", "", "", "player.png", "invWall.png", "corpse.png",
				"", "", "staircase.png", "", "", "" };
		t.setImatges(imatges);

	}

	public static void play() {
		int i, j;
		beat++;
		if (hp == 0) {
			for (i = 0; i < row; i++) {
				for (j = 0; j < col; j++) {
					if (map[i][j] == 5) {
						map[i][j] = 7;
					}
				}
			}
			view();
			System.out.println("Has mort");
			timer.cancel();
		}

		if (beat % 4 == 0) {
			map[row - 1][col / 2] = 6;
			view();
			pDir = ' ';

		} else if (beat % 4 == 2) {
			sl1.mv();
			sl2.mv();
			mv(pDir);
			map[row - 1][col / 2] = 1;

			view();

		}
	}

	public static void mv(char dir) {
		int i, j, iTar = 0, jTar = 0, iPos = 0, jPos = 0;

		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				if (map[i][j] == 5) {
					iPos = i;
					jPos = j;
				}
			}
		}

		switch (dir) {

		case 'w': {
			iTar = iPos - 1;
			jTar = jPos;
			combo++;
			System.out.println("combo: "+combo);
			break;
		}

		case 'a': {
			iTar = iPos;
			jTar = jPos - 1;
			combo++;
			System.out.println("combo: "+combo);
			break;
		}

		case 'd': {
			iTar = iPos;
			jTar = jPos + 1;
			combo++;
			System.out.println("combo: "+combo);
			break;
		}

		case 's': {
			iTar = iPos + 1;
			jTar = jPos;
			combo++;
			System.out.println("combo: "+combo);
			break;
		}

		default: {
			System.out.println("fail");
			combo=0;
		}

		}

		if (map[iTar][jTar] == 0) {
			map[iTar][jTar] = 5;
			map[iPos][jPos] = 0;
		} else if (map[iTar][jTar] == 2) {
			if (sl1.psx == iTar && sl1.psy == jTar) {
				sl1.hp--;
				System.out.println("slime -1 hp ");
				if (sl1.hp == 0) {
					sl1.kill();
					map[iTar][jTar] = 5;
					map[iPos][jPos] = 0;
					if (sl2.ded) {
						map[2][col / 2] = 10;
					}
				}
			}

			else if (sl2.psx == iTar && sl2.psy == jTar) {
				sl2.hp--;
				System.out.println("slime -1 hp ");
				if (sl2.hp == 0) {
					sl2.kill();
					map[iTar][jTar] = 5;
					map[iPos][jPos] = 0;
					if (sl1.ded) {
						map[2][col / 2] = 10;
					}
				}
			}

		} else if (map[iTar][jTar] == 3) {
			map[iTar][jTar] = 5;
			map[iPos][jPos] = 0;
			gold = gold + (int) (Math.random() * 16);
		}

		else if (map[iTar][jTar] == 10) {
			map[iTar][jTar] = 5;
			map[iPos][jPos] = 0;
			System.out.println("Fi");
			timer.cancel();

		}

	}

	/*
	 * private static void slimeMv() { int i; Iterator slIter = slimes.iterator();
	 * while(slIter.hasNext()) { Slime.mv(); Slime.test();
	 * 
	 * Slime next = (Slime) slIter.next(); }
	 * 
	 * }
	 */

	public static void visualitzaConsola() {
		int i, j;

		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static void view() {
		t.dibuixa(map);
	}

	public static void lightBumper() {
		
	}
}
