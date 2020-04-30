import javax.swing.*;

public class CreationBavard extends JFrame {
	private JFrame frame = new JFrame();
	private JPanel pan = new JPanel();
	private JButton bouton = new JButton("Creer");
	private JLabel username; 
	private JLabel motdepasse; 
	private JLabel confirmeMdp; 
	private JTextField usernameInput;
	private JPasswordField  motdepasseInput;
	private JPasswordField confirmeMdpInput;
	
	public CreationBavard () {
		this.setTitle("Creation Bavard");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		username = new JLabel("Username");
		motdepasse = new JLabel("Mot de passe");
		motdepasse.setLocation(30,50);
		confirmeMdp = new JLabel("Confirmer le mot de passe");
		
		usernameInput  = new JTextField();
		usernameInput.setColumns(10);
		
		motdepasseInput = new JPasswordField();
		motdepasseInput.setColumns(10);
		motdepasseInput.setBounds(50, 50, 10, 10);
		
		confirmeMdpInput = new JPasswordField();
		confirmeMdpInput.setColumns(10);
		
		pan.add(username);
		pan.add(usernameInput);
		pan.add(motdepasse);
		pan.add(motdepasseInput);
		pan.add(confirmeMdp);
		pan.add(confirmeMdpInput);
		
		
		pan.add(bouton);
		
		this.setContentPane(pan);
	    this.setVisible(true);
		
	}

}
