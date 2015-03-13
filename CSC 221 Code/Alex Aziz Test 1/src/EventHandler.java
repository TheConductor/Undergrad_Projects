import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class EventHandler implements ActionListener  {
	
	private GameBoard currentGameBoard;
	public EventHandler(GameBoard gb) {
		currentGameBoard = gb;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionID = e.getActionCommand();
		if(actionID.equals("New")){
			currentGameBoard.reset();
		}else if(actionID.equals("Exit")){
			System.exit(0);
		} else{
			Tile newTile = (Tile )e.getSource();
			newTile.setClicked(true);
			changeTile();
			newTile.setClicked(false);
		}// End If Else
	}// End action Performed

	private void changeTile() {
		// TODO Auto-generated method stub
		currentGameBoard.changeTile();
	}
	
}// End Class

