package home.planewars;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Launcher extends JFrame {
	

	public final static int WIDTH = 400;
	public final static int HEIGHT = 300;
			
    public Launcher() {
        
        initUI();
    }
    
    private void initUI() {
        
    	Board board = new Board();
        add(board);
        
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        
        setTitle("Plane Wars!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
            	Timer timer = board.getTimer();
                timer.stop();
            }
        });
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            Launcher ex = new Launcher();
            ex.setVisible(true);
        });
    }
}
