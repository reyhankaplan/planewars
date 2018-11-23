package home.planewars.entities;

import java.awt.Image;
import java.awt.Rectangle;

import home.planewars.utils.Utils;

public class Entity {
	
	protected int level;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
    protected String type;
    protected int speed;

    public Entity(int x, int y, String type, int level) {
        this.x = x;
        this.y = y;
        this.level = level;
        this.type = type;
        visible = true;
    }

    protected void loadImage(String imageName) {
        image = Utils.loadImage(imageName);
    }
    
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }    

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getLevel() {
    	return level;
    }
    
    public void setLevel(int level) {
    	this.level = level;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public String getType() {
    	return type;
    }

    public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setImage(String imageName) {
		image = Utils.loadImage(imageName);
		getImageDimensions();
	}

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
