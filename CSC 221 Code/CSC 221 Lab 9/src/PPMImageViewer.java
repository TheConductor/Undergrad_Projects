import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;

;
public class PPMImageViewer extends JFrame implements ActionListener {

	// this is where the image pixels are drawn
	private JPanel displayPanel;

	// this array holds all the bytes corresponding to the colors of the pixels
	// in the image
	private int[][][] imageData;

	JFileChooser jfc;

	// default values
	// for each image, these values will be changed
	// after you know the actual dimensions of the image
	private int imageWidth = 800, imageHeight = 800;

	public PPMImageViewer() {

		setSize(800, 800);
		setTitle("PPM Image Viewer 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(imageWidth, imageHeight));
		displayPanel.setBackground(Color.PINK);

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		cp.add(displayPanel, BorderLayout.CENTER);

		setJMenuBar(buildMenu());

		jfc = new JFileChooser();
		pack();
		setVisible(true);

	}

	public static void main(String[] args) {
		new PPMImageViewer();
	}

	public void paint(Graphics g) {
		super.paint(g);

		displayImage();

	}

	public void displayImage() {
		Graphics g = displayPanel.getGraphics();
		// Complete this method EXACTLY as directed below

		// for each pixel in the displayPanel
		// 1. Use the data in the imageData array to determine the
		// appropriate color for the corresponding pixel in the displayPanel
		// 2. Render the pixel in that color
		// by setting the color of the graphics context to that color
		// and then drawing a line of 0 length at that point

		if (imageData != null) {
			Color[][] colors = new Color[imageWidth][imageHeight];
			Color color;
			int red = 0, blue = 0, green = 0;
			for (int i = 0; i < imageWidth; i++) {
				for (int j = 0; j < imageHeight; j++) {
					for (int k = 0; k < 3; k++) {
						if (k == 0) {
							red = imageData[i][j][k];
						}
						if (k == 1) {
							blue = imageData[i][j][k];
						}
						if (k == 2) {
							green = imageData[i][j][k];
						}
					}
					color = new Color(red, blue, green);
					colors[i][j] = color;
				}
			}

			for (int i = 0; i < imageWidth; i++) {
				for (int j = 0; j < imageHeight; j++) {
					g.setColor(colors[i][j]);
					g.drawLine(i, j, i, j);
				}
			}
		}
	}

	private JMenuBar buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);

		fileMenu.add(menuItem);

		menuBar.add(fileMenu);

		return menuBar;

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("Open")) {
			int retVal = jfc.showOpenDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getPath();
				readImageData(fileName);
				repaint();
			}
		} else if (arg0.getActionCommand().equals("Exit")) {
			System.exit(0);
		}

	}

	private void readImageData(String ppmFileName) {
		// System.out.println("Opening file " + ppmFileName);

		readFile(ppmFileName);

		// Adjust size of displayPanel to these values
		displayPanel.setSize(imageWidth, imageHeight);

		// Adjust size of frame too
		if (imageWidth * imageHeight > 800 * 800) {
			setSize(imageWidth, imageHeight);
		}

		// read pixel color information from file into the imageData byte array
		imageData = new int[imageHeight][imageHeight][3];
		imageData = readPixelBytes(ppmFileName);

	}

	private void readFile(String ppmFileName) {
		File inFile = new File(ppmFileName);
		Scanner s = null;
		try {
			s = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (!s.nextLine().equals("P6")) {
			JOptionPane.showMessageDialog(displayPanel,
					"Error\nCan only open ppm Files.");
		}
		String deminsons = s.nextLine();
		String[] st = deminsons.split(" ");
		imageWidth = readWidth(st[0]);
		imageHeight = readHeight(st[1]);
		if (Integer.parseInt(s.nextLine()) != 255) {
			JOptionPane.showMessageDialog(displayPanel,
					"Error\nCan only open ppm Files.");
		}
		s.close();
	}

	// Determine the width of the ppm image
	private int readWidth(String ppmFileName) {
		int returnValue = 0;
		returnValue = Integer.parseInt(ppmFileName);
		return returnValue;
	}

	// Determine the height of the ppm image
	private int readHeight(String ppmFileName) {
		int returnValue = 0;
		returnValue = Integer.parseInt(ppmFileName);
		return returnValue;
	}

	// return a byte array that contains all the image-related bytes from the
	// ppm file
	private int[][][] readPixelBytes(String ppmFileName) {
		int[][][] returnValue = new int[imageWidth][imageHeight][3];
		File inFile = new File(ppmFileName);
		FileInputStream fis = null;
		DataInputStream inDataStream = null;

		try {
			fis = new FileInputStream(inFile);
			inDataStream = new DataInputStream(fis);
			inDataStream.readLine();
			inDataStream.readLine();
			inDataStream.readLine();
			for (int i = 0; i < imageHeight; i++) {
				for (int j = 0; j < imageWidth; j++) {
					for (int k = 0; k < 3; k++) {
						returnValue[j][i][k] = inDataStream.readUnsignedByte();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnValue;
	}
}
