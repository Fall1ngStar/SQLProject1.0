package app;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        initPanel();
        buildPanel();
        buildInteractions();
    }

    private void initPanel() {
        champRequete = new JTextField();
        executeRequete = new JButton("Executer la requête");
        resultatRequete = new JTable(new SQLTableModel());
    }

    private void buildPanel() {
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(resultatRequete);
        resultatRequete.setFillsViewportHeight(true);

        JPanel requestContainer = new JPanel();
        requestContainer.setLayout(new BoxLayout(requestContainer, BoxLayout.X_AXIS));
        requestContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        requestContainer.add(champRequete);
        requestContainer.add(executeRequete);


        champRequete.setColumns(20);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(requestContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void buildInteractions() {
        executeRequete.addActionListener((e) -> {
            ResultSet set = LinkSQL.getInstance().selectRequete(champRequete.getText());
            try {
                java.util.List<Object[]> data = toObjectList(set);
                Object[] names = new Object[set.getMetaData().getColumnCount()];
                for (int i = 0; i < names.length; i++) {
                    names[i] = set.getMetaData().getColumnName(i + 1);
                }
                displayData(data, names);
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
        });
    }

    private java.util.List<Object[]> toObjectList(ResultSet set) {
        java.util.List<Object[]> result = new ArrayList<>();
        try {
            int cols = set.getMetaData().getColumnCount();
            while (set.next()) {
                Object[] row = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    row[i] = set.getObject(i + 1);
                }
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void displayData(List<Object[]> data, Object[] names) {
        for (int i = 0; i < resultatRequete.getColumnCount(); i++) {
            resultatRequete.removeColumn(resultatRequete.getColumnModel().getColumn(i));
        }
        for (int i = 0; i < names.length; i++) {
            TableColumn tc = new TableColumn(i);
            tc.setHeaderValue(names[i]);
            resultatRequete.addColumn(tc);
        }
        resultatRequete.removeColumn(resultatRequete.getColumnModel().getColumn(0));
        ((SQLTableModel) resultatRequete.getModel()).setData(data);
    }
}
