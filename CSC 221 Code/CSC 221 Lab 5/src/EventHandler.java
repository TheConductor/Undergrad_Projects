import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EventHandler implements ActionListener {

	private GameBoard currentGameBoard;

	public EventHandler(GameBoard gb) {
		currentGameBoard = gb;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionID = e.getActionCommand();
		if (actionID.equals("Exit")) {
			System.exit(0);
		}// If to determine if Exit was clicked
		else if (actionID.equals("New Game")) {
			resetGameBoard();
		}/* If to determine if Open was clicked */
		else if (actionID.equals("Mode")) {
			changeMode();
		}/* If to determine if Open was clicked */
		else {
			Tile newTile = (Tile) e.getSource();
			highlight(newTile);
		}// End If Elses4
	}// End action Performed

	private void highlight(Tile newTile) {
		if (currentGameBoard.getTurn() == 1) {
			if (newTile.getOwner() == 0 && currentGameBoard.isMove() == false
					&& newTile.isAvailable() == true) {
				// System.out.println("highlight for player 1");
				newTile.highLightForPlayer1();
				newTile.setLastMove(true);
				currentGameBoard.setMove(true);
			}// End if to pick correct highlighter
		}// End if to determine who's turn it is
		if (currentGameBoard.getTurn() == 2
				&& currentGameBoard.isMove() == false && newTile.isAvailable()) {
			if (newTile.getOwner() == 0) {
				// System.out.println("highlight for player 2");
				newTile.highLightForPlayer2();
				currentGameBoard.setMove(true);
			}// End if to pick correct highlighter
		}// End if to determine who's turn it is
	}// End highlight

	private void changeMode() {
		// TODO Auto-generated method stub
		currentGameBoard.refresh();
	}// End changeMode

	private void resetGameBoard() {
		// TODO Auto-generated method stub
		currentGameBoard.reset();
	}// End resetGameBoard

}// End Class

