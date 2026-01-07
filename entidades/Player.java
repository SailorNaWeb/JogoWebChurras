package entidades;

import inputs.Inputs;

public class Player extends Entidade{
 
    int speed = 5;

    public Player() {
	x = 5;
	y = 5;
	width = 80;
	height = 100;
    }

    @Override
    public void update (Inputs inputs) {
	if (inputs.cima) {
	    y -= speed;
	}
	if (inputs.baixo) {
	    y += speed;
	}
	if (inputs.esquerda) {
	    x -= speed;
	}
	if (inputs.direita) {
	    x += speed;
	}
    }

    @Override
    public void intersected (Actions action) {
	
    }

    @Override
    public Actions intersected () {
	return Actions.HIT;
    }
}
