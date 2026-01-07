import processamento.StatePrincipal;

import javax.swing.JFrame;

public class Main {
    public static void main(String args[]) {
	JFrame painel = new JFrame();

	StatePrincipal mainState = new StatePrincipal();

	painel.add(mainState);

	painel.setVisible(true);

	painel.pack();
    }
}
