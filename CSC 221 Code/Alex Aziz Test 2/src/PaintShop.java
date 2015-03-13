import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PaintShop extends JFrame {

	/**
	 * Decided to have an EventHandler as an inner class rather than implement
	 * the interfaces in the PaintShop Class because it is easier for me to keep
	 * the code organized into two sections one to build the panel and the other
	 * to perform actions on it
	 */
	
	protected JPanel displayPanel;
	private int imageWidth = 800;
	private int imageHeight = 800;
	private int numberOfPaints = 0;
	private Paints newPaint;
	private ArrayList <Paints> paintSamples = new ArrayList <Paints>();
	private Paints[][] paints;
	private EventHandler eh = new EventHandler();

	public static void main(String[] args) {
		PaintShop lp = new PaintShop();
		lp.setVisible(true);
	}// End Main

	public PaintShop() {
		setTitle("Paint Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		eh = new EventHandler();
		displayPanel = makeDisplayPanel();
		cp.add(displayPanel, BorderLayout.CENTER);
		buildMenu();
		pack();
	}// End Constructor

	private JPanel makeDisplayPanel() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(imageWidth, imageHeight));
		p.setBackground(Color.BLUE);
		return p;
	}// End makedisplayPanel

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = buildFileMenu();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}// End buildMenu

	private JMenu buildFileMenu() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);
		return fileMenu;
	}
	
	public void refresh() {
		setSize(800, 800);
		Container c = getContentPane();
		c.removeAll();
		c.setBackground(Color.BLUE);
		c.setLayout(new BorderLayout());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Paint Shop");

		JPanel paintPanel = buildPaintPanel();
		c.add(paintPanel, BorderLayout.CENTER);

		buildMenu();

		setVisible(true);
	}
	
	private JPanel buildPaintPanel() {
		JPanel paintPanel = new JPanel();
		paintPanel.setBackground(Color.ORANGE);
		paintPanel.setPreferredSize(new Dimension(200, 200));
		paintPanel.setLayout(new GridLayout(imageWidth/200, imageHeight/200));
		for (int i = 0; i < paints.length; i++)
			for (int j = 0; j < paints[0].length; j++) {
				paints[i][j].addActionListener(eh);
				paints[i][j].setBackground(paints[i][j].getC());
				paints[i][j].setText(paints[i][j].getName());
				paintPanel.add(paints[i][j]);
			}
		return paintPanel;
	}

	private class EventHandler implements ActionListener, MouseListener,
			MouseMotionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionID = e.getActionCommand();
			if (actionID.equals("Exit")) {
				System.exit(0);
			} else if (actionID.equals("Open")) {
				try {
					openFile();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				
			}
		}// End actionPerformed

		private void openFile() throws FileNotFoundException {
			paintSamples.removeAll(paintSamples);
			repaint();
			
			// JFileChooser
			String myFile = File.separator+"txt";
			JFileChooser fileChooser = new JFileChooser(new File(myFile));
			fileChooser.showOpenDialog(displayPanel);
			File aFile = fileChooser.getSelectedFile();
			Scanner filePtr = new Scanner(aFile);
			// End JFileChooser
			
			// Variables
			int red=0, green=0, blue=0;
			float price = 0;
			String paintColor;
			// End Variables
			
			while (filePtr.hasNextLine()) {
				String str = filePtr.nextLine();
				String[] st = str.split(" ");
				paintColor = st[0];
				red = Integer.parseInt(st[1]);
				green = Integer.parseInt(st[2]);
				blue = Integer.parseInt(st[3]);
				price = Float.parseFloat(st[4]);
				newPaint = new Paints(red,green,blue,price,paintColor);
				paintSamples.add(newPaint);
			}// End While
			
			int numberOfPaints = paintSamples.size();
			int paintsPerRow=0, paintsPerCol=0;
			ArrayList<Integer> factors = new ArrayList<Integer>();
			for(int i=1; i<numberOfPaints; i++){
				if(numberOfPaints%i==0){
					factors.add(i);
				}
			}
			int z = factors.size();
			for(int i=1; i<factors.size(); i++){
				int x = factors.get(i);
				int y = factors.get(factors.size()-i);
				if(y-x > 0 && y-x < z){
					z = y-x;
					paintsPerRow = x;
					paintsPerCol = y;
				}
			}
			System.out.println(paintsPerRow);
			System.out.println(paintsPerCol);
			paints =new Paints[paintsPerRow][paintsPerCol];
			int k = 0;
			for(int i=0; i<paintsPerRow; i++){
				for(int j=0; j<paintsPerCol; j++){
					paints[i][j] = paintSamples.get(k);
					k++;
				}
			}
			refresh();
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}// End mouseDragged

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}// End mouseClicked

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}// End mouseEntered

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}// End mouseExited

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}// End mousePressed

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}// End mouseReleased

	}// End EventHandler

}// End Class
