package co.crisi.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheed {

	private final int width;
	private final int height;
	public final int[] pixels;

	// Coleccion de hojas de sprites
	public static SpriteSheed desert = new SpriteSheed("/textures/Desert.png", 320, 320);
	

	// Fin de coleccion

	public SpriteSheed(final String path, final int width, final int height) {
		this.height = height;
		this.width = width;

		pixels = new int[width * height];

		BufferedImage image;
		try {
			image = ImageIO.read(SpriteSheed.class.getResource(path));
			// X , Y, w, h, array para guardar informacion, offset: hasta que punto hay que
			// corregir la posicion, scansize: hasta que punto se lee de manera horizontal
			// antes de pasar a la siguiente linea.
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
