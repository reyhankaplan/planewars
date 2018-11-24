package home.planewars;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Launcher extends JFrame {
            
    public Launcher() {
        
        initUI();
    }
    
    private void initUI() {
        
        Board board = new Board();
        add(board);
        
        setSize(Config.BOARD_WIDTH, Config.BOARD_HEIGHT);
        setResizable(false);
        
        setTitle(Config.APP_TITLE);
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
