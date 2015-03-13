import java.awt.Color;
import java.awt.Graphics;

public class Main extends Method {
	
	public Main(String type, String scope, String name, String returnType,
			String belongsTo, boolean abstractMethod, boolean finalMethod,
			boolean staticMethod, int startX, int startY, int width,
			int height, Color c) {
		super(type, scope, name, returnType, belongsTo, abstractMethod, finalMethod,
				staticMethod, startX, startY, width, height, c);
	}

	public String code() {
		String returnValue = "public static void main(String[] args){\n}// End Main";
		return returnValue;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(this.getStartX(), this.getStartY(), this.getWidth(), this.getHeight());
		g.setColor(this.getC());
		g.fillRect(this.getStartX(), this.getStartY(), this.getWidth(), this.getHeight());
	}
	
}// End Main
