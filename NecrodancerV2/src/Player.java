
public class Player extends Character {

	protected int combo;
	protected char dir;

	public Player(int posx, int posy) {
		super(posx, posy);

		hp = 6;
		dmg = 1;
		gValue = 0;
		combo = 0;
		
		this.refreshPs();
	}

	public void mv() {
		int k, iTar = 0, jTar = 0;

		switch (dir) {

		case 'w': {
			iTar = psx - 1;
			jTar = psy;
			break;
		}

		case 'a': {
			iTar = psx;
			jTar = psy - 1;
			break;
		}

		case 'd': {
			iTar = psx;
			jTar = psy + 1;
			break;
		}

		case 's': {
			iTar = psx + 1;
			jTar = psy;
			break;
		}

		default: {
			System.out.println("fail");
			combo = 0;
			break;
		}

		}
		combo++;

		if (NecrodancerV2.map[iTar][jTar] == 0) {
			NecrodancerV2.map[psx][psy] = 0;
			psx = iTar;
			psy = jTar;
			this.refreshPs();
		}

		else if (NecrodancerV2.map[iTar][jTar] == 10) {
			NecrodancerV2.map[iTar][jTar] = 5;
			NecrodancerV2.map[psx][psx] = 0;
			NecrodancerV2.view();
			System.out.println("Fi");
			NecrodancerV2.timer.cancel();

		}

		else {
			for (k = 0; k < NecrodancerV2.characters.size(); k++) {
				if (NecrodancerV2.characters.get(k).psx == iTar && NecrodancerV2.characters.get(k).psy == jTar) {
					NecrodancerV2.p.attac(NecrodancerV2.characters.get(k));
				}
			}
		}

	}
}
