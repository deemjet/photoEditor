

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cs1302.effects.Artsy;

/**
 * This is the main panel for this application. Components
 * and sub-panels should be added to this panel.
 */
public class MainPanel extends JPanel {
	
	private Artsy artsy;
    private ImagePanel[] imagePanels;

    /** 
     * Creates a new <code>MainPanel</code> object. Feel free
     * to change the parameters to this constructor if needed. 
     * However, if you do change the parameters, you will need
     * to update the <code>Driver</code> class where an instance
     * of this class is created.
     */
    public MainPanel(Artsy artsy) {
    	this.artsy = artsy;
        // initialize the three image panels
    	this.imagePanels = new ImagePanel[] {
    		new ImagePanel(artsy, "Image 1", true),
    		new ImagePanel(artsy, "Image 2", true),
    		new ImagePanel(artsy, "Result", false),
    	};
    	// add them into this panel
    	JPanel imagePanel = new JPanel();
    	imagePanel.setLayout(new GridLayout(1, this.imagePanels.length));
    	for (ImagePanel panel : this.imagePanels) {
    		imagePanel.add(panel);
    	}
    	
    	// construct the three buttons
    	JButton checkersButton = new JButton("Checkers");
    	JButton horizontalButton = new JButton("Horizontal Stripes");
    	JButton verticalButton = new JButton("Vertical Stripes");
    	
    	// add them into the panel.
    	JPanel buttonPanel = new JPanel();
    	buttonPanel.add(checkersButton);
    	buttonPanel.add(horizontalButton);
    	buttonPanel.add(verticalButton);
    	
    	this.setLayout(new BorderLayout());
    	this.add(buttonPanel, BorderLayout.NORTH);
    	this.add(imagePanel, BorderLayout.CENTER);
    	
    	// add the button event listener
    	checkersButton.addActionListener((e) -> checkers());
    	horizontalButton.addActionListener((e) -> horizontal());
    	verticalButton.addActionListener((e) -> vertical());
    } // MainPanel

    // create the vertical stripes
    private void vertical() {
    	String value = JOptionPane.showInputDialog(this, "Please enter the desired stripe height, in pixels");
		if (value == null) { // user cancelled
			return;
		}
		// try to convert it to a double value
		try {
			int height = Integer.parseInt(value);
			BufferedImage result = this.artsy.doVerticalStripes(
					imagePanels[0].getImage(), imagePanels[1].getImage(), height);
			// update the result image panel
			imagePanels[2].updateImage(result);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Invalid check width");
		}
	}

    // create horizontal stripes
	private void horizontal() {
		String value = JOptionPane.showInputDialog(this, "Please enter the desired stripe width, in pixels");
		if (value == null) { // user cancelled
			return;
		}
		// try to convert it to a double value
		try {
			int width = Integer.parseInt(value);
			BufferedImage result = this.artsy.doHorizontalStripes(
					imagePanels[0].getImage(), imagePanels[1].getImage(), width);
			// update the result image panel
			imagePanels[2].updateImage(result);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Invalid check width");
		}
	}

	// create checkers
	private void checkers() {
		String value = JOptionPane.showInputDialog(this, "Please enter the desired checker width, in pixels");
		if (value == null) { // user cancelled
			return;
		}
		// try to convert it to a double value
		try {
			int size = Integer.parseInt(value);
			BufferedImage result = this.artsy.doCheckers(
					imagePanels[0].getImage(), imagePanels[1].getImage(), size);
			// update the result image panel
			imagePanels[2].updateImage(result);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Invalid check width");
		}
	}

	/**
     * Open image in the i-th panel.
     * @param i
     */
	public void openImage(int i) {
		// pick up one image file from the file system
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			// try to open the file as a new image in the given panel
			File file = chooser.getSelectedFile();
			String name = file.getName();
			try {
				imagePanels[i].loadImage(file);
				imagePanels[i].updateTitle(name);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Could not load the file as an image.");
			}
		}
	}

	/**
	 * Save i-th panel into a file.
	 * @param i
	 */
	public void saveImage(int i) {
		// pick up one file to save the image into
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			// try to open the file as a new image in the given panel
			File file = chooser.getSelectedFile();
			try {
				imagePanels[i].saveImage(file);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Could not save the image into the file.");
			}
		}
		
	}

} // MainPanel
