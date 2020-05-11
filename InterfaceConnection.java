import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.Border;



public class InterfaceConnection extends JFrame implements ActionListener {
	private JButton boutonConn = new JButton("Connexion");
	
	private JLabel label = new JLabel("Nom du bavard");
	private JTextField corps = new JTextField("",10);
	
	private JPanel panel = new JPanel();
	private static Concierge concierge = new Concierge();
	private InterfaceGestionnaire ig;

	// Constructeur
	
	public InterfaceConnection() {
		super();
		
		setTitle("Connexion");
		setLocation(250, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font font = new Font("Arial",Font.BOLD,14);
		label.setFont(font);
		
		// ajout des boutons comme ecouteurs
		boutonConn.addActionListener(this);  
		boutonConn.setActionCommand("signIn");

		Border border1=  BorderFactory.createEmptyBorder(20,25,20,25);
		panel.setBorder(border1);
		panel.add(label);
		panel.add(corps);
		panel.add(boutonConn);
		setContentPane(panel);
		
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.corps.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.boutonConn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pack();
		setVisible(true);	
	}
	
	// Utilisation des boutons
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("signIn")){ // Si on appui sur le bouton connexion
			
			String nomBavard = corps.getText();
			for (PapotageListener bavard : ig.getConcierge().getListeBavards()) {
				if (nomBavard.equals(bavard.getNom())) { // On regarde que le nom rentré correspond au nom d'un bavard déjà créé
					
					ig.getConcierge().connecteBavard(bavard); // connecte le bavard b									
					corps.setText("");
					
					InterfaceBavard id = new InterfaceBavard((Bavard)bavard);
					bavard.setInterfBavard(id);
					id.setBavard((Bavard) bavard);
					id.setConcierge(concierge);
						
				}else { // Si le nom rentré ne correspond pas rien ne se passe
				}
			}	
		}
		
		if (e.getActionCommand().equals("close")) {
		this.dispose(); //ferme la fenetre
		}
	}
	
	// Getters et Setters
	
	public void setIg(InterfaceGestionnaire ig) {
		this.ig = ig;
	}

	public Concierge setConcierge(Concierge c) {
		return concierge = c;
	}
	
	public Concierge getConcierge() {
		return concierge;
	}
	
}
