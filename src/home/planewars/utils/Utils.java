package home.planewars.utils;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Utils {
	
	// resimleri yukler
    public static Image loadImage(String imageName) {

        ImageIcon ii = new ImageIcon("res/img/" + imageName + ".png");
        return ii.getImage();
    }
}
