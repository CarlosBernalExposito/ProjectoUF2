public abstract class Character extends Entity{

	protected int hp;
	protected int dmg;
	protected int gValue;

	public Character(int posx, int posy, int id) {
		super(posx,posy,id);
		NecrodancerV2.characters.add(this);
	}

	public void kill() {

		ded = true;
		if (getClass().equals(Player.class) == false) {
			System.out.println("Has matado un " + getClass().toString());

			NecrodancerV2.p.gValue = NecrodancerV2.p.gValue
					+ (int) (Math.random() * (gValue + NecrodancerV2.p.gValue));
			System.out.println("Oro: " + NecrodancerV2.p.gValue);
			NecrodancerV2.map[psx][psy] = 0;
			NecrodancerV2.enemyCounter--;
			if (NecrodancerV2.enemyCounter == 0) {
				NecrodancerV2.map[1][NecrodancerV2.col / 2] = 10;
			}
		}

		else {
			System.out.println("Has muerto");
			NecrodancerV2.map[psx][psy] = 7;
			NecrodancerV2.timer.cancel();
		}
	}

	public void attac(Character atked) {
		atked.hp = atked.hp - dmg;

		if (this.getClass().equals(Player.class)) {
			System.out.println("Golpeas a " + atked.getClass().toString() + " por " + dmg);
		} else {
			System.out.println(this.getClass().toString() + " te golpea por " + dmg);
		}

		if (atked.hp <= 0) {
			atked.kill();
			this.refreshPs();
		}
	}
}
