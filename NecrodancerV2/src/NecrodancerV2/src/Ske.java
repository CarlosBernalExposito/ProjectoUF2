
public class Ske extends Enemy {

	protected int mvCount;
	protected char axis;

	public Ske(int posx, int posy, int mvDire, char eje) {

		super(posx, posy, mvDire);
		axis = eje;
		hp = 3;
		dmg = 2;
		gValue = 20;
		mvCount = 0;

		NecrodancerV2.skeletons.add(this);
	}

	public void mv() {
		int i = 0, j = 0, k = 0;
		if (ded == false) {
			mvCount++;
			if (axis == 'h') {

				switch (mvDir % 2) {

				case 0: {
					i = psx;
					j = psy + 1;
					break;
				}

				case 1: {
					i = psx;
					j = psy - 1;
					break;
				}
				}
			}

			else {

				switch (mvDir % 2) {

				case 0: {
					i = psx + 1;
					j = psy;
					break;
				}

				case 1: {
					i = psx - 1;
					j = psy;
					break;
				}
				}
			}
			
			if (i >= 0 && i < NecrodancerV2.row && j >= 0 && j < NecrodancerV2.col) {

				if (NecrodancerV2.map[i][j] == 0) {
					if(mvCount%3==0) {
						spearThrow(this);
					}
					else {
						NecrodancerV2.map[psx][psy] = 0;
						psx = i;
						psy = j;
						this.refreshPs();
					}

				}

				else if (NecrodancerV2.map[i][j] == 11 || NecrodancerV2.map[i][j] == 12 || NecrodancerV2.map[i][j] == 13
						|| NecrodancerV2.map[i][j] == 14) {
					for (k = 0; k < NecrodancerV2.proyectiles.size(); k++) {
						if (NecrodancerV2.proyectiles.get(k).psx == i && NecrodancerV2.proyectiles.get(k).psy == j) {
							NecrodancerV2.proyectiles.get(k).destroy();
						}
					}
				} else if (NecrodancerV2.map[i][j] == 5) {
					this.attac(NecrodancerV2.p);
				}

				else {
					mvDir++;
				}
			}
		}
	}

	public void spearThrow(Ske e) {
		int i = 0, j = 0;

		switch (e.mvDir % 2) {
		case 0: {
			if (e.axis == 'h') {
				i = e.psx;
				j = e.psy + 1;
			} else {
				i = e.psx + 1;
				j = e.psy;
			}
			break;
		}

		case 1: {
			if (e.axis == 'v') {
				i = e.psx - 1;
				j = e.psy;
			} else {
				i = e.psx;
				j = e.psy - 1;
			}
			break;
		}
		}

		if (e.mvDir == 0) {
			new Proyectile(i, j, e.mvDir, e.axis);
		} else {
			new Proyectile(i, j, e.mvDir, e.axis);
		}
	}

}
