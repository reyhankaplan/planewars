package home.planewars.utils;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import home.planewars.Config;

public class Utils {
	
	// resimleri yukler
    public Image loadImage(String imageName) {
    	
    	try {
    		return new ImageIcon(getResource( "/img/" + imageName + Config.IMG_SUFFIX + ".png")).getImage();
    	}
        catch(NullPointerException e) {
        	System.out.println("Verilen suffix ile resim bulunamadi, suffix olmadan deneniyor");
        	try {
        		// throw new NullPointerException();
        		return new ImageIcon(getResource( "/img/" + imageName + ".png")).getImage();
        	}
        	catch (NullPointerException ex) {
        		System.out.println("Resim alinamadigi icin oyun kapatildi");
        		System.exit(0);
        		
        		return null;
        	}
        	
        }
    }
    
    public Image getResource(String path) {
    	return  Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
    }
}
