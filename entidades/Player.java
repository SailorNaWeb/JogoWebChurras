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

		if (pontosVer[Entidade.ARES_CIMA]) {
		    cima = false;
		    intersected.put(entidade, CIMA);
		    y = dados[Entidade.Y] + dados[Entidade.HEIGHT];
		    return;
		} else if (pontosVer[Entidade.ARES_DIREITA]) {
		    direita = false;
		    intersected.put(entidade, DIREITA);
		    x = dados[Entidade.X] - width;
		    return;
		} else if (pontosVer[Entidade.ARES_BAIXO]) {
		    baixo = false;
		    intersected.put(entidade, BAIXO);
		    y = dados[Entidade.Y] - height;
		    return;
		} else if (pontosVer[Entidade.ARES_ESQUERDA]) {
		    esquerda = false;
		    intersected.put(entidade, ESQUERDA);
		    x = dados[Entidade.X] + dados[Entidade.WIDTH];
		    return;
		}

		if (pontosVer[Entidade.PONTO_0]) {

		    if (pontosVer[Entidade.PONTO_1]) {
			cima = false;
			intersected.put(entidade, CIMA);
			y = dados[Entidade.Y] + dados[Entidade.HEIGHT];
			return;
		    }

		    double[] entPonto = {dados[Entidade.X] + dados[Entidade.WIDTH], dados[Entidade.Y] + dados[Entidade.HEIGHT]};
		    double[] plaPonto = {x, y};

		    boolean isVertical = verifyRelative(entPonto, plaPonto, pontoC);

		    if (isVertical) {
			cima = false;
			intersected.put(entidade, CIMA);
			y = dados[Entidade.Y] + dados[Entidade.HEIGHT];
		    } else {
			esquerda = false;
			intersected.put(entidade, ESQUERDA);
			x = dados[Entidade.X] + dados[Entidade.WIDTH];
		    }
		} else if (pontosVer[Entidade.PONTO_1]) {

		    double[] entPonto = {dados[Entidade.X], dados[Entidade.Y] + dados[Entidade.HEIGHT]};
		    double[] plaPonto = {x + width, y};

		    boolean isVertical = verifyRelative(entPonto, plaPonto, pontoC);

		    if (isVertical) {
			cima = false;
			intersected.put(entidade, CIMA);
			y = dados[Entidade.Y] + dados[Entidade.HEIGHT];
		    } else {
			direita = false;
			intersected.put(entidade, DIREITA);
			x = dados[Entidade.X] - width;
		    }
		}  else if (pontosVer[Entidade.PONTO_2]) {

		    if (pontosVer[Entidade.PONTO_3]) {
			baixo = false;
			intersected.put(entidade, BAIXO);
			y = dados[Entidade.Y] - height;
			return;
		    }

		    double[] entPonto = {dados[Entidade.X] + dados[Entidade.WIDTH], dados[Entidade.Y]};
		    double[] plaPonto = {x, y + height};

		    boolean isVertical = verifyRelative(entPonto, plaPonto, pontoC);
		
		    if (isVertical) {
			baixo = false;
			intersected.put(entidade, BAIXO);
			y = dados[Entidade.Y] - height;
		    } else {
			esquerda = false;
			intersected.put(entidade, ESQUERDA);
			x = dados[Entidade.X] + dados[Entidade.WIDTH];
		    }
		} else {

		    double[] entPonto = {dados[Entidade.X], dados[Entidade.Y]};
		    double[] plaPonto = {x + width, y + height};

		    boolean isVertical = verifyRelative(entPonto, plaPonto, pontoC);
		
		    if (isVertical) {
			baixo = false;
			intersected.put(entidade, BAIXO);
			y = dados[Entidade.Y] - height;
		    } else {
			direita = false;
			intersected.put(entidade, DIREITA);
			x = dados[Entidade.X] - width;
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

	if ((plaRelativeX > 0 && entRelativeX < 0) || (plaRelativeX < 0 && entRelativeX > 0)) {
	    return true;
	} else if ((plaRelativeY > 0 && entRelativeY < 0) || (plaRelativeY < 0 && entRelativeY > 0)) {
	    return false;
	}

	entRelativeX = entRelativeX < 0 ? entRelativeX * -1 : entRelativeX;
	entRelativeY = entRelativeY < 0 ? entRelativeY * -1 : entRelativeY;

	plaRelativeX = (plaRelativeX < 0 ? plaRelativeX * -1 : plaRelativeX) + speed;
	plaRelativeY = (plaRelativeY < 0 ? plaRelativeY * -1 : plaRelativeY) + speed;
	
	double tanEnt = entRelativeY / entRelativeX;
	double tanPla = plaRelativeY / plaRelativeX;

	System.out.println("TanEnt: " + tanEnt + " TanPla: " + tanPla);
	System.out.println("PlaX: " + plaRelativeX + " PlaY: " + plaRelativeY);

	if (tanEnt > tanPla)
	    return false;

	return true;
    }
}
