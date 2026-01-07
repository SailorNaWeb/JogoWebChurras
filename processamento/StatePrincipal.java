package processamento;

import javax.swing.JPanel;
import java.lang.Runnable;
import java.awt.Dimension;
import java.lang.Thread;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import entidades.Player;
import inputs.Inputs;

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
	    
	    player.update(inputs);

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

	int[] playerInfos = player.getInfos();

	g2.setColor(new Color(50, 50, 80));
	g2.fillRect(playerInfos[Player.X], playerInfos[Player.Y], playerInfos[Player.WIDTH], playerInfos[Player.HEIGHT]);
    }
}
