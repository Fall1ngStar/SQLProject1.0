package app;

import javax.swing.*;

/**
 * PrincipalePane class
 * Created by Thierry
 * 24/05/2017
 */
public class PrincipalePane extends JPanel{

    JTextField champRequete;
    JButton executeRequete;
    JTable resultatRequete;

    public PrincipalePane(){
        buildPanel();
    }

    private void buildPanel(){
        champRequete = new JTextField();
        executeRequete = new JButton("Executer la requête");
        resultatRequete = new JTable();

        champRequete.setColumns(20);

        add(champRequete);
        add(executeRequete);
        add(resultatRequete);
    }
}
