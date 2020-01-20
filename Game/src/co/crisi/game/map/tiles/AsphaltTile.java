package co.crisi.game.map.tiles;

import co.crisi.game.graphics.Screen;
import co.crisi.game.graphics.Sprite;

public class AsphaltTile extends Tile {

	public AsphaltTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void show(int x, int y, Screen screen) {
		screen.showTile(x, y, this);
	}

}
