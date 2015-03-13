import java.awt.Graphics;
import java.awt.Point;

public class Line {
	private Point startPoint;
	private Point endPoint;
	private double length;
	private Boolean selected = false;
	private Boolean isAPerpendicular = false;

	public Line isPerpendicular(int x, int y) {
		Line line = null;
		double slope = this.getSlope();
		if (Double.isNaN(slope) || Double.isInfinite(slope)) {
			Point start = new Point(x, y);
			int xEndPt = (int) this.endPoint.getX();
			int yEndPt = (int) y;
			Point end = new Point(xEndPt, yEndPt);
			line = new Line(start, end);
		} else {
			int xEndPt = (int) ((slope * y - slope * endPoint.getY() + slope
					* slope * endPoint.getX() + x) / (slope * slope + 1));
			int yEndPt = (int) (slope * xEndPt + endPoint.getY() - slope
					* endPoint.getX());
			Point start, end;
			start = new Point(x, y);
			end = new Point(xEndPt, yEndPt);
			line = new Line(start, end);
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
		return startPoint.distance(endPoint);
	}

	public Line(Point start, Point end) {
		startPoint = start;
		endPoint = end;
	}// End Constructor

	public void draw(Graphics g) {
		drawLine(g, startPoint, endPoint);
	}// End draw

	private void drawLine(Graphics g, Point start, Point end) {
		// TODO Auto-generated method stub
		if (startPoint != null && endPoint != null) {
			int startX = ((Double) start.getX()).intValue();
			int startY = ((Double) start.getY()).intValue();
			int endX = ((Double) end.getX()).intValue();
			int endY = ((Double) end.getY()).intValue();
			g.drawLine(startX, startY, endX, endY);
		}// End If to check startPoint and endPoint
	}// End drawLine

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
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

	public Boolean isSelected() {
		return this.selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean getIsAPerpendicular() {
		return isAPerpendicular;
	}

	public void setIsAPerpendicular(Boolean isAPerpendicular) {
		this.isAPerpendicular = isAPerpendicular;
	}

}// End Line Class
