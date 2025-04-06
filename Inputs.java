import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Inputs implements KeyListener {

	public boolean cima = false, baixo = false, esquerda = false, direita = false;

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		System.out.println("Algo foi apertado");

		switch (keyCode) {
			case KeyEvent.VK_W:
				cima = true;
				break;
			case KeyEvent.VK_S:
				baixo = true;
				System.out.println("Baixo");
				break;
			case KeyEvent.VK_A:
				esquerda = true;
				break;
			case KeyEvent.VK_D:
				direita = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
			case KeyEvent.VK_W:
				cima = false;
				break;
			case KeyEvent.VK_S:
				baixo = false;
				break;
			case KeyEvent.VK_A:
				esquerda = false;
				break;
			case KeyEvent.VK_D:
				direita = false;
				break;
		}
	}
}
