import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Class extends Elements {
	private boolean abstractClass;
	private boolean finalClass;
	private boolean hasMain = false;
	private int numberOfVariables = 0;
	private int numberOfConstructors = 0;
	private int numberOfMethods = 0;
	private int numberOfInnerClasses = 0;
	private int numberOfElements = 0;
	private ArrayList<Elements> Elements = new ArrayList<Elements>();

	public Class(String type, String scope, String name, boolean AbstractClass,
			boolean FinalClass, int startX, int startY, int width, int height,
			Color c) {

		super(type, scope, name, startX, startY, width, height, c);

		this.setabstractClass(AbstractClass);
		this.setfinalClass(FinalClass);
	}

	public String code() {
		String returnValue = null;
		if (abstractClass) {
			returnValue = "public abstract class " + this.getName() + "{";
		}
		if (finalClass) {
			returnValue = "public final class " + this.getName() + "{";
		}
		if (finalClass == false && abstractClass == false) {
			returnValue = "public class " + this.getName() + "{";
		}
		return returnValue;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(this.getStartX(), this.getStartY(), this.getWidth(),
				this.getHeight());
		// g.drawString(this.getName(), this.getStartX(), this.getStartY());
		g.setColor(this.getC());
		System.out.println(this.getName()+" width "+ this.getWidth() +" startX "+ this.getStartX());
		g.fillRect(this.getStartX(), this.getStartY(), this.getWidth(),
				this.getHeight());
	}

	public boolean isabstractClass() {
		return abstractClass;
	}

	public void setabstractClass(boolean abstractClass) {
		this.abstractClass = abstractClass;
	}

	public boolean isfinalClass() {
		return finalClass;
	}

	public void setfinalClass(boolean finalClass) {
		this.finalClass = finalClass;
	}

	public int getNumberOfVariables() {
		return numberOfVariables;
	}

	public void setNumberOfVariables(int numberOfVariables) {
		this.numberOfVariables = numberOfVariables;
	}

	public int getNumberOfConstructors() {
		return numberOfConstructors;
	}

	public void setNumberOfConstructors(int numberOfConstructors) {
		this.numberOfConstructors = numberOfConstructors;
	}

	public int getNumberOfMethods() {
		return numberOfMethods;
	}

	public void setNumberOfMethods(int numberOfMethods) {
		this.numberOfMethods = numberOfMethods;
	}

	public int getNumberOfInnerClasses() {
		return numberOfInnerClasses;
	}

	public void setNumberOfInnerClasses(int numberOfInnerClasses) {
		this.numberOfInnerClasses = numberOfInnerClasses;
	}

	public boolean isHasMain() {
		return hasMain;
	}

	public void setHasMain(boolean hasMain) {
		this.hasMain = hasMain;
	}

	public ArrayList<Elements> getElements() {
		return Elements;
	}

	public void setElements(ArrayList<Elements> elements) {
		Elements = elements;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
}// End Class
