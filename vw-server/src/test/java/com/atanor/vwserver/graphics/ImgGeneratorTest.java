package com.atanor.vwserver.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.atanor.vwserver.domain.entity.Display;

public class ImgGeneratorTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Display display = new Display();
		display.setSegmentNumHeight(3);
		display.setSegmentNumWidth(4);
		display.setSegmentHeight(600);
		display.setSegmentWidth(800);
		
		ImgGenerator gen = new ImgGenerator();
		BufferedImage bi = gen.generate(display);
		
		ImageIO.write(bi, "PNG", new File("D:/temp/batik/111.PNG"));
	}

}
