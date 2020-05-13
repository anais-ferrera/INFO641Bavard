import java.util.ArrayList;
public class Concierge {
	private ArrayList<PapotageListener> listeEcouteurs;
	private InterfaceGestionnaire ig;
	
	public Concierge() {
		this.listeEcouteurs=new ArrayList<PapotageListener>();
	}
	
	public void addEcouteurs(PapotageListener b){
		listeEcouteurs.add(b);
	}
	
	public void removeEcouteur(PapotageListener b){
		listeEcouteurs.remove(b);
	}
	
	public Bavard generateBavard(String nom){
		Bavard b = new Bavard(nom);
		this.addEcouteurs(b);
		this.ig.afficheConnectes();
		return b;
		
	}
	
	public void connecteBavard(PapotageListener b) {
		b.setConnecte(true);
		this.ig.afficheConnectes();
	}

	public void deconnecteBavard(Bavard b) {
		b.setConnecte(false);
		this.ig.afficheConnectes();
	}
	
	public void envoieMessage(PapotageEvent mess, PapotageListener destinataire,PapotageListener envoyeur) {
			destinataire.getIb().afficheMessR(mess, envoyeur);			
			envoyeur.getIb().afficheMessE(mess, destinataire);
			ig.afficheMess(mess, envoyeur, destinataire);
	}

	public ArrayList<PapotageListener> getListeBavards() {
		return listeEcouteurs;
	}

	public void setListeBavards(ArrayList<PapotageListener> le) {
		this.listeEcouteurs = le;
	}

	public void setIg(InterfaceGestionnaire ig) {
		this.ig = ig;
	}
	
	
	
}
