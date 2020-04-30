import java.util.ArrayList;

public class Concierge {
	
	private String nom;
	private ArrayList <Bavard> listeBavards;
	
	
	public Concierge(String nom) {
		this.nom=nom;
		this.listeBavards = new ArrayList <Bavard>();
	
	}
	
	public void addBavard(Bavard b){
        listeBavards.add(b);
    }

    public void removeEcouteur(Bavard b){
        listeBavards.remove(b);
    }

    public void generateBavard(String nom){
        Bavard b = new Bavard(nom);
        this.addBavard(b);

    }
    
    public void bavardConnecte () {
    	for (Bavard b : this.listeBavards) {
    		System.out.println(b.getNom()+"est connecte");
    	}
    }

    public void envoieMessage(PapotageListener expediteur) {
        for (PapotageEvent message : expediteur.getMessagesEnvoyes()) {
            for (PapotageListener listener : this.listeBavards) {
                listener.afficheMessageRecus();
            }
        }
    }

}
