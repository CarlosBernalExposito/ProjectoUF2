
public class Proyectile extends Entity{

	protected int psx;
	protected int psy;
	protected int dir;
	protected char axis;
	protected int dmg;
	protected boolean ded = false;

	public Proyectile(int posx, int posy, int dire, char eje) {

		super(posx,posy);
		psx = posx;
		psy = posy;
		dir = dire;
		axis = eje;
		dmg=2;

		NecrodancerV2.proyectiles.add(this);
	}

	public void mv() {
		int i = 0, j = 0, k=0;
		
		if(ded == false) {
			if (axis == 'h') {

				switch (dir % 2) {

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

				switch (dir % 2) {

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
					NecrodancerV2.map[psx][psy] = 0;
					psx = i;
					psy = j;
					this.refreshPs();
				}

				else if(NecrodancerV2.map[i][j]==1){
					this.destroy();
				}

				else {
					for(k=0;k<NecrodancerV2.characters.size();k++) {
						if(NecrodancerV2.characters.get(k).psx==i && NecrodancerV2.characters.get(k).psy==j) {
							hit(NecrodancerV2.characters.get(k));
							this.destroy();
						}
					}
					
					for(k=0;k<NecrodancerV2.proyectiles.size();k++) {
						if(NecrodancerV2.proyectiles.get(k).psx==i && NecrodancerV2.proyectiles.get(k).psy==j) {
							NecrodancerV2.proyectiles.get(k).destroy();
							this.destroy();
						}
					}
				}
			}
		}
	}

	public void destroy() {
		NecrodancerV2.map[psx][psy] = 0;
		ded=true;
	}

	public void hit(Character c) {
		c.hp = c.hp - dmg;
		
		if(c.hp<=0) {
			c.kill();
		}
	}
	
}
