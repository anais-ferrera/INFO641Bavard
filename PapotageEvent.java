import java.util.EventObject;
public class PapotageEvent extends EventObject {
	private String sujet;
	private String corps;
	
	public PapotageEvent(Object source,String s, String c) {
		super(source);
		this.sujet = s;
		this.corps=c;
	}

	public String getSujet() {
		return sujet;
	}

	public String getCorps() {
		return corps;
	}
	
	
	
}