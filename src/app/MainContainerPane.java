package app;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.*;

/**
 * MainContainerPane class
 * Created by Thierry
 * 27/05/2017
 */
public class MainContainerPane extends JPanel {

    private RequestPane requestPane;
    private TableListPanel tableListPanel;

    public MainContainerPane(){
        build();
    }

    private void build(){
        requestPane = new RequestPane(this);
        tableListPanel = new TableListPanel(this);
        GridBagConstraints rqc = new GridBagConstraints(), tlc = new GridBagConstraints();
        rqc.anchor = LINE_END;
        tlc.anchor = LINE_START;
        rqc.weightx = 0.7;
        tlc.weighty = 0.3;
        rqc.fill = BOTH;
        tlc.fill = VERTICAL;

        setLayout(new GridBagLayout());
        add(tableListPanel, tlc);
        add(requestPane, rqc);
    }

    public RequestPane getRequestPane() {
        return requestPane;
    }

    public TableListPanel getTableListPanel() {
        return tableListPanel;
    }
}
