import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;

/**
 * This class models the Word Find game board
 * 
 * @author narayans
 * 
 */
public class GameBoard extends JFrame {
	private LetterTile[][] myTiles;
	private int numRows = 11;
	private int numCols = 15;
	private JTextField searchWord;
	EventHandler eh = new EventHandler(this);

	/**
	 * Construct a new GameBoard using the supplied two-dimensional array of
	 * LetterTiles
	 * 
	 * @param tiles
	 *            The tiles representing the letters in the game
	 */
	public GameBoard() {

		myTiles = new LetterTile[numRows][numCols];

		setSize(800, 800);

		Container c = getContentPane();
		c.setBackground(Color.GREEN);
		c.setLayout(new BorderLayout());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Word Find Game");

		JPanel tilePanel = buildTilePanel();

		JPanel controlPanel = buildControlPanel();

		c.add(tilePanel, BorderLayout.CENTER);
		c.add(controlPanel, BorderLayout.SOUTH);

		JMenuBar menuBar = buildMenu();
		setJMenuBar(menuBar);

		setVisible(true);

	}

	/**
	 * Build the tile panel
	 * 
	 * @return A reference to the panel containing the letter tiles
	 */
	private JPanel buildTilePanel() {
		JPanel tilePanel = new JPanel();
		tilePanel.setBackground(Color.ORANGE);
		tilePanel.setPreferredSize(new Dimension(800, 600));
		tilePanel.setLayout(new GridLayout(numRows, numCols));

		for (int i = 0; i < myTiles.length; i++)
			for (int j = 0; j < myTiles[0].length; j++) {
				myTiles[i][j] = new LetterTile();
				myTiles[i][j].addActionListener(eh);
				tilePanel.add(myTiles[i][j]);
			}
		return tilePanel;
	}

	private JPanel buildTilePanelWithLetters() {
		JPanel tilePanel = new JPanel();
		tilePanel.setBackground(Color.ORANGE);
		tilePanel.setPreferredSize(new Dimension(800, 600));
		tilePanel.setLayout(new GridLayout(numRows, numCols));
		for (int i = 0; i < myTiles.length; i++)
			for (int j = 0; j < myTiles[0].length; j++) {
				myTiles[i][j].addActionListener(eh);
				tilePanel.add(myTiles[i][j]);
			}
		return tilePanel;
	}

	/**
	 * Create control panel
	 * 
	 * @return A reference to the control panel
	 */
	private JPanel buildControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(800, 200));
		controlPanel.setBackground(Color.MAGENTA);
		controlPanel.setLayout(new FlowLayout());

		searchWord = new JTextField(20);
		searchWord.addActionListener(eh);
		searchWord.setActionCommand("searchWord");
		JButton b = new JButton("Search");
		b.addActionListener(eh);
		controlPanel.add(searchWord);
		controlPanel.add(b);

		JButton c = new JButton("Reset");
		c.addActionListener(eh);
		controlPanel.add(c);
		return controlPanel;
	}

	/**
	 * Create a menu
	 * 
	 * @return The menu bar for this application
	 */
	private JMenuBar buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuBar.add(fileMenu);

		return menuBar;

	}

	/**
	 * Set color of all tiles on the board to yellow
	 */
	public void reset() {
		for (int i = 0; i < myTiles.length; i++)
			for (int j = 0; j < myTiles[0].length; j++)
				myTiles[i][j].reset();
	}

	public static void main(String[] args) {
		new GameBoard();
	}

	public void refresh() {
		setSize(800, 800);
		Container c = getContentPane();
		c.removeAll();
		c.setBackground(Color.GREEN);
		c.setLayout(new BorderLayout());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Word Find Game");

		JPanel tilePanel = buildTilePanelWithLetters();

		JPanel controlPanel = buildControlPanel();

		c.add(tilePanel, BorderLayout.CENTER);
		c.add(controlPanel, BorderLayout.SOUTH);

		JMenuBar menuBar = buildMenu();
		setJMenuBar(menuBar);

		setVisible(true);
	}

	public void printMyTiles(LetterTile[][] printTiles) {
		for (int i = 0; i < myTiles.length; i++)
			for (int j = 0; j < myTiles[0].length; j++) {
				System.out.println(myTiles[i][j].getLetter());
			}
		System.out.println("");
	}

	// Getters and Setters
	public LetterTile[][] getMyTiles() {
		return myTiles;
	}

	public void setMyTiles(LetterTile[][] myTiles) {
		this.myTiles = myTiles;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	public JTextField getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(JTextField searchWord) {
		this.searchWord = searchWord;
	}

	// End Getters and Setters

	public void search(String word) {
		if (searchLeftToRight(word) == false) {
			if (searchRightToLeft(word) == false) {
				if (searchTopToBottom(word) == false) {
					if (searchBottomToTop(word) == false) {
						if (searchDiagonalDownLeftToRight(word) == false) {
							if (searchDiagonalUpLeftToRight(word) == false) {
								if (searchDiagonalDownRightToLeft(word) == false) {
									if (searchDiagonalUpRightToLeft(word) == false) {
										JOptionPane.showMessageDialog(null, "Sorry but"+word+" was not found.");
									} else {
										JOptionPane.showMessageDialog(null, "Congratulations "+word+" was found!");
									}// End Diagonal Up Right To Left
								} else {
									JOptionPane.showMessageDialog(null, "Congratulations "+word+" was found!");
								}// End Diagonal Down Right To Left
							} else {
								JOptionPane.showMessageDialog(null, "Congratulations "+word+" was found!");
							}// End Diagonal Up Left To Right
						} else {
							JOptionPane.showMessageDialog(null, "Congratulations "+word+" was found!");
						}// End Diagonal Down Left To Right
					} else {
						JOptionPane.showMessageDialog(null, "Congratulations "+word+" was found!");
					}// End Bottom To Top
				} else {
					JOptionPane.showMessageDialog(null, "Congratulations "+word+" was found!");
				}// End Top To Bottom
			} else {
				JOptionPane.showMessageDialog(null, "Congratulations "+word+" was found!");
			}// End Right to left
		} else {
			JOptionPane.showMessageDialog(null, "Congratulations "+word+" was found!");
		}// End Left to Right

	} // End search
		
		// Search Algorithms

	private boolean searchLeftToRight(String word) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		String foundWord = "";
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < col; c++) {
				for (int j = 0; j < word.length(); j++) {
					if (j + c < col) {
						foundWord = foundWord + myTiles[r][c + j].getLetter();
						if (foundWord.equals(word)) {
							returnValue = true;
							for (int highLighter = 0; highLighter < word
									.length(); highLighter++) {
								myTiles[r][c + highLighter].highLight();
							}// End For to Highlight myTiles
							j = word.length();
							c = col;
							r = rows;
						}// End If to test foundWord against Word
					}// End If to stop out of bounds errors
				}// End For to count word length
				foundWord = "";
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End searchLeftToRight

	private boolean searchRightToLeft(String word) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		String foundWord = "";
		for (int r = rows - 1; r > 0; r--) {
			for (int c = col - 1; c > 0; c--) {
				for (int j = 0; j < word.length(); j++) {
					if (c - j > 0) {
						foundWord = foundWord + myTiles[r][c - j].getLetter();
						if (foundWord.equals(word)) {
							returnValue = true;
							for (int highLighter = word.length() - 1; highLighter >= 0; highLighter--) {
								myTiles[r][c - highLighter].highLight();
							}// End For to Highlight myTiles
							j = 0;
							c = 0;
							r = 0;
						}// End If to test foundWord against Word
					}// End If to stop out of bounds errors
				}// End For to count word length
				foundWord = "";
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End

	private boolean searchTopToBottom(String word) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		String foundWord = "";
		for (int c = 0; c < col; c++) {
			for (int r = 0; r < rows; r++) {
				for (int wordLength = 0; wordLength < word.length(); wordLength++) {
					if (wordLength + r < rows) {
						foundWord = foundWord
								+ myTiles[r + wordLength][c].getLetter();
						if (foundWord.equals(word)) {
							returnValue = true;
							for (int highLighter = 0; highLighter < word
									.length(); highLighter++) {
								myTiles[r + highLighter][c].highLight();
							}// End For to Highlight myTiles
							wordLength = word.length();
							c = col;
							r = rows;
						}// End If to test foundWord against Word
					}// End If to stop out of bounds errors
				}// End For to count word length
				foundWord = "";
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End searchTopToBottom

	private boolean searchBottomToTop(String word) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		String foundWord = "";
		for (int c = col - 1; c > 0; c--) {
			for (int r = rows - 1; r > 0; r--) {
				for (int wordLength = 0; wordLength < word.length(); wordLength++) {
					if (r - wordLength > 0) {
						foundWord = foundWord
								+ myTiles[r - wordLength][c].getLetter();
						if (foundWord.equals(word)) {
							returnValue = true;
							for (int highLighter = 0; highLighter < word
									.length(); highLighter++) {
								myTiles[r - highLighter][c].highLight();
							}// End For to Highlight myTiles
							wordLength = 0;
							c = 0;
							r = 0;
						}// End If to test foundWord against Word
					}// End If to stop out of bounds errors
				}// End For to count word length
				foundWord = "";
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End searchBottomToTop

	private boolean searchDiagonalDownLeftToRight(String word) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		String foundWord = "";
		for (int i = 1; i <= rows; i++) {
			for (int r = rows - i, c = 0; r <= rows - 1 && c < i; r++, c++) {
				for (int wordLength = 0; wordLength < word.length(); wordLength++) {
					if (r + wordLength < rows && c + wordLength < col) {
						foundWord = foundWord
								+ myTiles[r + wordLength][c + wordLength]
										.getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if (foundWord.equals(word)) {
					returnValue = true;
					for (int highLighter = 0; highLighter < word.length(); highLighter++) {
						myTiles[r + highLighter][c + highLighter].highLight();
					}// End For to Highlight myTiles
					c = i + 1;
					i = rows;
				} else {
					foundWord = "";
				}// End if-else to Detrimine if foundWord equals word
			}// End for to structure count of r & c
		}// End for to structure count to first max diagonal
		for (int i = 1; i <= col - 1; i++) {
			for (int r = 0, c = i; r <= rows - 1 && c <= col - 1; r++, c++) {
				for (int wordLength = 0; wordLength < word.length(); wordLength++) {
					if (r + wordLength < rows && c + wordLength < col) {
						foundWord = foundWord
								+ myTiles[r + wordLength][c + wordLength]
										.getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if (foundWord.equals(word)) {
					returnValue = true;
					for (int highLighter = 0; highLighter < word.length(); highLighter++) {
						myTiles[r + highLighter][c + highLighter].highLight();
					}// End For to Highlight myTiles
					r = rows;
					i = col;
				} else {
					foundWord = "";
				}// End if-else to Detrimine if foundWord equals word
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalDownLeftToRight

	private boolean searchDiagonalUpLeftToRight(String word) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		String foundWord = "", line = "";
		for (int i = 0; i < rows; i++) {
			for (int r = i, c = 0; r >= 0 && c <= i; r--, c++) {
				line = line + myTiles[r][c].getLetter();
			}// End count to structure count of r & c
			if (i >= (word.length() - 1)) {
				for (int lineCounter = 0; lineCounter <= line.length(); lineCounter++) {
					if (lineCounter + word.length() <= (line.length())) {
						foundWord = line.substring(lineCounter,
								(lineCounter + word.length()));
						if (foundWord.equals(word)) {
							returnValue = true;
							for (int highLighter = 0; highLighter < word
									.length(); highLighter++) {
								myTiles[i - lineCounter - highLighter][lineCounter
										+ highLighter].highLight();
							}// End For to Highlight myTiles
							lineCounter = line.length() + 1;
							i = rows;
						} else {
							foundWord = "";
						}// End if-else to Detrimine if foundWord equals word
					}// End if to stop Bounds Errors
				}// End for to count through the String line
			}// End if to prevent unnecessary searching
			foundWord = "";
			line = "";
		}// End for to structure count to after first max diagonal
		for (int j = 1; j <= col - 1; j++) {
			for (int r = rows - 1, c = j; r >= 0 && c <= col - 1; r--, c++) {
				line = line + myTiles[r][c].getLetter();
			}// End count to structure count of r & c
			for (int lineCounter = 0; lineCounter <= line.length(); lineCounter++) {
				if (lineCounter + word.length() <= (line.length())) {
					foundWord = line.substring(lineCounter,
							(lineCounter + word.length()));
					if (foundWord.equals(word)) {
						returnValue = true;
						for (int highLighter = 0; highLighter < word.length(); highLighter++) {
							myTiles[rows - 1 - lineCounter - highLighter][j
									+ lineCounter + highLighter].highLight();
						}// End For to Highlight myTiles
						lineCounter = line.length() + 1;
						j = col;
					} else {
						foundWord = "";
					}// End if-else to Detrimine if foundWord equals word
				}// End if to stop Bounds Errors
			}// End for to count through the String line
			foundWord = "";
			line = "";
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalUpLeftToRight

	private boolean searchDiagonalDownRightToLeft(String word) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		String foundWord = "";
		for (int i = 0; i < rows; i++) {
			for (int r = rows - i, c = col - 1; r <= (rows - 1) && c >= 0; r++, c--) {
				for (int wordLength = 0; wordLength < word.length(); wordLength++) {
					if (r + wordLength <= (rows - 1) && c - wordLength >= 0) {
						foundWord = foundWord
								+ myTiles[r + wordLength][c - wordLength]
										.getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if (foundWord.equals(word)) {
					returnValue = true;
					for (int highLighter = 0; highLighter < word.length(); highLighter++) {
						myTiles[r + highLighter][c - highLighter].highLight();
					}// End For to Highlight myTiles
					r = -1;
					i = rows;
				} else {
					foundWord = "";
				}// End if-else to Detrimine if foundWord equals word
			}// End for to structure count of r & c
		}// End for to structure count to first max diagonal
		for (int i = 0; i < col; i++) {
			for (int r = 0, c = (col - 1) - i; r <= rows - 1 && c >= 0; r++, c--) {
				for (int wordLength = 0; wordLength < word.length(); wordLength++) {
					if (r + wordLength <= rows - 1 && c - wordLength >= 0) {
						foundWord = foundWord
								+ myTiles[r + wordLength][c - wordLength]
										.getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if (foundWord.equals(word)) {
					returnValue = true;
					for (int highLighter = 0; highLighter < word.length(); highLighter++) {
						myTiles[r + highLighter][c - highLighter].highLight();
					}// End For to Highlight myTiles
					r = -1;
					i = col;
				} else {
					foundWord = "";
				}// End if-else to Detrimine if foundWord equals word
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalDownRightToLeft

	private boolean searchDiagonalUpRightToLeft(String word) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		String foundWord = "";
		for (int i = 0; i < rows; i++) {
			for (int r = i, c = col - 1; r >= 0 && c >= (col - 1) - i; r--, c--) {
				for (int wordLength = 0; wordLength < word.length(); wordLength++) {
					if (r - wordLength >= 0 && c - wordLength >= (col - 1) - i) {
						foundWord = foundWord
								+ myTiles[r - wordLength][c - wordLength]
										.getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if (foundWord.equals(word)) {
					returnValue = true;
					for (int highLighter = 0; highLighter < word.length(); highLighter++) {
						myTiles[r - highLighter][c - highLighter].highLight();
					}// End For to Highlight myTiles
					r = -1;
					i = rows;
				} else {
					foundWord = "";
				}// End if-else to Detrimine if foundWord equals word
			}// End for to structure count of r & c
		}// End for to structure count to first max diagonal
		for (int i = 0; i < col; i++) {
			for (int r = rows - 1, c = (col - 1) - i; r >= 0
					&& c >= (col - 1 - i) - (rows - 1) && c >= 0; r--, c--) {
				for (int wordLength = 0; wordLength < word.length(); wordLength++) {
					if (r + wordLength >= 0
							&& c - wordLength >= (col - 1 - i) - (rows - 1)
							&& c - wordLength > 0) {
						foundWord = foundWord
								+ myTiles[r - wordLength][c - wordLength]
										.getLetter();
					}// End If to Stop Bounds Errors while Building foundWord
				}// End for to Build foundWord
				if (foundWord.equals(word)) {
					returnValue = true;
					for (int highLighter = 0; highLighter < word.length(); highLighter++) {
						myTiles[r - highLighter][c - highLighter].highLight();
					}// End For to Highlight myTiles
					r = -1;
					i = col;
				} else {
					foundWord = "";
				}// End if-else to Detrimine if foundWord equals word
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalUpRightToLeft
		
		// End Search Algorithms

}// End Class