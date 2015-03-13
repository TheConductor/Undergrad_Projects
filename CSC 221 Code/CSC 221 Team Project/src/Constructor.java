import java.awt.Color;
import java.awt.Graphics;

public class Constructor extends Elements {
	private String belongsTo;
	public Constructor(String type, String scope, String name, String belongsTo, int startX, int startY,
			int width, int height, Color c) {
		super(type, scope, name, startX, startY, width, height, c);
		this.setBelongsTo(belongsTo);
	}

	public String code() {
		String returnValue = this.getScope()+" "+this.getName()+"(){\n}// End Constructor";	
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

}// End Constructor
