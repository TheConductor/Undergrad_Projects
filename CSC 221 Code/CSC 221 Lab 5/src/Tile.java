import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

public class Tile extends JButton {
	private static Color defaultColor = Color.GRAY;
	private static Font f = new Font("Arial", Font.BOLD, 20);
	private int owner = 0;
	private boolean available = false;
	private boolean lastMove = false;
	


	public Tile(int currentOwner) {
		currentOwner = owner;
		setPreferredSize(new Dimension(10, 10));
		setBackground(defaultColor);
		setFont(f);
	}

	public void highLight(Color color) {
		setBackground(color);
		paintImmediately(0, 0, getWidth(), getHeight());
		getParent().repaint();
	}
	
	public void highLightAvailabeMoves() {
		setBackground(Color.ORANGE);
		paintImmediately(0, 0, getWidth(), getHeight());
		getParent().repaint();
		this.setAvailable(true);
	}
	
	public void highLightForPlayer1() {
		setBackground(Color.GREEN);
		paintImmediately(0, 0, getWidth(), getHeight());
		getParent().repaint();
		this.setOwner(1);
		this.setAvailable(false);
		this.setLastMove(true);
	}
	
	public void highLightForPlayer2() {
		setBackground(Color.RED);
		paintImmediately(0, 0, getWidth(), getHeight());
		getParent().repaint();
		this.setOwner(2);
		this.setAvailable(false);
	}
	
	public void reset() {
		setBackground(defaultColor);
		paintImmediately(0, 0, getWidth(), getHeight());
		this.setOwner(0);
		this.setLastMove(false);
	}
	
	public boolean isLastMove() {
		return lastMove;
	}

	public void setLastMove(boolean lastMove) {
		this.lastMove = lastMove;
	}
	
	public int getOwner() {
		return owner;
	}

	public void setOwner(int currentStatus) {
		this.owner = currentStatus;
	}
	

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}// End Class
