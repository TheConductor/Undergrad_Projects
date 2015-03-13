import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

public class Paints extends JButton{
	private int red, green, blue;
	private int startX, startY;
	private float price;
	private String name;
	private Color c = new Color (red, green, blue);
	private static Font f = new Font("Arial", Font.BOLD, 20);
	public Paints(int Red, int Green, int Blue, float Price, String Name){
		red=Red;
		green=Green;
		blue=Blue;
		price=Price;
		name=Name;
		setText(name);
		setBackground(c);
		setFont(f);
	}// End Constructor

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
	
}// End Class
