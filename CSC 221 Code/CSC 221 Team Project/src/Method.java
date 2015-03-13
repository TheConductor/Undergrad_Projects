import java.awt.Color;
import java.awt.Graphics;

public class Method extends Elements {

	private String returnType;
	private String belongsTo;
	private boolean abstractMethod;
	private boolean finalMethod;
	private boolean staticMethod;

	public Method(String type, String scope, String name, String returnType,
			String belongsTo, boolean abstractMethod, boolean finalMethod,
			boolean staticMethod, int startX, int startY, int width,
			int height, Color c) {
		super(type, scope, name, startX, startY, width, height, c);
		this.setReturnType(returnType);
		this.setBelongsTo(belongsTo);
		this.setAbstractMethod(abstractMethod);
		this.setFinalMethod(finalMethod);
		this.setStaticMethod(staticMethod);
	}

	public String code() {
		String returnValue = null;
		if (abstractMethod == true) {
			if (this.returnType.equals("void") || this.returnType.equals(null)) {
				returnValue = this.getScope() + " abstract void " + this.getName()
						+ "{\n}";
			} else {
				returnValue = this.getScope() + " abstract " + this.getReturnType() + " "
						+ this.getName() + ";";
			}
		}
		if (finalMethod == true) {
			if (this.returnType.equals("void") || this.returnType.equals(null)) {
				returnValue = this.getScope() + " final void " + this.getName()
						+ "(){\n}";
			} else {
				returnValue = this.getScope() + " final " + this.getReturnType() + " "
						+ this.getName() + "(){\n}";
			}
		}
		if (staticMethod == true) {
			if (this.returnType.equals("void") || this.returnType.equals(null)) {
				returnValue = this.getScope() + " static void " + this.getName()
						+ "(){\n}";
			} else {
				returnValue = this.getScope() + " static " + this.getReturnType() + " "
						+ this.getName() + "(){\n}";
			}
		}
		if(staticMethod == false && finalMethod == false && abstractMethod == false){
			if (this.returnType.equals("void") || this.returnType.equals(null)) {
				returnValue = this.getScope() + " void " + this.getName()
						+ "(){\n}";
			} else {
				returnValue = this.getScope() + " " + this.getReturnType() + " "
						+ this.getName() + "(){\n}";
			}
		}
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

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(String belongsTo) {
		this.belongsTo = belongsTo;
	}

	public boolean isAbstractMethod() {
		return abstractMethod;
	}

	public void setAbstractMethod(boolean abstractMethod) {
		this.abstractMethod = abstractMethod;
	}

	public boolean isFinalMethod() {
		return finalMethod;
	}

	public void setFinalMethod(boolean finalMethod) {
		this.finalMethod = finalMethod;
	}

	public boolean isStaticMethod() {
		return staticMethod;
	}

	public void setStaticMethod(boolean staticMethod) {
		this.staticMethod = staticMethod;
	}

}// End Method
