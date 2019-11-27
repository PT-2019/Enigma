package editor.utils;

import editor.texture.TextureArea;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class JsonSubTexture {

	public static Image getSubTexture(final String[] jsonFile, final String image, Point bounds){
		Point pos = getPosition(jsonFile[bounds.x+1]);
		Dimension size = getSize(jsonFile[bounds.x+2]);

		ImageIcon icon = new ImageIcon(image);

		if (icon.getIconHeight() < 0 || icon.getIconWidth() < 0) {
			throw new IllegalStateException("File not found. Bad Path.");
		}

		BufferedImage buffer = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);
		return buffer.getSubimage(pos.x,pos.y,size.width, size.height);
	}

	private static Dimension getSize(final String json) {
		int width, height;
		String[] values = json.split("size: ")[1].split(",");

		width = Integer.parseInt(values[0]);
		height = Integer.parseInt(values[1].trim());

		return new Dimension(width,height);
	}

	private static Point getPosition(String json) {
		int x,y;

		String[] values = json.split("xy: ")[1].split(",");

		x = Integer.parseInt(values[0]);
		y = Integer.parseInt(values[1].trim());

		return new Point(x,y);
	}

}
