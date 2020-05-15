import java.util.ArrayList;

public class Concierge {
	
	private ArrayList <PapotageListener> listeEcouteurs;
	private InterfaceGestionnaire ig;
	
	// Constructeur
	public Concierge() {	
		this.listeEcouteurs = new ArrayList <PapotageListener>();
	}
	
	// Ajoute un PapotageListener a listeEcouteurs
	public void addEcouteurs(PapotageListener b){
        listeEcouteurs.add(b);
    }

	// Enleve un PapotageListener a listeEcouteurs
	public void removeEcouteur(PapotageListener b){
        listeEcouteurs.remove(b);
    }
	
	// Cree et retourne un bavard
    public Bavard generateBavard(String nom){
        Bavard b = new Bavard(nom,this);
        this.addEcouteurs(b);
        this.ig.afficheConnectes();
        return b;
    }
    
    // Connecte un bavard
    public void connecteBavard (PapotageListener b) {
    	b.setConnecte(true);
    	this.ig.afficheConnectes();
    }
    
    // Deconnecte un bavard
    public void deconnecteBavard (Bavard b) {
    	b.setConnecte(false);
    	this.ig.afficheConnectes();
    }
    
    // Envoie le message au destinataire
    public void envoieMessage(PapotageEvent mess, PapotageListener destinataire,PapotageListener envoyeur) {
        destinataire.getIb().afficheMessR(mess,envoyeur);
        envoyeur.getIb().afficheMessE(mess,destinataire);
        ig.afficheMess(mess,envoyeur,destinataire);
    }
    
    // Envoie le message a tous les Bavards
    public void envoieMessageATous(PapotageEvent mess, PapotageListener envoyeur) {
        for(PapotageListener elem: this.getListeEcouteurs()) {
        	elem.getIb().afficheMessR(mess,envoyeur);
        	envoyeur.getIb().afficheMessE(mess, elem);
        	ig.afficheMess(mess, envoyeur, elem);
        }    
    }

    // Getters et Setters
	public void setListeBavards(ArrayList<PapotageListener> le) {
		this.listeEcouteurs = le;
	}
	
	public void setIg(InterfaceGestionnaire ig) {
		this.ig=ig;
	}

	public ArrayList<PapotageListener> getListeEcouteurs() {
		return listeEcouteurs;
	}
}
