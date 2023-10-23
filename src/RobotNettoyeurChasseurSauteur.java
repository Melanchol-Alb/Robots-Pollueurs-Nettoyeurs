import java.util.Vector;

public class RobotNettoyeurChasseurSauteur extends RobotNettoyeur{
	private int Portée;
	private boolean peutSauter;
	public RobotNettoyeurChasseurSauteur(int posx, int posy, int v, Monde m, int Cap, int Portée) {
		super(posx, posy, v, m, Cap);
		peutSauter = PeutSauter();
		this.Portée = Portée;
		nommer("CS");
	}

	@Override
	public void Parcourir() throws ExceptionCaseExistePas {
		if(!peutSauter) {
			peutSauter=PeutSauter();
			if(!peutSauter)
				Portée--;
			return;
		}
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
			if((x>0)&&(x<m.getNbL())&&(y>0)&&(y<m.getNbC())) {
				Vector<RobotPollueur> vec = m.PollueursA(x, y);
				if(vec.size()>0) {
					Vector<RobotPollueur> vecAtuer = new Vector<RobotPollueur>();
					for(RobotPollueur r : vec) 
						vecAtuer.add(r);
					SeDeplacer(x, y);
					if(m.TestPapierGras(posx, posy)) {
						m.PrendsPapierGras(x, y);
						incCharge();
					}
					vec.clear();
					for(RobotPollueur r : vecAtuer)
						r.Detruire();
					return;
				}
			}
			if(m.TestPapierGras(posx, posy)) {
				m.PrendsPapierGras(posx, posy);
				incCharge();
			}
			}
	}
	
	public boolean PeutSauter() {
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

	public void aDéchargé() {
		peutSauter=false;
	}
}
