import java.util.Vector;

public class GestionnaireRobots {
	Vector<RobotNettoyeur> Nettoyeurs;
	Vector<RobotPollueur> Pollueurs;
	Vector<RobotNettoyeur> Surcharg�s;
	Vector<RobotPollueur> D�truits;
	private Vector<Vector<Vector<RobotPollueur>>> MatRobotsP;
	
	private long instant;
	public GestionnaireRobots(Monde m, Vector<RobotNettoyeur> nettoyeurs, Vector<RobotPollueur> pollueurs,
			Vector<RobotNettoyeur> surcharg�s, Vector<RobotPollueur> d�truits) {
		Nettoyeurs = nettoyeurs;
		Pollueurs = pollueurs;
		Surcharg�s = surcharg�s;
		D�truits = d�truits;
		MatRobotsP = new Vector<Vector<Vector<RobotPollueur>>>();
		for(int i=0;i<m.getNbL();i++) {
			MatRobotsP.add(new Vector<Vector<RobotPollueur>>());
			for(int j=0;j<m.getNbC();j++) {
				MatRobotsP.get(i).add(new Vector<RobotPollueur>());
			}
		}
		instant=0L;
	}
	public long getInstant() {
		return instant;
	}
	public Vector<RobotPollueur> PollueursA(int x,int y){return MatRobotsP.get(x).get(y);}
	public void Evolution() throws ExceptionCaseExistePas {
		instant++;
		Vector<RobotNettoyeur> toRemove = new Vector<RobotNettoyeur>();
		for(RobotNettoyeur r:Nettoyeurs)
			if(!r.EstPasSurcharg�()) {
				toRemove.add(r);
				Surcharg�s.add(r);
			}else if(instant%r.getVitesse()==0) 
				r.Parcourir();
		for(RobotNettoyeur r:toRemove)
			Nettoyeurs.remove(r);
		toRemove.clear();
		
		Vector<RobotPollueur> toRemoveP = new Vector<RobotPollueur>();
		for(RobotPollueur r:Pollueurs)
			if(!r.estEnVie()) {
				toRemoveP.add(r);
				D�truits.add(r);
			}else if(instant%r.getVitesse()==0)
				r.Parcourir();
		for(RobotPollueur r:toRemoveP)
			Pollueurs.remove(r);
		toRemoveP.clear();
		
		for(RobotNettoyeur r:Surcharg�s) {
			r.D�charger();
			if(r.EstPasSurcharg�()) {
				toRemove.add(r);
				Nettoyeurs.add(r);
				if(r instanceof RobotNettoyeurChasseurSauteur)
					((RobotNettoyeurChasseurSauteur)r).aD�charg�();
			}
		}
		for(RobotNettoyeur r:toRemove)
			Surcharg�s.remove(r);
		toRemove.clear();
	}

	public void DeplacerRobotPollueur(int x,int y,RobotPollueur r) {
		MatRobotsP.get(r.posx).get(r.posy).remove(r);
		MatRobotsP.get(x).get(y).add(r);
	}
	public void DetruireRobot(RobotPollueur r) {
		MatRobotsP.get(r.posx).get(r.posy).remove(r);
	}

	public void AjouterRobot(Robot r) throws ExceptionCaseExistePas {
		if(r instanceof RobotPollueur)
			if(((RobotPollueur) r).estEnVie())
				Pollueurs.add((RobotPollueur)r);
			else D�truits.add((RobotPollueur)r);
		if(r instanceof RobotNettoyeur)
			if(((RobotNettoyeur)r).EstPasSurcharg�())
				Nettoyeurs.add((RobotNettoyeur)r);
			else Surcharg�s.add((RobotNettoyeur)r);
		if(r instanceof RobotPollueurSauteur)
			if(!((RobotPollueurSauteur)r).peutSauter())
				((RobotPollueurSauteur)r).Detruire();
	}
}
