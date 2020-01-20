package co.crisi.game.map.tiles;

import co.crisi.game.graphics.Screen;
import co.crisi.game.graphics.Sprite;

public abstract class Tile {
	public int x;
	public int y;

	public Sprite sprite;// Dibujo que tiene el tile(recuadro)

	public Tile(Sprite sprite) {
		this.sprite = sprite;

	}

	public void show(int x, int y, Screen screen) {

	}

	/**
	 * Metodo que permite verificar si un tile es o no solido
	 * 
	 * @return true si llega a un tile que sea solido
	 */
	public boolean isSolid() {
		return false;
	}
}