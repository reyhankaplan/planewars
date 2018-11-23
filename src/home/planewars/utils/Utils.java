package home.planewars.utils;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Utils {
	
	// resimleri yukler
    public Image loadImage(String imageName) {

        return new ImageIcon(getResource( "/img/" + imageName + ".png")).getImage();
    }
    public Image getResource(String path) {
    	return  Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
    }
}
