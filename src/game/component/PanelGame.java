package game.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.FlatteningPathIterator;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import game.object.Player;

public class PanelGame extends JComponent {
	
	private Graphics2D g2;
	private BufferedImage image;
	private int width;
	private int height;
	private Thread thread;
	private boolean start = true;
	
	private final int FPS = 60;
	private final int TARGET_TIME = 1000000000/FPS;
	
	//player
	private Player player;
	private Key key;
	
	public void start() {

		width = getWidth();
		height = getHeight();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(start) {
					long startTime = System.nanoTime();
					drawBackground();
					drawGame();
					render();
					long time = System.nanoTime() - startTime;
					if(time < TARGET_TIME) {
						long sleep = (TARGET_TIME - time)/1000000;
						sleep(sleep);
					}
				}
			}
		});		
		
		initObjectGame();
		initKeyboard();
		thread.start();
	}
	
	public void initObjectGame() {
		player = new Player();
	}
	
	public void initKeyboard() {
		key = new Key();
		requestFocus();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A) {
					key.setKey_left(true);
				} else if(e.getKeyCode() == KeyEvent.VK_D) {
					key.setKey_right(true);
				} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					key.setKey_space(true);
				} else if(e.getKeyCode() == KeyEvent.VK_J) {
					key.setKey_j(true);
				} else if(e.getKeyCode() == KeyEvent.VK_K) {
					key.setKey_k(true);
				}
			}
			
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_A) {
					key.setKey_left(false);
				} else if(e.getKeyCode() == KeyEvent.VK_D) {
					key.setKey_right(false);
				} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					key.setKey_space(false);
				} else if(e.getKeyCode() == KeyEvent.VK_J) {
					key.setKey_j(false);
				} else if(e.getKeyCode() == KeyEvent.VK_K) {
					key.setKey_k(false);
				}
			}
		});
			
			new Thread(new Runnable() {
				public void run() {
					float s = 0.5f;
					while(start) {
						float angle = player.getAngle();
						if(key.isKey_left()) {
							angle += s;
						}
						else if(key.isKey_right()) {
							angle -= s;
						}
						player.changeAngle(angle);
						sleep(1);
					}
			}
		}).start();
	}
	
	public void drawBackground() {
		g2.setColor(new Color(30, 30, 30));
		g2.fillRect(0, 0, width, height);
	}
	
	public void drawGame() {
		player.draw(g2);
	}
	
	public void render() {
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0,null);
		g.dispose();
	}
	
	public void sleep(long speed) {
		try {
			thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
