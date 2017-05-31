package app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        setContentPane(new MainContainerPane());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LinkSQL.getInstance().closeConnexion();
                super.windowClosing(e);
            }
        });
    }


}
