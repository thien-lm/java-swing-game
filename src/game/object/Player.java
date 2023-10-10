package game.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.security.KeyStore.PrivateKeyEntry;

import javax.swing.ImageIcon;

public class Player{
	
	public static final double PLAYER_SIZE = 64;
	private double x;
	private double y;
	private float angle = 0f;
	private final Image image;
	private final Image imageSpeed;
	
	public Player() {
		this.image = new ImageIcon(getClass().getResource("/game/images/plane.png")).getImage();
		this.imageSpeed = new ImageIcon(getClass().getResource("/game/images/plane_speed.png")).getImage();;
	}
	
	public void changeAngle(float angle) {
		if(this.angle < 0) {
			this.angle = 359;
		}
		else if(this.angle > 359) {
			this.angle = 0;
		}
		this.angle = angle;
	}
	
	public void changeLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform oldTransform = g2.getTransform();
		g2.translate(x, y);
		AffineTransform tran = new AffineTransform();
		tran.rotate(Math.toRadians(angle + 45), PLAYER_SIZE/2, PLAYER_SIZE/2);
		g2.drawImage(image, tran, null);
		g2.setTransform(oldTransform);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public float getAngle() {
		return angle;
	}
}
