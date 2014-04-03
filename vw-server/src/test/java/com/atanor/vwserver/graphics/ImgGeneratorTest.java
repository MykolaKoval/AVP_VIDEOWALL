package com.atanor.vwserver.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.atanor.vwserver.domain.entity.Display;
import com.atanor.vwserver.domain.entity.Layout;
import com.atanor.vwserver.domain.entity.LayoutWindow;

public class ImgGeneratorTest {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//testGenerateDisplayImage();
		testGenerateLayoutImage();
	}

	private static void testGenerateDisplayImage() throws IOException {
		Display display = new Display();
		display.setSegmentNumHeight(1);
		display.setSegmentNumWidth(2);
		display.setResolution("1920x1080");
		display.setOrientation("Landscape");

		ImgGenerator gen = new ImgGenerator();
		BufferedImage bi = gen.generate(display);

		ImageIO.write(bi, "PNG", new File("D:/temp/batik/111.PNG"));
	}

	private static void testGenerateLayoutImage() throws IOException {
		Layout layout = new Layout();
		layout.setName("TEST");

		LayoutWindow w1 = createLayoutWindow(layout, "Window1", 0, 0, 50, 50);
		LayoutWindow w2 = createLayoutWindow(layout, "Window2", 0, 50, 100, 50);
		LayoutWindow w3 = createLayoutWindow(layout, "Window2", 25, 25, 70, 50);
		layout.setWindows(Arrays.asList(w1, w2, w3));

		ImgGenerator gen = new ImgGenerator();
		BufferedImage bi = gen.generate(layout);

		ImageIO.write(bi, "PNG", new File("D:/temp/batik/111.PNG"));
	}

	private static LayoutWindow createLayoutWindow(Layout layout, String name, Integer left, Integer top, Integer width,
			Integer height) {
		LayoutWindow window = new LayoutWindow();
		window.setName(name);
		window.setLeft(left);
		window.setTop(top);
		window.setHeight(height);
		window.setWidth(width);
		window.setLayout(layout);
		return window;
	}

}
