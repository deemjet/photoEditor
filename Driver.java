
import cs1302.effects.Artsy;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/**
 * This is the driver for this application. It ensures that GUI components
 * are created in the Event Dispatch Thread.
 */
public class Driver {

    /**
     * Creates the frame for this application and sets up the main panel.
     */
    public static void createAndShowGUI() {

        // Create the frame
    	JFrame frame = new JFrame("Artsy!");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The single MyArtsy object that you are allowed to pass around
        Artsy artsy = new MyArtsy();

        // Create the main panel and add it to the frame
        MainPanel mainPanel = new MainPanel(artsy);
        frame.add(mainPanel);
        // setup menu
    	addMenuBar(frame, mainPanel);
    	
    	// show the window.
        frame.pack();
        frame.setVisible(true);
		
    } // createAndShowGUI
    
    /**
     * Setup the menu bar for the main frame.
     * @param frame
     */
    private static void addMenuBar(JFrame frame, MainPanel mainPanel) {
    	JMenuBar menuBar = new JMenuBar();
    	JMenu fileMenu = new JMenu("File");
    	// open menu
    	JMenu openMenu = new JMenu("Open");
    	JMenuItem open1Menu = new JMenuItem("Open Image 1");
    	JMenuItem open2Menu = new JMenuItem("Open Image 2");
    	openMenu.add(open1Menu);
    	openMenu.add(open2Menu);
    	
    	// save menu
    	JMenuItem saveMenu = new JMenuItem("Save Result As");
    	
    	// exit menu
    	JMenuItem exitMenu = new JMenuItem("Exit");
    	fileMenu.add(openMenu);
    	fileMenu.addSeparator();
    	fileMenu.add(saveMenu);
    	fileMenu.addSeparator();
    	fileMenu.add(exitMenu);
    	
    	menuBar.add(fileMenu);
    	
    	frame.setJMenuBar(menuBar);
    	
    	// register menu listener
    	open1Menu.addActionListener((e) -> mainPanel.openImage(0));
    	open2Menu.addActionListener((e) -> mainPanel.openImage(1));
    	saveMenu.addActionListener((e) -> mainPanel.saveImage(2));
    	exitMenu.addActionListener((e) -> System.exit(0));
    }

    /**
     * The entry point into the program. 
     *
     * @param args  command line arguments
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    } // main

} // Driver
