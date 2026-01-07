package entidades;

import inputs.Inputs;

public abstract class Entidade {
    public static final int X = 0;
    public static final int Y = 1;
    public static final int WIDTH = 2;
    public static final int HEIGHT = 3;      

    int x;
    int y;
    int width;
    int height;

    public abstract void update(Inputs inputs);

    public abstract Actions intersected(); // Passiva
    public abstract void intersected(Actions action); // Ativa

    public int[] getInfos() {
	int[] infos = {x, y, width, height};
	return infos;
    }

    public boolean intersects(Entidade entidade) {
	int[] dados = entidade.getInfos();
	
	boolean interPonto0 = ( x > dados[X] && x < (dados[X] + dados[WIDTH]) ) && ( y > dados[Y] && y < (dados[Y] + dados[HEIGHT]) );
	boolean interPonto1 = ( (x + width) > dados[X] && (x + width) < (dados[X] + dados[WIDTH]) ) && ( y > dados[Y] && y < (dados[Y] + dados[HEIGHT]) );
	boolean interPonto2 = ( x > dados[X] && x < (dados[X] + dados[WIDTH]) ) && ( (y + height) > dados[Y] && (y + height) < (dados[Y] + dados[HEIGHT]) );
	boolean interPonto3 = ( (x + width) > dados[X] && (x + width) < (dados[X] + dados[WIDTH]) ) && ( (y + height) > dados[Y] && (y + height) < (dados[Y] + dados[HEIGHT]) );

	if (interPonto0 || interPonto1 || interPonto2 || interPonto3) {
	    Actions acao = entidade.intersected();

	    intersected(acao);

	    return true;
	}

	return false;
    }

}
