package processamento;

import javax.swing.JPanel;
import java.lang.Runnable;
import java.awt.Dimension;
import java.lang.Thread;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import entidades.Entidade;
import entidades.Parede;
import entidades.Player;
import inputs.Inputs;

public class StatePrincipal extends JPanel implements Runnable{
    Thread thread;
    Player player;
    Inputs inputs;

    Parede parede = new Parede(200, 300, 400, 30);

    Entidade[] entidades = {parede};

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
	    
	    player.update(inputs, entidades);

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
	g2.fillRect(playerInfos[Entidade.X], playerInfos[Entidade.Y], playerInfos[Entidade.WIDTH], playerInfos[Entidade.HEIGHT]);

	for (Entidade entidade : entidades) {
	    int[] infos = entidade.getInfos();

	    g2.setColor(new Color(50, 10, 80));
	    g2.fillRect(infos[Entidade.X], infos[Entidade.Y], infos[Entidade.WIDTH], infos[Entidade.HEIGHT]);

   
	}
    }
}
