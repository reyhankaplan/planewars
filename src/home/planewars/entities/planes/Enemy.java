package home.planewars.entities.planes;

public class Enemy extends Plane {
	
	public final static int DEFAULT_SPEED = 1;
	private int ring;
	private int movement;
	
	public Enemy(int x, int y, int level) {
		super(x, y, level, Plane.ENEMY);
		speed = DEFAULT_SPEED;
		ring = 0;
		movement = 0;
	}
	
	/// kendi kendine gitmesi gerek
	// ilk level için olabilir
	@Override
	public void move() {
		boolean isRing = false;
		
		if (ring % 2 == 0)
			isRing = move(speed, 0);
		else
			isRing = move(-speed, 0);
		if (isRing == true) {
			ring ++;
		}
		// düzenlenecek
		if (movement % (ring + 1) == 7 && missiles.size() < maxMissileCount) {
			fire();
		}
		
		movement ++;
	}
}
