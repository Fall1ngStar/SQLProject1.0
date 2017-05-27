package app;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableListPanel extends JPanel {

    private JList<String> liste;

    public TableListPanel() {
        build();
    }


    private void build() {
        setLayout(new BorderLayout());
        liste = new JList<>(new SQLTableListModel(getTablesNames()));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
        add(liste, BorderLayout.CENTER);
        add(new JLabel("Tables"), BorderLayout.NORTH);
        ((BorderLayout)getLayout()).setVgap(10);
    }

    private List<String> getTablesNames() {
        ResultSet data = LinkSQL.getInstance().selectRequete("SELECT * FROM user_tables");
        List<String> names = new ArrayList<>();
        try {
            while (data.next()) {
                names.add(data.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

}
