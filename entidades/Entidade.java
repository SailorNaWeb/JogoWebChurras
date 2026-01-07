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

    public int[] getInfos() {
	int[] infos = {x, y, width, height};
	return infos;
    }
}
