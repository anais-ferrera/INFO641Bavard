import java.util.ArrayList;

public class Bavard implements PapotageListener{
	private String nom;
	
	private ArrayList<PapotageEvent> messagesEnvoyes;
    private ArrayList<PapotageEvent> messagesRecus;
	
	public Bavard(String nom){
		this.nom=nom;
		this.messagesEnvoyes = new ArrayList<PapotageEvent>();
		this.messagesRecus= new ArrayList<PapotageEvent>();
	}
	
	
	
	public void addEvent(PapotageEvent pe){
		this.messagesEnvoyes.add(pe);
	}
	
	public void removeEvent(PapotageEvent pe){
		this.messagesEnvoyes.remove(pe);
	}
	
	public void afficheEvent(PapotageEvent pe) {
		pe.toString();
	}
	
	 public void afficheMessageRecus() {
	        System.out.println(this.getNom()+" a recu le message suivant : ");
	        for (PapotageEvent message : this.messagesRecus) {
	            System.out.println(message);
	        }
	    }

	  public String getNom() {
		return nom;
	}



	public void envoyerMessage(String sujet,String corps){
	        PapotageEvent pe = new PapotageEvent(this,sujet,corps);
	        this.addEvent(pe);

	    }

	  public ArrayList<PapotageEvent> getMessagesEnvoyes() {
	        return messagesEnvoyes;
	    }
		
	
}
