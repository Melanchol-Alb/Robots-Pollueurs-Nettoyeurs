public class RobotPollueurSauteur extends RobotPollueur{
	private int Portée;
	/*
	 * Portée = 3  Portée = 2    x:Case vers laquelle on peut sauter, il y en a 8*Portée au total
	 * xxxxxxx     xxxxx
	 * x00000x     x000x
	 * x00000x     x0x0x
	 * x00x00x     x000x
	 * x00000x     xxxxx
	 * x00000x
	 * xxxxxxx
	 * */

	public RobotPollueurSauteur(int posx, int posy,int v, Monde m, int portée) {
		super(posx, posy, v, m);
		Portée = portée;
		nommer("SS");
	}

	@Override
	public void Parcourir() throws ExceptionCaseExistePas {
		int Case = (int)(Math.random()*Portée*8);
		int x,y;
		if(Case<Portée*2) {
			x=posx-Portée;
			y=posy-Portée+Case;
		}else if(Case<Portée*4) {
			y=posy+Portée;
			x=posx-Portée+Case-2*Portée;
		}else if(Case<Portée*6) {
			x=posx+Portée;
			y=posy-Portée+Case-4*Portée+1;
		}else {
			y=posy-Portée;
			x=posx-Portée+Case-6*Portée;
		}
		if((x>=0)&&(x<m.getNbL())&&(y>=0)&&(y<m.getNbC())) {
			m.MettrePapierGras(x, y);
			SeDeplacer(x, y);
		}else {
			m.MettrePapierGras(posx, posy);
		}
	}
	
	public boolean peutSauter() {
		int x,y;
		for(int Case=1;Case<8*Portée+1;Case++) {
			if(Case<Portée*2) {
				x=posx-Portée;
				y=posy-Portée+Case;
			}else if(Case<Portée*4) {
				y=posy+Portée;
				x=posx-Portée+Case-2*Portée;
			}else if(Case<Portée*6) {
				x=posx+Portée;
				y=posy-Portée+Case-4*Portée+1;
			}else {
				y=posy-Portée;
				x=posx-Portée+Case-6*Portée;
			}
			if((x>0)&&(x<m.getNbL())&&(y>0)&&(y<m.getNbC()))
				return true;
			}
		return false;
	}
	public void Detruire() throws ExceptionCaseExistePas { //A sa destruction il fait tomber du papier gras tout autours de lui
		int x,y;
		for(int Case=1;Case<9;Case++) {
			if(Case<2) {
				x=posx-1;
				y=posy-1+Case;
			}else if(Case<4) {
				y=posy+1;
				x=posx-1+Case-2;
			}else if(Case<6) {
				x=posx+1;
				y=posy-1+Case-4+1;
			}else {
				y=posy-1;
				x=posx-1+Case-6;
			}
			if((x>=0)&&(x<m.getNbL())&&(y>=0)&&(y<m.getNbC()))
				m.MettrePapierGras(x, y);
			}
		tuer();
	}
	
}
