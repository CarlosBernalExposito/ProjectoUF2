
public abstract class Enemy extends Character {

	protected int mvDir;
	
	public Enemy(int posx, int posy, int mvDire) {
		super(posx, posy);
		mvDir = mvDire;
		NecrodancerV2.enemies.add(this);
		NecrodancerV2.enemyCounter++;
	}

}
