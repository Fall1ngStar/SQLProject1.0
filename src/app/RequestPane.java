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
        otherResult.setEditable(false);
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
        executeRequete.addActionListener((e) -> execute());

        champRequete.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER && executeRequete.isEnabled()){
                    execute();
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

    private void execute(){
        new Thread(()->{
            executeRequete.setEnabled(false);
            String request = champRequete.getText();
            if(request.length() > 0){
                String first = request.split(" ")[0];
                if(first.equalsIgnoreCase("select")){
                    search(request);
                }
                else if(first.equalsIgnoreCase("insert") || first.equalsIgnoreCase("update") || first.equalsIgnoreCase("delete")){
                    modify(request);
                }
                else {

                }
            }
            executeRequete.setEnabled(true);
        }).start();
    }

    private void search(String request){
        try {
            ((CardLayout)resultContainer.getLayout()).show(resultContainer, TABLE);
            ResultSet set = LinkSQL.getInstance().selectRequete(request);
            java.util.List<Object[]> data = toObjectList(set);
            String[] names = new String[set.getMetaData().getColumnCount()];
            for (int i = 0; i < names.length; i++) {
                names[i] = set.getMetaData().getColumnName(i + 1);
            }
            displayData(data, names);
        } catch (SQLException e1) {
            ((CardLayout)resultContainer.getLayout()).show(resultContainer, OTHER);
            otherResult.append(e1.getMessage() + "\n\n");
        }
    }

    private void modify(String request){
        try{
            ((CardLayout)resultContainer.getLayout()).show(resultContainer, OTHER);
            int nbMModified = LinkSQL.getInstance().modifyRequete(request);
            otherResult.append("Nombre de lignes modifiées : " + nbMModified + "\n\n");
        } catch (SQLException e){
            ((CardLayout)resultContainer.getLayout()).show(resultContainer, OTHER);
            otherResult.append(e.getMessage() + "\n\n");
        }
    }
}
