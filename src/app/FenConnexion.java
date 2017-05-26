package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;


public class FenConnexion extends JFrame {

    private JButton connexion;
    private JTextField identifiant;
    private JPasswordField mdp;
    private JLabel errorOutput;

    public FenConnexion() {
        super();
        build();
        setContentPane(buildComponent());
        createInteractions();
        setVisible(true);
    }

    public void build() {

        setTitle("Connexion");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public JPanel buildComponent() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JPanel haut = new JPanel();

        JLabel lab1 = new JLabel("Nom d'utilisateur :");
        JLabel lab2 = new JLabel("Mot de passe : ");

        identifiant = new JTextField();
        mdp = new JPasswordField();

        identifiant.setColumns(20);
        mdp.setColumns(20);

        haut.add(lab1);
        haut.add(identifiant);
        haut.add(lab2);
        haut.add(mdp);

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
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    login();
                }
                super.keyPressed(e);
            }
        });
    }

    private void login() {
        new Thread(() -> {
            try {
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
