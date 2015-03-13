import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.StringTokenizer;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
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
	private Oval myOval;// used to prevent Ovals from being erased
	private String currentShape = "Line";
	private ArrayList<Shape> Shapes = new ArrayList<Shape>(); // This can contain both Lines and Ovals because they both extend the shapes class which means that all Lines are shapes and all Ovals are shapes
	private EventHandler eh; // event handler

	public static void main(String[] args) {
		BasicLinePix lp = new BasicLinePix();
		lp.setVisible(true);
	}// End Main

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
	}// End Constructor
	
	// this method overrides the paint method defined in JFrame
	public void paint(Graphics g) {
		super.paint(g);
		// add code below
		Graphics g1 = drawingPanel.getGraphics();
		if (myLine != null && myLine.getIsAPerpendicular() == false) {
			Shapes.add(myLine);
			for (int i = 0; i < Shapes.size(); i++) {
				Shapes.get(i).draw(g1);
			}
		}// End If to prevent null pointer exception
		if (myOval != null) {
			Shapes.add(myOval);
			for (int i = 0; i < Shapes.size(); i++) {
				Shapes.get(i).draw(g1); // To make this legal an abstract method named draw which accepted graphics g need to be added to the shapes class
			}
		}// End If to prevent null pointer exception
	}// End paint

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = buildFileMenu();
		JMenu editMenu = buildEditMenu();
		JMenu drawMenu = buildDrawMenu();
		JMenu colorMenu = buildColorMenu();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(drawMenu);
		menuBar.add(colorMenu);
		setJMenuBar(menuBar);
	}// End buildMenu
	private JMenu buildColorMenu() {
		JMenu colorMenu = new JMenu("Color");
		JMenuItem menuItem = new JMenuItem("Outline");
		menuItem.addActionListener(eh);
		colorMenu.add(menuItem);
		menuItem = new JMenuItem("Fill");
		menuItem.addActionListener(eh);
		colorMenu.add(menuItem);
		return colorMenu;
	}
	
	private JMenu buildDrawMenu() {
		JMenu editMenu = new JMenu("Draw");
		JMenuItem menuItem = new JMenuItem("Line");
		menuItem.addActionListener(eh);
		editMenu.add(menuItem);
		menuItem = new JMenuItem("Oval");
		menuItem.addActionListener(eh);
		editMenu.add(menuItem);
		return editMenu;
	}// End buildDrawMenu

	private JMenu buildEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		JMenuItem menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(eh);
		editMenu.add(menuItem);
		return editMenu;
	}// End buildEditMenu

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
	}// End buildFileMenu

	private JPanel makeDrawingPanel() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 400));
		p.setBackground(Color.YELLOW);
		return p;
	}// End makeDrawingPanel

	private JPanel createStatusBar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		statusLabel = new JLabel("No message");
		panel.add(statusLabel, BorderLayout.CENTER);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		return panel;
	}// End createStatusBar

	public String getCurrentShape() {
		return currentShape;
	}// End getCurrentShape 

	public void setCurrentShape(String currentShape) {
		this.currentShape = currentShape;
	}// End setCurrentShape

	
	/** Inner class - instances of this class handle action events
	 * EventHandler 
	 */
	private class EventHandler implements ActionListener, MouseListener, MouseMotionListener {
		private Point startPoint = null; // line's start point
		private Point endPoint = null; // line's most recent end point
		private int width;
		private int height;
		private Color outline = Color.black;
		private Color fill = Color.black;
		
		
		public void actionPerformed(ActionEvent e) {
			String actionID = e.getActionCommand();
			if (actionID.equals("Exit")) {
				statusLabel.setText("Exiting program...");
				System.exit(0);
			} else if (actionID.equals("New")) {
				statusLabel.setText("New Drawing...");
				Shapes.removeAll(Shapes);
				myLine = null;
				myOval = null;
				repaint();
			} else if (actionID.equals("Save")) {
				statusLabel.setText("Save Drawing...");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showSaveDialog(drawingPanel);
				File newFile = fileChooser.getSelectedFile();
				if (!newFile.exists()) {
					try {
						newFile.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					FileWriter fileWriter = new FileWriter(newFile);
					BufferedWriter bufferedWriter = new BufferedWriter(
							fileWriter);
					for (int i = 0; i < Shapes.size(); i++) {
						if(Shapes.get(i).getType()=="Line"){
							Line newLine = (Line) Shapes.get(i);
							int outlineRed = newLine.getOutlineRed(), outlineGreen = newLine.getOutlineGreen(), outlineBlue  = newLine.getOutlineBlue();
							String startX = ((Line) Shapes.get(i)).getStartPoint().getX()+ "";
							String startY = ((Line) Shapes.get(i)).getStartPoint().getY()+ "";
							String endX = ((Line) Shapes.get(i)).getEndPoint().getX() + "";
							String endY = ((Line) Shapes.get(i)).getEndPoint().getY() + "";
							bufferedWriter.write("L"+" "+startX+" "+startY+" "+endX+" "+endY+" "+outlineRed+" "+outlineGreen+" "+outlineBlue);
							bufferedWriter.write("\n");	
						}
						if(Shapes.get(i).getType()=="Oval"){
							Oval newOval = (Oval) Shapes.get(i);
							int outlineRed = newOval.getOutlineRed(), outlineGreen = newOval.getOutlineGreen(), outlineBlue  = newOval.getOutlineBlue();
							int fillRed = newOval.getFillRed(), fillGreen = newOval.getFillGreen(), fillBlue  = newOval.getFillBlue();
							int startX = (int) ((Oval) Shapes.get(i)).getStartPoint().getX();
							int startY = (int) ((Oval) Shapes.get(i)).getStartPoint().getY();
							int width = ((Oval) Shapes.get(i)).getWidth();
							int height = ((Oval) Shapes.get(i)).getHeight();
							bufferedWriter.write("O"+" "+startX+" "+startY+" "+width+" "+height+" "+outlineRed+" "+outlineGreen+" "+outlineBlue+" "+fillRed+" "+fillGreen+" "+fillBlue);
							bufferedWriter.write("\n");	
						}
					}
					bufferedWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (actionID.equals("Open")) {
				statusLabel.setText("Open Drawing...");
				try {
					openFile();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else if (actionID.equals("Cut")) {
				Graphics g = drawingPanel.getGraphics();
				g.setColor(Color.red);
				for (int i = 0; i < Shapes.size(); i++) {
					if (Shapes.get(i).isSelected()) {
						Shapes.remove(i);
					}
				}
				repaint();
				repaint();
			} else if (actionID.equals("Line")){
				setCurrentShape("Line");
			} else if (actionID.equals("Oval")){
				setCurrentShape("Oval");
			}else if (actionID.equals("Outline")){
				outline= JColorChooser.showDialog(getParent(), "Set Outline Color", Color.black);
			}else if (actionID.equals("Fill")){
				fill = JColorChooser.showDialog(getParent(), "Set Outline Color", Color.black);
			}
		}

		private void openFile() throws FileNotFoundException {
			// Remove all form shapes
			Shapes.removeAll(Shapes);
			myLine = null;
			myOval = null;
			repaint();
			// End Remove all form shapes
			
			// JFileChooser
			String myFile = File.separator+"txt";
			JFileChooser fileChooser = new JFileChooser(new File(myFile));
			fileChooser.showOpenDialog(drawingPanel);
			File aFile = fileChooser.getSelectedFile();
			Scanner filePtr = new Scanner(aFile);
			
			// End JFileChooser
			
			// Variables
			int startX=0, startY=0; // Used to build the start point for both lines and ovals
			int endX=0, endY=0; // Used only in Lines. Used to build the end point of a line.
			int width=0, height=0; // Used only by Ovals. 
			int outlineRed=0, outlineGreen=0, outlineBlue=0; // Used to determine the color of the outline.
			int fillRed=0, fillGreen=0, fillBlue=0;
			// End Variables
			
			statusLabel.setText("Open Drawing...");
			
			while (filePtr.hasNextLine()) {
				String str = filePtr.nextLine();
				String[] st = str.split(" ");
				String type = st[0];
				//int i = 0; // used to count the elements of each line so that each element is assigned to correct variable
				for(int i = 1; i<st.length; i++){
					if(type.equals("L")){
						if (i == 1) {
							System.out.println(st[i]);
							startX = (int) Double.parseDouble(st[i]);
						} else if (i == 2) {
							startY = (int) Double.parseDouble(st[i]);
							System.out.println(st[i]);
						} else if (i == 3) {
							endX = (int) Double.parseDouble(st[i]);
							System.out.println(st[i]);
						} else if (i == 4) {
							endY = (int) Double.parseDouble(st[i]);
							System.out.println(st[i]);
						} else if (i == 5) {
							outlineRed = Integer.parseInt(st[i]);
							System.out.println(st[i]);
						} else if (i == 6) {
							outlineBlue = Integer.parseInt(st[i]);
							System.out.println(st[i]);
						} else if (i == 7) {
							outlineGreen = Integer.parseInt(st[i]);
							System.out.println(st[i]);
							startPoint = new Point(startX, startY);
							endPoint = new Point(endX, endY);
							Color outline = new Color(outlineRed, outlineBlue, outlineGreen);
							myLine = new Line(startPoint, endPoint, outline);
							Shapes.add(myLine);
							startPoint = null;
							endPoint = null;
							repaint();
						}
					}
					if(type.equals("O")){
						if (i == 0) {
							startX = (int) Double.parseDouble(st[i]); // x pt of starting point
						} else if (i == 1) {	
							startY = (int) Double.parseDouble(st[i]); // y pt of starting point
						} else if (i == 2) {
							width = (int) Double.parseDouble(st[i]); // y is width
						} else if (i == 3) {
							height = (int) Double.parseDouble(st[i]); // z is height
						} else if (i == 4) {
							outlineRed = Integer.parseInt(st[i]);
						} else if (i == 5) {
							outlineBlue = Integer.parseInt(st[i]);
						} else if (i == 6) {
							fillRed = Integer.parseInt(st[i]);
						} else if (i == 7) {
							fillBlue = Integer.parseInt(st[i]);
						} else if (i == 8) {
							fillGreen = Integer.parseInt(st[i]);
						} else if (i == 9) {
							outlineGreen = Integer.parseInt(st[i]);
							startPoint = new Point(startX, startY);
							Color outline = new Color(outlineRed, outlineGreen, outlineBlue);
							Color fill = new Color(fillRed, fillGreen, fillBlue);
							myOval = new Oval(startPoint, width, height, outline, fill);
							Shapes.add(myOval);
							startPoint = null;
							endPoint = null;
							width = 0;
							height = 0;
							repaint();
						}
					}
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

			if (e.isShiftDown()&& getCurrentShape()=="Line") {
				// record starting point for line
				startPoint = new Point(e.getX(), e.getY());
				// initialize endPoint
				endPoint = startPoint;
			}
			if (e.isShiftDown()&& getCurrentShape()=="Oval") {
				startPoint = new Point(e.getX(), e.getY());
				endPoint = startPoint;
			}

			if (e.isControlDown()) {
				Graphics g = drawingPanel.getGraphics();
				g.setColor(Color.red);
				Point pointClicked = new Point(e.getX(), e.getY());
				ArrayList<Oval> ovals = new ArrayList<Oval>();
				ArrayList<Line> lines = new ArrayList<Line>();
				for (int i = 0; i < Shapes.size(); i++) {
					Shapes.get(i).setSelected(false);
				}
				for (int i = 0; i < Shapes.size(); i++) {
					if(Shapes.get(i).getType().equals("Oval")){
						ovals.add((Oval) Shapes.get(i));
					}
					if(Shapes.get(i).getType().equals("Line")){
						lines.add((Line) Shapes.get(i));
					}
				}
				boolean shapeSelected = false;
				if(shapeSelected==false && ovals!=null){
					for (int i = 0; i < ovals.size(); i++) {
						Oval testOval = ovals.get(i);
						testOval.contains(pointClicked);
						if(testOval.isSelected()==true){
							int startX = ((Double) ovals.get(i).getStartPoint().getX()).intValue();
							int startY = ((Double) ovals.get(i).getStartPoint().getY()).intValue();
							g.drawOval(startX, startY, ovals.get(i).getWidth(), ovals.get(i).getHeight());
							shapeSelected = true;
							i=Shapes.size();
						}
					}// End For to check Ovals
				}
				if(shapeSelected==false && lines.size()!=0){
					Line closestLine = lines.get(0);
					int closestLineIndex = 0;
					for (int i = 0; i < lines.size(); i++) {
						Line newLine = lines.get(i).isPerpendicular(e.getX(),e.getY(), outline);
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
					Line selectedLine = lines.get(closestLineIndex);
					selectedLine.setSelected(true);
					shapeSelected = true;
					Point start = new Point(selectedLine.getStartPoint());
					Point end = new Point(selectedLine.getEndPoint());
					int startX = ((Double) start.getX()).intValue();
					int startY = ((Double) start.getY()).intValue();
					int endX = ((Double) end.getX()).intValue();
					int endY = ((Double) end.getY()).intValue();
					g.drawLine(startX, startY, endX, endY);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// repaint the frame and its contents
			// this executes the paint method defined above
			if (e.isShiftDown() && getCurrentShape()=="Line") {
				myLine = new Line(startPoint, endPoint, outline);
				startPoint = null;
				endPoint = null;
				repaint();
			}
			if (e.isShiftDown() && getCurrentShape()=="Oval") {	
				myOval = new Oval(startPoint, width, height, outline, fill);
				startPoint = null;
				endPoint = null;
				width = 0;
				height = 0;
				repaint();
			}
			if (e.isControlDown()) {
				repaint();
				Shapes.remove(Shapes.get(Shapes.size() - 1));
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			if (e.isShiftDown() && getCurrentShape()=="Line") {
				// Implement rubber-band cursor
				Graphics g = drawingPanel.getGraphics();
				g.setColor(outline);

				g.setXORMode(drawingPanel.getBackground());

				// REDRAW the line that was drawn
				// most recently during this drag
				// XOR mode means that yellow pixels turn black
				// essentially erasing the existing line
				drawLine(g, startPoint, endPoint, outline);

				// Update the end point of the line to current mouse position
				endPoint = new Point(e.getX(), e.getY());

				// Draw line to current mouse position
				// XOR mode: yellow pixels become black
				// black pixels, like those from existing lines, temporarily
				// become
				// yellow
				drawLine(g, startPoint, endPoint, outline);

			}
			
			if (e.isShiftDown() && getCurrentShape()=="Oval") {
				Graphics g = drawingPanel.getGraphics();
				g.setColor(outline);
				g.setXORMode(drawingPanel.getBackground());
				drawOval(g, startPoint, endPoint, outline, fill);
				endPoint = new Point(e.getX(), e.getY());
				drawOval(g, startPoint, endPoint, outline, fill);
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		private void drawLine(Graphics g, Point start, Point end, Color outline) {
			g.setColor(outline);
			if (startPoint != null && endPoint != null) {
				int startX = ((Double) start.getX()).intValue();
				int startY = ((Double) start.getY()).intValue();
				int endX = ((Double) end.getX()).intValue();
				int endY = ((Double) end.getY()).intValue();
				g.drawLine(startX, startY, endX, endY);
			}
		}
		
		private void drawOval(Graphics g, Point start, Point end, Color outline, Color fill) {
			if (startPoint != null && endPoint != null) {
				int startX = ((Double) start.getX()).intValue();
				int startY = ((Double) start.getY()).intValue();
				int endX = ((Double) end.getX()).intValue();
				int endY = ((Double) end.getY()).intValue();
				if(startX<endX){
					width=endX-startX;
				}else{
					width=startX-endX;
				}
				if(startY<endY){
					height=endY-startY;
				}else{
					height=startY-endY;
				}
				g.setColor(fill);
				g.fillOval(startX, startY, width, height);
				g.setColor(outline);
				g.drawRect(startX, startY, width, height);
				g.drawOval(startX, startY, width, height);
				
			}
		}
	}
}
