package app;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class FenConnexion extends JFrame {
	
	private JButton but1;
	private JTextField textf1;
	private JPasswordField textf2;
	
	public FenConnexion() {
		super();
		build();
		setContentPane(buildComponent());
		setVisible(true);
	}
	
	public void build(){
	
		setTitle("Connexion");
		setSize(350,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public JPanel buildComponent() {
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
		
		JPanel haut = new JPanel();
		
		JLabel lab1 = new JLabel("Nom d'utilisateur :");
		JLabel lab2 = new JLabel("Mot de passe : ");
		
		textf1 = new JTextField();
		textf2 = new JPasswordField();
		
		textf1.setColumns(20);
		textf2.setColumns(20);
		
		haut.add(lab1);
		haut.add(textf1);
		haut.add(lab2);
		haut.add(textf2);
		
		JPanel bas = new JPanel();
		
		but1 = new JButton("Connexion");
		bas.add(but1);
		
		pan.add(haut);
		pan.add(bas);
		
		return pan;
	}
	
	private void createInteractions(){
		but1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				LinkSQL.getInstance().connexionServeur(textf1.getText(),textf2.getPassword());
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						new FenetrePrincipale();
					}
				});
			}
		}
		);
	}
	
	public JTextField getJTextField1(){
		return textf1;
	}
	
	public JTextField getJTextField2(){
		return textf2;
	}
	
	public JButton getJButton1(){
		return but1;
	}
		
}
