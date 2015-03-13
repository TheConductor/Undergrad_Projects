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
		if(actionID.equals("Open")){
			//System.out.println("Opening...");
			readTiles();
		}// If to determine if Open was clicked
		else if(actionID.equals("Exit")){
			System.exit(0);
		}// If to determine if Exit was clicked
		else if(actionID.equals("Search")||actionID.equals("searchWord")){
			//System.out.println("Searching...");
			searchTiles(currentGameBoard.getSearchWord().getText().toUpperCase());
		}// If to determine if Open was clicked
		else if(actionID.equals("Reset")){
			//System.out.println("Reset...");
			resetGameBoard();
		}/* If to determine if Open was clicked*/
		else{
			LetterTile newTile = (LetterTile )e.getSource();
			if(newTile.isHighLighted()==false){
				newTile.highLight();
				//System.out.println("highlighted");
			}else{
				newTile.reset();
				//System.out.println("unhighlighted");
			}
		}// End If Else
	}// End action Performed

	private void resetGameBoard() {
		// TODO Auto-generated method stub
		currentGameBoard.reset();
	}

	public void readTiles() {
		LetterTile [][] newTiles = new LetterTile[currentGameBoard.getNumRows()][currentGameBoard.getNumCols()];
		String fileName = JOptionPane.showInputDialog("Please type the name of the file you wish to use.\n(The deafult file is Letters.txt)");
		File myFile = new File(fileName);
		Scanner inputFile = null;
		try {
			inputFile = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}// End try catch		
		Scanner inputFile1 = null;
		try {
			inputFile1 = new Scanner(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}// End Try Catch
		for(int r=0; r<currentGameBoard.getMyTiles().length; r++){
			for(int c=0; c<currentGameBoard.getMyTiles()[0].length; c++){
				newTiles[r][c]=new LetterTile(inputFile1.next().charAt(0));
			}// End For
		}// End For
		//GameBoard gb = new GameBoard(newTiles);
		currentGameBoard.setMyTiles(newTiles);
		currentGameBoard.refresh();
	}// End readTiles
	
	private void searchTiles(String word) {
		currentGameBoard.search(word);
	}

}// End Class

