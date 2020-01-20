package co.crisi.game.map;

import java.util.Random;

public class GenerateMap extends Map{
	
	private final Random random = new Random();

	public GenerateMap(int width, int height) {
		super(width, height);
	}
	
	@Override
	protected void generateMap() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x+y*width] = random.nextInt(3);
			}
		}
	}
		

}