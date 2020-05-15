import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLEditorKit;

public class InterfaceBavard extends JFrame implements ActionListener {
	
	private JLabel labelMessagesR = new JLabel("Messages recus :");
	private JLabel labelMessagesE = new JLabel("Messages envoyes :");
	private JLabel labelBavard = new JLabel("Bavard :");
	private String messageR = "";
	private String messageE = "";
	
	private JPanel panel = new JPanel();
	private JButton boutonMessage = new JButton("Nouveau Message");
	private JButton boutonDeco = new JButton("Deconnexion");
	private Bavard bavard;
	private Concierge concierge;
	
	JTextPane zoneMessageR = new JTextPane();
	JTextPane zoneMessageE = new JTextPane();
	HTMLEditorKit kit = new HTMLEditorKit();

	
	JEditorPane jEditorPane = new JEditorPane();
	
	// Construction
	public InterfaceBavard (Bavard b){
		super();
		this.bavard=b;
		
		// Definition du titre et de la position de la fenetre
		this.setTitle("Messagerie de "+this.bavard.getNom());
		this.setSize(800,500);
		this.setLocation(1050,200);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 		
		
		// Mise en place des deux boutons
		boutonMessage.addActionListener(this);
	    boutonMessage.setActionCommand("newMessage");
	    
	    boutonDeco.addActionListener(this);
	    boutonDeco.setActionCommand("deconnexion");
	    
	    // Creation du container
	 	Container mainContainer = this.getContentPane();
	 	mainContainer.add(this.panel);
	 	
		// HTML
		jEditorPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(jEditorPane);
		jEditorPane.setEditorKit(kit);
        String htmlString = "<html>"
                + "<body>"
                + "<p></p>"
                + "<p><center><font color=#666666>Il n'y a pas encore d'utilisateur connecté</font></center></p>"
                + "<p></p>"
                + "<p></p>"
                + "</body>";
        jEditorPane.setText(htmlString);
	 	
	    // Creation des bordures
	 	Border border1=  BorderFactory.createEmptyBorder(30,35,30,35);
	 	Border border2=  BorderFactory.createEmptyBorder(20,45,20,45);
	 	Border border3=  BorderFactory.createEmptyBorder(10,10,10,10);
	 		
	 	// Creation du Layout
	 	BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);
	 	
	    // Mise en place des labels
	 	Font font = new Font("Arial",Font.BOLD,20);
	 	labelMessagesR.setFont(font);
	 	labelMessagesE.setFont(font);
	 	Color c1 = new Color(250, 165, 24);
	 	labelMessagesR.setForeground(Color.orange);
	 	labelMessagesE.setForeground(c1);
	 	labelBavard.setForeground(Color.orange);
	 	labelBavard.setBorder(border3);
	 	labelMessagesE.setBorder(border3);
	 	scrollPane.setBorder(border3);
	    
	 	 
	 	// Creation de la zone messages recus
	 	this.zoneMessageR.setEditable(false);
	 	this.zoneMessageR.setEditorKit(kit);
	 	JScrollPane scrollPane1 = new JScrollPane(this.zoneMessageR);  
		this.zoneMessageR.setBorder(border2);
		this.zoneMessageR.setPreferredSize(new Dimension(150, 85));
		this.zoneMessageR.setMinimumSize(new Dimension(10, 10));

	 		
	 	// Creation de la zone message envoyes
		this.zoneMessageE.setEditable(false);
		this.zoneMessageE.setEditorKit(kit);
	 	JScrollPane scrollPane2 = new JScrollPane(this.zoneMessageE); 
	 	this.zoneMessageE.setBorder(border2);
		this.zoneMessageE.setPreferredSize(new Dimension(150, 85));
		this.zoneMessageE.setMinimumSize(new Dimension(10, 10));
	 	
	 		
	 	// Centrage des elements 
	 	labelMessagesR.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	this.zoneMessageR.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	this.zoneMessageE.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	scrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	scrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	boutonMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	boutonDeco.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	labelMessagesE.setAlignmentX(Component.CENTER_ALIGNMENT);
	 	labelBavard.setAlignmentX(Component.CENTER_ALIGNMENT);
	 		
	 	// Mise en place de panel1
	 	panel.setBorder(border1);
	 	panel.setLayout(layout);
	 	panel.add(labelMessagesR);
	 	panel.add(scrollPane1);
	 	panel.add(labelMessagesE);
	 	panel.add(scrollPane2);
		panel.add(labelBavard);
	 	panel.add(scrollPane, BorderLayout.CENTER);
	 	panel.add(boutonMessage);
	 	panel.add(boutonDeco);
	 		
	 	pack();
		this.setVisible(false);
	}
	
	// Utilisation des boutons
	public void actionPerformed(ActionEvent e) {

	    if(e.getActionCommand().equals("newMessage")) { // Creation d'une interfaceMessage
	    	InterfaceMessage iM = new InterfaceMessage(bavard,concierge);
				iM.setConcierge(concierge);
	    }
	  
	    if(e.getActionCommand().equals("deconnexion")) { // Concierge deconnecte le bavard
	    	this.concierge.deconnecteBavard(bavard);
	    	this.dispose();
	    }
	    
	    if(e.getActionCommand().equals(EXIT_ON_CLOSE)) { // Si le bavard ferme la fenetre on deconnecte le bavard
	    	this.concierge.deconnecteBavard(bavard);
	    	this.dispose();
	    }
	}

	// Permet d'afficher les messages recus dans la zone de texte zoneMessagesR
	public void afficheMessR(PapotageEvent mess, PapotageListener envoyeur) {
		String htmlString ="<html>\n"
				+"<body>";
		 htmlString += this.messageR = this.messageR +
				"<b><font color=#FA1818>De : </font></b>"+envoyeur.getNom()+"<br/>"+"<b><font color=#FF3396>Sujet : </font></b>" + mess.getSujet() + "<br/>" +mess.getCorps()+"<br/>";

		htmlString+="</body></html>";
		zoneMessageR.setText(htmlString);
	}
	
	// Permet d'afficher les messages envoyes dans la zone de texte zoneMessagesE
	public void afficheMessE(PapotageEvent mess, PapotageListener destinataire) {
		String htmlString = "<html>\n"
				+"<body>";
		htmlString += this.messageE= this.messageE
			+ "<b><font color=#FA1818>À : </font></b>"+destinataire.getNom()+"<br/>"+"<b><font color=#FF3396>Sujet : </font></b>" + mess.getSujet() + "<br/>" +mess.getCorps()+"<br/>";
		htmlString += "</body></html>";
		zoneMessageE.setText(htmlString);
	}
	
	// Permet d'afficher les personnes connectes
	public void afficheConnectes() {
		String htmlString = "<html>"
                			+ "<body>";
		if (concierge.getListeEcouteurs().size()==1) {
			htmlString += "<p><center><font color=#666666>Il n'y a pas encore d'utilisateur</font></center></p>";
		}else {
			for (PapotageListener bavard : concierge.getListeEcouteurs()) {
				if (!bavard.getNom().equals(this.bavard.getNom())){
					if (bavard.isConnecte()) {
						htmlString +="<p>"+ bavard.getNom() + " : " + "<font color=green>en ligne</font></p>"; 
					}else {
						htmlString += "<p>"+ bavard.getNom() + " : " + "<font color=red>hors ligne</font></p>"; 
					}
				}
			}
		}
		htmlString += "</body></html>";
		jEditorPane.setText(htmlString);
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
