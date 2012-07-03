package com.eazow.blog.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	/**
	 * Éú³ÉËõÂÔÍ¼
	 * 
	 * @param sourceImageUrl
	 */
	public static void createThumbnail(String sourceImageUrl,
			String destImageUrl, int newSize) {
		try {
			BufferedImage sourceImage = ImageIO.read(new File(sourceImageUrl));
			int height = sourceImage.getHeight();
			int width = sourceImage.getWidth();

			int newHeight = 0;
			int newWidth = 0;
			if (height >= width) {
				newHeight = newSize;
				newWidth = newSize * width / height;
			} else {
				newHeight = newSize * height / width;
				newWidth = newSize;
			}

			BufferedImage destImage = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);
			destImage.getGraphics().drawImage(sourceImage, 0, 0, newWidth,
					newHeight, null);
			FileOutputStream thumbnailOutputStream = new FileOutputStream(
					destImageUrl);
			JPEGImageEncoder jpegImageEncoder = JPEGCodec
					.createJPEGEncoder(thumbnailOutputStream);
			jpegImageEncoder.encode(destImage);
			thumbnailOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
