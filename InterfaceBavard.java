import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class InterfaceBavard extends JFrame implements ActionListener {
	
	private JLabel labelMessagesR = new JLabel("Messages recus :");
	private JLabel labelMessagesE = new JLabel("Messages envoyes :");
	private String messageR = "";
	private String messageE = "";
	private JTextArea zoneMessagesR = new JTextArea(5, 20);
	private JTextArea zoneMessagesE = new JTextArea(5, 20);
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton boutonMessage = new JButton("Nouveau Message");
	private JButton boutonDeco = new JButton("Deconnexion");
	/*
	 * private JLabel labelSujet = new JLabel("Sujet :"); private JLabel
	 * labelMessage = new JLabel("Corps :"); private JTextField sujetMessage = new
	 * JTextField("",20); private JTextField message = new JTextField("",50);
	 */
	private Bavard bavard;
	private Concierge concierge;
	
	//private String fils_discussion ="";
	//private JLabel discussion = new JLabel("Message");
	
	public InterfaceBavard (Bavard b){
		super();
		this.bavard=b;
		
		// Definition du titre et de la position de la fenetre
		this.setTitle("Messagerie de "+this.bavard.getNom());
		this.setSize(800,500);
		this.setLocation(1050,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		// Mise en place des deux boutons
		boutonMessage.addActionListener(this);
	    boutonMessage.setActionCommand("newMessage");
	    
	    boutonDeco.addActionListener(this);
	    boutonDeco.setActionCommand("deconnexion");
	    
	    // Creation du container
	 	Container mainContainer = this.getContentPane();
	 	mainContainer.add(this.panel);
	 	
	    // Creation des bordures
	 	Border border1=  BorderFactory.createEmptyBorder(30,35,30,35);
	 	Border border2=  BorderFactory.createEmptyBorder(20,45,20,45);
	 		
	 	// Creation du Layout
	 	BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
	 	
	    // Mise en place des labels
	 	Font font = new Font("Arial",Font.BOLD,20);
	 	labelMessagesR.setFont(font);
	 	labelMessagesE.setFont(font);
	 	Color c1 = new Color(150, 10, 20);
	 	labelMessagesR.setForeground(Color.orange);
	 	labelMessagesE.setForeground(c1);
	 		
	 	// Creation de la zone Messages Recus
		JScrollPane scrollPane1 = new JScrollPane(this.zoneMessagesR); 
		scrollPane1.setBorder(border2);
	 	this.zoneMessagesR.setEditable(false);
	 		
	 	// Creation de la zone Bavards Connectes
	 	JScrollPane scrollPane2 = new JScrollPane(this.zoneMessagesE); 
	 	zoneMessagesE.setBorder(border2);
	 	zoneMessagesE.setEditable(false);
	 		
	 	// Centrage des elements 
	 	labelMessagesR.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	this.zoneMessagesR.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	this.zoneMessagesE.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	scrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	scrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	boutonMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	boutonDeco.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	labelMessagesE.setAlignmentX(Component.CENTER_ALIGNMENT);
	 		
	 	// Mise en place de panel1
	 	panel.setBorder(border1);
	 	panel.setLayout(layout);
	 	panel.add(labelMessagesR);
	 	panel.add(scrollPane1);
	 	panel.add(labelMessagesE);
	 	panel.add(scrollPane2);
	 	panel.add(boutonMessage);
	 	panel.add(boutonDeco);
	 		
	 	pack();
		 this.setVisible(true);
		 
	}
	
	// Utilisation des boutons
	public void actionPerformed(ActionEvent e) {
		// Creation d'une interface message avec le bouton newMessage
	    if(e.getActionCommand().equals("newMessage")) {
	    	InterfaceMessage iM = new InterfaceMessage(bavard,concierge);
				iM.setConcierge(concierge);
	    }
	    // Concierge deconnecte le bavard avec le bouton deconnexion
	    if(e.getActionCommand().equals("deconnexion")) {
	    	this.concierge.deconnecteBavard(bavard);
	    	//concierge.removeEcouteur(bavard);
	    	this.dispose();
	    }
	    // Si le bavard ferme la fenetre on deconnecte le bavard
	    if(e.getActionCommand().equals(EXIT_ON_CLOSE)) {
	    	this.concierge.deconnecteBavard(bavard);
	    	//concierge.removeEcouteur(bavard);
	    	this.dispose();
	    }
	}
	
	// Permet d'afficher les messages recus dans la zone de texte zoneMessagesR
	public void afficheMessR(PapotageEvent mess, PapotageListener envoyeur) {
		this.messageR = this.messageR + "De "+envoyeur.getNom()+ "\n Sujet : " + mess.getSujet()+ "\n"+mess.getCorps() + "\n\n";
		zoneMessagesR.setText(this.messageR);
	}
	
	// Permet d'afficher les messages envoyes dans la zone de texte zoneMessagesE
	public void afficheMessE(PapotageEvent mess, PapotageListener destinataire) {
		this.messageE = this.messageE + "À "+destinataire.getNom()+ "\n Sujet : " + mess.getSujet()+ "\n" + mess.getCorps() + "\n\n";
		zoneMessagesE.setText(this.messageE);
	}
	
	// Getters et Setters
	
	public Bavard getBavard() {
		return bavard;
	}


	public void setBavard(Bavard b) {
		this.bavard = b;
	}
	
	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}
}