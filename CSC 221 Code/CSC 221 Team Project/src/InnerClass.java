import java.awt.Color;
import java.awt.Graphics;


public class InnerClass extends Class {
	public String belongsTo;
	
	public InnerClass(String type, String scope, String name, String belongsTo,
			boolean abstractClass, boolean finalClass, int startX, int startY,
			int width, int height, Color c) {
		super(type, scope, name, abstractClass, finalClass, startX, startY, width,
				height, c);
		this.setBelongsTo(belongsTo);
	}

	@Override
	public String code() {
		String returnValue = "private class "+this.getName()+"{\n}//End InnerClass";
		return returnValue;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(this.getStartX(), this.getStartY(), this.getWidth(), this.getHeight());
		g.setColor(this.getC());
		g.fillRect(this.getStartX(), this.getStartY(), this.getWidth(), this.getHeight());
	}

	public String getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(String belongsTo) {
		this.belongsTo = belongsTo;
	}
	
}
