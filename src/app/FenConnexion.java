package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.SQLException;


public class FenConnexion extends JFrame {

    private JButton connexion;
    private JTextField identifiant;
    private JPasswordField mdp;
    private JLabel errorOutput;
    private JCheckBox check1;
    private File fe;

    public FenConnexion() {
        super();
        build();
        setContentPane(buildComponent());
        getPreviousLogin();
        createInteractions();
        setVisible(true);
    }

    public void build() {

        setTitle("Connexion");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public JPanel buildComponent() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JPanel haut = new JPanel();

        JPanel ligne1 = new JPanel(); //ligne1, ligne2 et ligne3 sont les cases de mon BoxLayout
        ligne1.setLayout(new FlowLayout());

        JPanel ligne2 = new JPanel();
        ligne2.setLayout(new FlowLayout());

        JPanel ligne3 = new JPanel();
        ligne3.setLayout(new FlowLayout());

        check1 = new JCheckBox("Mémoriser mon compte : ", true);

        JLabel lab1 = new JLabel("Nom d'utilisateur :");
        JLabel lab2 = new JLabel("Mot de passe : ");

        identifiant = new JTextField();
        mdp = new JPasswordField();

        identifiant.setColumns(20);
        mdp.setColumns(20);

        ligne1.add(lab1);
        ligne1.add(identifiant);

        ligne2.add(lab2);
        ligne2.add(mdp);

        ligne3.add(check1);

        haut.add(ligne1);
        haut.add(ligne2);
        haut.add(ligne3);

        JPanel bas = new JPanel();

        connexion = new JButton("Connexion");
        bas.add(connexion);

        main.add(haut);
        main.add(bas);

        errorOutput = new JLabel();
        errorOutput.setPreferredSize(new Dimension(350, 50));

        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());
        pan.add(main, BorderLayout.CENTER);
        pan.add(errorOutput, BorderLayout.SOUTH);

        return pan;
    }

    private void createInteractions() {
        connexion.addActionListener(e -> login());

        mdp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER && connexion.isEnabled()) {
                    login();
                }
                super.keyPressed(e);
            }
        });

    }

    public void getPreviousLogin(){

        fe = new File("DataProfile.txt");

        BufferedReader buf1 = null;

        try {
            buf1 = new BufferedReader(new FileReader(fe));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(fe.exists() && check1.isSelected()){
            try {
                identifiant.setText(buf1.readLine());
                mdp.setText(buf1.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void login() {
        new Thread(() -> {
            try {
                if(!fe.exists() && check1.isSelected()){
                    try{
                        PrintWriter writer = new PrintWriter("DataProfile.txt", "UTF-8");
                        writer.println(identifiant.getText());
                        writer.println(mdp.getPassword());
                        writer.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if(!check1.isSelected()){
                    fe.delete();
                }
                
                connexion.setEnabled(false);
                errorOutput.setText("Connexion ...");
                LinkSQL.getInstance().connexionServeur(identifiant.getText(), String.valueOf(mdp.getPassword()));
                SwingUtilities.invokeLater(FenetrePrincipale::new);
                dispose();
            } catch (SQLException e1) {
                errorOutput.setText(e1.getMessage());
                connexion.setEnabled(true);
            }
        }).start();
    }
}
