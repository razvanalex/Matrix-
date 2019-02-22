package test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Board {
	private Vector<PixelSquare> squares = new Vector<PixelSquare>();
	private int width = 0;
	private int height = 0;
	private int numSqWidth = 0;
	private int numSqHeight = 0;
	private int squareSize = 0;
	
	public Board(int numSqWidth, int numSqHeight) {
		this.numSqWidth = numSqWidth;
		this.numSqHeight = numSqHeight;
		
		this.width = Program.WindowWidth;
		this.height = Program.WindowHeight;
		
		float ratioW = (float)this.width / this.numSqWidth;
		float ratioH = (float)this.height / this.numSqHeight;
		
		this.squareSize = ratioW < ratioH ? (int)ratioW : (int)ratioH;
	}
	
	public Board(int squareSize) {
		this.squareSize = squareSize;
		
		this.width = Program.WindowWidth;
		this.height = Program.WindowHeight;
		
		this.numSqWidth = this.width / this.squareSize;
		this.numSqHeight = this.height / this.squareSize;
	}
	
	public void Init() {
		int num = this.numSqWidth * 2 + (this.numSqHeight - 2) * 2;
		
		for (int i = 0; i <= num; i++) {
			PixelSquare s = new PixelSquare();
			s.setColor(Color.getHSBColor(139 / 360.0f, 300 / 360.0f, 150 / 360.0f));
			s.setFill(true);
			s.setScale(1);
			s.setSquareL(this.squareSize);
			s.setSpeed(0);
			Vector2D position = Vector2D.Zero;
			
			if (i < this.numSqWidth) {
				position = new Vector2D(
						i * this.squareSize, 
						0);
			}
			else if (i >= this.numSqWidth && 
					i < this.numSqWidth + this.numSqHeight - 2) {
				position = new Vector2D(
						(this.numSqWidth - 1) * this.squareSize, 
						(i - this.numSqWidth + 1) * this.squareSize);
			}		
			else if (i >= this.numSqWidth + this.numSqHeight - 2 && 
					i < 2 * this.numSqWidth + this.numSqHeight - 2) {
				position = new Vector2D(
						(2 * this.numSqWidth + this.numSqHeight - 3 - i) * this.squareSize, 
						(this.numSqHeight - 1) * this.squareSize);
			}
			else if (i >= 2 * this.numSqWidth + this.numSqHeight - 1) {
				position = new Vector2D(
						0, 
						(2 * this.numSqWidth + 2 * this.numSqHeight - 3 - i) * this.squareSize);
			}
			
			s.setPosition(position);
			squares.addElement(s);
		}
	}
	
	public void Draw(Graphics g) {
		for (PixelSquare p:squares) {
			p.Draw(g);
		}
	}
}
