package co.crisi.game.graphics;

public final class Sprite {
	private final int size;// cuanto mide el lado de cada cuadrado

	private int x;// eje horizontal
	private int y;// eje vertical

	public int[] pixels;
	private final SpriteSheed sheed;

	public Sprite(final int size, final int column, final int row, final SpriteSheed sheed) {
		this.size = size;

		pixels = new int[size * size];

		x = column * size;
		y = row * size;

		this.sheed = sheed;
		// Recorre de izquierda a derecha la hoja de sprites
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[(x + y) * size] = sheed.pixels[(x + this.x) + (y + this.y) * sheed.getWidth()];
			}
		}

	}

}
