import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
public class Oval extends Shape {
	private int width=0, height=0;
	private Color fill;
	private int fillRed, fillGreen, fillBlue;

	public Oval(Point start, int w, int h, Color outline, Color fill) {
		this.setType("Oval");
		this.setStartPoint(start);
		this.setWidth(w);
		this.setHeight(h);
		this.setFill(fill);
		this.setOutline(outline);
		this.setOutlineBlue(this.getOutline().getBlue());
		this.setOutlineGreen(this.getOutline().getGreen());
		this.setOutlineRed(this.getOutline().getRed());
		this.setFillBlue(this.getFill().getBlue());
		this.setFillGreen(this.getFill().getGreen());
		this.setFillRed(this.getFill().getRed());
	}// End Constructor
	
	private void drawOval(Graphics g, Point start, int w, int h, Color outline, Color fill) {
		if (this.getStartPoint() != null) {
			int startX = ((Double) start.getX()).intValue();
			int startY = ((Double) start.getY()).intValue();
			int width = w, height = h;
			g.setColor(fill);
			g.fillOval(startX, startY, width, height);
			g.setColor(outline);
			g.drawOval(startX, startY, width, height);
		}// End If to check startPoint and endPoint
	}// End drawOval
	
	public void draw(Graphics g) {
		int startX = ((Double) this.getStartPoint().getX()).intValue();
		int startY = ((Double) this.getStartPoint().getY()).intValue();
		g.setColor(this.getFill());
		g.fillOval(startX, startY,this.getWidth(), this.getHeight());
		g.setColor(this.getOutline());
		g.drawOval(startX, startY, width, height);
	}// End draw

	public void contains(Point p){
		double startX = this.getStartPoint().getX();
		double endX = startX+width;
		double startY = this.getStartPoint().getY();
		double endY = startY+height;
		if(startX<p.getX() && p.getX()<endX && startY<p.getY() && p.getY()<endY){
			this.setSelected(true);
		}
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

	public Color getFill() {
		return fill;
	}

	public void setFill(Color fill) {
		this.fill = fill;
	}

	public int getFillRed() {
		return fillRed;
	}

	public void setFillRed(int fillRed) {
		this.fillRed = fillRed;
	}

	public int getFillGreen() {
		return fillGreen;
	}

	public void setFillGreen(int fillGreen) {
		this.fillGreen = fillGreen;
	}

	public int getFillBlue() {
		return fillBlue;
	}

	public void setFillBlue(int fillBlue) {
		this.fillBlue = fillBlue;
	}
	
}
