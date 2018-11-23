package home.planewars;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import home.planewars.entities.planes.Enemy;
import home.planewars.entities.planes.Plane;
import home.planewars.entities.planes.Player;
import home.planewars.entities.weapon.Missile;

import java.util.List;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	// -1 Game Over
	// 0 preparing
	// 1 level 1
	// 2 level 2
	public static int state, oldState;
	// bunlar dinamik yapılacak
	private final int PLANE_MAX_HEIGHT = 40, PLANE_MAX_WIDTH = 20;
	
    private final int PLAYER_X = Launcher.WIDTH / 2 - PLANE_MAX_WIDTH, 
    		PLAYER_Y = Launcher.HEIGHT - PLANE_MAX_HEIGHT, 
    		E_X = Launcher.WIDTH / 2 - PLANE_MAX_WIDTH, 
    		E_Y = 0;
    
    private final int DELAY = 10;
    private Timer timer;
    
    // bunlar da dinamik olacak
    private Player player;
    private Enemy enemy;
    
    private int score, lastHighestScore;
    private final int NEW_LEVEL_TIMEOUT = 80;
    private int newLevelTimeout;

    public Board() {
        initBoard();
    }

    private void initBoard() {
    	state = 0;
    	score = 0;
    	lastHighestScore = score;
    	newLevelTimeout = NEW_LEVEL_TIMEOUT;
    	
        addKeyListener(new TAdapter());
        
        // bunlar key listener'ın çalışmasında etkili
        this.setFocusable(true);
        this.requestFocus();
        
        state = 1;
        oldState = state;
        // şu an sabit ama değişecek
        player = new Player(PLAYER_X, PLAYER_Y, 1);
        enemy = new Enemy(E_X, E_Y, 1);
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public Timer getTimer() {
    	return timer;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        fillBackground(g);
        doDrawing(g);
        showScores(g);
        if (oldState < state) {
        	if (newLevelTimeout == 0) {
        		oldState = state;
            	newLevelTimeout = NEW_LEVEL_TIMEOUT;
        	}
        	else {
        		newLevelTimeout --;
        	}
        }
        
        // bunun ne iş yaptığına bak
        Toolkit.getDefaultToolkit().sync();
    }
    
    // arkaplanı doldurur
    private void fillBackground(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
    	
    	GradientPaint paint;
    	
    	if (state < 2) 
    		paint = new GradientPaint(0, 0, new Color(34, 166, 179), 0, getWidth(), new Color(223, 249, 251));
    	else
    		paint = new GradientPaint(0, 0, new Color(48, 53, 56), 0, getWidth(), new Color(49, 83, 130));
    	g2d.setPaint(paint);
    	g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    
    // asıl şeyleri çizer
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        // player'ı çiz
        g2d.drawImage(player.getImage(), player.getX(),
                player.getY(), this);
        
        // enemy'i çiz
        g2d.drawImage(enemy.getImage(), enemy.getX(),
                enemy.getY(), this);
        
        // player'ın missiles'ı
        List<Missile> missiles = player.getMissiles();

        for (Missile missile : missiles) {
            
            g2d.drawImage(missile.getImage(), missile.getX(),
                    missile.getY(), this);
        }
        
        // enemy'nin missiles'ı
        List<Missile> emissiles = enemy.getMissiles();

        for (Missile missile : emissiles) {
            
            g2d.drawImage(missile.getImage(), missile.getX(),
                    missile.getY(), this);
        }
    }
    
    private void showScores(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
    	
    	if (state != oldState) {
    		g2d.setPaint(new Color(49, 83, 130, 125));
        	g2d.fillRect(0, 0, getWidth(), getHeight());
        	g2d.setPaint(new Color(48, 51, 107));
        	g2d.setFont(new Font("Purisa", Font.BOLD, 36));
        	g2d.drawString("Level " + state + "!", 120, getHeight() / 2);
    	}
        if (state >= 0) {
        	String playerScore = "Health: " + player.getHealth();
            String enemyScore = "Health: " + enemy.getHealth();
            String playerMissiles = "Missiles: " + (player.getMaxMissileCount() - player.getMissiles().size());
            String enemyMissiles = "Missiles: " + (enemy.getMaxMissileCount() - enemy.getMissiles().size());
            
            g2d.setFont(new Font("Purisa", Font.BOLD, 16));
            if (state < 2)
            	g2d.setPaint(new Color(48, 51, 107));
            else
            	g2d.setPaint(Color.white);
            g2d.drawString("Enemy", 10, 30);
            g2d.drawString(enemyScore, 10, 50);
            g2d.drawString(enemyMissiles, 10, 70);
            g2d.drawString("Player", 10, Launcher.HEIGHT - 70);
            g2d.drawString(playerScore, 10, Launcher.HEIGHT - 50);
            g2d.drawString(playerMissiles, 10, Launcher.HEIGHT - 30);
            g2d.drawString("Score: " + score, 10, Launcher.HEIGHT / 2);
            
            
        }
        else {
        	g2d.setPaint(new Color(255, 255, 255, 125));
        	g2d.fillRect(0, 0, getWidth(), getHeight());
        	g2d.setPaint(new Color(48, 51, 107));
        	g2d.setFont(new Font("Purisa", Font.BOLD, 36));
        	g2d.drawString("Game Over !", 60, getHeight() / 2);
        	g2d.setFont(new Font("Purisa", Font.BOLD, 18));
        	g2d.drawString("Score: " + score, 60, getHeight() / 2 + 36);
        	g2d.drawString("Highest Score: " + lastHighestScore, 60, getHeight() / 2 + 60);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (checkState()) {
    		initializeNewState();
    	}
    	
    	if (oldState == state) {
    		checkCollisions();
            updateMissiles();
            updatePlanes();
    	}
		
        repaint();
    }
    
    private void initializeNewState() {
    	if (state == -1) {
    		if (lastHighestScore < score)
    			lastHighestScore = score;
    		// game over
    		return;
    	}
    	
    	player.setHealth(Plane.DEFAULT_HEALTH - state);
    	enemy.setHealth(Plane.DEFAULT_HEALTH + state);
    	
    	int mc = Plane.DEFAULT_MISSILE_COUNT + state;
    	if (mc > 10) {
    		mc = Plane.DEFAULT_MISSILE_COUNT;
    	}
    	
    	player.setMaxMissileCount(mc);
    	enemy.setMaxMissileCount(mc);

    	// konumlari sifirla
    	enemy.setX(E_X);
    	enemy.setY(E_Y);
    	
    	player.setX(PLAYER_X);
    	player.setY(PLAYER_Y);
    	
    	if (state > 2) {
    		return;
    	}
    	
    	// yeni state entityleri
    	player.setLevel(state);
    	enemy.setLevel(state);
    	
    	player.setImage("player" + player.getLevel());
    	enemy.setImage("enemy" + enemy.getLevel());
    	
    }
    
    // state'i kontrol eder
    // değiştiyse true değişmediyse false döndürür
    private boolean checkState() {
    	if (oldState == -1 && state > 0) {
    		score = 0;
    		return true;
    	}
    	if (player.getHealth() < 0) {
    		state = -1; // game over
    		return true;
    	}
    	else if (enemy.getHealth() < 0) {
    		state = state + 1; // next level
    		return true;
    	}
    	// no change
    	return false;
    }
    
    // silinmesi gereken missiles'ı siler
    // diğerlerini hareket ettirir
    private void updateMissiles() {

        List<Missile> missiles = player.getMissiles();

        for (int i = 0; i < missiles.size(); i++) {

            Missile missile = missiles.get(i);

            if (missile.isVisible()) {

                missile.move();
            } else {

                missiles.remove(i);
            }
        }
        
        List<Missile> emissiles = enemy.getMissiles();

        for (int i = 0; i < emissiles.size(); i++) {

            Missile missile = emissiles.get(i);

            if (missile.isVisible()) {

                missile.move();
            } else {

                emissiles.remove(i);
            }
        }
    }
    
    // plane'leri hareket ettirir
    private void updatePlanes() {
    	enemy.move();
        player.move();
    }
    
    private void checkCollisions() {
    	Rectangle r1, r2, r3, r4;
    	
    	r3 = player.getBounds();
    	r4 = enemy.getBounds();
    	
    	if (r3.intersects(r4)) {
    		player.increaseHealth(-2);
    		System.out.println("player enemy ile carpisti");
    	}
    	
    	for (Missile m : player.getMissiles()) {
    		r1 = m.getBounds();
    		
    		if (r4.intersects(r1)) {
    			m.setVisible(false);
    			// enemy health --
    			enemy.increaseHealth(-1);
    			score += 1; // player score
				System.out.println("enemy missile ile carpisti");
			}
    		
    		for(Missile n : enemy.getMissiles()) {
    			r2 = n.getBounds();
    			
    			if (r1.intersects(r2)) {
    				n.setVisible(false);
    				m.setVisible(false);
    				score += 1; // player score
    				System.out.println("missile'lar carpisti");
    			}
    		}
    	}
    	
    	for(Missile n : enemy.getMissiles()) {
			r2 = n.getBounds();
			
			if (r3.intersects(r2)) {
				n.setVisible(false);
				// player health --
				player.increaseHealth(-1);
				System.out.println("player missile ile carpisti");
			}
			
		}
    	
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        	int keycode = e.getKeyCode();
        	if (state == -1) {
        		if (keycode == KeyEvent.VK_SPACE) {
        			state = oldState;
        			oldState = -1;
        		}
        		else if(keycode == KeyEvent.VK_ENTER) {
        			state = 1;
        			oldState = -1;
        		}
        		else if (keycode == KeyEvent.VK_ESCAPE) {
        			System.exit(0);
        		}
        	}
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}