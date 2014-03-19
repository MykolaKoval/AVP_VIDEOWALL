package com.atanor.vwserver.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.domain.entity.Display;
import com.google.common.collect.Lists;

public class ImgGenerator {

	private static final Float borderThickness = 2f;
	private static final Integer IMG_MAX_WIDTH = 1364;
	private static final Integer IMG_MAX_HEIGHT = 768;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public BufferedImage generate(final Display display) {
		Validate.notNull(display.getSegmentHeight(), "Display segment height can not be null");
		Validate.notNull(display.getSegmentWidth(), "Display segment width can not be null");
		Validate.notNull(display.getSegmentNumHeight(), "Display segment number (height) can not be null");
		Validate.notNull(display.getSegmentNumWidth(), "Display segment number (width) can not be null");

		final Integer width = display.getSegmentWidth() * display.getSegmentNumWidth();
		final Integer height = display.getSegmentHeight() * display.getSegmentNumHeight();

		final List<Rectangle> panels = createDisplayPanels(display);

		return createDisplayImg(width, height, panels);
	}

	private List<Rectangle> createDisplayPanels(final Display display) {
		final List<Rectangle> panels = Lists.newArrayList();
		final Integer panelWidth = display.getSegmentWidth();
		final Integer panelHeight = display.getSegmentHeight();

		for (int row = 0, left = 0, top = 0; row < display.getSegmentNumHeight(); row++) {
			for (int col = 0; col < display.getSegmentNumWidth(); col++) {
				panels.add(new Rectangle(left, top, panelWidth, panelHeight));
				left += panelWidth;
			}
			left = 0;
			top += panelHeight;
		}

		return panels;
	}

	private BufferedImage createDisplayImg(final Integer imgWidth, final Integer imgHeight, final List<Rectangle> panels) {
		// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed into
		// integer pixels
		final BufferedImage bi = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);

		final Graphics2D g2d = bi.createGraphics();
		final Rectangle background = new Rectangle(0, 0, imgWidth, imgHeight);
		paint(g2d, background, panels);

		// Disposes of this graphics context and releases any system resources
		// that it is using.
		g2d.dispose();

		return bi;
	}

	private static void paint(Graphics2D g2d, final Rectangle background, final List<Rectangle> panels) {
		g2d.setColor(Color.GRAY);
		g2d.fill(background);

		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(borderThickness));

		for (Rectangle panel : panels) {
			paint(g2d, panel);
		}
	}

	private static void paint(Graphics2D g2d, final Rectangle panel) {
		g2d.drawRect(fitCoord(panel.getX()), fitCoord(panel.getY()), fitLength(panel.getWidth()),
				fitLength(panel.getHeight()));
	}

	private static int fitCoord(Double coord) {
		return coord.intValue() + new Float(borderThickness / 2).intValue();
	}

	private static int fitLength(Double length) {
		return length.intValue() - borderThickness.intValue();
	}
}
