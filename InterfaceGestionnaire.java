
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;



public class InterfaceGestionnaire extends JFrame implements ActionListener {
	private Concierge concierge;
	private JPanel panel1 = new JPanel();
	private JLabel labelDiscussion = new JLabel("Toutes les discussions :");
	private String message = "";
	private JLabel labelConnectes = new JLabel("Bavards :");	
	private JButton boutonNewb = new JButton("Nouveau bavard");
	private JTextArea zoneMessages = new JTextArea(5, 20);
	private JTextArea zoneBConnectes = new JTextArea(5, 20);
	private InterfaceConnection iConnec;

	
	public InterfaceGestionnaire() {
		super();
		
		// Definition du titre et de la position de la fenetre
		setTitle("Fenetre du concierge");
		setLocation(1450, 200);
			
		boutonNewb.addActionListener(this);
		boutonNewb.setActionCommand("newBavard");

		// Creation du container
		Container mainContainer = this.getContentPane();
		mainContainer.add(panel1);
		
		// Creation des bordures
		Border border1=  BorderFactory.createEmptyBorder(30,35,30,35);
		Border border2=  BorderFactory.createEmptyBorder(20,45,20,45);
		
		// Creation du Layout
		BoxLayout layout = new BoxLayout(panel1,BoxLayout.Y_AXIS);
		
		// Mise en place des labels
		labelDiscussion.setForeground(Color.blue);
	    Border border = LineBorder.createGrayLineBorder();
		labelDiscussion.setBorder(border);
		
		// Creation de la zone Messages
		JScrollPane scrollPane2 = new JScrollPane(this.zoneMessages); 
		scrollPane2.setBorder(border2);
		this.zoneMessages.setEditable(false);
		
		// Creation de la zone Bavards Connectes
		JScrollPane scrollPane = new JScrollPane(this.zoneBConnectes); 
		scrollPane.setBorder(border2);
		zoneBConnectes.setEditable(false);
		
		// Centrage des elements 
		labelDiscussion.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.zoneMessages.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.zoneBConnectes.setAlignmentX(Component.CENTER_ALIGNMENT);
		scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonNewb.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelConnectes.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Mise en place de panel1
		panel1.setBorder(border1);
		panel1.setLayout(layout);
		panel1.add(labelDiscussion);
		panel1.add(scrollPane2);
		panel1.add(labelConnectes);
		panel1.add(scrollPane);
		panel1.add(boutonNewb);
		
		// Creation de la fenetre connection
		InterfaceConnection iConnec = new InterfaceConnection();
		iConnec.setIg(this);
		
		
		pack();
		setVisible(true);
			
	}

	
	
	
	public void actionPerformed(ActionEvent e) {
		//si on appui sur le bouton nouveau bavard
		if (e.getActionCommand().equals("newBavard")) {
			InterfaceCreationBavard icf = new InterfaceCreationBavard();
			icf.setFc(this);
			icf.setConcierge(concierge);
		}
	}
	
	
	public void afficheMess(PapotageEvent mess) {
		this.message = this.message + mess.getSujet()+" : "+mess.getCorps();
		zoneMessages.setText(message);
	}
	
	
	public void afficheConnectes() {
		System.out.println("cc"+this.getConcierge().getListeBavards());
		String etatBavards="";
		for (PapotageListener bavard : concierge.getListeBavards()) {
			etatBavards=etatBavards + bavard.getNom() + " : " + bavard.getEtat() + "\n" ;
		}
		zoneBConnectes.setText(etatBavards);
	}


	// Getters et Setters
	public Concierge getConcierge() {
		return concierge;
	}


	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}


}
