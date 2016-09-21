
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cs1302.effects.Artsy;

/**
 * This is the panel to display one image and the corresponding rotate and reset
 * buttons.
 *
 */
public class ImagePanel extends JPanel {

	private static final int IMAGE_SIZE = 300;

	private String title;
	private boolean showFileName; // whether file name is shown in the label
	private JLabel label; // the label
	private JLabel imageLabel; // label to display the image
	private JButton rotateButton; // rotate button
	private JButton resetButton; // reset button

        private BufferedImage original; // original image (loaded from file)
	private BufferedImage current; // current image (displayed)

	private double rotateAngle; // the accumulate rotate angle
	private Artsy artsy; // artsy object to manipulate image

	/**
	 * Create a ImagePanel that holds the image and rotate/reset button.
	 * 
	 * @param title
	 * @param showFileName
	 */
	public ImagePanel(Artsy artsy, String title, boolean showFileName) {
		this.artsy = artsy;
		this.title = title;
		this.showFileName = showFileName;
		this.rotateAngle = 0;
		// initialize the GUI components
		initialize();
		// update title
		updateTitle("default.png");
	}

	// update the title shown in the label
	public void updateTitle(String fileName) {
		if (showFileName) {
			this.label.setText(title + ": " + fileName);
		} else {
			this.label.setText(title);
		}
	}

	// load a image from the path and reset the displayed image to the original one
	public void loadImage(File file) throws IOException {
		this.original = ImageIO.read(file);
		this.reset();
	}
	
	// update the image in this panel directly
	public void updateImage(BufferedImage original) {
		this.original = original;
		this.reset();
	}

	// initialize the GUI components
	// use the default.png in the resources folder for the image
	private void initialize() {
		// create the components
		this.label = new JLabel("", SwingConstants.CENTER);
		this.imageLabel = new JLabel();
		this.rotateButton = new JButton("Rotate");
		this.resetButton = new JButton("Reset");

		// set up size
		int borderSize = 8;
		this.imageLabel.setPreferredSize(new Dimension(IMAGE_SIZE + borderSize
				* 2, IMAGE_SIZE + borderSize * 2));
		this.imageLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE,
				borderSize));

		// add them onto this panel
		this.setLayout(new BorderLayout());
		this.add(this.label, BorderLayout.NORTH);
		this.add(this.imageLabel, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.rotateButton);
		buttonPanel.add(this.resetButton);
		this.add(buttonPanel, BorderLayout.SOUTH);

		// load the default image images
		try {
			loadImage(new File("resources/default.png"));
			this.original = ImageIO.read(new File("resources/default.png"));
		} catch (IOException ioe) {
	  
			JOptionPane.showMessageDialog(this, "Default Image is missing!");
			System.exit(0);
		}
		
		// add button event handler
		this.resetButton.addActionListener((e) -> reset());
		this.rotateButton.addActionListener((e) -> rotate());
	}
	
	// reset the image to the original image
	private void reset() {
	    
	        this.current = new BufferedImage(original.getColorModel(),
				original.copyData(null), original.isAlphaPremultiplied(), null);
		// display it in the image label
		this.imageLabel.setIcon(new ImageIcon(this.original));
	}
	
	// rotate the current image and display it
	private  void rotate() {
		String value = JOptionPane.showInputDialog(this, "Please enter your angle, in degrees: ");
		if (value == null) { // user cancelled
			return;
		}
		// try to convert it to a double value
		try {
			double degrees = Double.parseDouble(value);
			this.rotateAngle += degrees;
			// rotate the current image and update the image
			this.current = artsy.doRotate(original, this.rotateAngle);
			this.imageLabel.setIcon(new ImageIcon(this.current));
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this, "Invalid angle to rotate");
		}
	}
	
	// save the current image into the file
	public void saveImage(File file) throws IOException {
		ImageIO.write(current, "png", file);
	}

	// return the current buffered image
	public BufferedImage getImage() {
		return this.current;
	}
}
