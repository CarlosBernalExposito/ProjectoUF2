
public abstract class Entity {

	protected int psx;
	protected int psy;
	protected int id;
	protected boolean ded;

	public Entity(int posx, int posy) {

		psx = posx;
		psy = posy;
		ded = false;

		if (this.getClass().equals(Proyectile.class)) {
			switch (((Proyectile) this).axis) {
			case 'h': {
				if (((Proyectile) this).dir == 0) {
					this.id = 11;
				} else {
					this.id = 12;
				}
				break;
			}
			case 'v': {
				if (((Proyectile) this).dir == 0) {
					this.id = 13;
				} else {
					this.id = 14;
				}
				break;
			}
			}
		}
		else if(this.getClass().equals(Slime.class)) {
			id=2;
		}
		else if(this.getClass().equals(Ske.class)) {
			id=4;
		}
		else if(this.getClass().equals(Player.class)) {
			id=5;
		}

		NecrodancerV2.entities.add(this);
	}

	public abstract void mv(); 

	public void refreshPs() {
		NecrodancerV2.map[psx][psy] = id;
	}
}
