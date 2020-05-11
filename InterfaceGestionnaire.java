
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;



public class InterfaceGestionnaire extends JFrame implements ActionListener {
	private Concierge concierge;
	
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	
	private JLabel labelDiscussion = new JLabel("Toutes les discussions :");
	private JLabel labelConnectes = new JLabel("Bavards :");	
	private JLabel labelNom = new JLabel("Nom du bavard :");
	
	private String message = "";
	
	private JTextField corps = new JTextField("",10);
	
	private JTextArea zoneMessages = new JTextArea(15, 20);
	//private JTextArea zoneBConnectes = new JTextArea(5, 20);
	JEditorPane jEditorPane = new JEditorPane();
	HTMLEditorKit kit = new HTMLEditorKit();
	
	private JButton boutonNewb = new JButton("Nouveau bavard");
	
	// Constructeur
	
	public InterfaceGestionnaire(Concierge c) {
		super();
		
		// Definition du titre et de la position de la fenetre
		setTitle("Fenetre du concierge");
		setLocation(1250, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		// HTML
		jEditorPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(jEditorPane);
		jEditorPane.setEditorKit(kit);
		
		//StyleSheet styleSheet = kit.getStyleSheet();
        //styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
        //styleSheet.addRule("h1 {display:inline; color: green; font : 10px monaco;}");
        //styleSheet.addRule("h2 {display:inline; color: red; font : 10px monaco;}");
        
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
		JScrollPane scrollPane2 = new JScrollPane(this.zoneMessages); 
		scrollPane2.setBorder(border2);
		this.zoneMessages.setEditable(false);
		
		// Creation de la zone Bavards Connectes
		//JScrollPane scrollPane = new JScrollPane(this.zoneBConnectes); 
		//scrollPane.setBorder(border2);
		//zoneBConnectes.setEditable(false);
		
		
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
		//si on appui sur le bouton nouveau bavard
		if (e.getActionCommand().equals("createBavard")){
			
			String nomBavard = corps.getText();
			if (nomBavard.isEmpty()) { // Verifie qu'un nom soit rentré
			}
			else {
			concierge.generateBavard(nomBavard); // cree un nouveau bavard ayant le nom rentré dans la fenetre
			}
		}
	}
	
	// Permet d'afficher tous les messages envoyés dans la zone de Texte zoneMessages
	public void afficheMess(PapotageEvent mess) {
		this.message = this.message + mess.getSujet();
		zoneMessages.setText(message);
	}
	
	// Permet d'afficher l'état (en ligne ou hors ligne) de tous les bavards créés dans la zone de Texte zoneBConnectes
	public void afficheConnectes() {
		//String etatBavards="";
		String htmlString = "<html>\n"
                + "<body>";
        
		for (PapotageListener bavard : concierge.getListeBavards()) {
			//etatBavards = "<html>" + "de" + "</html>";
			//etatBavards="<html>"+etatBavards + bavard.getNom() + " : <FONT color=\"red\">toto</FONT>" + bavard.getEtat() + "\n"+"</html>" ;
			if (bavard.isConnecte()) {
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
	
