import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLEditorKit;



public class InterfaceGestionnaire extends JFrame implements ActionListener {
	private Concierge concierge;
	
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	
	private JLabel labelDiscussion = new JLabel("Toutes les discussions :");
	private JLabel labelConnectes = new JLabel("Bavards :");
	private JLabel labelNom = new JLabel("Nom du bavard :");

	private String message="";
	private JTextField corps = new JTextField("",10);
	

	private JTextPane zoneMessages = new JTextPane();
	JEditorPane jEditorPane = new JEditorPane();
	HTMLEditorKit kit = new HTMLEditorKit();
	
	private JButton boutonNewb = new JButton("Nouveau bavard");
	
	
	// Constructeur
	public InterfaceGestionnaire(Concierge c) {
		super();
	
	    //definition du titre, det de la position de la fenetre
		setTitle("Fenetre du concierge");
		setLocation(1250, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// HTML
		jEditorPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(jEditorPane);
		jEditorPane.setEditorKit(kit);
		
        
        String htmlString = "<html>"
                + "<body>"
                + "<p><center><font color=#666666>Il n'y a pas encore d'utilisateur</font></center></p>"
                + "<p></p>"
                + "<p></p>"
                + "</body>";
        
        jEditorPane.setText(htmlString);
        
        // Ajout des boutons comme ecouteurs
		boutonNewb.addActionListener(this);
		boutonNewb.setActionCommand("createBavard");
		
		// Mise en place de panel2
		panel2.add(labelNom);
		panel2.add(corps);
		panel2.add(boutonNewb);
		panel2.setVisible(true);
		
		// Creation du container
		Container mainContainer = this.getContentPane();
		mainContainer.add(panel1);
		
		// Creation des bordures
		Border border1=  BorderFactory.createEmptyBorder(30,35,30,35);
		Border border2=  BorderFactory.createEmptyBorder(20,45,20,45);
		
		// Creation du Layout
		BoxLayout layout = new BoxLayout(panel1,BoxLayout.Y_AXIS);
		
		// Mise en place des labels
		Font font = new Font("Arial",Font.ITALIC,24);
		Font font2 = new Font("Arial",Font.ITALIC,18);
		labelDiscussion.setFont(font);
		labelNom.setFont(font2);
		labelConnectes.setFont(font);
		labelDiscussion.setForeground(Color.blue.darker().darker());
		labelConnectes.setForeground(Color.blue);
		labelNom.setForeground(Color.darkGray);
	    //Border border = LineBorder.createGrayLineBorder();
		//labelDiscussion.setBorder(border);
		
		// Creation de la zone Messages
	 	this.zoneMessages.setEditable(false);
	 	this.zoneMessages.setEditorKit(kit);
	 	JScrollPane scrollPane2 = new JScrollPane(this.zoneMessages);  
		this.zoneMessages.setBorder(border2);
		this.zoneMessages.setPreferredSize(new Dimension(250, 185));
		this.zoneMessages.setMinimumSize(new Dimension(10, 10));

		
		
		// Centrage des elements 
		labelDiscussion.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.zoneMessages.setAlignmentX(Component.CENTER_ALIGNMENT);
		//this.zoneBConnectes.setAlignmentX(Component.CENTER_ALIGNMENT);
		scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		scrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonNewb.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelConnectes.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelNom.setAlignmentX(Component.CENTER_ALIGNMENT);
		corps.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Mise en place de panel1
		panel1.setBorder(border1);
		panel1.setLayout(layout);
		panel1.add(labelDiscussion);
		panel1.add(scrollPane2);
		panel1.add(labelConnectes);
		//panel1.add(scrollPane);
		panel1.add(scrollPane, BorderLayout.CENTER);
		panel1.add(panel2);
		
		// On relie le concierge et l'interface du gestionnaire
		this.concierge = c;
		this.concierge.setIg(this);
		
		// Creation de l'interface connection et on relie le concierge et l'interface du gestionnaire à iC
		InterfaceConnection iC = new InterfaceConnection();
		iC.setConcierge(c);
		iC.setIg(this);
		
		pack();
		setVisible(true);
		
	}
	
	
	// Utilisation des boutons
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> listeNoms = new ArrayList<String>();
        
        if (e.getActionCommand().equals("createBavard")){ // Creation d'un nouveau bavard
            for (PapotageListener pl : this.concierge.getListeEcouteurs()) {
                listeNoms.add(pl.getNom());
            }
            String nomBavard = corps.getText();
            
            if (nomBavard.isEmpty()) { // Verifie qu'un nom soit rentré
            	corps.setText("");
            }else if (listeNoms.contains(nomBavard)) { // Verifie qu'un bavard n'ai pas deja le meme nom
            	corps.setText("");
            }else {
            	Bavard b = concierge.generateBavard(nomBavard); // cree un nouveau bavard ayant le nom rentré dans la fenetre
            	InterfaceBavard id = new InterfaceBavard(b);
            	corps.setText("");
            	id.setBavard(b);
            	id.setConcierge(concierge);
            	b.setIb(id);
            }
        }
    }
	
	// Permet d'afficher tous les messages envoyés dans la zone de Texte zoneMessages
	public void afficheMess(PapotageEvent mess, PapotageListener envoyeur, PapotageListener destinataire) {
		String htmlString ="<html>\n"
				+"<body>";
		 htmlString += this.message = this.message +
				envoyeur.getNom()+ "<b><font color=#85A5C8> à : </font></b>" + destinataire.getNom() + "<br/>"+
				 "<b><font color=#21568F>Sujet : </font></b>" + mess.getSujet() + "<br/>" +mess.getCorps()+"<br/>";

		htmlString+="</body></html>";
		zoneMessages.setText(htmlString);
	}
	
	// Permet d'afficher l'état (en ligne ou hors ligne) de tous les bavards crees dans la zone de Texte zoneBConnectes
	public void afficheConnectes() {

		String htmlString = "<html>\n"
			+"<body>";
		for (PapotageListener bavard : concierge.getListeEcouteurs()) {
			if (bavard.isConnecte()) {
				bavard.getIb().afficheConnectes();
				htmlString +="<p>"+ bavard.getNom() + " : " + "<font color=green>en ligne</font></p>"; 
			}else {
				htmlString += "<p>"+ bavard.getNom() + " : " + "<font color=red>hors ligne</font></p>"; 
			}
		}
		htmlString += "</body></html>";
		jEditorPane.setText(htmlString);
	}


    // Getters et Setters
	
	public Concierge getConcierge() {
		return concierge;
	}

	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
		this.concierge.setIg(this);
	}


}
