import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Class provides support for creating line drawings
 * 
 * @author Sridhar Narayan
 * @version 1.0
 * 
 */
public class BasicLinePix extends JFrame {

	private JPanel drawingPanel; // user draws here

	private JLabel statusLabel;// used to show informational messages

	private Line myLine;// used to prevent Lines from being erased

	private ArrayList<Line> Lines = new ArrayList<Line>();

	private EventHandler eh; // event handler

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BasicLinePix lp = new BasicLinePix();
		lp.setVisible(true);

	}

	/**
	 * No argument constructor
	 */
	public BasicLinePix() {
		setTitle("Basic Line Pix 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		eh = new EventHandler();

		drawingPanel = makeDrawingPanel();
		drawingPanel.addMouseListener(eh);
		drawingPanel.addMouseMotionListener(eh);

		JPanel statusBar = createStatusBar();

		cp.add(drawingPanel, BorderLayout.CENTER);
		cp.add(statusBar, BorderLayout.SOUTH);

		buildMenu();

		pack();
	}

	// this method overrides the paint method defined in JFrame
	// add code here for drawing on the drawingPanel
	public void paint(Graphics g) {
		super.paint(g);
		// add code below
		Graphics g1 = drawingPanel.getGraphics();
		if (myLine != null && myLine.getIsAPerpendicular() == false) {
			Lines.add(myLine);
			for (int i = 0; i < Lines.size(); i++) {
				Lines.get(i).draw(g1);
			}
		}// End If to prevent null pointer exception
	}// End paint

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = buildFileMenu();
		JMenu editMenu = buildEditMenu();

		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		setJMenuBar(menuBar);

	}

	private JMenu buildEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		JMenuItem menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(eh);
		editMenu.add(menuItem);
		return editMenu;
	}

	private JMenu buildFileMenu() {
		JMenu fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("New");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);
		return fileMenu;
	}

	private JPanel makeDrawingPanel() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 400));
		p.setBackground(Color.YELLOW);

		return p;
	}

	private JPanel createStatusBar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		statusLabel = new JLabel("No message");
		panel.add(statusLabel, BorderLayout.CENTER);

		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));

		return panel;
	}

	public void clearLines() {

	}

	// GETTERS AND SETTERS //

	// Inner class - instances of this class handle action events
	private class EventHandler implements ActionListener, MouseListener,
			MouseMotionListener {

		private Point startPoint = null; // line's start point
		private Point endPoint = null; // line's most recent end point

		public void actionPerformed(ActionEvent e) {
			String actionID = e.getActionCommand();
			if (actionID.equals("Exit")) {
				statusLabel.setText("Exiting program...");
				System.exit(0);
			} else if (actionID.equals("New")) {
				statusLabel.setText("New Drawing...");
				Lines.removeAll(Lines);
				myLine = null;
				repaint();
			} else if (actionID.equals("Save")) {
				statusLabel.setText("Save Drawing...");
				String fileName = JOptionPane
						.showInputDialog("What would you like to name this file?(don't type the .txt)");
				File newFile;
				newFile = new File(fileName + ".txt");
				if (!newFile.exists()) {
					try {
						newFile.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try {
					FileWriter fileWriter = new FileWriter(newFile);
					BufferedWriter bufferedWriter = new BufferedWriter(
							fileWriter);
					for (int i = 0; i < Lines.size(); i++) {
						String startX = Lines.get(i).getStartPoint().getX()
								+ "";
						String startY = Lines.get(i).getStartPoint().getY()
								+ "";
						String endX = Lines.get(i).getEndPoint().getX() + "";
						String endY = Lines.get(i).getEndPoint().getY() + "";
						bufferedWriter.write(startX);
						bufferedWriter.write("\n");
						bufferedWriter.write(startY);
						bufferedWriter.write("\n");
						bufferedWriter.write(endX);
						bufferedWriter.write("\n");
						bufferedWriter.write(endY);
						bufferedWriter.write("\n");
					}
					bufferedWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (actionID.equals("Open")) {
				statusLabel.setText("Open Drawing...");
				try {
					openFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// ADD CODE TO OPEN newFile
			} else if (actionID.equals("Cut")) {
				for (int i = 0; i < Lines.size(); i++) {
					if (Lines.get(i).isSelected() == true) {
						Lines.remove(i);
						i = Lines.size();
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						repaint();
					}
				}
			}

		}

		private void openFile() throws FileNotFoundException {
			// TODO Auto-generated method stub
			String myFile = JOptionPane
					.showInputDialog("What file would like to open?(don't type the .txt)")
					+ ".txt";
			File aFile = new File(myFile);
			Scanner filePtr = new Scanner(aFile);
			int i = 0;
			int w = 0, x = 0, y = 0, z = 0;
			statusLabel.setText("New Drawing...");
			Lines.removeAll(Lines);
			myLine = null;
			repaint();
			while (filePtr.hasNextLine()) {
				String str = filePtr.nextLine();
				if (i == 0) {
					w = (int) Double.parseDouble(str);
				} else if (i == 1) {
					x = (int) Double.parseDouble(str);
				} else if (i == 2) {
					y = (int) Double.parseDouble(str);
				} else if (i == 3) {
					z = (int) Double.parseDouble(str);
					startPoint = new Point(w, x);
					endPoint = new Point(y, z);
					myLine = new Line(startPoint, endPoint);
					Lines.add(myLine);
					startPoint = null;
					endPoint = null;
					repaint();
				}
				if (i < 3) {
					i++;
				} else {
					i = 0;
				}

			}// End While Loop
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (e.isShiftDown()) {
				// record starting point for line
				startPoint = new Point(e.getX(), e.getY());
				// initialize endPoint
				endPoint = startPoint;
			}

			if (e.isControlDown()) {
				Graphics g = drawingPanel.getGraphics();
				g.setColor(Color.red);
				/*
				 * for (int i = 0; i < Lines.size(); i++) {
				 * Lines.get(i).setSelected(false); }// Makes sure only one line
				 * is selected
				 */
				// g.setXORMode(drawingPanel.getBackground());
				int closestLineIndex = 0;
				Line closestLine = Lines.get(0);
				for (int i = 0; i < Lines.size(); i++) {
					Line newLine = Lines.get(i).isPerpendicular(e.getX(),
							e.getY());
					newLine.setIsAPerpendicular(true);
					Point start = new Point(e.getX(), e.getY());
					Point end = new Point(newLine.getEndPoint());
					int startX = ((Double) start.getX()).intValue();
					int startY = ((Double) start.getY()).intValue();

					int endX = ((Double) end.getX()).intValue();
					int endY = ((Double) end.getY()).intValue();

					if (i == 0) {
						closestLine = newLine;
					}

					if (closestLine.getLength() > newLine.getLength()) {
						closestLine = newLine;
						closestLineIndex = i;
					}
					g.drawLine(startX, startY, endX, endY);
				}
				Line selectedLine = Lines.get(closestLineIndex);
				selectedLine.setSelected(true);
				Point start = new Point(selectedLine.getStartPoint());
				Point end = new Point(selectedLine.getEndPoint());
				int startX = ((Double) start.getX()).intValue();
				int startY = ((Double) start.getY()).intValue();
				int endX = ((Double) end.getX()).intValue();
				int endY = ((Double) end.getY()).intValue();
				g.drawLine(startX, startY, endX, endY);
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// repaint the frame and its contents
			// this executes the paint method defined above
			if (e.isShiftDown()) {
				myLine = new Line(startPoint, endPoint);
				startPoint = null;
				endPoint = null;
				repaint();
			}
			if (e.isControlDown()) {
				repaint();
				Lines.remove(Lines.get(Lines.size() - 1));
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			if (e.isShiftDown()) {
				// Implement rubber-band cursor
				Graphics g = drawingPanel.getGraphics();
				g.setColor(Color.black);

				g.setXORMode(drawingPanel.getBackground());

				// REDRAW the line that was drawn
				// most recently during this drag
				// XOR mode means that yellow pixels turn black
				// essentially erasing the existing line
				drawLine(g, startPoint, endPoint);

				// Update the end point of the line to current mouse position
				endPoint = new Point(e.getX(), e.getY());

				// Draw line to current mouse position
				// XOR mode: yellow pixels become black
				// black pixels, like those from existing lines, temporarily
				// become
				// yellow
				drawLine(g, startPoint, endPoint);

			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		private void drawLine(Graphics g, Point start, Point end) {
			if (startPoint != null && endPoint != null) {
				int startX = ((Double) start.getX()).intValue();
				int startY = ((Double) start.getY()).intValue();

				int endX = ((Double) end.getX()).intValue();
				int endY = ((Double) end.getY()).intValue();

				g.drawLine(startX, startY, endX, endY);
			}
		}

	}

}
