
public class Slime extends Enemy {

	public Slime(int posx, int posy, int mvDire) {
		super(posx, posy, mvDire);
		hp = 2;
		dmg = 1;
		gValue = 10;
		NecrodancerV2.slimes.add(this);
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

			if (i >= 0 && i < NecrodancerV2.row && j >= 0 && j < NecrodancerV2.col) {

				if (NecrodancerV2.map[i][j] == 0) {
					NecrodancerV2.map[psx][psy] = 0;
					psx = i;
					psy = j;
					mvDir++;
				}

				else if (NecrodancerV2.map[i][j] == 5) {
					((Character) this).attac(NecrodancerV2.p);
				}
			}
		}
	}
}
