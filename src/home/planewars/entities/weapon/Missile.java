package home.planewars.entities.weapon;

import home.planewars.Config;
import home.planewars.entities.Entity;

public class Missile extends Entity {
    
    private final int MISSILE_SPEED = 2;
    private String name;
    
    // x, y pozisyon
    // name enemy || player
    public Missile(int x, int y, int level, String name) {
        super(x, y, name.substring(0, 1), level);
        
        // enemy, level 1 => emissile1
        // player, level 2 => pmissile2
        this.name = type + "missile" + level;
        
        initMissile();
    }
    
    private void initMissile() {
        
        loadImage(name);  
        getImageDimensions();
        
        x -= width / 2;
        
        switch(type) {
            case "p": 
                y -= height;
                break;
            case "e":
                //y += height;
                break;
        }
    }

    public void move() {
        
        switch(type) {
            case "p": 
                y -= MISSILE_SPEED;
                
                if (y < 0) {
                    visible = false;
                }
                break;
            case "e":
                y += MISSILE_SPEED;
                
                if (y > Config.BOARD_HEIGHT - 10) {
                    visible = false;
                }
                break;
        } 
    }
}
