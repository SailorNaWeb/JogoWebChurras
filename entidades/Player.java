package entidades;

import inputs.Inputs;

import java.util.HashMap;

public class Player extends Entidade{
 
    private final int CIMA = 1;
    private final int DIREITA = 2;
    private final int BAIXO = 3;
    private final int ESQUERDA = 4;

    int speed = 5;

    boolean cima = true, baixo = true, esquerda = true, direita = true;

    public Player() {
	x = 5;
	y = 5;
	width = 80;
	height = 100;
    }

    @Override
    public void update (Inputs inputs, Entidade[] entidades) {
	if (inputs.cima && cima) {
	    y -= speed;
	}
	if (inputs.baixo && baixo) {
	    y += speed;
	}
	if (inputs.esquerda && esquerda) {
	    x -= speed;
	}
	if (inputs.direita && direita) {
	    x += speed;
	}

	intersects(entidades);
    }

    @Override
    public void intersected (Actions action, Entidade entidade) {
	 
	switch (action) {
	    case STOP :
		int[] dados = entidade.getInfos();
		double[] pontoC = {dados[Entidade.WIDTH] / 2 + dados[Entidade.X], dados[Entidade.HEIGHT] / 2 + dados[Entidade.Y]}; 

		boolean[] pontosVer = verifyPoints(dados);	

		if (pontosVer[Entidade.PONTO_0]) {

		    if (pontosVer[Entidade.PONTO_1]) {
			cima = false;
		    }

		    double[] entPonto = {dados[Entidade.X] + dados[Entidade.WIDTH], dados[Entidade.Y] + dados[Entidade.HEIGHT]};
		    double[] plaPonto = {x, y};

		    boolean isVertical = verifyRelative(entPonto, plaPonto, pontoC);

		    if (isVertical) {
			cima = false;
			intersected.put(entidade, CIMA);
		    } else {
			esquerda = false;
			intersected.put(entidade, ESQUERDA);
		    }
		} else if (pontosVer[Entidade.PONTO_1]) {

		    double[] entPonto = {dados[Entidade.X], dados[Entidade.Y] + dados[Entidade.HEIGHT]};
		    double[] plaPonto = {x + width, y};

		    boolean isVertical = verifyRelative(entPonto, plaPonto, pontoC);

		    if (isVertical) {
			cima = false;
			intersected.put(entidade, CIMA);
		    } else {
			direita = false;
			intersected.put(entidade, DIREITA);
		    }
		}  else if (pontosVer[Entidade.PONTO_2]) {

		    double[] entPonto = {dados[Entidade.X] + dados[Entidade.WIDTH], dados[Entidade.Y]};
		    double[] plaPonto = {x, y + height};

		    boolean isVertical = verifyRelative(entPonto, plaPonto, pontoC);
		
		    if (isVertical) {
			baixo = false;
			intersected.put(entidade, BAIXO);
		    } else {
			esquerda = false;
			intersected.put(entidade, ESQUERDA);
		    }
		} else {

		    double[] entPonto = {dados[Entidade.X], dados[Entidade.Y]};
		    double[] plaPonto = {x + width, y + height};

		    boolean isVertical = verifyRelative(entPonto, plaPonto, pontoC);
		
		    if (isVertical) {
			baixo = false;
			intersected.put(entidade, BAIXO);
		    } else {
			direita = false;
			intersected.put(entidade, DIREITA);
		    }
		}

		break;
	}
    }

    @Override
    public Actions intersected () {
	return Actions.PLAYER;
    }

    @Override
    public void removeIntersected(Entidade entidade) {
	int lado = intersected.get(entidade);

	switch (lado) {
	    case CIMA :
		cima = true;
		break;
	    case DIREITA :
		direita = true;
		break;
	    case ESQUERDA :
		esquerda = true;
		break;
	    case BAIXO :
		baixo = true;
		break;
	    default:
		break;
	}

	intersected.remove(entidade);
    }

    private boolean verifyRelative(double[] entPonto, double[] plaPonto, double[] pontoC) {

	double entRelativeX = entPonto[Entidade.X] - pontoC[Entidade.X];
	double entRelativeY = entPonto[Entidade.Y] - pontoC[Entidade.Y];

	double plaRelativeX = plaPonto[Entidade.X] - pontoC[Entidade.X];
	double plaRelativeY = plaPonto[Entidade.Y] - pontoC[Entidade.Y];

	
    }
}
