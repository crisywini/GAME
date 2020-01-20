package co.crisi.game.map.tiles;

import co.crisi.game.graphics.Screen;
import co.crisi.game.graphics.Sprite;

public class EmptyTile extends Tile {

	public EmptyTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void show(int x, int y, Screen screen) {
		screen.showTile(x, y, this);
	}

}
