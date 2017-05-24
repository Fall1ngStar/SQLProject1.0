package app;

import javax.swing.*;

/**
 * PrincipalePane class
 * Created by Thierry
 * 24/05/2017
 */
public class PrincipalePane extends JPanel {

    JTextField champRequete;
    JButton executeRequete;
    JTable resultatRequete;

    public PrincipalePane() {
        buildPanel();
    }

    private void buildPanel() {
        Object[][] data = new Object[][]{
                {
                        "Salut", "ceci", "est un test"
                },
                {
                        "J'aime", "les", "nouilles"
                }
        };
        Object[] cols = new Object[]{
                "A", "B", "C"
        };

        champRequete = new JTextField();
        executeRequete = new JButton("Executer la requête");
        resultatRequete = new JTable(data, cols);

        champRequete.setColumns(20);


        add(champRequete);
        add(executeRequete);
        add(resultatRequete);
    }
}
