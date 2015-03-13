import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame {
	/**
	 * Used for Object I/O
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * General Code by Alex Aziz Save and Open Methods done by both Gene and
	 * Alex GUI designed and prototyped by Alex Aziz
	 */
	private JPanel displayPanel; // User draws here
	private int imageWidth = 800, imageHeight = 800;
	// Used to determine size of GUI and size of elements in GUI.
	private Class newClass;
	private InnerClass newInnerClass;
	private Constructor newConstructor;
	private Main newMain;
	private Method newMethod;
	private Variable newVariable;
	private ArrayList<Class> Elements = new ArrayList<Class>();
	// Contains all user created Elements
	private EventHandler eh; // Event handler
	public int numberOfClasses = 0;
	private int numberOfMains = 0;

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setVisible(true);
	}// End Main

	public GUI() {
		setTitle("Java Application Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		eh = new EventHandler();
		displayPanel = makeDisplayPanel();
		cp.add(displayPanel, BorderLayout.CENTER);
		buildMenu();
		pack();
	}// End Constructor

	public void paint(Graphics g) {
		super.paint(g);
		Graphics g1 = displayPanel.getGraphics();
		for (int i = 0; i < Elements.size(); i++) {
			Elements.get(i).draw(g1);
			for (int j = 0; j < Elements.get(i).getElements().size(); j++) {
				Elements.get(i).getElements().get(j).draw(g1);
			}
		}
	}// End paint

	private JPanel makeDisplayPanel() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(imageWidth, imageHeight));
		p.setBackground(Color.YELLOW);
		return p;
	}// End makedisplayPanel

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = buildFileMenu();
		JMenu editMenu = buildElementMenu();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		setJMenuBar(menuBar);
	}// End buildMenu

	private JMenu buildFileMenu() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("New Project");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);
		menuItem = new JMenuItem("Generate Code");
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

	private JMenu buildElementMenu() {
		JMenu elementMenu = new JMenu("Elements");
		JMenuItem menuItem = new JMenuItem("Remove Element");
		menuItem.addActionListener(eh);
		elementMenu.add(menuItem);
		menuItem = new JMenuItem("Edit Element");
		menuItem.addActionListener(eh);
		elementMenu.add(menuItem);
		menuItem = new JMenuItem("New Class");
		menuItem.addActionListener(eh);
		elementMenu.add(menuItem);
		menuItem = new JMenuItem("New Inner Class");
		menuItem.addActionListener(eh);
		elementMenu.add(menuItem);
		menuItem = new JMenuItem("New Variable");
		menuItem.addActionListener(eh);
		elementMenu.add(menuItem);
		menuItem = new JMenuItem("New Main");
		menuItem.addActionListener(eh);
		elementMenu.add(menuItem);
		menuItem = new JMenuItem("New Constructor");
		menuItem.addActionListener(eh);
		elementMenu.add(menuItem);
		menuItem = new JMenuItem("New Method");
		menuItem.addActionListener(eh);
		elementMenu.add(menuItem);
		return elementMenu;
	}// End buildElementMenu

	private class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String actionID = e.getActionCommand();
			Graphics g = displayPanel.getGraphics();
			int width = 0, height = 0, startX = 0, startY = 0; // Used to scale
																// elements to
																// frame
			if (actionID.equals("Exit")) {
				System.exit(0);
			} else if (actionID.equals("New Project")) {
				Elements.removeAll(Elements);
				newClass = null;
				newInnerClass = null;
				newConstructor = null;
				newMain = null;
				newMethod = null;
				newVariable = null;
				numberOfClasses = 0;
				numberOfMains = 0;
				repaint();
			} else if (actionID.equals("Generate Code")) {
				generateCode();
			} else if (actionID.equals("Save")) {
				saveProject();
			} else if (actionID.equals("Open")) {
				try {
					openProject();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else if (actionID.equals("Remove Element")) {
				String name = JOptionPane
						.showInputDialog("What is the name of the Element you would like to remove?");
				for (int i = 0; i < Elements.size(); i++) {
					Class newElement = (Class) Elements.get(i);
					if (newElement.getName().equals(name)) {
						boolean removeConstructor = false;
						for (int j = 0; j < newElement.getElements().size(); j++) {
							if (newElement.getElements().get(j).getType()
									.equals("Constructor")) {
								if (JOptionPane
										.showConfirmDialog(
												displayPanel,
												"Would you like to remove the Constructor or Class?\n(yes for constructor no for class)") == 0) {
									newElement.getElements().remove(j);
									newElement
											.setNumberOfConstructors(newElement
													.getNumberOfConstructors() - 1);
									removeConstructor = true;
								}
							}

						}
						if (removeConstructor == false) {
							numberOfClasses--;
							for (int j = 0; j < newElement.getElements().size(); j++) {
								if (newElement.getElements().get(j).getType()
										.equals("Main")) {
									numberOfMains = 0;
								}
							}
							Elements.remove(i);
						}
					} else {
						for (int j = 0; j < newElement.getElements().size(); j++) {
							if (newElement.getElements().get(j).getName()
									.equals(name)) {
								if (newElement.getElements().get(j).getType()
										.equals("Main")) {
									numberOfMains = 0;
								}
								if (newElement.getElements().get(j).getType()
										.equals("Variable")) {
									newElement.setNumberOfVariables(newElement
											.getNumberOfVariables() - 1);
								}
								if (newElement.getElements().get(j).getType()
										.equals("Method")) {
									newElement.setNumberOfMethods(newElement
											.getNumberOfMethods() - 1);
								}
								if (newElement.getElements().get(j).getType()
										.equals("Inner Class")) {
									newElement
											.setNumberOfInnerClasses(newElement
													.getNumberOfInnerClasses() - 1);
								}
								newElement.getElements().remove(j);
							}
						}
					}
				}
				repaint();
			} else if (actionID.equals("Edit Element")) {
				String editElementName = JOptionPane
						.showInputDialog("What is the name of the Element you would like to edit?");
				for (int i = 0; i < Elements.size(); i++) {
					if (Elements.get(i).getName().equals(editElementName)) {
						Class editClass = Elements.get(i);
						String editProperty = JOptionPane
								.showInputDialog("What is the name of the Property you would like to edit?");
						if (editProperty.toLowerCase().equals("name")) {
							String editName = JOptionPane
									.showInputDialog("What would you like to change the name to?");
							editClass.setName(editName);
							for (int j = 0; j < editClass.getElements().size(); j++) {
								editClass.getElements().get(j)
										.setName(editName);
							}
						}
						if (editProperty.toLowerCase().equals("scope")) {
							editClass
									.setScope("What would you like to change the scope to?");
						}
						if (editProperty.toLowerCase().equals("abstract")) {
							int editAbstract = JOptionPane.showConfirmDialog(
									displayPanel, "Would like to make "
											+ editElementName + " abastract");
							if (editAbstract == 1) {
								editClass.setabstractClass(false);
							}
							if (editAbstract == 0) {
								editClass.setabstractClass(true);
							}
							if (editAbstract == 2) {

							}
						}
						if (editProperty.toLowerCase().equals("final")) {
							int editAbstract = JOptionPane.showConfirmDialog(
									displayPanel, "Would like to make "
											+ editElementName + " abastract");
							if (editAbstract == 1) {
								editClass.setabstractClass(false);
							}
							if (editAbstract == 0) {
								editClass.setabstractClass(true);
							}
							if (editAbstract == 2) {

							}
						}
					}
					for (int j = 0; j < Elements.get(i).getElements().size(); j++) {
						if (Elements.get(i).getElements().get(j).getName()
								.equals(editElementName)) {
							if (Elements.get(i).getElements().get(j).getName()
									.equals(editElementName)) {
								Elements editElement = Elements.get(i)
										.getElements().get(j);
								if (Elements.get(i).getElements().get(j)
										.getType().equals("Constructor")) {
									// Do Nothing, don't want user to try and
									// edit constructors only let them edit
									// Classes
								}
								String editProperty = JOptionPane
										.showInputDialog("What is the name of the Property you would like to edit?\n(name, scope, variable type, return type, abstract, static, final)");
								if (editProperty.toLowerCase().equals("name")) {
									editElement
											.setName(JOptionPane
													.showInputDialog("What would you like to the new name to be?"));
								}
								if (editProperty.toLowerCase().equals("scope")) {
									editElement
											.setScope(JOptionPane
													.showInputDialog("What would you like to the new scope to be?"));
								}
								if (editProperty.toLowerCase().equals(
										"return type")) {
									Method editMethod = (Method) editElement;
									editMethod
											.setReturnType(JOptionPane
													.showInputDialog("What would you like to the new type to be?"));
								}
								if (editProperty.toLowerCase().equals(
										"abstract")) {
									Method editMethod = (Method) editElement;
									int editAbstract = JOptionPane
											.showConfirmDialog(displayPanel,
													"Would like to make "
															+ editElementName
															+ " abastract?");
									if (editAbstract == 1) {
										editMethod.setAbstractMethod(false);
									}
									if (editAbstract == 0) {
										editMethod.setAbstractMethod(true);
									}
									if (editAbstract == 2) {

									}
								}
								if (editProperty.toLowerCase().equals("static")) {
									Method editMethod = (Method) editElement;
									int editStatic = JOptionPane
											.showConfirmDialog(displayPanel,
													"Would like to make "
															+ editElementName
															+ " static?");
									if (editStatic == 1) {
										editMethod.setStaticMethod(false);
									}
									if (editStatic == 0) {
										editMethod.setStaticMethod(true);
									}
									if (editStatic == 2) {

									}
								}
								if (editProperty.equals("final")) {
									Method editMethod = (Method) editElement;
									int editFinal = JOptionPane
											.showConfirmDialog(displayPanel,
													"Would like to make "
															+ editElementName
															+ " final?");
									if (editFinal == 1) {
										editMethod.setFinalMethod(false);
									}
									if (editFinal == 0) {
										editMethod.setFinalMethod(true);
									}
									if (editFinal == 2) {

									}
								}
								if (editProperty.toLowerCase().equals(
										"variable type")) {
									Variable editMethod = (Variable) editElement;
									editMethod
											.setVariableType(JOptionPane
													.showInputDialog("What would you like to the new return type to be?"));
								}
							}
						}
					}
				}
			} else if (actionID.equals("New Class")) {
				numberOfClasses++;
				width = 700/numberOfClasses;
				height = 600;
				startX = 50;
				startY = 50;
				newElement(g, width, height, startX, startY, "Class",
						Color.blue);
				Elements.add(newClass);
				newClass = null;
				for(int i=0; i<Elements.size(); i++){
					if(Elements.get(i).getType().equals("Class")){
						width = 700/numberOfClasses;
						Elements.get(i).setWidth(width);
						startX = 50 + width*i;
						Elements.get(i).setStartX(startX);
					}
				}
				repaint();
			} else if (actionID.equals("New Inner Class")) {
				width = 500;
				height = 50;
				startX = 150;
				startY = 550;
				newElement(g, width, height, startX, startY, "Inner Class",
						Color.cyan);
				newInnerClass = null;
				repaint();
			} else if (actionID.equals("New Variable")) {
				width = 50;
				height = 50;
				startX = 150;
				startY = 150;
				newElement(g, width, height, startX, startY, "Variable",
						Color.green);
				repaint();
			} else if (actionID.equals("New Main")) {
				width = 350;
				height = 50;
				startX = 150;
				startY = 250;
				newElement(g, width, height, startX, startY, "Main", Color.red);
				repaint();
			} else if (actionID.equals("New Constructor")) {
				width = 350;
				height = 50;
				startX = 150;
				startY = 350;
				newElement(g, width, height, startX, startY, "Constructor",
						Color.MAGENTA);
				newConstructor = null;
				repaint();
			} else if (actionID.equals("New Method")) {
				width = 350;
				height = 50;
				startX = 150;
				startY = 450;
				newElement(g, width, height, startX, startY, "Method",
						Color.black);
				newMethod = null;
				repaint();
			}

		}

		private void saveProject() {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showSaveDialog(displayPanel);
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
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				for (int i = 0; i < Elements.size(); i++) {
					Class saveClass = (Class) Elements.get(i);

					String name = saveClass.getName();
					String type = saveClass.getType();
					String scope = saveClass.getScope();
					int saveWidth = saveClass.getWidth();
					int saveHeight = saveClass.getHeight();
					int saveStartX = saveClass.getStartX();
					int saveStartY = saveClass.getStartY();
					boolean abstractClass = saveClass.isabstractClass();
					boolean finalClass = saveClass.isfinalClass();
					boolean hasMain = saveClass.isHasMain();
					int numberOfVariables = saveClass.getNumberOfVariables();
					int numberOfConstructors = saveClass
							.getNumberOfConstructors();
					int numberOfMethods = saveClass.getNumberOfMethods();
					int numberOfInnerClasses = saveClass
							.getNumberOfInnerClasses();
					int numberOfElements = numberOfVariables
							+ numberOfConstructors + numberOfMethods
							+ numberOfInnerClasses;

					bufferedWriter.write(type + " " + scope + " " + name + " "
							+ abstractClass + " " + finalClass + " " + hasMain
							+ " " + saveStartX + " " + saveStartY + " "
							+ saveWidth + " " + saveHeight + " "
							+ numberOfVariables + " " + numberOfConstructors
							+ " " + numberOfMethods + " "
							+ numberOfInnerClasses + " " + numberOfElements);
					bufferedWriter.write("\n");
					for (int j = 0; j < saveClass.getElements().size(); j++) {
						Elements saveElement = saveClass.getElements().get(j);
						if (saveElement.getType().equals("Constructor")) {
							Constructor saveConstructor = (Constructor) saveElement;
							bufferedWriter.write(saveConstructor.getType()
									+ " " + saveConstructor.getScope() + " "
									+ saveConstructor.getName() + " "
									+ saveConstructor.getBelongsTo() + " "
									+ saveConstructor.getStartX() + " "
									+ saveConstructor.getStartY() + " "
									+ saveConstructor.getWidth() + " "
									+ saveConstructor.getHeight() + " ");
							bufferedWriter.write("\n");
						}
						if (saveElement.getType().equals("Inner Class")) {
							InnerClass saveInnerClass = (InnerClass) saveElement;
							bufferedWriter.write(saveInnerClass.getType() + " "
									+ saveInnerClass.getScope() + " "
									+ saveInnerClass.getName() + " "
									+ saveInnerClass.getBelongsTo() + " "
									+ saveInnerClass.isabstractClass() + " "
									+ saveInnerClass.isfinalClass() + " "
									+ saveInnerClass.getStartX() + " "
									+ saveInnerClass.getStartY() + " "
									+ saveInnerClass.getWidth() + " "
									+ saveInnerClass.getHeight());
							bufferedWriter.write("\n");
						}
						if (saveElement.getType().equals("Main")) {
							Main saveMain = (Main) saveElement;
							bufferedWriter.write(saveMain.getType() + " "
									+ saveMain.getScope() + " "
									+ saveMain.getName() + " "
									+ saveMain.getReturnType() + " "
									+ saveMain.getBelongsTo() + " "
									+ saveMain.isAbstractMethod() + " "
									+ saveMain.isFinalMethod() + " "
									+ saveMain.isStaticMethod() + " "
									+ saveMain.getStartX() + " "
									+ saveMain.getStartY() + " "
									+ saveMain.getWidth() + " "
									+ saveMain.getHeight());
							bufferedWriter.write("\n");
						}
						if (saveElement.getType().equals("Method")) {
							Method saveMethod = (Method) saveElement;
							bufferedWriter.write(saveMethod.getType() + " "
									+ saveMethod.getScope() + " "
									+ saveMethod.getName() + " "
									+ saveMethod.getReturnType() + " "
									+ saveMethod.getBelongsTo() + " "
									+ saveMethod.isAbstractMethod() + " "
									+ saveMethod.isFinalMethod() + " "
									+ saveMethod.isStaticMethod() + " "
									+ saveMethod.getStartX() + " "
									+ saveMethod.getStartY() + " "
									+ saveMethod.getWidth() + " "
									+ saveMethod.getHeight());
							bufferedWriter.write("\n");
						}
						if (saveElement.getType().equals("Variable")) {
							Variable saveVariable = (Variable) saveElement;
							bufferedWriter.write(saveVariable.getType() + " "
									+ saveVariable.getScope() + " "
									+ saveVariable.getName() + " "
									+ saveVariable.getVariableType() + " "
									+ saveVariable.getBelongsTo() + " "
									+ saveVariable.getStartX() + " "
									+ saveVariable.getStartY() + " "
									+ saveVariable.getWidth() + " "
									+ saveVariable.getHeight());
							bufferedWriter.write("\n");
						}
					}
				}
				bufferedWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}// End saveProject

		public void openProject() throws FileNotFoundException {
			// Remove all form shapes
			Elements.removeAll(Elements);
			newClass = null;
			newInnerClass = null;
			newConstructor = null;
			newMain = null;
			newMethod = null;
			newVariable = null;
			numberOfClasses = 0;
			numberOfMains = 0;
			repaint();
			// End Remove all form shapes

			// Variables for Classes
			String name = null;
			String openType = null;
			String scope = null;
			int openWidth = 0;
			int openHeight = 0;
			int openStartX = 0;
			int openStartY = 0;
			Color c = null;
			boolean abstractClass = false;
			boolean finalClass = false;
			boolean hasMain = false;
			int numberOfVariables = 0;
			int numberOfConstructors = 0;
			int numberOfMethods = 0;
			int numberOfInnerClasses = 0;
			int numberOfElements = 0;
			// End Variables for Classes

			// Variables for Elements
			String openElementName = null;
			String openElementScope = null;
			String openElementReturnType = null;
			String openElementBelongsTo = null;
			int openElementWidth = 0;
			int openElementHeight = 0;
			int openElementStartX = 0;
			int openElementStartY = 0;
			Color openElementC = null;
			boolean openElementAbstractMethod = false;
			boolean openElementFinalMethod = false;
			boolean openElementStaticMethod = false;
			boolean openElementAbstractClass = false;
			boolean openElementFinalClass = false;
			// End Variables for Elements

			// JFileChooser
			String myFile = File.separator + "txt";
			JFileChooser fileChooser = new JFileChooser(new File(myFile));
			fileChooser.showOpenDialog(displayPanel);
			File aFile = fileChooser.getSelectedFile();
			Scanner filePtr = new Scanner(aFile);
			// End JFileChooser

			while (filePtr.hasNextLine()) {
				String str = filePtr.nextLine();
				String[] st = str.split(" ");
				openType = st[0];
				for (int i = 0; i < st.length; i++) {
					if (openType.equals("Class")) {
						if (i == 1) {
							scope = st[i];
						}
						if (i == 2) {
							name = st[i];
						}
						if (i == 3) {
							abstractClass = Boolean.parseBoolean(st[i]);
						}
						if (i == 4) {
							finalClass = Boolean.parseBoolean(st[i]);
						}
						if (i == 5) {
							hasMain = Boolean.parseBoolean(st[i]);
						}
						if (i == 6) {
							openStartX = Integer.parseInt(st[i]);
						}
						if (i == 7) {
							openStartY = Integer.parseInt(st[i]);
						}
						if (i == 8) {
							openWidth = Integer.parseInt(st[i]);
						}
						if (i == 9) {
							openHeight = Integer.parseInt(st[i]);
						}
						if (i == 10) {
							numberOfVariables = Integer.parseInt(st[i]);
						}
						if (i == 11) {
							numberOfConstructors = Integer.parseInt(st[i]);
						}
						if (i == 12) {
							numberOfMethods = Integer.parseInt(st[i]);
						}
						if (i == 13) {
							numberOfInnerClasses = Integer.parseInt(st[i]);
						}
						if (i == 14) {
							numberOfElements = Integer.parseInt(st[i]);
							c = Color.blue;
							newClass = new Class(openType, scope, name,
									abstractClass, finalClass, openStartX,
									openStartY, openWidth, openHeight, c);
							newClass.setHasMain(hasMain);
							newClass.setNumberOfVariables(numberOfVariables);
							newClass.setNumberOfMethods(numberOfMethods);
							newClass.setNumberOfInnerClasses(numberOfInnerClasses);
							newClass.setNumberOfConstructors(numberOfConstructors);
							newClass.setNumberOfElements(numberOfElements);
							Elements.add(newClass);
							numberOfClasses++;
							repaint();
						}// End If-Else
					}// End If
					if (openType.equals("Constructor")) {
						if (i == 1) {
							openElementScope = st[i];
						}
						if (i == 2) {
							openElementName = st[i];
						}
						if (i == 3) {
							openElementBelongsTo = st[i];
						}
						if (i == 4) {
							openElementStartX = Integer.parseInt(st[i]);
						}
						if (i == 5) {
							openElementStartY = Integer.parseInt(st[i]);
						}
						if (i == 6) {
							openElementWidth = Integer.parseInt(st[i]);
						}
						if (i == 7) {
							openElementHeight = Integer.parseInt(st[i]);
							openElementC = Color.magenta;
							newConstructor = new Constructor("Constructor",
									openElementScope, openElementName,
									openElementBelongsTo, openElementStartX,
									openElementStartY, openElementWidth,
									openElementHeight, openElementC);

							for (int k = 0; k < Elements.size(); k++) {
								if (Elements.get(k).getName()
										.equals(newConstructor.getBelongsTo())) {
									Elements.get(k).getElements()
											.add(newConstructor);
									newConstructor = null;
								}
							}
							repaint();
						}// End If-Else
					}
					if (openType.equals("Inner")) {
						if (i == 2) {
							openElementScope = st[i];
						}
						if (i == 3) {
							openElementName = st[i];
						}
						if (i == 4) {
							openElementBelongsTo = st[i];
						}
						if (i == 5) {
							openElementAbstractClass = Boolean
									.parseBoolean(st[i]);
						}
						if (i == 6) {
							openElementFinalClass = Boolean.parseBoolean(st[i]);
						}
						if (i == 7) {
							openElementStartX = Integer.parseInt(st[i]);
						}
						if (i == 8) {
							openElementStartY = Integer.parseInt(st[i]);
						}
						if (i == 9) {
							openElementWidth = Integer.parseInt(st[i]);
						}
						if (i == 10) {
							openElementHeight = Integer.parseInt(st[i]);
							openElementC = Color.cyan;
							newInnerClass = new InnerClass("Inner Class",
									openElementScope, openElementName,
									openElementBelongsTo,
									openElementAbstractClass,
									openElementFinalClass, openElementStartX,
									openElementStartY, openElementWidth,
									openElementHeight, openElementC);
							for (int k = 0; k < Elements.size(); k++) {
								if (Elements.get(k).getName()
										.equals(newInnerClass.getBelongsTo())) {
									Elements.get(k).getElements()
											.add(newInnerClass);
									newInnerClass = null;
								}
							}
							repaint();
						}// End If-Else
					}
					if (openType.equals("Main")) {
						if (i == 1) {
							openElementScope = st[i];
						}
						if (i == 2) {
							openElementName = st[i];
						}
						if (i == 3) {
							openElementReturnType = st[i];
						}
						if (i == 4) {
							openElementBelongsTo = st[i];
						}
						if (i == 5) {
							openElementAbstractMethod = Boolean
									.parseBoolean(st[i]);
						}
						if (i == 6) {
							openElementFinalMethod = Boolean
									.parseBoolean(st[i]);
						}
						if (i == 7) {
							openElementStaticMethod = Boolean
									.parseBoolean(st[i]);
						}
						if (i == 8) {
							openElementStartX = Integer.parseInt(st[i]);
						}
						if (i == 9) {
							openElementStartY = Integer.parseInt(st[i]);
						}
						if (i == 10) {
							openElementWidth = Integer.parseInt(st[i]);
						}
						if (i == 11) {
							openElementHeight = Integer.parseInt(st[i]);
							openElementC = Color.red;
							newMain = new Main("Main", openElementScope,
									openElementName, openElementReturnType,
									openElementBelongsTo,
									openElementAbstractMethod,
									openElementFinalMethod,
									openElementStaticMethod, openElementStartX,
									openElementStartY, openElementWidth,
									openElementHeight, openElementC);
							for (int k = 0; k < Elements.size(); k++) {
								if (Elements.get(k).getName()
										.equals(newMain.getBelongsTo())) {
									Elements.get(k).getElements().add(newMain);
									numberOfMains++;
									newMain = null;
								}
							}

						}
						repaint();
					}
					if (openType.equals("Method")) {
						if (i == 1) {
							openElementScope = st[i];
						}
						if (i == 2) {
							openElementName = st[i];
						}
						if (i == 3) {
							openElementReturnType = st[i];
						}
						if (i == 4) {
							openElementBelongsTo = st[i];
						}
						if (i == 5) {
							openElementAbstractMethod = Boolean
									.parseBoolean(st[i]);
						}
						if (i == 6) {
							openElementFinalMethod = Boolean
									.parseBoolean(st[i]);
						}
						if (i == 7) {
							openElementStaticMethod = Boolean
									.parseBoolean(st[i]);
						}
						if (i == 8) {
							openElementStartX = Integer.parseInt(st[i]);
						}
						if (i == 9) {
							openElementStartY = Integer.parseInt(st[i]);
						}
						if (i == 10) {
							openElementWidth = Integer.parseInt(st[i]);
						}
						if (i == 11) {
							openHeight = Integer.parseInt(st[i]);
							openElementC = Color.black;
							newMethod = new Method("Method", openElementScope,
									openElementName, openElementReturnType,
									openElementBelongsTo,
									openElementAbstractMethod,
									openElementFinalMethod,
									openElementStaticMethod, openElementStartX,
									openElementStartY, openElementWidth,
									openElementHeight, openElementC);
							for (int k = 0; k < Elements.size(); k++) {
								if (Elements.get(k).getName()
										.equals(newMethod.getBelongsTo())) {

									Elements.get(k).getElements()
											.add(newMethod);
									newMethod = null;
								}
							}
						}
						repaint();
					}
					if (openType.equals("Variable")) {
						if (i == 1) {
							openElementScope = st[i];
						}
						if (i == 2) {
							openElementName = st[i];
						}
						if (i == 3) {
							openElementReturnType = st[i];
						}
						if (i == 4) {
							openElementBelongsTo = st[i];
						}
						if (i == 5) {
							openElementStartX = Integer.parseInt(st[i]);
						}
						if (i == 6) {
							openElementStartY = Integer.parseInt(st[i]);
						}
						if (i == 7) {
							openElementWidth = Integer.parseInt(st[i]);
						}
						if (i == 8) {
							openElementHeight = Integer.parseInt(st[i]);
							openElementC = Color.green;
							newVariable = new Variable("Variable",
									openElementScope, openElementName,
									openElementReturnType,
									openElementBelongsTo, openElementStartX,
									openElementStartY, openElementWidth,
									openElementHeight, openElementC);
							for (int k = 0; k < Elements.size(); k++) {
								if (Elements.get(k).getName()
										.equals(newVariable.getBelongsTo())) {
									Elements.get(k).getElements()
											.add(newVariable);
									newVariable = null;
								}
							}
						}
						repaint();
					}
				}// End For
			}// End While Loop
		}// End openProject

		private void generateCode() {
			String fileName = null;

			for (int i = 0; i < Elements.size(); i++) {
				if (Elements.get(i).getType().equals("Class")) {
					newClass = (Class) Elements.get(i);
					if (newClass.isHasMain()) {
						fileName = Elements.get(i).getName() + ".java";
					}
				}
			}

			if (fileName != null) {
				File newFile = new File(fileName);
				if (!newFile.exists()) {
					try {
						newFile.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				FileWriter fileWriter;
				try {
					fileWriter = new FileWriter(newFile);
					BufferedWriter bufferedWriter = new BufferedWriter(
							fileWriter);
					for (int i = 0; i < Elements.size(); i++) {
						if (Elements.get(i).getType().equals("Class")) {
							Class newClass = (Class) Elements.get(i);
							bufferedWriter.write(newClass.code());
							bufferedWriter.write("\n");
							for (int j = 0; j < newClass.getElements().size(); j++) {
								if (newClass.getElements().get(j).getType() == "Variable") {
									Variable newVariable = (Variable) newClass
											.getElements().get(j);
									bufferedWriter.write(newVariable.code());
									bufferedWriter.write("\n");
								} else if (newClass.getElements().get(j)
										.getType() == "Method") {
									Method newMethod = (Method) newClass
											.getElements().get(j);
									bufferedWriter.write(newMethod.code());
									bufferedWriter.write("\n");
								} else if (newClass.getElements().get(j)
										.getType() == "Constructor") {
									Constructor newConstructor = (Constructor) newClass
											.getElements().get(j);
									bufferedWriter.write(newConstructor.code());
									bufferedWriter.write("\n");
								} else if (newClass.getElements().get(j)
										.getType() == "Main") {
									Main newMain = (Main) newClass
											.getElements().get(j);
									bufferedWriter.write(newMain.code());
									bufferedWriter.write("\n");
								} else if (newClass.getElements().get(j)
										.getType() == "Inner Class") {
									InnerClass newInnerClass = (InnerClass) newClass
											.getElements().get(j);
									bufferedWriter.write(newInnerClass.code());
									bufferedWriter.write("\n");
								}
							}
							bufferedWriter.write("\n");
							bufferedWriter.write("}// End Class");
							bufferedWriter.write("\n");
						}
					}
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane
						.showMessageDialog(
								displayPanel,
								"You must have a Main method before generating java code.\n (To create one go to Elements>New Main)");
			}
		}

		private void newElement(Graphics g, int width, int height, int startX,
				int startY, String type, Color c) {
			if (type.equals("Class")) {
				String name = "unassigned";
				boolean abstractClass = false, finalClass = false;
				while (name.equals("unassigned")) {
					name = JOptionPane
							.showInputDialog("What would you like to name this "
									+ type + "?");
					for (int i = 0; i < Elements.size(); i++) {
						if (Elements.get(i).getType().equals("Class")) {
							if (Elements.get(i).getName().equals(name)) {
								JOptionPane
										.showMessageDialog(
												displayPanel,
												"You allready have a Class named "
														+ name
														+ " you must pick a different name");
								name = "unassigned";
							}
						}
					}
				}
				if (JOptionPane.showConfirmDialog(displayPanel,
						"Would you like this to be an abstract Class?") == 0) {
					abstractClass = true;
				}
				if (abstractClass == false) {
					if (JOptionPane.showConfirmDialog(displayPanel,
							"Would you like this to be a final Class?") == 0) {
						finalClass = true;
					}
				}
				newClass = new Class(type, "public", name, abstractClass,
						finalClass, startX, startY, width, height, c);

			}
			if (type.equals("Inner Class")) {
				if (numberOfClasses == 0) {
					JOptionPane
							.showMessageDialog(displayPanel,
									"You must create a Class before you can create anything else.");
				} else {
					String belongsTo = null;
					if (numberOfClasses == 1) {
						for (int i = 0; i < Elements.size(); i++) {
							if (Elements.get(i).getType().equals("Class")) {
								belongsTo = Elements.get(i).getName();
							}
						}
					}
					if (numberOfClasses > 1) {
						boolean validClass = false;
						belongsTo = JOptionPane
								.showInputDialog("Which class would you like this "
										+ type + " to be a part of?");
						do {
							for (int i = 0; i < Elements.size(); i++) {
								if (Elements.get(i).getType().equals("Class")) {
									if (belongsTo.equals(Elements.get(i)
											.getName())) {
										validClass = true;
									}
								}
							}
							if (validClass == false) {
								belongsTo = JOptionPane
										.showInputDialog("Sorry but "
												+ belongsTo
												+ " is not a Class created in this project?\nWhich class would you like this "
												+ type + " to be a part of?");
							}
						} while (validClass == false);
					}
					String name = "unassigned";
					while (name.equals("unassigned")) {
						name = JOptionPane
								.showInputDialog("What would you like to name this "
										+ type + "?");
						for (int i = 0; i < Elements.size(); i++) {
							Class newElement = Elements.get(i);
							for (int j = 0; j < newElement.getElements().size(); j++) {
								if (newElement.getElements().get(j).getType()
										.equals("Inner Class")) {
									InnerClass testVariable = (InnerClass) newElement
											.getElements().get(j);
									if (testVariable.getName().equals(name)) {
										JOptionPane
												.showMessageDialog(
														displayPanel,
														"You allready have a Inner Class named "
																+ name
																+ " you must pick a different name");
										name = "unassigned";
									}
								}
							}
						}
					}
					for (int i = 0; i < Elements.size(); i++) {
						if (Elements.get(i).getType().equals("Class")) {
							if (Elements.get(i).getName().equals(belongsTo)) {
								Class parentClass = (Class) Elements.get(i);
								newInnerClass = new InnerClass(type, "private",
										name, belongsTo, false, false, startX,
										startY, width, height, c);
								parentClass.getElements().add(newInnerClass);
								newInnerClass = null;
								parentClass.setNumberOfInnerClasses(parentClass
										.getNumberOfInnerClasses() + 1);
							}
						}
					}
				}
			}
			if (type.equals("Variable")) {
				if (numberOfClasses == 0) {
					JOptionPane
							.showMessageDialog(displayPanel,
									"You must create a Class before you can create anything else.");
				} else {
					String belongsTo = null;
					String scope = JOptionPane
							.showInputDialog("What Scope would you like this "
									+ type + " to have?");
					String variableType = JOptionPane
							.showInputDialog("What type of this variable would you like this "
									+ type + " to be?");
					if (numberOfClasses == 1) {
						for (int i = 0; i < Elements.size(); i++) {
							if (Elements.get(i).getType().equals("Class")) {
								belongsTo = Elements.get(i).getName();
							}
						}
					}
					if (numberOfClasses > 1) {
						boolean validClass = false;
						belongsTo = JOptionPane
								.showInputDialog("Which class would you like this "
										+ type + " to be a part of?");
						do {
							for (int i = 0; i < Elements.size(); i++) {
								if (Elements.get(i).getType().equals("Class")) {
									if (belongsTo.equals(Elements.get(i)
											.getName())) {
										validClass = true;
									}
								}
							}
							if (validClass == false) {
								belongsTo = JOptionPane
										.showInputDialog("Sorry but "
												+ belongsTo
												+ " is not a Class created in this project?\nWhich class would you like this "
												+ type + " to be a part of?");
							}
						} while (validClass == false);
					}
					String name = "unassigned";
					while (name.equals("unassigned")) {
						name = JOptionPane
								.showInputDialog("What would you like to name this "
										+ type + "?");
						for (int i = 0; i < Elements.size(); i++) {
							Class newElement = Elements.get(i);
							for (int j = 0; j < newElement.getElements().size(); j++) {
								if (newElement.getElements().get(j).getType()
										.equals("Variable")) {
									Variable testVariable = (Variable) newElement
											.getElements().get(j);
									if (testVariable.getName().equals(name)) {
										JOptionPane
												.showMessageDialog(
														displayPanel,
														"You allready have a Variable named "
																+ name
																+ " you must pick a different name");
										name = "unassigned";
									}
								}
							}
						}
					}
					for (int i = 0; i < Elements.size(); i++) {
						if (Elements.get(i).getType().equals("Class")) {
							if (Elements.get(i).getName().equals(belongsTo)) {
								Class parentClass = (Class) Elements.get(i);
								newVariable = new Variable(type, scope, name,
										variableType, belongsTo, startX,
										startY, width, height, c);
								parentClass.getElements().add(newVariable);
								newVariable = null;
								parentClass.setNumberOfVariables(parentClass
										.getNumberOfVariables() + 1);
							}
						}
					}
				}
			}
			if (type.equals("Main")) {
				if (numberOfClasses == 0) {
					JOptionPane
							.showMessageDialog(displayPanel,
									"You must create a Class before you can create anything else.");
				} else {
					String belongsTo = null;
					if (numberOfClasses == 1) {
						for (int i = 0; i < Elements.size(); i++) {
							if (Elements.get(i).getType().equals("Class")) {
								belongsTo = Elements.get(i).getName();
							}
						}
					}
					if (numberOfClasses > 1) {
						boolean validClass = false;
						belongsTo = JOptionPane
								.showInputDialog("Which class would you like main to be a part of?");
						do {
							for (int i = 0; i < Elements.size(); i++) {
								if (Elements.get(i).getType().equals("Class")) {
									if (belongsTo.equals(Elements.get(i)
											.getName())) {
										validClass = true;
									}
								}
							}
							if (validClass == false) {
								belongsTo = JOptionPane
										.showInputDialog("Sorry but "
												+ belongsTo
												+ " is not a Class created in this project?\nWhich class would you like main to be a part of?");
							}
						} while (validClass == false);
					}

					if (numberOfMains == 0) {
						newMain = new Main(type, "public", "main", "void",
								belongsTo, false, false, true, startX, startY,
								width, height, c);
						for (int i = 0; i < Elements.size(); i++) {
							if (Elements.get(i).getName().equals(belongsTo)) {
								Class parentClass = (Class) Elements.get(i);
								parentClass.getElements().add(newMain);
								newMain = null;
								parentClass.setHasMain(true);
							}
						}
						numberOfMains++;
					} else {
						JOptionPane
								.showMessageDialog(displayPanel,
										"Sorry but you already have a main method in this project.");
					}
				}
			}
			if (type.equals("Constructor")) {
				String belongsTo = null;
				if (numberOfClasses == 0) {
					JOptionPane
							.showMessageDialog(displayPanel,
									"You must create a Class before you can create anything else.");
				} else {
					if (numberOfClasses == 1) {
						for (int i = 0; i < Elements.size(); i++) {
							if (Elements.get(i).getType().equals("Class")) {
								belongsTo = Elements.get(i).getName();
							}
						}
					}
					if (numberOfClasses > 1) {
						boolean validClass = false;
						belongsTo = JOptionPane
								.showInputDialog("Which class would you like main to be a part of?");
						do {
							for (int i = 0; i < Elements.size(); i++) {
								if (Elements.get(i).getType().equals("Class")) {
									if (belongsTo.equals(Elements.get(i)
											.getName())) {
										validClass = true;
									}
								}
							}
							if (validClass == false) {
								belongsTo = JOptionPane
										.showInputDialog("Sorry but "
												+ belongsTo
												+ " is not a Class created in this project?\nWhich class would you like main to be a part of?");
							}
						} while (validClass == false);
					}
					String name = belongsTo;
					for (int i = 0; i < Elements.size(); i++) {
						if (Elements.get(i).getType().equals("Class")) {
							if (Elements.get(i).getName().equals(belongsTo)) {
								Class parentClass = (Class) Elements.get(i);
								newConstructor = new Constructor(type,
										"public", name, belongsTo, startX,
										startY, width, height, c);
								parentClass.getElements().add(newConstructor);
								newConstructor = null;
								parentClass.setNumberOfConstructors(parentClass
										.getNumberOfConstructors() + 1);

							}
						}
					}
				}
			}
			if (type.equals("Method")) {
				if (numberOfClasses == 0) {
					JOptionPane
							.showMessageDialog(displayPanel,
									"You must create a Class before you can create anything else.");
				} else {
					String belongsTo = null;
					boolean abstractMethod = false, finalMethod = false, staticMethod = false;
					String scope = JOptionPane
							.showInputDialog("What Scope would you like this method to have?");
					String returnType = JOptionPane
							.showInputDialog("What is the return type of this method?");

					if (numberOfClasses == 1) {
						for (int i = 0; i < Elements.size(); i++) {
							if (Elements.get(i).getType().equals("Class")) {
								belongsTo = Elements.get(i).getName();
							}// End If
						}// End For
					}// End If
					if (numberOfClasses > 1) {
						boolean validClass = false;
						belongsTo = JOptionPane
								.showInputDialog("Which class would you like main to be a part of?");
						do {
							for (int i = 0; i < Elements.size(); i++) {
								if (Elements.get(i).getType().equals("Class")) {
									if (belongsTo.equals(Elements.get(i)
											.getName())) {
										validClass = true;
									}
								}
							}
							if (validClass == false) {
								belongsTo = JOptionPane
										.showInputDialog("Sorry but "
												+ belongsTo
												+ " is not a Class created in this project?\nWhich class would you like main to be a part of?");
							}
						} while (validClass == false);
					}// End If
					if (JOptionPane.showConfirmDialog(displayPanel,
							"Would you like this to be an abstract Method?") == 0) {
						abstractMethod = true;
					}
					if (abstractMethod == false) {
						if (JOptionPane.showConfirmDialog(displayPanel,
								"Would you like this to be a final Method?") == 0) {
							finalMethod = true;
						}
					}
					if (abstractMethod == false && finalMethod == false) {
						if (JOptionPane.showConfirmDialog(displayPanel,
								"Would you like this to be a static Method?") == 0) {
							staticMethod = true;
						}
					}
					String name = "unassigned";
					while (name.equals("unassigned")) {
						name = JOptionPane
								.showInputDialog("What would you like to name this "
										+ type + "?");
						for (int i = 0; i < Elements.size(); i++) {
							Class newElement = Elements.get(i);
							for (int j = 0; j < newElement.getElements().size(); j++) {
								if (newElement.getElements().get(j).getType()
										.equals("Method")) {
									Method testMethod = (Method) newElement
											.getElements().get(j);
									if (testMethod.getBelongsTo().equals(
											belongsTo)) {
										if (newElement.getElements().get(j)
												.getName().equals(name)) {
											JOptionPane
													.showMessageDialog(
															displayPanel,
															"You allready have a Method named "
																	+ name
																	+ " you must pick a different name");
											name = "unassigned";
										}
									}
								}

							}
						}
					}// End While
					for (int i = 0; i < Elements.size(); i++) {
						if (Elements.get(i).getType().equals("Class")) {
							if (Elements.get(i).getName().equals(belongsTo)) {
								Class parentClass = (Class) Elements.get(i);
								newMethod = new Method(type, scope, name,
										returnType, belongsTo, abstractMethod,
										finalMethod, staticMethod, startX,
										startY, width, height, c);
								parentClass.getElements().add(newMethod);
								newMethod = null;
								parentClass.setNumberOfMethods(parentClass
										.getNumberOfMethods() + 1);
							}
						}
					}
				}// End if-else
			}
		}
	}// End Event Handler
}// End GUI
