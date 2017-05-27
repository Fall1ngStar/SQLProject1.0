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

    RequestPane requestPane;
    TableListPanel tableListPanel;

    public MainContainerPane(){
        build();
    }

    private void build(){
        requestPane = new RequestPane();
        tableListPanel = new TableListPanel();
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
}
