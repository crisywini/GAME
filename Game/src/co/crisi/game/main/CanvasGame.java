package co.crisi.game.main;

import java.awt.BorderLayout;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import co.crisi.game.control.KeyBoard;
import co.crisi.game.graphics.Screen;

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
	private static Screen screen;

	private static int aps = 0;// Actualizaciones por segundo
	private static int fps = 0;// Frames por segundo
	private static int x = 0;
	private static int y = 0;

	private static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	/**
	 * Unica manera en la que se puede manipular los ints de los pixeles de la
	 * imagen getRaster() devuelve la secuencia de pixeles de la imagen
	 */
	private static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();// Devuelve un array de
																								// int de los pixels de
																								// la imagen

	public CanvasGame() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		screen = new Screen(WIDTH, HEIGHT);

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
			y++;// El mapa se deberia mover para abajo para dar ilusion de que el personaje es
				// quien se mueve
		}
		if (keyBoard.down) {
			y--;
		}
		if (keyBoard.left) {
			x++;
		}
		if (keyBoard.right) {
			x--;
		}

		aps++;

	}

	/**
	 * Metodos necesarios para redibujar gráficos
	 */
	private void showGraphics() {
		BufferStrategy strategy = getBufferStrategy();// Crear un buffer
		if (strategy == null) {
			createBufferStrategy(3);// Se utiliza un triple buffer, dibuja 3 imagenes en buffer y al llegar al
									// buffer 3 la dibuja en la pantalla
			return;
		}
		screen.clean();
		// Temporal
		screen.showScreen(x, y);

//		for (int i = 0; i < pixels.length; i++) {
//			pixels[i] = screen.pixels[i];
//		}

		System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

		Graphics g = strategy.getDrawGraphics();// Se encarga de dibujar las cosas dentro del buffer
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();// hace que que g se borre para liberar memoria

		strategy.show();

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
