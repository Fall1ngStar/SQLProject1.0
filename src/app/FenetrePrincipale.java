package app;

import javax.swing.*;

/**
 * FenetrePrincipale class
 * Created by Thierry
 * 24/05/2017
 */
public class FenetrePrincipale extends JFrame {


    public FenetrePrincipale() {
        build();
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void build() {
        setTitle("SQL interface");
        setSize(1280, 720);
        //setContentPane(new PrincipalePane());
        setContentPane(new TableListPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
