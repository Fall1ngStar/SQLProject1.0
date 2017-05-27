package app;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * RequestPane class
 * Created by Thierry
 * 24/05/2017
 */
public class RequestPane extends JPanel {

    JTextField champRequete;
    JButton executeRequete;


    JPanel resultContainer;
    JTable tableResult;
    JTextArea otherResult;

    final String TABLE = "Table display", OTHER = "Text display";

    public RequestPane (){
        initPanel();
        buildPanel();
        buildInteractions();
    }

    private void initPanel() {
        champRequete = new JTextField();
        executeRequete = new JButton("Executer la requête");
        tableResult = new JTable(new SQLTableModel());
    }

    private void buildPanel() {
        setLayout(new BorderLayout());

        //tableResult.setColumnModel(new SQLColumnModel());
        resultContainer = new JPanel();
        resultContainer.setLayout(new CardLayout());

        JScrollPane scrollPane = new JScrollPane(tableResult);
        otherResult = new JTextArea();
        tableResult.setFillsViewportHeight(true);
        resultContainer.add(scrollPane, TABLE);
        resultContainer.add(otherResult, OTHER);
        ((CardLayout)resultContainer.getLayout()).show(resultContainer, TABLE);


        JPanel requestContainer = new JPanel();
        requestContainer.setLayout(new BoxLayout(requestContainer, BoxLayout.X_AXIS));
        requestContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        requestContainer.add(champRequete);
        requestContainer.add(executeRequete);


        champRequete.setColumns(20);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(requestContainer, BorderLayout.NORTH);
        add(resultContainer, BorderLayout.CENTER);
    }

    private void buildInteractions() {
        executeRequete.addActionListener((e) -> search());
        champRequete.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER && executeRequete.isEnabled()){
                    search();
                }
                super.keyPressed(e);
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

    private void displayData(List<Object[]> data, String[] names) {
        int previousColumn = tableResult.getColumnCount();
        for (int i = 0; i < names.length; i++) {
            TableColumn tc = new TableColumn(i);
            tc.setHeaderValue(names[i]);
            tableResult.addColumn(tc);
        }
        for (int i = 0; i < previousColumn; i++) {
            tableResult.removeColumn(tableResult.getColumnModel().getColumn(0));
        }
        //tableResult.removeColumn(tableResult.getColumnModel().getColumn(0));
        //((SQLColumnModel)tableResult.getColumnModel()).setColumns(names);
        ((SQLTableModel) tableResult.getModel()).setData(data);
    }

    private void search(){
        new Thread(()->{
            executeRequete.setEnabled(false);
            try {
                ResultSet set = LinkSQL.getInstance().selectRequete(champRequete.getText());
                java.util.List<Object[]> data = toObjectList(set);
                String[] names = new String[set.getMetaData().getColumnCount()];
                for (int i = 0; i < names.length; i++) {
                    names[i] = set.getMetaData().getColumnName(i + 1);
                }
                displayData(data, names);
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
            executeRequete.setEnabled(true);
        }).start();
    }
}
