
public class Slime {

	int hp;
	int mvDir;
	int psx;
	int psy;
	boolean ded = false;

	public Slime(int posx, int posy) {
		hp = 2;
		mvDir = 0;
		psx = posx;
		psy = posy;
		

		// NecrodancerV2.slimes.add(this);
	}

	public void mv() {
		int i = 0, j = 0;

		if (ded == false) {
			switch (mvDir % 4) {
			case 0: {
				i = psx - 1;
				j = psy - 1;
				break;
			}
			case 1: {
				i = psx - 1;
				j = psy + 1;
				break;
			}

			case 2: {
				i = psx + 1;
				j = psy + 1;
				break;
			}

			case 3: {
				i = psx + 1;
				j = psy - 1;
				break;
			}
			}

			if (i > 0 && i < NecrodancerV2.row && j > 0 && j < NecrodancerV2.col)
				if (NecrodancerV2.map[i][j] == 0) {
					NecrodancerV2.map[i][j] = 2;
					NecrodancerV2.map[psx][psy] = 0;
					psx = i;
					psy = j;
					mvDir++;
				}

				else if (NecrodancerV2.map[i][j] == 5) {
					NecrodancerV2.hp--;
					System.out.println("Slime te golpea por 1 punto. hp restante: "+NecrodancerV2.hp);
				}
		}

	}

	public void kill() {
		ded = true;
		NecrodancerV2.gold= NecrodancerV2.gold + (int) (Math.random() * (10+NecrodancerV2.combo));
		System.out.println("Oro: "+NecrodancerV2.gold);
		NecrodancerV2.map[psx][psy] = 0;

	}

	public void test() {
		System.out.println(hp);
		System.out.println(mvDir);
		System.out.println(psx);
		System.out.println(psy);
	}
}
