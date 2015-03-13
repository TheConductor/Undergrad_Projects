import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line extends Shape {
	private Point endPoint;
	private double length;
	private boolean isAPerpendicular = false;

	public Line(Point start, Point end, Color outline) {
		this.setType("Line");
		this.setStartPoint(start);
		endPoint = end;
		this.setOutline(outline);
		this.setOutlineBlue(this.getOutline().getBlue());
		this.setOutlineGreen(this.getOutline().getGreen());
		this.setOutlineRed(this.getOutline().getRed());
	}// End Constructor

	public void draw(Graphics g) {
		drawLine(g, this.getStartPoint(), endPoint);
	}// End draw

	private void drawLine(Graphics g, Point start, Point end) {
		g.setColor(this.getOutline());
		if (this.getStartPoint() != null && endPoint != null) {
			int startX = ((Double) start.getX()).intValue();
			int startY = ((Double) start.getY()).intValue();
			int endX = ((Double) end.getX()).intValue();
			int endY = ((Double) end.getY()).intValue();
			g.drawLine(startX, startY, endX, endY);
		}// End If to check startPoint and endPoint
	}// End drawLine
	
	public Line isPerpendicular(int x, int y, Color outline) {
		Line line = null;
		double slope = this.getSlope();
		if (Double.isNaN(slope) || Double.isInfinite(slope)) {
			Point start = new Point(x, y);
			int xEndPt = (int) this.endPoint.getX();
			int yEndPt = (int) y;
			Point end = new Point(xEndPt, yEndPt);
			line = new Line(start, end, outline);
		} else {
			int xEndPt = (int) ((slope * y - slope * endPoint.getY() + slope
					* slope * endPoint.getX() + x) / (slope * slope + 1));
			int yEndPt = (int) (slope * xEndPt + endPoint.getY() - slope
					* endPoint.getX());
			Point start, end;
			start = new Point(x, y);
			end = new Point(xEndPt, yEndPt);
			line = new Line(start, end, outline);
		}

		return line;
	}

	public double getSlope() {
		double slope = 0;
		slope = (this.getEndPoint().getY() - this.getStartPoint().getY())
				/ (this.getEndPoint().getX() - this.getStartPoint().getX());
		return slope;
	}

	public double getLength() {
		return this.getStartPoint().distance(endPoint);
	}
	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Boolean getIsAPerpendicular() {
		return isAPerpendicular;
	}

	public void setIsAPerpendicular(Boolean isAPerpendicular) {
		this.isAPerpendicular = isAPerpendicular;
	}
}// End Line Class
