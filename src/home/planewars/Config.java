package home.planewars;

import java.awt.Color;
import java.awt.Font;

public class Config {
    
    // Application
    public final static int BOARD_HEIGHT = 400;
    public final static int BOARD_WIDTH = 600;
    public final static String APP_TITLE = "Plane Wars!";
    
    // Resources
    // resim değişmesi(farklı isimde eklenmesi) durumunda varsayılan isimlendirmeye eklenen son ek buraya yazılarak
    // kodda aramakla uğraşmamak için
    public final static String IMG_SUFFIX = "";
    
    // resimlerin boyutuna göre olması gerektiği için 
    // resimleri değiştirdiğimde kolaylık olsun diye burada!!!!!!!!!!!!
    public final static int PLANE_MAX_HEIGHT = 40, PLANE_MAX_WIDTH = 20;
    
    // Colors and fonts
    
    public final static Color LIGHT_GR_START = new Color(34, 166, 179);
    public final static Color LIGHT_GR_END = new Color(223, 249, 251);
    public final static Color DARK_GR_START = new Color(48, 53, 56);
    public final static Color DARK_GR_END = new Color(49, 83, 130);
    public final static Color DARK_BG = new Color(49, 83, 130, 125), LIGHT_BG = new Color(255, 255, 255, 125);
    public final static Color DARK_TEXT = new Color(48, 51, 107), LIGHT_TEXT = new Color(223, 249, 251, 200);
    
    
    public final static Font BIG_FONT = new Font("Purisa", Font.BOLD, 36);
    public final static Font MEDIUM_FONT = new Font("Purisa", Font.BOLD, 18);
    public final static Font SMALL_FONT = new Font("Purisa", Font.BOLD, 16);
    
    
}
