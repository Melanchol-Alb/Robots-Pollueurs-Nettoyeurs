
public abstract class RobotNettoyeur extends Robot {
	private int Capacit�,Charge;
	private static int nbRob=0;
	
	public void incCharge() {Charge++;}
	public RobotNettoyeur(int posx, int posy, int v, Monde m,int Cap) {
		super(posx, posy, v, m);
		Capacit� = Cap;
		Charge = 0;
		nommer("N-"+nbRob);
		nbRob++;
	}
	public RobotNettoyeur(int v, Monde m,int Cap) {
		super(v,m);
		Capacit� = Cap;
		Charge = 0;
		nommer("N-"+nbRob);
		nbRob++;
	}
	public boolean EstPasSurcharg�() {
		return Capacit�>=Charge;
	}
	public int getNbRob() {
		return nbRob;
	}
	public void D�charger() throws ExceptionCaseExistePas {
		int x=(posx==m.getxDecharge())? posx : ((posx<m.getxDecharge())? posx+1 : posx-1);
		int y=(posy==m.getyDecharge())? posy : ((posy<m.getyDecharge())? posy+1 : posy-1);
		SeDeplacer(x, y);
		if((x==m.getxDecharge())&&(y==m.getyDecharge())) {
			Charge = 0;
			m.decharger(Capacit�);
			if(this instanceof RobotNettoyeurToutDroit)
				SeDeplacer(((RobotNettoyeurToutDroit)this).getLig(), posy);
		}
	}
}
