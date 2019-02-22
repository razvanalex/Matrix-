package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class PixelSquare {
	private int speed = 0;
	private int timer = 0;
	private float squareL = 1;
	private float scale = 1;
	private Vector2D position = Vector2D.Zero;
	private Color color = Color.BLACK;
	private boolean fill = false;
	public enum Direction { UP, DOWN, LEFT, RIGHT, NONE };
	private Direction dir = Direction.NONE;
	
	public PixelSquare() {
	}
	
	public PixelSquare(float squareL, float scale, Vector2D position) {
		this.squareL = squareL;
		this.scale = scale;
		this.position = position;
	}
	
	public PixelSquare(float squareL, float scale) {
		this.squareL = squareL;
		this.scale = scale;
	}
	
	/* Getters and setters */
	/**
	 * Get Square Length
	 * @return
	 */
	public float getSquareL() {
		return squareL;
	}

	/**
	 * Set Square Length
	 * @param squareL
	 */
	public void setSquareL(float squareL) {
		this.squareL = squareL;
	}

	/**
	 * Get Scale
	 * @return
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * Set Scale
	 * @param scale
	 */
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	/**
	 * Get Position
	 * @return
	 */
	public Vector2D getPosition() {
		return position;
	}

	/**
	 * Set Position
	 * @return
	 */
	public void setPosition(Vector2D position) {
		this.position = position;
	}
	
	/**
	 * Get Speed
	 * @return
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Set Speed
	 * @return
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Get Direction
	 * @return
	 */
	public Direction getDir() {
		return dir;
	}
	
	/**
	 * Set Direction
	 * @return
	 */
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	/**
	 * Get Border Color
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Get Border Color
	 * @return
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Get Is Filled 
	 * @return
	 */
	public boolean isFill() {
		return fill;
	}
	
	/**
	 * Set Fill
	 * @return
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}

	/* Methods */
	/**
	 * Increase Speed
	 * @return
	 */
	public void increaseSpeed(int amount) {
		this.speed -= amount;
		if (speed <= 1)
			speed = 1;
	}
	
	/**
	 * Decrease Speed
	 * @return
	 */
	public void decreaseSpeed(int amount) {
		this.speed += amount;
	}
	
	
	private void moveSquareUp() {
		this.position = Vector2D.Add(
				this.position, 
				new Vector2D(0, -this.squareL * this.scale));
	}
	
	private void moveSquareDown() {
		this.position = Vector2D.Add(
				this.position, 
				new Vector2D(0, this.squareL * this.scale));
	}
	
	private void moveSquareLeft() {
		this.position = Vector2D.Add(
				this.position, 
				new Vector2D(-this.squareL * this.scale, 0));
	}
	
	private void moveSquareRight() {
		this.position = Vector2D.Add(
				this.position, 
				new Vector2D(this.squareL * this.scale, 0));
	}
	
	public void Move(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP: case KeyEvent.VK_W:
				dir = Direction.UP;
				break;
			case KeyEvent.VK_DOWN: case KeyEvent.VK_S: 
				dir = Direction.DOWN;
				break;
			case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
				dir = Direction.LEFT;
				break;
			case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
				dir = Direction.RIGHT;
				break;
			default: break;
		}		
	}

	private void collision() {
		final int maxWidth = Program.WindowWidth;
		final int maxHeight = Program.WindowHeight;
		
		if (this.position.getX() > maxWidth - 2 * this.squareL)
			this.position.setX(this.squareL);
		
		if (this.position.getY() > maxHeight - 2 * this.squareL)
			this.position.setY(this.squareL);
		
		if (this.position.getX() < this.squareL)
			this.position.setX(maxWidth -  2 * this.squareL);
		
		if (this.position.getY() < this.squareL )
			this.position.setY(maxHeight - 2 * this.squareL);
	}
	
	public void Update() {
		timer++;
		collision();
		
		if (timer % speed == 0) {
			switch (dir) {
				case UP:
					moveSquareUp();
					break;
				case DOWN:
					moveSquareDown();
					break;
				case LEFT:
					moveSquareLeft();
					break;
				case RIGHT:
					moveSquareRight();
					break;
				default: break;
			}
		}
	}
	
	public void Draw(Graphics g) {
		Rectangle2D rect = new Rectangle2D.Float(
				position.getX(), 
				position.getY(), 
				squareL * scale, 
				squareL * scale);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		if (fill)
			g2d.fill(rect);
		g2d.draw(rect);
	}
}
