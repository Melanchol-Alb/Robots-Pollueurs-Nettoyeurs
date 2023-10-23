public class RobotPollueurSauteur extends RobotPollueur{
	private int Port�e;
	/*
	 * Port�e = 3  Port�e = 2    x:Case vers laquelle on peut sauter, il y en a 8*Port�e au total
	 * xxxxxxx     xxxxx
	 * x00000x     x000x
	 * x00000x     x0x0x
	 * x00x00x     x000x
	 * x00000x     xxxxx
	 * x00000x
	 * xxxxxxx
	 * */

	public RobotPollueurSauteur(int posx, int posy,int v, Monde m, int port�e) {
		super(posx, posy, v, m);
		Port�e = port�e;
		nommer("SS");
	}

	@Override
	public void Parcourir() throws ExceptionCaseExistePas {
		int Case = (int)(Math.random()*Port�e*8);
		int x,y;
		if(Case<Port�e*2) {
			x=posx-Port�e;
			y=posy-Port�e+Case;
		}else if(Case<Port�e*4) {
			y=posy+Port�e;
			x=posx-Port�e+Case-2*Port�e;
		}else if(Case<Port�e*6) {
			x=posx+Port�e;
			y=posy-Port�e+Case-4*Port�e+1;
		}else {
			y=posy-Port�e;
			x=posx-Port�e+Case-6*Port�e;
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
		for(int Case=1;Case<8*Port�e+1;Case++) {
			if(Case<Port�e*2) {
				x=posx-Port�e;
				y=posy-Port�e+Case;
			}else if(Case<Port�e*4) {
				y=posy+Port�e;
				x=posx-Port�e+Case-2*Port�e;
			}else if(Case<Port�e*6) {
				x=posx+Port�e;
				y=posy-Port�e+Case-4*Port�e+1;
			}else {
				y=posy-Port�e;
				x=posx-Port�e+Case-6*Port�e;
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
