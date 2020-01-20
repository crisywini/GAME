package co.crisi.game.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

	private final static int NUMBER_KEYS = 120;
	private final boolean[] keys = new boolean[NUMBER_KEYS];

	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;

	public void actualize() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	}

	/**
	 * pulsar y soltar la tecla
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Pulsar una tecla y no soltarla
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	/**
	 * Soltar la tecla que se habia pulsado
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

}
