package co.crisi.game.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import co.crisi.game.control.KeyBoard;

public class CanvasGame extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JFrame window;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final String TITLE = "GAME";
	private static volatile boolean isActive = false;
	private static Thread gameThread;
	private static KeyBoard keyBoard;

	private static int aps = 0;// Actualizaciones por segundo
	private static int fps = 0;// Frames por segundo

	public CanvasGame() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		keyBoard = new KeyBoard();
		addKeyListener(keyBoard);

		window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLayout(new BorderLayout());
		window.add(this, BorderLayout.CENTER);
		window.pack();// Todos los elementos se ajustan a al tamaño 800*600
		window.setLocationRelativeTo(null);// Ventana en centro del escritorio
		window.setVisible(true);
		initThread();
	}

	public synchronized void initThread() {
		isActive = true;
		gameThread = new Thread(this, "Graficos");
		gameThread.start();

	}

	public synchronized void stop() {
		isActive = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualizar variables del juego
	 */
	private void actualize() {
		keyBoard.actualize();

		if (keyBoard.up) {
			// Codigo para mover personaje hacia arriba
			System.out.println("Arriba pulsado: W");
		}
		if (keyBoard.down) {
			System.out.println("Abajo pulsado: S");
		}
		if (keyBoard.left) {
			System.out.println("Izquierda pulsado: A");
		}
		if (keyBoard.right) {
			System.out.println("Derecha pulsado: D");
		}

		aps++;

	}

	/**
	 * Metodos necesarios para redibujar gráficos
	 */
	private void showGraphics() {
		fps++;
	}

	@Override
	public void run() {
		final int NS_POR_SEG = 1000000000;// Equivalencia de nanosegundos por segundos
		final byte APS_OBJETIVO = 60;// Actualizaciones por segundo
		final double NS_POR_ACTUALIZACION = NS_POR_SEG / APS_OBJETIVO;// cuantos nanosegunods ocurren por actualizacion
		long referenciaActualizacion = System.nanoTime();// ciclos de reloj del procesador, cuenta nano segundos, 1
															// seguido de 9 ceros
		long referenciaContador = System.nanoTime();

		double tiempoTransCurrido, delta = 0;// Delta, cantidad de tiempo que ha transcurrido hasta que se realiza una
												// actualizacion

		requestFocus();// Para que la ventana al iniciar quede con el control del teclado

		while (isActive) {
			final long inicioBucle = System.nanoTime();
			tiempoTransCurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;

			delta += tiempoTransCurrido / NS_POR_ACTUALIZACION;
			while (delta >= 1) {
				actualize();
				delta--;
			}
			showGraphics();
			if (System.nanoTime() - referenciaContador > NS_POR_SEG) {
				window.setTitle(TITLE + "||APS: " + aps + "||FPS: " + fps);
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}

	}

}
