import java.awt.Color;
import java.awt.Graphics;

public abstract class Elements {
	
	// Variables
	private String name;
	private String type;
	private String scope;
	private int width;
	private int height;
	private int startX;
	private int startY;
	private Color c;
	// End Variabes
	
	public Elements(String type, String scope, String name, int startX, int startY, int width, int height, Color c){
		this.setType(type);
		this.setScope(scope);
		this.setName(name);
		this.setStartX(startX);
		this.setStartY(startY);
		this.setWidth(width);
		this.setHeight(height);
		this.setC(c);
	}
	
	public abstract String code();
	public abstract void draw(Graphics g);
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	// End Getters and Setters
	
}// End Elements
