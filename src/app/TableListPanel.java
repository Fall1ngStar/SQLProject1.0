package app;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableListPanel extends JPanel {

    private MainContainerPane parent;

    private JList<String> liste;

    public TableListPanel(MainContainerPane parent) {
        build();
        createInterractions();
        this.parent = parent;
    }


    private void build() {
        setLayout(new BorderLayout());
        liste = new JList<>(new SQLTableListModel(getTablesNames()));
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
        add(liste, BorderLayout.CENTER);
        add(new JLabel("Tables"), BorderLayout.NORTH);
        ((BorderLayout)getLayout()).setVgap(10);
    }

    private List<String> getTablesNames() {
        List<String> names = new ArrayList<>();
        try {
            ResultSet data = LinkSQL.getInstance().selectRequete("SELECT * FROM user_tables");
            while (data.next()) {
                names.add(data.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    private void createInterractions(){
        liste.addListSelectionListener((e -> {
            if (e.getValueIsAdjusting()) {
                new Thread(()->parent.getRequestPane().search("SELECT * FROM " + liste.getSelectedValue())).start();
            }
        }));
    }

}
