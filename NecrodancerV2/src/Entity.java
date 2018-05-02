
public abstract class Entity {

	protected int psx;
	protected int psy;
	protected int id;
	protected boolean ded;

	public Entity(int posx, int posy, int id) {

		this.id=id;
		psx = posx;
		psy = posy;
		ded = false;

		NecrodancerV2.entities.add(this);
	}

	public abstract void mv(); 

	public void refreshPs() {
		if(this.ded==false) {
			NecrodancerV2.map[psx][psy] = id;
		}
	}
}
