package app;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.Vector;

/**
 * SQLColumnModel class
 * Created by Thierry
 * 27/05/2017
 */
public class SQLColumnModel extends DefaultTableColumnModel implements TableColumnModel {

    public void setColumns(String[] value){
        Vector<TableColumn> tcVector = new Vector<>();
        for(String name : value){
            TableColumn tc = new TableColumn();
            tc.setHeaderValue(name);
            tcVector.add(tc);
        }
        this.tableColumns = tcVector;
    }
}
