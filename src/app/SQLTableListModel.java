package app;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SQLTableListModel extends AbstractListModel<String> implements ListModel<String> {

    private List<String> data;

    public SQLTableListModel() {
        this(new ArrayList<>());
    }


    public SQLTableListModel(List<String> data) {
        this.data = data;
    }

    @Override
    public String getElementAt(int arg0) {
        return data.get(arg0);
    }

    @Override
    public int getSize() {
        return data.size();
    }

    public void setData(List<String> value) {
        this.data = value;
    }

}
