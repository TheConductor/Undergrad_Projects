import java.awt.Color;
import java.awt.Graphics;

public class Variable extends Elements {

	String variableType = null;
	public String belongsTo;

	public Variable(String type, String scope, String name,
			String variableType, String belongsTo, int startX, int startY,
			int width, int height, Color c) {
		super(type, scope, name, startX, startY, width, height, c);
		this.setVariableType(variableType);
		this.setBelongsTo(belongsTo);
	}

	public String code() {
		String returnValue = this.getScope() + " " + variableType + " "
				+ this.getName() + ";";
		return returnValue;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(this.getStartX(), this.getStartY(), this.getWidth(),
				this.getHeight());
		g.setColor(this.getC());
		g.fillRect(this.getStartX(), this.getStartY(), this.getWidth(),
				this.getHeight());
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public String getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(String belongsTo) {
		this.belongsTo = belongsTo;
	}
}// End Variable
