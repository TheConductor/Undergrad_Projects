import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;


public class LetterTile extends JButton {

	private static Color defaultColor = Color.YELLOW;
	private char myLetter = '?';
	private static Font f = new Font("Arial", Font.BOLD, 20);
	private boolean highLighted = false;

	/**
	 * Construct a tile using the supplied letter
	 * @param letter The letter that shows on the face of this tile
	 */
	public LetterTile(char letter) {
		myLetter = letter;
		setText(Character.toString(letter));
		setPreferredSize(new Dimension(10, 10));
		setBackground(defaultColor);
		
		setFont(f);
	}
	
	/**
	 * Update the letter displayed on the face of the tile 
	 */
	public void updateTile(char letter) {
		setText(Character.toString(letter));
	}
	
	/**
	 * Default, no-argument constructor. Letter will be the '?' character
	 */
	public LetterTile() {
		this('?');
	}
	
	/**
	 * Return the letter that appears on this tile
	 * @return The letter that appears on this tile
	 */
	public char getLetter() {
		return myLetter;
	}
	
	/**
	 * Change the letter on this tile's face
	 * @param letter The letter that should appear
	 */
	public void setLetter(char letter) {
		myLetter = letter;
	}
	
	/**
	 * Highlight this tile by setting its background to cyan
	 */
	public void highLight() {
		setBackground(Color.CYAN);
		paintImmediately(0, 0, getWidth(), getHeight());
		getParent().repaint();
		this.setHighLighted(true);
	}

	/**
	 * Un-highlight this tile by setting its background to yellow
	 */
	public void reset() {
		setBackground(defaultColor);
		paintImmediately(0, 0, getWidth(), getHeight());
		this.setHighLighted(false);
	}

	public boolean isHighLighted() {
		return highLighted;
	}

	public void setHighLighted(boolean highLighted) {
		this.highLighted = highLighted;
	}

}
