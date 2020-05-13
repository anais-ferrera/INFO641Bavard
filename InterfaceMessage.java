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
	private String sujet = "";
	private String corps = "";
	//private JTextArea corps = new JTextArea(5, 20);
	
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	
	private JButton boutonEnvoyerATous = new JButton("Envoyer a tous");
	private JButton boutonEnvoyer = new JButton("Envoyer");
	private JButton boutonAnnuler = new JButton("Annuler");
	
	private Bavard bavard;
	private Concierge concierge;
	private InterfaceBavard iB;
	
	//private String fils_discussion ="";
	//private JLabel discussion = new JLabel("Message");
	
	public InterfaceMessage(Bavard bavard, Concierge concierge) {
		super();
		
		this.bavard = bavard;
		this.concierge=concierge;
		
		// Definition du titre et de la position de la fenetre
		setTitle("Nouveau Message");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		// Mise en place de la liste deroulante 
		// On parcourt la liste des bavards et on ajoute dans la liste deroulante chaque bavard
		//for(PapotageListener pl : this.concierge.getListeBavards()) {
			//liste1.addItem(pl.getNom());
		//}
		// Mise en place de la liste deroulante 
        // On parcourt la liste des bavards et on ajoute dans la liste deroulante chaque bavard
        for(PapotageListener pl : this.concierge.getListeBavards()) {
            if (pl.isConnecte()) {
                liste1.addItem(pl.getNom());
            }
        }
		boutonEnvoyerATous.addActionListener(this);
		boutonEnvoyerATous.setActionCommand("envoyerATous");
		// Mise en place des deux boutons 
		boutonEnvoyer.addActionListener(this);
		boutonEnvoyer.setActionCommand("envoyer");
		
		boutonAnnuler.addActionListener(this);
		boutonAnnuler.setActionCommand("annuler");	

		// Creation du container
		Container mainContainer = this.getContentPane();
		mainContainer.add(this.panel1);
		
		// Creation des bordures
		Border border1=  BorderFactory.createEmptyBorder(30,35,30,35);
		Border border2=  BorderFactory.createEmptyBorder(20,45,20,45);
		
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
		
		// Creation de la zone de texte corpsMessage
		//JScrollPane scrollPane1 = new JScrollPane(this.corpsMessage); 
		//scrollPane1.setBorder(border2);
		//this.corpsMessage.setEditable(false);
		
		// Centrage des elements 
		destinataire.setAlignmentX(Component.CENTER_ALIGNMENT);
		sujetMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		corpsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		//scrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT);
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

	public void actionPerformed(ActionEvent e) {
	    if(e.getActionCommand().equals("envoyer")) {
	    	
	    	Object selected = this.liste1.getSelectedItem();
	    	PapotageListener destinataire = getPapotageListenerListe(selected);
	    	this.concierge.envoieMessage(new PapotageEvent(this, zoneSujet.getText(), zoneCorps.getText()),destinataire,bavard);
	    	this.dispose();
	    }
	    
	    if(e.getActionCommand().equals("annuler")) {
	    	this.dispose();
	    }
	    
	    if(e.getActionCommand().contentEquals("envoyerATous")) {
	    	this.concierge.envoieMessageATous(new PapotageEvent(this, zoneSujet.getText(), zoneCorps.getText()),this.concierge.getListeBavards(),bavard);
	    	this.dispose();
	    }
	}
	
	public PapotageListener getPapotageListenerListe(Object selected) {
		for(PapotageListener pl : this.concierge.getListeBavards()) {
			if (selected.equals(pl.getNom())){
				return pl;
			}
		}
		return null;
	}
	
	public void setConcierge(Concierge concierge) {
		this.concierge = concierge;
	}

	public void setiB(InterfaceBavard iB) {
		this.iB = iB;
	}
}
