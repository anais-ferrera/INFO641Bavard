import java.util.ArrayList;

public interface PapotageListener {
	public void addEvent(PapotageEvent pe);
	public void removeEvent(PapotageEvent pe);
	public void afficheEvent(PapotageEvent pe);
	public ArrayList<PapotageEvent> getMessagesEnvoyes();
    public void afficheMessageRecus();
		 
}
