package entidades;

import inputs.Inputs;

import java.util.HashMap;

public abstract class Entidade {
    public static final int X = 0;
    public static final int Y = 1;
    public static final int WIDTH = 2;
    public static final int HEIGHT = 3;

    public static final int PONTO_0 = 0;
    public static final int PONTO_1 = 1;
    public static final int PONTO_2 = 2;
    public static final int PONTO_3 = 3;

    int x;
    int y;
    int width;
    int height;

    HashMap<Entidade, Integer> intersected = new HashMap<Entidade, Integer>();

    public abstract void update(Inputs inputs, Entidade[] entidades);

    public abstract Actions intersected(); // Passiva
    public abstract void intersected(Actions action, Entidade entidade); // Ativa

    public abstract void removeIntersected(Entidade entidade);

    public int[] getInfos() {
	int[] infos = {x, y, width, height};
	return infos;
    }

    public void intersects(Entidade[] entidades) {
	for (Entidade entidade : entidades) {
	    int[] dados = entidade.getInfos();
	    
	    boolean[] pontosVer = verifyPoints(dados);

	    if (pontosVer[PONTO_0] || pontosVer[PONTO_1] || pontosVer[PONTO_2] || pontosVer[PONTO_3]) {
		
		if (!intersected.containsKey(entidade)) {
		    Actions acao = entidade.intersected();

		    intersected(acao, entidade);
		}
	    } else if (intersected.containsKey(entidade)) {
		removeIntersected(entidade);
	    }
	}
    }

    public boolean[] verifyPoints(int[] dados) {
	
	boolean interPonto0 = ( x > dados[X] && x < (dados[X] + dados[WIDTH]) ) && ( y > dados[Y] && y < (dados[Y] + dados[HEIGHT]) );
	boolean interPonto1 = ( (x + width) > dados[X] && (x + width) < (dados[X] + dados[WIDTH]) ) && ( y > dados[Y] && y < (dados[Y] + dados[HEIGHT]) );
	boolean interPonto2 = ( x > dados[X] && x < (dados[X] + dados[WIDTH]) ) && ( (y + height) > dados[Y] && (y + height) < (dados[Y] + dados[HEIGHT]) );
	boolean interPonto3 = ( (x + width) > dados[X] && (x + width) < (dados[X] + dados[WIDTH]) ) && ( (y + height) > dados[Y] && (y + height) < (dados[Y] + dados[HEIGHT]) );

	boolean[] result = {interPonto0, interPonto1, interPonto2, interPonto3};

	return result;
    }

}
