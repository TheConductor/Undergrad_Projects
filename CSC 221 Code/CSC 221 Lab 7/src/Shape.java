import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
public abstract class Shape {
	private String Type = "";
	private Point startPoint;
	private Color outline;
	private int outlineRed, outlineGreen, outlineBlue;
	private boolean selected = false;
	public abstract void draw(Graphics g);
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public int getOutlineRed() {
		return outlineRed;
	}

	public void setOutlineRed(int outlineRed) {
		this.outlineRed = outlineRed;
	}

	public int getOutlineGreen() {
		return outlineGreen;
	}

	public void setOutlineGreen(int outlineGreen) {
		this.outlineGreen = outlineGreen;
	}

	public int getOutlineBlue() {
		return outlineBlue;
	}

	public void setOutlineBlue(int outlineBlue) {
		this.outlineBlue = outlineBlue;
	}
	public Color getOutline() {
		return outline;
	}

	public void setOutline(Color outline) {
		this.outline = outline;
	}

}// End Class
