import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	/* This class loads images */
	
	// Returns the image from the given path
	public BufferedImage loadImage(String path) throws IOException{
		return ImageIO.read(getClass().getResource(path));
	}
}
