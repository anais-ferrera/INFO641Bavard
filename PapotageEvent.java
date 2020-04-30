import java.util.EventObject;

public class PapotageEvent extends EventObject {
	private String sujet, corps;
	
	public PapotageEvent (Object source, String sujet, String corps) {
		super(source);
		this.sujet=sujet;
		this.corps=corps;
	}

	@Override
	public String toString() {
		return "PapotageEvent [sujet=" + sujet + ", corps=" + corps + "]";
	}

		
}
