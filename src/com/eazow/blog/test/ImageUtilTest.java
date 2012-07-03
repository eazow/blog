package com.eazow.blog.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtilTest {
	public static void main(String[] args) {
		try {
			BufferedImage sourceImage = ImageIO.read(new File("d:/1.jpg"));
			int height = sourceImage.getHeight();
			int width = sourceImage.getWidth();

			if (width > 100) {
				height = 200;
				width = 200;
			}

			BufferedImage destImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			destImage.getGraphics().drawImage(sourceImage, 0, 0, width, height,
					null);
			FileOutputStream thumbnailOutputStream = new FileOutputStream(
					"d:/thumbnail.jpg");
			JPEGImageEncoder jpegImageEncoder = JPEGCodec
					.createJPEGEncoder(thumbnailOutputStream);
			jpegImageEncoder.encode(destImage);
			thumbnailOutputStream.close();
		} catch (Exception e) {
		}
	}
}
