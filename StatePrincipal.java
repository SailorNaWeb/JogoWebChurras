import javax.swing.JPanel;
import java.lang.Runnable;
import java.awt.Dimension;
import java.lang.Thread;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class StatePrincipal extends JPanel implements Runnable{
	Thread thread;
	Player player;
	Inputs inputs;

	public StatePrincipal() {
		this.setPreferredSize( new Dimension(1280, 720));
		thread = new Thread(this);
		player = new Player();
		inputs = new Inputs();

		this.addKeyListener(inputs);
		this.setFocusable(true);

		this.requestFocusInWindow();


		thread.start();
	}

	@Override
	public void run () {
		while (true) {
			repaint();
			
			if (inputs.cima) {
				System.out.print("Cima");
				player.y--;
			}
			if (inputs.baixo) {
				player.y++;
			}
			if (inputs.esquerda) {
				player.x--;
			}
			if (inputs.direita) {
				player.x++;
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(new Color(50, 50, 80));
		g2.fillRect(player.x, player.y, player.width, player.height);
	}
}
