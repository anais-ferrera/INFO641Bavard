import java.util.ArrayList;
public class Concierge {
	private ArrayList<PapotageListener> listeEcouteurs;
	
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
		return b;
		
	}
	
	public void connecteBavard(PapotageListener b) {
		b.setConnecte(true);
	}

	public void deconnecteBavard(Bavard b) {
		b.setConnecte(false);
	}
	
//	public void envoieMessage(PapotageListener expediteur) {
	//	for (PapotageEvent message : expediteur.getMessagesEnvoyes()) {
		//	for (PapotageListener listener : this.listeEcouteurs) {
			//	listener.afficheMessageRecus();
//	}
	//	}
//	}
	
	public void transmitPapotageEvent(PapotageEvent mess) {
		
		for (PapotageListener elem:listeEcouteurs) {
			elem.afficheMessage(mess);			
		}
	}

	public ArrayList<PapotageListener> getListeBavards() {
		return listeEcouteurs;
	}

	public void setListeBavards(ArrayList<PapotageListener> le) {
		this.listeEcouteurs = le;
	}
	
	
}
