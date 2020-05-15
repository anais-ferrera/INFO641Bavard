import java.util.ArrayList;
public class Bavard implements PapotageListener {
	private String nom;

	private boolean connecte = false;
	private InterfaceBavard ib = null;
	private Concierge concierge;
	
	// Constructeur
	public Bavard(String nom, Concierge c){
		this.nom=nom;
		this.concierge = c;
	}
	
	// Envoie le message a un seul destinataire
	public void envoyerMessage(String sujet,String corps,PapotageListener destinataire,Bavard envoyeur){
		PapotageEvent pe = new PapotageEvent(this,sujet,corps);
		this.concierge.envoieMessage(pe, destinataire, envoyeur);
	}
	
	// Envoie le message a tous le monde
	public void envoieMessageATous(String sujet,String corps) {
		PapotageEvent pe = new PapotageEvent(this,sujet,corps);
		concierge.envoieMessageATous(pe, this);
	}

	// Getters et Setters
	
	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}

	public void setIb(InterfaceBavard ib) {
		this.ib = ib;
	}

	public InterfaceBavard getIb() {
		return ib;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean isConnecte() {
		return connecte;
	}
}
