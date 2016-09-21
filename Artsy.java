

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * This interface describes artsy effects that can be applied to BufferedImages.
 */
public interface Artsy {

	/**
	 * Given two BufferedImages, this method returns a new BufferedImage, the contents of which
	 * is composed of the source images alternating horizontally and vertically at the specified 
	 * size, in pixels. This causes the images to appear woven together in a checkered fashion. 
	 * For an example, please see the project description.
	 * 
	 * @param src1 the first BufferedImage
	 * @param src2 the second BufferedImage
	 * @param height the height, in pixels, of the horizontal stripes.
	 * @return a BufferedImage with the horizontal stripes effect
	 */
	public BufferedImage doCheckers(BufferedImage src1, BufferedImage src2, int size);
	
	/**
	 * Given two BufferedImages, this method returns a new BufferedImage, the contents of which
	 * is composed of the source images alternating horizontally at the specified pixel height. 
	 * For an example, please see the project description.
     *
	 * @param src1 the first BufferedImage
	 * @param src2 the second BufferedImage
	 * @param height the height, in pixels, of the horizontal stripes.
	 * @return a BufferedImage with the horizontal stripes effect
	 */
	public BufferedImage doHorizontalStripes(BufferedImage src1, BufferedImage src2, int height);
	
	/**
	 * Given two BufferedImages, this method returns a new BufferedImage, the contents of which
	 * is composed of the source images alternating vertically at the specified pixel width. For
	 * an example, please see the project description.
	 * 
	 * @param src1 the first BufferedImage
	 * @param src2 the second BufferedImage
	 * @param width the width, in pixels, of the vertical stripes.
	 * @return a BufferedImage with the vertical stripes effect
	 */
	public BufferedImage doVerticalStripes(BufferedImage src1, BufferedImage src2, int width);
	
	/**
	 * Given a BufferedImage, this method returns a new BufferedImage which contains
	 * the source image rotated clockwise by a certain number of degrees. The image
	 * should be rotated about the center of the image. For an example, please see
     * the project description.
     *
     * <p>
     * The easiest way to rotate an image is to use an affine transformation.
     * See the reference mentioned below for a link to AWT's
     * <code>AffineTransform</code> class.
     * 
	 * @see http://docs.oracle.com/javase/8/docs/api/java/awt/geom/AffineTransform.html
	 * @param src     the source BufferedImage
	 * @param degrees the degrees to rotate the image, in degrees (not radians)
	 * @return a new BufferedImage containing a rotated version of the source image
	 */
	public BufferedImage doRotate(BufferedImage src, double degrees);
	
} // Artsy
