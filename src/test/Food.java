package test;

import java.awt.Color;
import java.util.Random;

public class Food extends PixelSquare {
	
	public Food(float squareL, float scale) {
		super(squareL, scale);
		this.setColor(Color.YELLOW);
		this.setFill(true);
		genRandomPosition();
	}
	
	public void genRandomPosition() {
		Random rand = new Random();
		int minW = (int)this.getSquareL();
		int maxW = Program.WindowWidth - (int)this.getSquareL();
		int minH = (int)this.getSquareL();
		int maxH = Program.WindowHeight - (int)this.getSquareL();
		
		int randomNumW = rand.nextInt((maxW - minW) + 1) + minW;
		int randomNumH = rand.nextInt((maxH - minH) + 1) + minH;
		
		Vector2D newPosition = new Vector2D(
				randomNumW - randomNumW % this.getSquareL(), 
				randomNumH - randomNumH % this.getSquareL());
		
		this.setPosition(newPosition);
	}
	
}
