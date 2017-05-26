package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class TableListPanel extends JPanel {

	private JList<String> liste;
	
	public TableListPanel(){
		build();
	}
	
	
	private void build(){
		liste = new JList<>(new SQLTableListModel(getTablesNames()));
		add(liste);
	}
	
	private List<String> getTablesNames(){
			ResultSet data = LinkSQL.getInstance().selectRequete("SELECT * FROM user_tables");
			List<String> names = new ArrayList<>();
			try {
				while(data.next()) {
					names.add(data.getString(1));
					System.out.println("Name: " + data.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return names;
	}
	
}
