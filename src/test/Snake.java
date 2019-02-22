package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class Snake {
	
	private Vector<PixelSquare> bSquares = new Vector<PixelSquare>();
	
	public Snake(int scale, float squareLength, int speed, Vector2D position) {
		PixelSquare head = new PixelSquare();
		head.setScale(scale);
		head.setFill(false);
		head.setColor(Color.BLUE);
		head.setSquareL(squareLength);
		head.setSpeed(speed);
		head.setPosition(position);
		
		bSquares.addElement(head);	
	}
	
	public void increaseSize() {
		PixelSquare head = bSquares.get(0);
		PixelSquare body = new PixelSquare();

		body.setScale(head.getScale());
		body.setFill(false);
		body.setColor(Color.BLUE);
		body.setSquareL(head.getSquareL());
		body.setSpeed(head.getSpeed());
		body.setPosition(new Vector2D(head.getSquareL(), 0));
		
		bSquares.addElement(body);	
	}
	
	public Vector2D getHeadPosition() {
		return bSquares.get(0).getPosition();
	}
	
	public Integer getSizeOfSnake() {
		return bSquares.size();
	}
	
	public int getSpeedOfSnake() {
		return bSquares.get(0).getSpeed();
	}
	
	public void setSpeedOfSnake(int speed) {
		for (PixelSquare p : bSquares) {
			p.setSpeed(speed);
		}
	}
	
	public void Move(KeyEvent e) {
		bSquares.get(0).Move(e);
	}
	
	public void Update() {
		for (int i = 0; i < bSquares.size(); i++) {			
			if (i > 0) {
				bSquares.get(i).setDir(bSquares.get(i - 1).getDir());
			}
			
			bSquares.get(i).Update();
		}
	}
	
	public void Draw(Graphics g) {
		for (PixelSquare p:bSquares) {
			p.Draw(g);
		}
	}
}
