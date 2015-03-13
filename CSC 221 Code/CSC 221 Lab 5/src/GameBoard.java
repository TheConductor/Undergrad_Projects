import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameBoard extends JFrame {
	private Tile[][] myTiles;
	private int numRows = 16;
	private int numCols = 16;
	private int connect = 4; // The number of tiles that need to be connected to
								// win
	private int Turn = 1; // Used to determine whose turn it is
	private int mode = 0; // Used to pick the mode
	private int countConnected = 0; // Used in search
	private int playerWhoWon = 0; // Used to determine who won
	private boolean move = false; // Used to change turn by being changed after
									// a player moves
	private boolean winner = false; // Used to check for a winner and insure
									// that new games start properly
	EventHandler eh = new EventHandler(this);

	public GameBoard() {
		myTiles = new Tile[numRows][numCols];
		setSize(800, 800);
		Container c = getContentPane();
		c.removeAll();
		c.setBackground(Color.GREEN);
		c.setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("CONNECT 4!");

		JPanel tilePanel = buildTilePanel();

		c.add(tilePanel, BorderLayout.CENTER);

		JMenuBar menuBar = buildMenu();
		setJMenuBar(menuBar);

		setVisible(true);
	}// End Constructor

	private JPanel buildTilePanel() {
		JPanel tilePanel = new JPanel();
		tilePanel.setBackground(Color.BLUE);
		tilePanel.setPreferredSize(new Dimension(800, 600));
		tilePanel.setLayout(new GridLayout(numRows, numCols));

		for (int i = 0; i < myTiles.length; i++)
			for (int j = 0; j < myTiles[0].length; j++) {
				myTiles[i][j] = new Tile(0);
				myTiles[i][j].addActionListener(eh);
				tilePanel.add(myTiles[i][j]);
			}
		return tilePanel;
	}// End build TilePanel

	private JMenuBar buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("New Game");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Mode");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuBar.add(fileMenu);

		return menuBar;
	}

	public static void main(String[] args) {
		GameBoard gb = new GameBoard();
		gb.changeMode();
		gb.play();
	}// End main

	public void changeMode() {
		String selectMode = new JOptionPane()
				.showInputDialog("Which mode would you like to play?\n(Type 1 for 1 player or 2 for 2 player)");
		int changeMode = Integer.parseInt(selectMode);
		while (changeMode != 1 && changeMode != 2) {
			String reSelectMode = new JOptionPane()
					.showInputDialog("Please give a valid mode #.\n(Type 1 for 1 player or 2 for 2 player)");
			changeMode = Integer.parseInt(reSelectMode);
		}// End input validation
		this.setMode(changeMode);
	}

	public boolean checkForWinner() {
		boolean returnValue = false;
		if (this.search(1) == true) {
			this.setWinner(true);
			returnValue = true;
			this.setPlayerWhoWon(1);
		}
		if (this.search(2) == true) {
			this.setWinner(true);
			returnValue = true;
			this.setPlayerWhoWon(2);
		}
		if (this.isWinner() && this.getPlayerWhoWon() == 0) {
			returnValue = true;
		}
		return returnValue;
	}

	private void play() {
		int continuePlaying = 0;
		while (continuePlaying == 0) {
			if (this.getMode() == 1) {
				while (this.isWinner() == false) {
					if (this.getTurn() == 1) {
						this.turn();
						this.setTurn(2);
						if (this.checkForWinner() == true) {
							break;
						}
					}// End While that governs the players turn
					if (this.getTurn() == 2) {
						this.computerTurn();
						this.setTurn(1);
						if (this.checkForWinner() == true) {
							break;
						}
					}// End While that governs the players turn
				}// End While to see if someone has won

				if (this.getPlayerWhoWon() == 1) {
					JOptionPane.showMessageDialog(null,
							"Player 1 is the winner!");
					this.reset();
					this.setWinner(false);
				}
				if (this.getPlayerWhoWon() == 2) {
					JOptionPane.showMessageDialog(null, "I won!");
					this.reset();
					this.setWinner(false);
				}// End If Else to print winner message
			}
			if (this.getMode() == 2) {
				while (this.isWinner() == false) {
					if (this.getTurn() == 1) {
						this.turn();
						this.setTurn(2);
					}// End While that governs the players turn
					if (this.checkForWinner() == true) {
						break;
					}
					if (this.getTurn() == 2) {
						this.turn();
						this.setTurn(1);
					}// End While that governs the players turn
					if (this.checkForWinner() == true) {
						break;
					}
				}// End While to see if someone has won

				if (this.getPlayerWhoWon() == 1) {
					JOptionPane.showMessageDialog(null,
							"Player 1 is the winner!");
					this.reset();
					this.setWinner(false);
				}
				if (this.getPlayerWhoWon() == 2) {
					JOptionPane.showMessageDialog(null,
							"Player 1 is the winner!");
					this.reset();
					this.setWinner(false);
				}// End If Else to print winner message
			}
			this.setWinner(false);
			this.setTurn(1);
			this.setMove(false);
		}
	}

	public void turn() {
		int printMessage = 0;
		if (printMessage == 0) {
			JOptionPane.showMessageDialog(null,
					"It is player " + this.getTurn()
							+ "'s turn.\nClick the tile you want to claim.");
			printMessage++;
		}// End While to prevent infinite loop message windows
		for (int i = 0; i < myTiles.length; i++) {
			for (int j = 0; j < myTiles[0].length; j++) {
				if (i + 1 < myTiles.length) {
					if (myTiles[i + 1][j].getOwner() != 0) {
						if (myTiles[i][j].getOwner() == 0) {
							myTiles[i][j].highLightAvailabeMoves();
						}
					}// End If to determine if tile is a valid move
				} else {
					if (myTiles[i][j].getOwner() == 0) {
						myTiles[i][j].highLightAvailabeMoves();
					}
				}// End If to determine if tile is a valid move
			}// End for to move through cols
		}// End for to move through rows
		do {
			// System.out.println(this.isMove());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (this.isMove() == false);// End While to prevent infinite loop
		this.setMove(false);
	}

	public void computerTurn() {
		int printMessage = 0;
		if (printMessage == 0) {
			JOptionPane.showMessageDialog(null, "It is my turn.");
			printMessage++;
		}// End While to prevent infinite loop message windows

		int x = 0, y = 0; // used to hold the x and y cooridnates of the last
							// move
		for (int i = 0; i < myTiles.length; i++) {
			for (int j = 0; j < myTiles[0].length; j++) {
				if (i + 1 < myTiles.length) {
					if (myTiles[i + 1][j].getOwner() != 0) {
						if (myTiles[i][j].getOwner() == 0) {
							myTiles[i][j].highLightAvailabeMoves();
						}
					}// End If to determine if tile is a valid move
				} else {
					if (myTiles[i][j].getOwner() == 0) {
						myTiles[i][j].highLightAvailabeMoves();
					}
				}// End If to determine if tile is a valid move
			}// End for to move through cols
		}// End for to move through rows

		boolean checkMove = false;
		for (int i = 0; i < myTiles.length; i++) {
			for (int j = 0; j < myTiles[0].length; j++) {
				if (myTiles[i][j].isLastMove() == true) {
					x = i;
					y = j;
				}
				myTiles[i][j].setLastMove(false);
			}
		}

		System.out.println(x+","+y);
		// Block connect 4 when player has 3 connected //
		if (x + 1 < this.getNumRows()) {
			if (myTiles[x + 1][y].getOwner() == 1) {
				if (x + 2 < this.getNumRows() && x - 1 >= 0) {
					if (myTiles[x + 2][y].getOwner() == 1
							&& myTiles[x - 1][y].isAvailable()
							&& checkMove == false) {
						myTiles[x - 1][y].highLightForPlayer2();
						checkMove = true;
					}// End if to check if the player has 3 connected vertically
				}// End if to check bounds
			}// End if to check if the player has 2 connected vertically
		}// End if to check bounds
		// End Block connect 4 when player has 3 connected //
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Block connect 4 when player has 2 connected //
		if (y - 1 > 0 && checkMove == false) {
			if (myTiles[x][y-1].getOwner() == 1 && checkMove == false) {
				if (y + 1 < this.getNumCols() && checkMove == false){
					if (myTiles[x][y+1].getOwner() == 0 && myTiles[x][y+1].isAvailable() && checkMove == false) {
						myTiles[x][y+1].highLightForPlayer2();
						checkMove = true;
					}// End if to check if the player has 3 connected vertically
				}// End if to check bounds
			}// End if to check if the player has 2 connected vertically
		}// End if to check bounds
		if (y + 1 < this.getNumCols()) {
			if (myTiles[x][y+1].getOwner() == 1) {
				if (y - 1 >= 0){
					if (myTiles[x][y-1].getOwner() == 0 && myTiles[x][y-1].isAvailable() && checkMove == false) {
						myTiles[x][y-1].highLightForPlayer2();
						checkMove = true;
					}// End if to check computer move
				}// End if to check bounds
			}// End if to check if the player has 2 connected horizontally
		}// End if to check bounds
		// End Block connect 4 when player has 2 connected //

		// Block connect 4 when player has 1 connected //
		Random rand = new Random();
		int firstMove = rand.nextInt(1);
		if(firstMove == 0){
			if (y - 1 > 0) {
				if (myTiles[x][y-1].getOwner() == 0 && myTiles[x][y-1].isAvailable() && checkMove == false) {
					myTiles[x][y-1].highLightForPlayer2();
					checkMove = true;
				}// End if to check if the player has 3 connected vertically
			}// End if to check bounds
		}else if(firstMove == 1){
			if (y + 1 < this.getNumCols()) {
				if (myTiles[x][y+1].getOwner() == 0 && myTiles[x][y+1].isAvailable() && checkMove == false) {
					myTiles[x][y+1].highLightForPlayer2();
					checkMove = true;
				}// End if to check if the player has 2 connected horizontally
			}// End if to check bounds
		}// End If
		if(firstMove == 1){
			if (y + 1 < this.getNumCols()) {
				if (myTiles[x][y+1].getOwner() == 0 && myTiles[x][y+1].isAvailable() && checkMove == false) {
					myTiles[x][y+1].highLightForPlayer2();
					checkMove = true;
				}// End if to check if the player has 2 connected horizontally
			}// End if to check bounds
		}else if(firstMove == 0){
			if (y - 1 > 0) {
				if (myTiles[x][y-1].getOwner() == 0 && myTiles[x][y-1].isAvailable() && checkMove == false) {
					myTiles[x][y-1].highLightForPlayer2();
					checkMove = true;
				}// End if to check if the player has 3 connected vertically
			}// End if to check bounds
		}// End If
		// End Block connect 4 when player has 1 connected //
		
		if(checkMove ==  false){
			for (int i = 0; i < myTiles.length; i++) {
				for (int j = 0; j < myTiles[0].length; j++) {
					if (i + 1 < myTiles.length) {
						if (myTiles[i + 1][j].getOwner() != 0) {
							if (myTiles[i][j].getOwner() == 0 && myTiles[i + 1][j].isAvailable()) {
								myTiles[i][j].highLightForPlayer2();
								i = myTiles.length;
								j = myTiles[0].length;
							}
						}// End If to determine if tile is a valid move
					} else {
						if (myTiles[i][j].getOwner() == 0 && myTiles[i][j].isAvailable()) {
							myTiles[i][j].highLightForPlayer2();
							i = myTiles.length;
							j = myTiles[0].length;
						}
					}// End If to determine if tile is a valid move
				}// End for to move through cols
			}// End for to move through rows
		}
	}// End Computer Turn

	public void refresh() {
		this.changeMode();
		this.setMove(true);
		this.setWinner(true);
		for (int i = 0; i < myTiles.length; i++) {
			for (int j = 0; j < myTiles[0].length; j++) {
				myTiles[i][j].reset();
			}
		}
	}

	public void reset() {
		for (int i = 0; i < myTiles.length; i++) {
			for (int j = 0; j < myTiles[0].length; j++) {
				myTiles[i][j].reset();
			}
		}
		if(this.isWinner()==false){
			this.setTurn(1);
			this.setCountConnected(0);
			this.setMove(false);
			int printMessage = 0;
			if (printMessage == 0) {
				JOptionPane.showMessageDialog(null,
						"It is player " + this.getTurn()
								+ "'s turn.\nClick the tile you want to claim.");
				printMessage++;
			}// End While to prevent infinite loop message windows
			
			for (int i = 0; i < myTiles.length; i++) {
				for (int j = 0; j < myTiles[0].length; j++) {
					if (i + 1 < myTiles.length) {
						if (myTiles[i + 1][j].getOwner() != 0) {
							if (myTiles[i][j].getOwner() == 0) {
								myTiles[i][j].highLightAvailabeMoves();
							}
						}// End If to determine if tile is a valid move
					} else {
						if (myTiles[i][j].getOwner() == 0) {
							myTiles[i][j].highLightAvailabeMoves();
						}
					}// End If to determine if tile is a valid move
				}// End for to move through cols
			}// End for to move through rows	
		}
	}

	// GETTERS AND SETTERS //
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

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getConnect() {
		return connect;
	}

	public void setConnect(int connect) {
		this.connect = connect;
	}

	public int getTurn() {
		return Turn;
	}

	public void setTurn(int Turn) {
		this.Turn = Turn;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public int getCountConnected() {
		return countConnected;
	}

	public void setCountConnected(int countConnected) {
		this.countConnected = countConnected;
	}

	public int getPlayerWhoWon() {
		return playerWhoWon;
	}

	public void setPlayerWhoWon(int playerWhoWon) {
		this.playerWhoWon = playerWhoWon;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	// END GETTERS AND SETTERS //

	public boolean search(int player) {
		boolean returnValue = false;
		if (searchLeftToRight(player) == false) {
			if (searchBottomToTop(player) == false) {
				if (searchDiagonalUpRightToLeft(player) == false) {
					if (searchDiagonalUpLeftToRight(player) == false) {
						returnValue = false;
					} else {

						returnValue = true;
					}// End Diagonal Up Left To Right
				} else {
					returnValue = true;
				}// End Diagonal Down Left To Right
			} else {
				returnValue = true;
			}// End Top To Bottom
		} else {
			returnValue = true;
		}// End Left to Right
		return returnValue;
	} // End search

	// Search Algorithms //
	private boolean searchLeftToRight(int player) {
		boolean returnValue = false;
		for (int r = 0; r < this.getNumRows(); r++) {
			for (int c = 0; c < this.getNumCols(); c++) {
				if (myTiles[r][c].getOwner() == player) {
					this.setCountConnected(this.getCountConnected() + 1);
				} else {
					this.setCountConnected(0);
				}
				if (countConnected == this.getConnect()) {
					returnValue = true;
					c = this.getNumCols();
					r = this.getNumRows();
				}// End If to test for win
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End searchLeftToRight

	private boolean searchBottomToTop(int player) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		for (int c = col - 1; c > 0; c--) {
			for (int r = rows - 1; r > 0; r--) {
				if (myTiles[r][c].getOwner() == player) {
					this.setCountConnected(this.getCountConnected() + 1);
				} else {
					this.setCountConnected(0);
				}
				if (countConnected == this.getConnect()) {
					returnValue = true;
					c = 0;
					r = 0;
				}// End If to test for win
			}// End For to count cols
		}// End For to count rows
		return returnValue;
	}// End searchBottomToTop

	private boolean searchDiagonalUpRightToLeft(int player) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		for (int i = 0; i < rows; i++) {
			for (int r = i, c = col - 1; r >= 0 && c >= (col - 1) - i; r--, c--) {
				if (myTiles[r][c].getOwner() == player) {
					this.setCountConnected(this.getCountConnected() + 1);
				} else {
					this.setCountConnected(0);
				}
				if (countConnected == this.getConnect()) {
					returnValue = true;
					c = this.getNumCols();
					r = this.getNumRows();
				}// End If to test for win
			}// End for to structure count of r & c
		}// End for to structure count to first max diagonal
		for (int i = 0; i < col; i++) {
			for (int r = rows - 1, c = (col - 1) - i; r >= 0
					&& c >= (col - 1 - i) - (rows - 1) && c >= 0; r--, c--) {
				if (myTiles[r][c].getOwner() == player) {
					this.setCountConnected(this.getCountConnected() + 1);
				} else {
					this.setCountConnected(0);
				}
				if (countConnected == this.getConnect()) {
					returnValue = true;
					c = this.getNumCols();
					r = this.getNumRows();
				}// End If to test for win
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalUpRightToLeft

	private boolean searchDiagonalUpLeftToRight(int player) {
		boolean returnValue = false;
		int rows = myTiles.length, col = myTiles[0].length;
		for (int i = 0; i < rows; i++) {
			for (int r = i, c = 0; r >= 0 && c <= i; r--, c++) {
				if (myTiles[r][c].getOwner() == player) {
					this.setCountConnected(this.getCountConnected() + 1);
				} else {
					this.setCountConnected(0);
				}
				if (countConnected == this.getConnect()) {
					returnValue = true;
					c = this.getNumCols();
					r = this.getNumRows();
				}// End If to test for win
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		for (int j = 1; j <= col - 1; j++) {
			for (int r = rows - 1, c = j; r >= 0 && c <= col - 1; r--, c++) {
				if (myTiles[r][c].getOwner() == player) {
					this.setCountConnected(this.getCountConnected() + 1);
				} else {
					this.setCountConnected(0);
				}
				if (countConnected == this.getConnect()) {
					returnValue = true;
					c = this.getNumCols();
					r = this.getNumRows();
				}// End If to test for win
			}// End count to structure count of r & c
		}// End for to structure count to after first max diagonal
		return returnValue;
	}// End searchDiagonalDownLeftToRight
		// End Search Algorithms //
}// End Class
