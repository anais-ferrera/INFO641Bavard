import java.util.ArrayList;
public class Bavard implements PapotageListener {
	private String nom;
	private ArrayList<PapotageEvent> messagesEnvoyes;
	private ArrayList<PapotageEvent> messagesRecus;
	private boolean connecte = false;
	
	public Bavard(String nom){
		this.messagesEnvoyes = new ArrayList<PapotageEvent>();
		this.messagesRecus = new ArrayList<PapotageEvent>();
		this.nom=nom;
		
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean isConnecte() {
		return connecte;
	}

	public void addEvent(PapotageEvent pe){
		this.messagesEnvoyes.add(pe);
	}
	
	public void removeEvent(PapotageEvent pe){
		this.messagesEnvoyes.remove(pe);
	}
	
	public void afficheEvent(PapotageEvent pe){
		pe.toString();
	}
	
	public void afficheMessage(PapotageEvent message) {
			System.out.println(message);
	}
	
	public void envoyerMessage(String sujet,String corps){
		PapotageEvent pe = new PapotageEvent(this,sujet,corps);
		this.addEvent(pe);
		
	}

	public ArrayList<PapotageEvent> getMessagesEnvoyes() {
		return messagesEnvoyes;
	}

	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}
	
	
	
}
