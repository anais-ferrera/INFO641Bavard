import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class InterfaceMessage extends JFrame implements ActionListener{
	
	private JComboBox liste1 = new JComboBox();
	private JLabel destinataire = new JLabel("À :");
	private JLabel sujetMessage = new JLabel("Sujet :");
	private JTextField zoneSujet = new JTextField("",10);
	private JLabel corpsMessage = new JLabel("Corps :");	
	private JTextField zoneCorps = new JTextField("",10);
	
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	
	private JButton boutonEnvoyerATous = new JButton("Envoyer a tous");
	private JButton boutonEnvoyer = new JButton("Envoyer");
	private JButton boutonAnnuler = new JButton("Annuler");
	
	private Bavard bavard;
	private Concierge concierge;
	
	// Constructeur
	public InterfaceMessage(Bavard bavard, Concierge concierge) {
		super();
		
		this.bavard = bavard;
		this.concierge=concierge;
		
		// Definition du titre et de la position de la fenetre
		setTitle("Nouveau Message");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		
		// Mise en place de la liste deroulante 
        // On parcourt la liste des bavards et on ajoute dans la liste deroulante chaque bavard
        for(PapotageListener pl : this.concierge.getListeEcouteurs()) {
        	liste1.addItem(pl.getNom());
        }
        
		// Mise en place des boutons 
        boutonEnvoyerATous.addActionListener(this);
        boutonEnvoyerATous.setActionCommand("envoyerATous");
        
		boutonEnvoyer.addActionListener(this);
		boutonEnvoyer.setActionCommand("envoyer");
		
		boutonAnnuler.addActionListener(this);
		boutonAnnuler.setActionCommand("annuler");	

		// Creation du container
		Container mainContainer = this.getContentPane();
		mainContainer.add(this.panel1);
		
		// Creation des bordures
		Border border1=  BorderFactory.createEmptyBorder(30,35,30,35);
		
		// Creation du Layout
		BoxLayout layout = new BoxLayout(panel1,BoxLayout.Y_AXIS);
		
		// Mise en place des labels
		Font font = new Font("Arial",Font.BOLD,20);
		destinataire.setFont(font);
		sujetMessage.setFont(font);
		corpsMessage.setFont(font);
		destinataire.setForeground(Color.pink);
		sujetMessage.setForeground(Color.orange);
		corpsMessage.setForeground(Color.red);

		// Centrage des elements 
		destinataire.setAlignmentX(Component.CENTER_ALIGNMENT);
		sujetMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		corpsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonEnvoyerATous.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonEnvoyer.setAlignmentX(Component.CENTER_ALIGNMENT);
		boutonAnnuler.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Mise en place de panel1
		panel1.setBorder(border1);
		panel1.setLayout(layout);
		panel1.add(destinataire);
		panel1.add(liste1);
		panel1.add(sujetMessage);
		panel1.add(zoneSujet);
		panel1.add(corpsMessage);
		panel1.add(zoneCorps);
		panel2.add(boutonEnvoyerATous);
		panel2.add(boutonEnvoyer);
		panel2.add(boutonAnnuler);
		panel1.add(panel2);
		
		pack();
		setVisible(true);	
	}

	// Utilisation des boutons
	public void actionPerformed(ActionEvent e) {
		
	    if(e.getActionCommand().equals("envoyer")) { // Envoie un message a un seul destinataire
	    	Object selected = this.liste1.getSelectedItem();
	    	PapotageListener destinataire = getPapotageListenerListe(selected);
	    	this.bavard.envoyerMessage(zoneSujet.getText(), zoneCorps.getText(), destinataire,this.bavard);
	    	this.dispose();
	    }
	    
	    if(e.getActionCommand().equals("annuler")) { // Ferme la fenetre
	    	this.dispose();
	    }
	    
	    if(e.getActionCommand().contentEquals("envoyerATous")) { // Envoie un message a tous les destinataires
	    	this.bavard.envoieMessageATous(zoneSujet.getText(), zoneCorps.getText());
	    	this.dispose();
	    }
	}
	
	// Retourne le PapotageListener correspondant au nom passé en parametre
	public PapotageListener getPapotageListenerListe(Object selected) {
		for(PapotageListener pl : this.concierge.getListeEcouteurs()) {
			if (selected.equals(pl.getNom())){
				return pl;
			}
		}
		return null;
	}
	
	// Setters
	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}
}
