package app;

import javax.swing.*;
import java.awt.*;

/**
 * PrincipalePane class
 * Created by Thierry
 * 24/05/2017
 */
public class PrincipalePane extends JPanel {

    JTextField champRequete;
    JButton executeRequete;
    JTable resultatRequete;
    Object[][] data;
    String[] cols;

    public PrincipalePane() {
        initPanel();
        buildPanel();
    }

    private void initPanel(){
        data = new Object[][]{
                {
                        "Salut", "ceci", "est un test"
                },
                {
                        "J'aime", "les", "nouilles"
                }
        };
        cols = new String[]{
                "A", "B", "C"
        };

        champRequete = new JTextField();
        executeRequete = new JButton("Executer la requête");
        resultatRequete = new JTable(data, cols);
    }

    private void buildPanel() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout    );

        champRequete.setColumns(20);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(champRequete);
        add(Box.createRigidArea(new Dimension(40,40)));
        add(executeRequete);
        add(resultatRequete);
    }
}
