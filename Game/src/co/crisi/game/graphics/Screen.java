package co.crisi.game.graphics;

import co.crisi.game.map.tiles.Tile;

public final class Screen {

	private final int width;
	private final int height;

	public final int[] pixels;

	// Temporal
	private final static int LADO_SPRITE = 32;
	private final static int MASCARA_SPRITE = LADO_SPRITE - 1;
	// Fin temporal

	public Screen(final int width, final int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
	}

	/**
	 * Limpia la pantalla de todo lo que tenia antes para actualizar
	 */
	public void clean() {

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;// 0 es el color negro absoluto
		}
	}

	// TEMPORAL
	/**
	 * Dibuja en la pantalla
	 * 
	 * @param x1 compensacion x movimiento en el eje x con las teclas
	 * @param y2 compensacion y movimiento en el eje y con las teclas
	 */
	public void showScreen(final int x1, final int y2) {
		for (int y = 0; y < height; y++) {
			int posY = y + y2;
			if (posY < 0 || posY >= height) {
				continue;// siguiente paso para no salirse del mapa
			}
			for (int x = 0; x < pixels.length; x++) {
				int posX = x + x1;
				if (posX < 0 || posX >= width) {
					continue;
				}
				// Redibujar la pantalla
				// Temporal ahora
				pixels[posX + posY * width] = Sprite.ASPHALT.pixels[(x & MASCARA_SPRITE)
						+ (y & MASCARA_SPRITE) * LADO_SPRITE];
			}
		}
	}
	// FIN TEMPORAL
	/**
	 * 
	 * @param x2 compensacion x
	 * @param y2 compensacion y
	 * @param tile cuadro
	 */
	public void showTile(int x2, int y2, Tile tile) {
		for (int y = 0; y < tile.sprite.getSize(); y++) {
			int posY = y+y2;
			for (int x = 0; x < pixels.length; x++) {
				int posX = x+x2;
				if(posX<0||posX>width||posY<0||posY>height)
					break;
				pixels[posX+posY*width] = tile.sprite.pixels[x+y*tile.sprite.getSize()];
			}
		}
	}
	
	
}
