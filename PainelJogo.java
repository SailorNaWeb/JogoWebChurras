import javax.swing.JFrame;

public class PainelJogo extends JFrame {
	public static void main(String args[]) {
		PainelJogo painel = new PainelJogo();

		StatePrincipal mainState = new StatePrincipal();

		painel.add(mainState);

		painel.setVisible(true);

		painel.pack();
	}
}
