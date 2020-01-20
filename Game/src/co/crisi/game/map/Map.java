package co.crisi.game.map;

import co.crisi.game.graphics.Screen;

public abstract class Map {

	private int width;
	private int height;

	private int[] tiles;// Recuadro que forman el mapa

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
	}
	
	public Map(String path) {
		loadMap(path);
	}
	
	
	private void generateMap() {
		
	}
	private void loadMap(String path) {
		
	}
	public void actualize() {
		
	}
	public void show(int x2, int y2, Screen screen) {
		
	}

}
