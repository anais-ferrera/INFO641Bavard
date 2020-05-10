import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class InterfaceCreationBavard extends JFrame implements ActionListener{
	
	private JButton boutonConn = new JButton("Valider");
	private JButton boutonAnnuler = new JButton("Annuler");
	
	private JLabel label = new JLabel("Nom du bavard :");
	private JTextField corps = new JTextField("",15);
	
	private JPanel conteneur = new JPanel();
	private static Concierge concierge = new Concierge();

	private InterfaceGestionnaire fc;
	
	public InterfaceCreationBavard() {
		super();
		
		setTitle("Creation Bavard");
		setSize(350, 120);
		setLocationRelativeTo(null);
		
		// ajout des boutons comme ecouteurs
		boutonConn.addActionListener(this);  
		boutonConn.setActionCommand("signIn");
		
		boutonAnnuler.addActionListener(this);
		boutonAnnuler.setActionCommand("close");
		
		conteneur.add(label, BorderLayout.NORTH);
		conteneur.add(corps, BorderLayout.NORTH);
		conteneur.add(boutonConn, BorderLayout.SOUTH);
		conteneur.add(boutonAnnuler, BorderLayout.SOUTH);
		setContentPane(conteneur);
		setVisible(true);
	
}
	
	
	
	public void setConcierge(Concierge concierge) {
		InterfaceCreationBavard.concierge = concierge;
	}



	public void setFc(InterfaceGestionnaire fc) {
		this.fc = fc;
	}



	public Concierge getConcierge() {
		return concierge;
	}
	
	public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("signIn")){
				
				String nomBavard = corps.getText();
				if (nomBavard.isEmpty()) {
				}
				else {
				concierge.generateBavard(nomBavard); // cree un nouveau bavard ayant le nom rentré dans la fenetre
				this.fc.afficheConnectes();
				this.dispose(); //ferme la fenetre de connexion
				}
			}
			
			if (e.getActionCommand().equals("close")) {
				this.dispose(); //ferme la fenetre
			}
		}
	
	

}