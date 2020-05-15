import java.util.ArrayList;

public interface PapotageListener {
	
	// Getters et Setters
	public boolean isConnecte();
	public void setConnecte(boolean connecte); 
	public void setIb(InterfaceBavard ib);
	public InterfaceBavard getIb();
	public String getNom();
}
