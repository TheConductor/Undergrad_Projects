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


public class GameBoard extends JFrame {
	private Tile[][] myTiles;
	private int numRows = 10;
	private int numCols = 10;
	EventHandler eh = new EventHandler(this);

	public GameBoard() {

		myTiles = new Tile[numRows][numCols];

		setSize(800, 800);

		Container c = getContentPane();
		c.setBackground(Color.GREEN);
		c.setLayout(new BorderLayout());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Word Find Game");

		JPanel tilePanel = buildTilePanel();

		c.add(tilePanel, BorderLayout.CENTER);

		JMenuBar menuBar = buildMenu();
		setJMenuBar(menuBar);

		setVisible(true);
	}
	
	private JPanel buildTilePanel(){
		JPanel tilePanel = new JPanel();
		tilePanel.setBackground(Color.ORANGE);
		tilePanel.setPreferredSize(new Dimension(800, 600));
		tilePanel.setLayout(new GridLayout(numRows, numCols));
		int tileNumber = 1; 
		for (int i = 0; i < myTiles.length; i++)
			for (int j = 0; j < myTiles[0].length; j++) {
				if(tileNumber<100){
					myTiles[i][j] = new Tile(tileNumber);
				}
				if(tileNumber==100){
					myTiles[i][j] = new Tile('?');
				}
				myTiles[i][j].addActionListener(eh);
				tilePanel.add(myTiles[i][j]);
				tileNumber++;
			}
		return tilePanel;
	}
	
	private JPanel buildTilePanelWithLetters() {
		JPanel tilePanel = new JPanel();
		tilePanel.setBackground(Color.ORANGE);
		tilePanel.setPreferredSize(new Dimension(800, 600));
		tilePanel.setLayout(new GridLayout(numRows, numCols));
		for (int i = 0; i < myTiles.length; i++){
			for (int j = 0; j < myTiles[0].length; j++) {
				myTiles[i][j].addActionListener(eh);
				tilePanel.add(myTiles[i][j]);
			}
		}
		return tilePanel;
	}
	
	private JMenuBar buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("New");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuBar.add(fileMenu);

		return menuBar;

	}
	
	public static void main(String[] args) {
		new GameBoard();
	}
	
	private JPanel resetTilePanel() {
		JPanel tilePanel = new JPanel();
		tilePanel.setBackground(Color.ORANGE);
		tilePanel.setPreferredSize(new Dimension(800, 600));
		tilePanel.setLayout(new GridLayout(numRows, numCols));
		int tileNumber = 1; 
		for (int i = 0; i < myTiles.length; i++)
			for (int j = 0; j < myTiles[0].length; j++) {
				myTiles[i][j] = null;
				if(tileNumber<100){
					myTiles[i][j] = new Tile(tileNumber);
				}
				if(tileNumber==100){
					myTiles[i][j] = new Tile('?');
				}
				myTiles[i][j].addActionListener(eh);
				tilePanel.add(myTiles[i][j]);
				tileNumber++;
			}
		return tilePanel;
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

		//JPanel controlPanel = buildControlPanel();

		c.add(tilePanel, BorderLayout.CENTER);
		//c.add(controlPanel, BorderLayout.SOUTH);

		JMenuBar menuBar = buildMenu();
		setJMenuBar(menuBar);

		setVisible(true);
	}
	public void reset() {
		setSize(800, 800);
		Container c = getContentPane();
		c.removeAll();
		c.setBackground(Color.GREEN);
		c.setLayout(new BorderLayout());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Word Find Game");

		JPanel tilePanel = resetTilePanel();

		//JPanel controlPanel = buildControlPanel();

		c.add(tilePanel, BorderLayout.CENTER);
		//c.add(controlPanel, BorderLayout.SOUTH);

		JMenuBar menuBar = buildMenu();
		setJMenuBar(menuBar);

		setVisible(true);
	}

	public void changeTile() {
		// TODO Auto-generated method stub
		int x=0, y=0;// tile that was clicked
		int a=0, b=0;// ? tile location
		int tileNumber=0;
		for(int i=0; i<getNumRows(); i++){
			for(int j=0; j<getNumCols(); j++){
				if(myTiles[i][j].isClicked()==true){
					x=i;
					y=j;
					tileNumber=myTiles[i][j].getMyNum();
				}
				if(myTiles[i][j].isUnknown()==true){
					a=i;
					b=j;
				}
			}
		}
		
		if(x==a-1&&y==b){
			myTiles[x][y]=new Tile('?');
			myTiles[a][b]=new Tile(tileNumber);
			this.refresh();
		}
		
		if(x==a+1&&y==b){
			myTiles[x][y]=new Tile('?');
			myTiles[a][b]=new Tile(tileNumber);
			this.refresh();
		}
		
		if(y==b-1&&x==a){
			myTiles[x][y]=new Tile('?');
			myTiles[a][b]=new Tile(tileNumber);
			this.refresh();
		}
		
		if(y==b+1&&x==a){
			myTiles[x][y]=new Tile('?');
			myTiles[a][b]=new Tile(tileNumber);
			this.refresh();
		}
	}// End changeTile
	
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
	
	//Getters and Setters
	
}