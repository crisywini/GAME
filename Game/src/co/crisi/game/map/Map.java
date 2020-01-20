package co.crisi.game.map;

import co.crisi.game.graphics.Screen;
import co.crisi.game.map.tiles.Tile;

public abstract class Map {

	protected int width;
	protected int height;

	protected int[] tiles;// Recuadro que forman el mapa

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
	}

	public Map(String path) {
		loadMap(path);
	}

	protected void generateMap() {

	}

	private void loadMap(String path) {

	}

	public void actualize() {

	}

	public void show(int x2, int y2, Screen screen) {
		int oeste = x2 >> 5;// >> significa que desplaza el numero para dividir en 5 posiciones es decir
							// 32(Binario)
		int este = (x2 + screen.getWidth()) >> 5;
		int norte = y2 >> 5;
		int sur = (y2 + screen.getHeight()) >> 5;
	}

	public Tile getTail(final int x, final int y) {
		switch (tiles[x + y * width]) {
		case 0:
			return Tile.ASPHALT;
		default:
			return null;  
		}
	}

}
