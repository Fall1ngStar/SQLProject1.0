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
    Object[][] data;
    String[] cols;

    public PrincipalePane() {
        initPanel();
        buildPanel();
        buildInteractions();
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
        List<Object[]> dat2 = new ArrayList<>();
        dat2.add(new Object[]{"Salut", "ceci", "est un test"});
        cols = new String[]{
                "A", "B", "C"
        };

        champRequete = new JTextField();
        executeRequete = new JButton("Executer la requête");
        resultatRequete = new JTable(new SQLTableModel(dat2, cols));
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

    private void buildInteractions() {
        executeRequete.addActionListener((e) -> {
            ResultSet set = LinkSQL.getInstance().executeRequete(champRequete.getText());
            try {
                java.util.List<Object[]> data = toObjectList(set);
                Object[] names = new Object[set.getMetaData().getColumnCount()];
                for (int i = 0; i < names.length; i++) {
                    names[i] = set.getMetaData().getColumnName(i + 1);
                }
                displayData(data, names);
            } catch (SQLException e1) {
                e1.printStackTrace();
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

    private Object[][] toObjectArray(java.util.List<Object[]> liste) {
        Object[][] result = new Object[liste.size()][liste.get(0).length];
        for (int i = 0; i < result.length; i++) {
            result[i] = liste.get(i);
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

        for (int i = 0; i < resultatRequete.getModel().getRowCount(); i++) {
            resultatRequete.getModel();
        }

        ((SQLTableModel)resultatRequete.getModel()).setData(data);
    }
}
