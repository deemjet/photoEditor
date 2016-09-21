
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import cs1302.effects.Artsy;

public class MyArtsy implements Artsy {

	@Override
	public BufferedImage doCheckers(BufferedImage src1, BufferedImage src2,
			int size) {
		BufferedImage[] images = { src2, src1 };
		// new image size is the max. of both image sizes
		int w = Math.max(images[0].getWidth(), images[1].getWidth());
		int h = Math.max(images[0].getHeight(), images[1].getHeight());

		BufferedImage result = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) result.getGraphics();
		// get each section into the result image
		for (int row = 0; row <= h / size; row++) {
			for (int col = 0; col <= w / size; col++) {
				int index = (row + col) % 2;
				BufferedImage source = images[index];
				int dx0 = col * size;
				int dy0 = row * size;
				int dx1 = dx0 + size;
				int dy1 = dy0 + size;
				int sx0 = col * size;
				int sy0 = row * size;
				int sx1 = dx0 + size;
				int sy1 = dy0 + size;
				// draw the check onto the result image
				g.drawImage(source, dx0, dy0, dx1, dy1, sx0, sy0, sx1, sy1,
						null);
			}
		}
		return result;
	} // doCheckers

	@Override
	public BufferedImage doHorizontalStripes(BufferedImage src1,
			BufferedImage src2, int size) {
		BufferedImage[] images = { src2, src1 };
		// new image size is the max. of both image sizes
		int w = Math.max(images[0].getWidth(), images[1].getWidth());
		int h = Math.max(images[0].getHeight(), images[1].getHeight());

		BufferedImage result = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) result.getGraphics();
		// get each section into the result image
		for (int row = 0; row <= h / size; row++) {
			int index = row % 2;
			BufferedImage source = images[index];
			int dx0 = 0;
			int dy0 = row * size;
			int dx1 = w;
			int dy1 = dy0 + size;
			int sx0 = 0;
			int sy0 = row * size;
			int sx1 = w;
			int sy1 = dy0 + size;
			// draw the stripe onto the result image
			g.drawImage(source, dx0, dy0, dx1, dy1, sx0, sy0, sx1, sy1, null);
		}
		return result;
	} // doHorizontalStripes

	@Override
	public BufferedImage doRotate(BufferedImage src, double degrees) {
		// use AffineTransform to rotate the image
		AffineTransform trans = new AffineTransform();
		// move to center and add the rotate
		trans.translate(src.getWidth() / 2, src.getHeight() / 2);
		trans.rotate(degrees / 180 * Math.PI);
		// translate back to move at the center
		trans.translate(-src.getWidth() / 2, -src.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(trans,
				AffineTransformOp.TYPE_BILINEAR);
		// create the result and draw the rotated image

		BufferedImage result = new BufferedImage(src.getWidth(),
				src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		op.filter(src, result);
		return result;
	} // doRotate

	@Override
	public BufferedImage doVerticalStripes(BufferedImage src1,
			BufferedImage src2, int size) {
		BufferedImage[] images = { src2, src1 };
		// new image size is the max. of both image sizes
		int w = Math.max(images[0].getWidth(), images[1].getWidth());
		int h = Math.max(images[0].getHeight(), images[1].getHeight());

		BufferedImage result = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) result.getGraphics();
		// get each section into the result image
		for (int col = 0; col <= w / size; col++) {
			int index = col % 2;
			BufferedImage source = images[index];
			int dx0 = col * size;
			int dy0 = 0;
			int dx1 = dx0 + size;
			int dy1 = h;
			int sx0 = col * size;
			int sy0 = 0;
			int sx1 = dx0 + size;
			int sy1 = h;
			// draw the check onto the result image
			g.drawImage(source, dx0, dy0, dx1, dy1, sx0, sy0, sx1, sy1, null);
		}
		return result;

	} // doVerticalStripes

} // MyArtsy

