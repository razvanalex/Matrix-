package test;

public class Vector2D {
	private float x;
	private float y;
	public static Vector2D Zero = new Vector2D(0, 0);
	
	public Vector2D() {
		
	}
	
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(float x) {
		this.x = x;
		this.y = x;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public static Vector2D Add(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}
	
	public static boolean Equals(Vector2D v1, Vector2D v2) {
		return (v1.getX() == v2.getX()) && (v1.getY() == v2.getY());
	}
}
