
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class InterfaceGestionnaire extends JFrame implements ActionListener {
	private Concierge concierge;
	
	private JPanel conteneur1 = new JPanel();
	private JPanel conteneur2 = new JPanel();
	
	private JLabel labelDiscussion = new JLabel("<html><h1>Discussion :</h1></html>");
	private String discussion="<h1>Discussion :</h1>";

	private JLabel labelConnectes = new JLabel("<h2>Bavards connectés</h2>");
	
	private JButton boutonNewb = new JButton("Nouveau bavard");
	
	
	
	public InterfaceGestionnaire() {
		super();
	
	//definition du titre, de la taille et de la position de la fenetre
		setTitle("Fenetre du concierge");
		setSize(400, 900);
		setLocation(1450, 50);
		
		boutonNewb.addActionListener(this);
		boutonNewb.setActionCommand("newBavard");
		
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		
		conteneur1.add(labelDiscussion, BorderLayout.SOUTH);
		this.getContentPane().add(conteneur1);
		
		conteneur2.add(labelConnectes);
		conteneur2.add(boutonNewb, BorderLayout.SOUTH);
		this.getContentPane().add(conteneur2);
		
		setVisible(true);
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		//si on appui sur le bouton nouveau bavard
		if (e.getActionCommand().equals("newBavard")) {
			new InterfaceBavard();
		}
	}
	
	
	public void afficheMess(PapotageEvent mess) {
		discussion = discussion + "<p>" + mess.getSujet()+" : "+mess.getCorps()+"</p>";
		labelDiscussion.setText("<html>" + discussion + "</html>");
	}
	
	
	public void afficheConnectes() {
		String a ="<h2>Bavards connectés</h2>";
		for (PapotageListener bavard : concierge.getListeBavards()) {
			if (bavard.isConnecte()==true) {
				a = a + "<p>" + bavard.getNom() + "</p>";
			}
		}
		labelConnectes.setText("<html>" + a + "</html>");
	}


//getters et setters
	public Concierge getConcierge() {
		return concierge;
	}


	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}


}
