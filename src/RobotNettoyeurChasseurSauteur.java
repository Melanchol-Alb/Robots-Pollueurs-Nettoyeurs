import java.util.Vector;

public class RobotNettoyeurChasseurSauteur extends RobotNettoyeur{
	private int Port�e;
	private boolean peutSauter;
	public RobotNettoyeurChasseurSauteur(int posx, int posy, int v, Monde m, int Cap, int Port�e) {
		super(posx, posy, v, m, Cap);
		peutSauter = PeutSauter();
		this.Port�e = Port�e;
		nommer("CS");
	}

	@Override
	public void Parcourir() throws ExceptionCaseExistePas {
		if(!peutSauter) {
			peutSauter=PeutSauter();
			if(!peutSauter)
				Port�e--;
			return;
		}
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

	public void aD�charg�() {
		peutSauter=false;
	}
}
