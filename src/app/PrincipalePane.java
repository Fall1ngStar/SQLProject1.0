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

    private void initPanel() {
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
        setLayout(new BorderLayout());

        JPanel tableContainer = new JPanel();
        tableContainer.setLayout(new BorderLayout());
        tableContainer.add(resultatRequete.getTableHeader(), BorderLayout.PAGE_START);
        tableContainer.add(resultatRequete, BorderLayout.CENTER);

        JPanel requestContainer = new JPanel();
        requestContainer.setLayout(new BoxLayout(requestContainer, BoxLayout.X_AXIS));
        requestContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        requestContainer.add(champRequete);
        requestContainer.add(executeRequete);


        champRequete.setColumns(20);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(requestContainer, BorderLayout.NORTH);
        add(tableContainer, BorderLayout.CENTER);
    }
}
