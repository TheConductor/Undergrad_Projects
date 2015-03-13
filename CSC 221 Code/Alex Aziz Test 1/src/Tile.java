import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;


public class Tile extends JButton {

	private static Color defaultColor = Color.YELLOW;
	private char myLetter;
	private int myNum = 0;
	private static Font f = new Font("Arial", Font.BOLD, 20);
	private boolean unknown = false;
	private boolean clicked = false;
	
	public Tile(int num) {
		myNum = num;
		String tileNumber = num+"";
		setText(tileNumber);
		setPreferredSize(new Dimension(10, 10));
		setBackground(defaultColor);
		this.unknown=false;
		setFont(f);
	}
	
	public Tile(char letter) {
		myLetter = letter;
		setText(Character.toString(letter));
		setPreferredSize(new Dimension(10, 10));
		setBackground(defaultColor);
		this.unknown=true;
		setFont(f);
	}
	
	public void updateTile(char letter) {
		setText(Character.toString(letter));
	}
	
	public int getLetter() {
		return myLetter;
	}

	public int getMyNum() {
		return myNum;
	}

	public void setMyNum(int myNum) {
		this.myNum = myNum;
	}

	public boolean isUnknown() {
		return unknown;
	}

	public void setUnknown(boolean unknown) {
		this.unknown = unknown;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	

}
