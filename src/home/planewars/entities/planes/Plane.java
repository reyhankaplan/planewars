package home.planewars.entities.planes;

import java.util.ArrayList;
import java.util.List;

import home.planewars.Launcher;
import home.planewars.entities.Entity;
import home.planewars.entities.weapon.Missile;

public class Plane extends Entity{
	
	public final static String PLAYER = "player";
	public final static String ENEMY = "enemy";
	public final static int DEFAULT_HEALTH = 10;
	public final static int DEFAULT_MISSILE_COUNT = 3;
	
    protected int dx;
    protected int dy;
    protected List<Missile> missiles;
    protected String name;
    protected int health;
    protected int maxMissileCount;
    
    // new Plane (x,y, <level>, Plane.PLAYER || Plane.ENEMY)
    public Plane(int x, int y, int level, String name) {
        super(x, y, name.substring(0, 1), level);
        
        // enemy, level 1 => enemy1
        // player, level 2 => player2
        this.name = name;
        System.out.println("Name: " + name );
        
        initPlane();
    }

    private void initPlane() {
    	
    	health = DEFAULT_HEALTH;
    	maxMissileCount = DEFAULT_MISSILE_COUNT;
        missiles = new ArrayList<>();

        System.out.println("loadImage " + (name+level) );
        loadImage(getImageName()); 
        getImageDimensions();
    }
    
    private String getImageName() {
    	return name + level;
    }
    
    public void move() {
        move(dx, dy);
    }
    
    public boolean move(int dx, int dy) {
    	y += dy;
    	x += dx;

    	dy = 0;
    	dx = 0;
    	
    	if (y > Launcher.HEIGHT - height) {
            y = Launcher.HEIGHT - height;
            return true;
        }
    	else if(y < 0) {
    		y = 0;
    		return true;
    	}
    	
    	if (x < 0) {
    		x = 0;
    		return true;
    	}
    	else if(x > Launcher.WIDTH - width) {
    		x = Launcher.WIDTH - width;
    		return true;
    	}
    	
    	return false;

    }
    
    public List<Missile> getMissiles() {
        return missiles;
    }

    public void fire() {
        missiles.add(new Missile(x + width / 2, name == "player" ? y : y + height, level, name));
    }
    
    public void setHealth(int health) {
    	this.health = health;
    }
    
    public int getHealth() {
    	return health;
    }
    
    public void increaseHealth(int inc) {
    	this.health += inc;
    }

	public int getMaxMissileCount() {
		return maxMissileCount;
	}

	public void setMaxMissileCount(int maxMissileCount) {
		this.maxMissileCount = maxMissileCount;
	}
    
}
