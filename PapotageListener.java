import java.util.ArrayList;

public interface PapotageListener {
	public void addEvent(PapotageEvent pe);
	public void removeEvent(PapotageEvent pe);
	public void afficheEvent(PapotageEvent pe);
	public ArrayList<PapotageEvent> getMessagesEnvoyes();
	public void afficheMessage(PapotageEvent message);
	public boolean isConnecte();
	public String getNom();
	public void setConnecte(boolean connecte); 
	public void setInterfBavard(InterfaceBavard interfBavard);
	public void setIb(InterfaceBavard ib);
	public InterfaceBavard getIb();
}
