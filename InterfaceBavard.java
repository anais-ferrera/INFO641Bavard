import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfaceBavard extends JFrame implements ActionListener {
	
	private JFrame frame = new JFrame();
	private JPanel pan = new JPanel();
	private JButton boutonEnvoyer = new JButton("Envoyer");
	private JButton boutonDeco = new JButton("Deconnexion");
	private JLabel labelSujet = new JLabel("Sujet :");
	private JLabel labelMessage = new JLabel("Corps :");
	private JTextField sujetMessage = new JTextField("",20);
	private JTextField message = new JTextField("",50);
	private Bavard bavard;
	private Concierge concierge;


	private String fils_discussion ="";
	private JLabel discussion = new JLabel("Message");
	
	public InterfaceBavard() {
		super();
		System.out.println("cc");
		this.setTitle("Messagerie");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		pan.add(discussion);
		pan.add(labelSujet);
		sujetMessage.addActionListener(this);
		pan.add(sujetMessage);
		
		pan.add(labelMessage);
		message.addActionListener(this);
		pan.add(message);
		
		boutonEnvoyer.addActionListener(this);
	    boutonEnvoyer.setActionCommand("envoyer");
		pan.add(boutonEnvoyer, BorderLayout.SOUTH);
		
		boutonDeco.addActionListener(this);
	    boutonDeco.setActionCommand("deconnexion");
		pan.add(boutonDeco, BorderLayout.SOUTH);
		
		 this.setContentPane(pan);
		 this.setVisible(true);
		 
	}
	
	public void actionPerformed(ActionEvent e) {
	    if(e.getActionCommand().equals("boutonEnvoyer")) {
	    	String sujetTexte = sujetMessage.getText();
	    	String texte = message.getText();
	    	bavard.envoyerMessage(sujetTexte, texte);
	    }
	    
	    if(e.getActionCommand().equals("deconnexion")) {
	    	this.concierge.deconnecteBavard(bavard);
	    	concierge.removeEcouteur(bavard);
	    	this.dispose();
	    }
	}
	
	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}

	public void afficheMess(PapotageEvent mess) {
		fils_discussion = fils_discussion +"<p>"+mess.getSujet()+" : "+mess.getCorps()+"</p>";
		discussion.setText("<html>"+ fils_discussion +"<br><h3>Nouveau message :</h3></html>");
	}
	
	public Bavard getBavard() {
		return bavard;
	}


	public void setBavard(Bavard b) {
		this.bavard = b;
	}
}