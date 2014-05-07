package com.atanor.vwserver.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.AppUtils;
import com.atanor.vwserver.domain.entity.Display;
import com.atanor.vwserver.domain.entity.Layout;
import com.atanor.vwserver.domain.entity.LayoutWindow;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class ImgGenerator {

	private static final Float borderThickness = 4f;

	public BufferedImage generate(final Display display) {
		Validate.notNull(display, "display can not be null");
		Validate.notNull(display.getSegmentNumHeight(), "Display segment number (height) can not be null");
		Validate.notNull(display.getSegmentNumWidth(), "Display segment number (width) can not be null");
		Validate.notEmpty(display.getOrientation(), "Display orientation can not be null or empty");
		Validate.notEmpty(display.getResolution(), "Display resolution can not be null or empty");

		final boolean isLandscape = AppUtils.isLandscape(display.getOrientation());

		Integer panelWidth = AppUtils.getPanelWidth(isLandscape, display.getResolution());
		Integer panelHeight = AppUtils.getPanelHeight(isLandscape, display.getResolution());

		// scale display to Full HD size
		Integer width = AppConstants.FULL_HD_DISPLAY_WIDTH;
		Integer height = AppConstants.FULL_HD_DISPLAY_HEIGHT;
		final Double scaleFactor = AppConstants.FULL_HD_SCALE_FACTOR;
		if (isLandscape) {
			panelWidth = width / display.getSegmentNumWidth();
			panelHeight = Ints.checkedCast(Math.round(scaleFactor * panelWidth));
			height = panelHeight * display.getSegmentNumHeight();
		} else {
			panelHeight = height / display.getSegmentNumHeight();
			panelWidth = Ints.checkedCast(Math.round(scaleFactor * panelHeight));
			width = panelWidth * display.getSegmentNumWidth();
		}

		final List<Rectangle> panels = createDisplayPanels(display, panelWidth, panelHeight);

		return createDisplayImg(width, height, panels);
	}

	public BufferedImage generate(final Layout layout) {
		Validate.notNull(layout, "layout can not be null");
		Validate.isTrue(!layout.getWindows().isEmpty(), "layout should have at least one window");

		final List<WndRectangle> panels = createLayoutPanels(layout.getWindows());

		return createLayoutImg(AppConstants.FULL_HD_DISPLAY_WIDTH, AppConstants.FULL_HD_DISPLAY_HEIGHT, panels);
	}

	private List<Rectangle> createDisplayPanels(final Display display, final Integer panelWidth,
			final Integer panelHeight) {
		final List<Rectangle> panels = Lists.newArrayList();

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

		paintBackground(g2d, imgWidth, imgHeight);
		paintBorders(g2d, panels);

		// Disposes of this graphics context and releases any system resources
		// that it is using.
		g2d.dispose();

		return bi;
	}

	private BufferedImage createLayoutImg(final Integer imgWidth, final Integer imgHeight,
			final List<WndRectangle> panels) {
		// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed into
		// integer pixels
		final BufferedImage bi = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);

		final Graphics2D g2d = bi.createGraphics();

		paintBackground(g2d, imgWidth, imgHeight);
		paintWndRectangles(g2d, panels);

		// Disposes of this graphics context and releases any system resources
		// that it is using.
		g2d.dispose();

		return bi;
	}

	private static void paintBackground(Graphics2D g2d, final Integer width, final Integer height) {
		g2d.setColor(Color.GRAY);
		g2d.fill(new Rectangle(0, 0, width, height));
	}

	private static <T extends Rectangle> void paintBorders(Graphics2D g2d, final List<T> panels) {
		for (T panel : panels) {
			paintBorders(g2d, panel);
		}
	}

	private static <T extends Rectangle> void paintBorders(Graphics2D g2d, final T panel) {
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(borderThickness));

		g2d.drawRect(fitCoord(panel.getX()), fitCoord(panel.getY()), fitLength(panel.getWidth()),
				fitLength(panel.getHeight()));
	}

	private static void paintWndRectangles(Graphics2D g2d, final List<WndRectangle> panels) {
		for (final WndRectangle panel : panels) {
			paintWndRectangle(g2d, panel);
		}
	}

	private static void paintWndRectangle(Graphics2D g2d, final WndRectangle panel) {
		g2d.setPaint(Color.YELLOW);
		g2d.fill(panel);

		paintBorders(g2d, panel);
		paintText(g2d, panel);
	}

	private static void paintText(Graphics2D g2d, WndRectangle panel) {
		g2d.setPaint(Color.BLACK);
		g2d.setFont(new Font("Arial", Font.PLAIN, 100));

		int sW = (int) g2d.getFontMetrics().getStringBounds(panel.getText(), g2d).getWidth();
		g2d.drawString(panel.getText(), panel.getTextX() - sW / 2, panel.getTextY());
	}

	private static int fitCoord(Double coord) {
		return coord.intValue() + new Float(borderThickness / 2).intValue();
	}

	private static int fitLength(Double length) {
		return length.intValue() - borderThickness.intValue();
	}

	private List<WndRectangle> createLayoutPanels(final List<LayoutWindow> windows) {
		final List<WndRectangle> result = Lists.newArrayList();
		for (final LayoutWindow window : windows) {
			result.add(createLayoutPanel(window));
		}
		return result;
	}

	private WndRectangle createLayoutPanel(final LayoutWindow window) {
		final Integer left = toRealValue(window.getLeft(), AppConstants.FULL_HD_DISPLAY_WIDTH);
		final Integer top = toRealValue(window.getTop(), AppConstants.FULL_HD_DISPLAY_HEIGHT);
		final Integer width = toRealValue(window.getWidth(), AppConstants.FULL_HD_DISPLAY_WIDTH);
		final Integer height = toRealValue(window.getHeight(), AppConstants.FULL_HD_DISPLAY_HEIGHT);
		return new WndRectangle(window.getName(), left, top, width, height);
	}

	private static Integer toRealValue(final Integer percent, final Integer base) {
		return base * percent / 100;
	}
}
