package entidades;

import inputs.Inputs;

public class Parede extends Entidade {
    
    public Parede(int x, int y, int width, int height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }

    @Override
    public void update(Inputs inputs, Entidade[] entidades) {

    }

    @Override
    public void intersected (Actions action, Entidade entidade) {
	
    }

    @Override
    public Actions intersected () {
	return Actions.STOP;
    }

    @Override
    public void removeIntersected (Entidade entidade) {

    }
}
