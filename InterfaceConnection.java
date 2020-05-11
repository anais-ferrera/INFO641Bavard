import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;



public class InterfaceConnection extends JFrame implements ActionListener {
	private JButton boutonConn = new JButton("Connexion");
	
	private JLabel label = new JLabel("Nom du bavard");
	private JTextField corps = new JTextField("",10);
	
	private JPanel conteneur = new JPanel();
	private static Concierge concierge = new Concierge();
	private InterfaceGestionnaire ig;

	
	public InterfaceConnection() {
		super();
		
		setTitle("Connexion");
		setSize(350, 100);
		setLocationRelativeTo(null);
		
		// ajout des boutons comme ecouteurs
		boutonConn.addActionListener(this);  
		boutonConn.setActionCommand("signIn");

		
		conteneur.add(label, BorderLayout.NORTH);
		conteneur.add(corps, BorderLayout.NORTH);
		conteneur.add(boutonConn, BorderLayout.SOUTH);
		setContentPane(conteneur);

		setVisible(true);
		
	}
	
	
	
	public void setIg(InterfaceGestionnaire ig) {
		this.ig = ig;
	}



	public Concierge setConcierge(Concierge c) {
		return concierge = c;
	}
	
	public Concierge getConcierge() {
		return concierge;
	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("signIn")){
			
			String nomBavard = corps.getText();
			for (PapotageListener bavard : ig.getConcierge().getListeBavards()) {
				System.out.println(nomBavard.equals(bavard.getNom()));
				if (nomBavard.equals(bavard.getNom())) {
					ig.getConcierge().connecteBavard(bavard); // connecte le bavard b						
					InterfaceBavard id = new InterfaceBavard();
					bavard.setInterfBavard(id);
					corps.setText("");
					id.setBavard((Bavard) bavard);
					id.setConcierge(concierge);
					
					id.setTitle("Fenetre dialogue de " + bavard.getNom());
					id.setVisible(true);
						
				}else {
				}
			}
				
				

		}
		
		if (e.getActionCommand().equals("close")) {
		this.dispose(); //ferme la fenetre
		}
	}
	
	

}
